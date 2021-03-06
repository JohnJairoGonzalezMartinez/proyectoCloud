package com.marketplace.Authentication.models;

public class LoginResponse {

    private String token;
    private String userId;

    public LoginResponse(String token, String userId) {
        this.token = token;
        this.userId = userId;
    }

    public LoginResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
