package com.marketplace.Service.exceptions;

public class NotQuantityAvailableException extends RuntimeException {
    public NotQuantityAvailableException(String s) {
        super(s);
    }
}
