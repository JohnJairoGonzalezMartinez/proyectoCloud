package com.marketplace.Service.models;

public class LodgingService extends Service{

    private String lodgingType;
    private String[] extras;

    public LodgingService(String id, String serviceType, double cost, String providerId, String providerName, int quantityAvailable, String description, Location location, String[] extras, String searchString) {
        super(id, serviceType, cost, providerId, providerName, quantityAvailable, description, location, searchString);
        this.extras = extras;
    }

    public LodgingService() {}

    public String getLodgingType() {
        return lodgingType;
    }

    public void setLodgingType(String lodgingType) {
        this.lodgingType = lodgingType;
    }

    public String[] getExtras() {
        return extras;
    }

    public void setExtras(String[] extras) {
        this.extras = extras;
    }
}
