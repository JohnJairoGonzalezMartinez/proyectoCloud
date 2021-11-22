package com.marketplace.Marketplace.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@JsonTypeInfo(use= JsonTypeInfo.Id.NAME,  include = JsonTypeInfo.As.PROPERTY, property = "userType", visible = true )
@JsonSubTypes({
        @JsonSubTypes.Type(value=Customer.class, name = "Customer"),
        @JsonSubTypes.Type(value=Provider.class, name = "Provider")
})
public class User {

    @Id
    protected String id;
    protected String email;
    protected String name;
    protected LocalDateTime birthDate;
    protected String photoUrl;
    protected String description;
    protected String userType;

    public User(String id, String email, String name, LocalDateTime birthDate, String photoUrl, String description, String userType) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.photoUrl = photoUrl;
        this.description = description;
        this.userType = userType;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", photoUrl='" + photoUrl + '\'' +
                ", description='" + description + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
