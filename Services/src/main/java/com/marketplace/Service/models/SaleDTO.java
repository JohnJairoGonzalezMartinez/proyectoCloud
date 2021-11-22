package com.marketplace.Service.models;

public class SaleDTO {

    private String serviceId;
    private int quantity;

    public SaleDTO(String serviceId, int quantity) {
        this.serviceId = serviceId;
        this.quantity = quantity;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
