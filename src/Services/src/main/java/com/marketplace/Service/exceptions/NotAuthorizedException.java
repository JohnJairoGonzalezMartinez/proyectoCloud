package com.marketplace.Service.exceptions;

public class NotAuthorizedException extends RuntimeException {

    public NotAuthorizedException(String s) {
        super(s);
    }
}
