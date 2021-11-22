package com.marketplace.Shopping.exceptions;

public class PurchaseNotFoundException extends RuntimeException {
    public PurchaseNotFoundException(String s) {
        super(s);
    }
}
