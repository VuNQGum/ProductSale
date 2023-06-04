package com.hust.productsale.service;

import com.hust.productsale.model.Role;
import com.hust.productsale.model.User;
import com.hust.productsale.model.payload.SignUpRequest;
import com.hust.productsale.repository.RoleRepository;
import com.hust.productsale.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public User signUp(SignUpRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        Set<Role> roleList = new HashSet<>();
        Role role = roleRepository.findById(4l).get();
        roleList.add(role);
        user.setRoles(roleList);
        return userRepository.saveAndFlush(user);
    }
}
