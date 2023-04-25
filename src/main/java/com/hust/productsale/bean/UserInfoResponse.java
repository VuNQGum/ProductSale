package com.hust.productsale.bean;

import com.hust.productsale.model.ApiResponse;
import java.util.List;

public class UserInfoResponse extends ApiResponse {

    private String userId;
    private String userName;
    private List<String> roles;

    public UserInfoResponse(boolean status, String message) {
        super(status, message);
    }

    public UserInfoResponse(String userId, String userName, List<String> roles, boolean status, String message) {
        super(status, message);
        this.userId = userId;
        this.userName = userName;
        this.roles = roles;

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
