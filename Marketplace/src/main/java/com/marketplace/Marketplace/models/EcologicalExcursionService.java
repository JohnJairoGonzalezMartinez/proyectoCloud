package com.marketplace.Marketplace.models;

public class EcologicalExcursionService extends Service {

    private double durationInHours;
    private String excursionType;
    private String ecosystem;
    private String weather;

    public EcologicalExcursionService(String id, String serviceType, double cost, String providerId, String providerName, int quantityAvailable, String description, Location location, double durationInHours, String excursionType, String ecosystem, String weather, String searchString) {
        super(id, serviceType, cost, providerId, providerName, quantityAvailable, description, location, searchString);
        this.durationInHours = durationInHours;
        this.excursionType = excursionType;
        this.ecosystem = ecosystem;
        this.weather = weather;
    }

    public EcologicalExcursionService() {
    }

    public double getDurationInHours() {
        return durationInHours;
    }

    public void setDurationInHours(double durationInHours) {
        this.durationInHours = durationInHours;
    }

    public String getExcursionType() {
        return excursionType;
    }

    public void setExcursionType(String excursionType) {
        this.excursionType = excursionType;
    }

    public String getEcosystem() {
        return ecosystem;
    }

    public void setEcosystem(String ecosystem) {
        this.ecosystem = ecosystem;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
