package com.marketplace.User.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class UserAuthentication {

    @Id
    private String userId;
    private String email;
    private String password;

    public UserAuthentication(String userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }

    public UserAuthentication(){}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
