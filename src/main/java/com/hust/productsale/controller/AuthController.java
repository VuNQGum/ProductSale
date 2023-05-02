package com.hust.productsale.controller;

import com.hust.productsale.bean.SignInResponse;
import com.hust.productsale.bean.TokenRefreshResponse;
import com.hust.productsale.bean.UserInfoResponse;
import com.hust.productsale.bean.ApiResponse;
import com.hust.productsale.exception.TokenRefreshException;
import com.hust.productsale.model.CustomUserDetails;
import com.hust.productsale.model.RefreshToken;
import com.hust.productsale.model.payload.LoginRequest;
import com.hust.productsale.model.payload.TokenRefreshRequest;
import com.hust.productsale.security.JwtTokenValidator;
import com.hust.productsale.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenValidator jwtUtils;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    RefreshTokenService refreshTokenService;

//    @Value("${app.jwt.authType}")
//    private String tokenRequestauthType;

    @PostMapping("/sign-in")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//            if (userDetails.getLocal()) {
                List<String> roles = userDetails.getAuthorities().stream()
                        .map(item -> item.getAuthority())
                        .collect(Collectors.toList());

            int expiration = loginRequest.getExpiration().intValue();
            int maxAge = expiration == 0 ? 30 : expiration;

            String userId = loginRequest.getUserId();
            String accessToken = jwtUtils.generateTokenFromUsername(userDetails.getUsername());
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());

                return ResponseEntity.ok().body(new SignInResponse(accessToken,
                        new UserInfoResponse(userDetails.getUsername(), userDetails.getName(),
                                roles, true, ""), refreshToken.getToken(), true, "Đăng nhập thành công"));
//            } else {
//                return ResponseEntity.ok().body(new SignInResponse(null, null, 0, "Đăng nhập lỗi"));
//            }
        } catch (Exception e) {

            return ResponseEntity.ok().body(new SignInResponse(null, null, null, false, "Đăng nhập lỗi"));
        }

    }


    @PostMapping("/sign-in-token")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> authenticateUserToken(@Valid @RequestBody Map<String, Object> loginRequest) {
        try {
            String token = loginRequest.get("accessToken").toString();
            if (token != null) {
                String username = jwtUtils.validateToken(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication
                        = new UsernamePasswordAuthenticationToken(userDetails,
                                null,
                                userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                CustomUserDetails userDetailsImpl = (CustomUserDetails) authentication.getPrincipal();

                List<String> roles = userDetails.getAuthorities().stream()
                        .map(item -> item.getAuthority())
                        .collect(Collectors.toList());
                RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());
                return ResponseEntity.ok().body(new SignInResponse(jwtUtils.generateTokenFromUsername(userDetailsImpl.getUsername()),
                        new UserInfoResponse(userDetailsImpl.getUsername(), userDetails.getUsername(),
                                roles, true, ""), refreshToken.getToken(), true, "Đăng nhập thành công"));

            } else {
                return ResponseEntity.ok().body(new SignInResponse(null, null, null, false, "Đăng nhập lỗi"));
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.ok().body(new SignInResponse(null, null, null, false, "Token không tồn tại"));
        }
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        //ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok()
                .body(new ApiResponse(true, "Bạn đã thoát!"));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }
}
