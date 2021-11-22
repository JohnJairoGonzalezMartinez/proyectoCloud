package com.marketplace.Service.exceptions;

public class AuthenticationCreateException extends RuntimeException {
    public AuthenticationCreateException(String message) {
        super(message);
    }
}
