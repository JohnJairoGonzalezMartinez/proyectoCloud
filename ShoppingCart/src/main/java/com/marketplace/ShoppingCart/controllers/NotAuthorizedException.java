package com.marketplace.ShoppingCart.controllers;

public class NotAuthorizedException extends RuntimeException {

    public NotAuthorizedException(String s) {
        super(s);
    }
}
