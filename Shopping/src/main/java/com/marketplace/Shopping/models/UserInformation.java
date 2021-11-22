package com.marketplace.Shopping.models;

public class UserInformation {

    private String id;
    private String name;
    private String userType;

    public UserInformation(String id, String name, String userType) {
        this.id = id;
        this.name = name;
        this.userType = userType;
    }

    public UserInformation() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
