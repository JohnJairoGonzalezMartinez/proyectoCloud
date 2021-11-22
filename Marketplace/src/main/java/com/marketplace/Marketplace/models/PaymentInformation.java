package com.marketplace.Marketplace.models;

public class PaymentInformation {

    private String creditCardNumber;
    private int expirationYear;
    private String ownerName;

    public PaymentInformation(String creditCardNumber, int expirationYear, String ownerName) {
        this.creditCardNumber = creditCardNumber;
        this.expirationYear = expirationYear;
        this.ownerName = ownerName;
    }

    public PaymentInformation() {
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public int getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
