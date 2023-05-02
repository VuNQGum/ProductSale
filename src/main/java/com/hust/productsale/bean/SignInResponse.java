package com.hust.productsale.bean;

public class SignInResponse extends ApiResponse {

    private String token;
    private UserInfoResponse userInfo;

    private String refreshToken;

    public SignInResponse(boolean status, String message) {
        super(status, message);
    }

    public SignInResponse(String token, UserInfoResponse userInfo, String refreshToken, boolean status, String message) {
        super(status, message);
        this.token = token;
        this.userInfo = userInfo;
        this.refreshToken = refreshToken;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserInfoResponse getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoResponse userInfo) {
        this.userInfo = userInfo;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
