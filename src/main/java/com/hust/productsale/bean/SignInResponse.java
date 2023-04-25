package com.hust.productsale.bean;

import com.hust.productsale.model.ApiResponse;

public class SignInResponse extends ApiResponse {

    private String token;
    private UserInfoResponse userInfo;

    public SignInResponse(boolean status, String message) {
        super(status, message);
    }

    public SignInResponse(String token, UserInfoResponse userInfo, boolean status, String message) {
        super(status, message);
        this.token = token;
        this.userInfo = userInfo;
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
}
