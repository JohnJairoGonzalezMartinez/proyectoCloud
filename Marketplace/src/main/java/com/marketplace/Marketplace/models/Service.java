package com.marketplace.Marketplace.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.data.annotation.Id;

@JsonTypeInfo(use= JsonTypeInfo.Id.NAME,  include = JsonTypeInfo.As.PROPERTY, property = "serviceType", visible = true )
@JsonSubTypes({
        @JsonSubTypes.Type(value=LodgingService.class, name = "Lodging"),
        @JsonSubTypes.Type(value=FoodService.class, name = "Food"),
        @JsonSubTypes.Type(value=EcologicalExcursionService.class, name = "EcologicalExcursion"),
        @JsonSubTypes.Type(value=TransportService.class, name = "Transport")
})
public class Service {

    @Id
    protected String id;
    protected String serviceType;
    protected double cost;
    protected String providerId;
    protected String providerName;
    protected int quantityAvailable;
    protected String description;
    protected Location location;
    protected String searchString;

    public Service(String id, String serviceType, double cost, String providerId, String providerName, int quantityAvailable, String description, Location location, String searchString) {
        this.id = id;
        this.serviceType = serviceType;
        this.cost = cost;
        this.providerId = providerId;
        this.providerName = providerName;
        this.quantityAvailable = quantityAvailable;
        this.description = description;
        this.location = location;
        this.searchString = searchString;
    }

    public Service() {
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
