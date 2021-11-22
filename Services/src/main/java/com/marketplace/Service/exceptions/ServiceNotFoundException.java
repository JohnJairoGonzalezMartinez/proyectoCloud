package com.marketplace.Service.exceptions;

public class ServiceNotFoundException extends RuntimeException {
    public ServiceNotFoundException(String s) {
        super(s);
    }
}
