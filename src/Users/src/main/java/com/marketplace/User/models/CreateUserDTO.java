package com.marketplace.User.models;

public class CreateUserDTO {

    private User userData;
    private String password;
    private String email;

    public CreateUserDTO(User userData, String password, String email) {
        this.userData = userData;
        this.password = password;
        this.email = email;
    }

    public CreateUserDTO(){}

    public User getUserData() {
        return userData;
    }

    public void setUserData(User userData) {
        this.userData = userData;
    }

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
}
