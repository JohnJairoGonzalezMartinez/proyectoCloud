package com.marketplace.Marketplace.models;

import org.springframework.data.annotation.Id;

public class Purchase {

    @Id
    private String id;
    private String cartId;
    private String userId;
    private PaymentInformation paymentInformation;

    public Purchase(String id, String cartId, String userId, PaymentInformation paymentInformation) {
        this.id = id;
        this.cartId = cartId;
        this.userId = userId;
        this.paymentInformation = paymentInformation;
    }

    public Purchase() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public PaymentInformation getPaymentInformation() {
        return paymentInformation;
    }

    public void setPaymentInformation(PaymentInformation paymentInformation) {
        this.paymentInformation = paymentInformation;
    }
}
