package com.marketplace.Shopping.models;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Review {

    @Id
    private String id;
    private double rating;
    private String serviceId;
    private String customerId;
    private String comment;
    private LocalDateTime publicationDate;

    public Review(String id, double rating, String serviceId, String customerId, String comment, LocalDateTime publicationDate) {
        this.id = id;
        this.rating = rating;
        this.serviceId = serviceId;
        this.customerId = customerId;
        this.comment = comment;
        this.publicationDate = publicationDate;
    }

    public Review(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }
}
