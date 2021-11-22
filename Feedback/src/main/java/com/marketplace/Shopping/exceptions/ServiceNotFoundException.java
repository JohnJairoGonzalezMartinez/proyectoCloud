package com.marketplace.Shopping.exceptions;

public class ServiceNotFoundException extends RuntimeException {
    public ServiceNotFoundException(String s) {
        super(s);
    }
}
