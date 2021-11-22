package com.marketplace.User.exceptions;

public class AuthenticationCreateException extends RuntimeException {
    public AuthenticationCreateException(String message) {
        super(message);
    }
}
