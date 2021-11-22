package com.marketplace.Marketplace.models;

import java.time.LocalDateTime;

public class Customer extends User {

    public Customer(String id, String email, String name, LocalDateTime birthDate, String photoUrl, String description, String userType) {
        super(id, email, name, birthDate, photoUrl, description, userType);
    }

    public Customer(){
        super();
    }

}
