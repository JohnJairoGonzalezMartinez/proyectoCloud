package com.marketplace.User.models;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class Provider extends Customer{

    private String phoneNumber;
    private String webUrl;
    private String[] socialNetworkLinks;

    public Provider(String id, String email, String name, LocalDateTime birthDate, String photoUrl, String description, String userType, String phoneNumber, String webUrl, String[] socialNetworkLinks) {
        super(id, email, name, birthDate, photoUrl, description, userType);
        this.phoneNumber = phoneNumber;
        this.webUrl = webUrl;
        this.socialNetworkLinks = socialNetworkLinks;
    }

    public Provider(String phoneNumber, String webUrl, String[] socialNetworkLinks) {
        this.phoneNumber = phoneNumber;
        this.webUrl = webUrl;
        this.socialNetworkLinks = socialNetworkLinks;
    }

    public Provider(){
        super();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String[] getSocialNetworkLinks() {
        return socialNetworkLinks;
    }

    public void setSocialNetworkLinks(String[] socialNetworkLinks) {
        this.socialNetworkLinks = socialNetworkLinks;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", socialNetworkLinks=" + Arrays.toString(socialNetworkLinks) +
                ", _id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", photoUrl='" + photoUrl + '\'' +
                ", description='" + description + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
