package com.marketplace.User.controllers;

import com.marketplace.User.models.Customer;
import com.marketplace.User.models.User;
import org.springframework.stereotype.Service;

@Service
public class CustomerController{

    public CustomerController(){}

    public User create(Customer data) {
        return data;
    }

    public User update(Customer data) {
        return data;
    }

}
