package com.marketplace.Marketplace.models;

import java.time.LocalDateTime;

public class TransportService extends Service {

    private String route;
    private String transportType;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Location destiny;

    public TransportService(String id, String serviceType, double cost, String providerId, String providerName, int quantityAvailable, String description, Location location, String route, String transportType, LocalDateTime departureTime, LocalDateTime arrivalTime, Location destiny, String searchString) {
        super(id, serviceType, cost, providerId, providerName, quantityAvailable, description, location, searchString);
        this.route = route;
        this.transportType = transportType;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.destiny = destiny;
    }

    public TransportService() {
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Location getDestiny() {
        return destiny;
    }

    public void setDestiny(Location destiny) {
        this.destiny = destiny;
    }
}
