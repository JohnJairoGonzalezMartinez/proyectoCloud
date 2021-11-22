package com.marketplace.Shopping.models;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Question {

    @Id
    private String id;
    private String customerId;
    private String serviceId;
    private String question;
    private String answer;
    private LocalDateTime publicationDate;

    public Question(String id, String customerId, String serviceId, String question, String answer, LocalDateTime publicationDate) {
        this.id = id;
        this.customerId = customerId;
        this.serviceId = serviceId;
        this.question = question;
        this.answer = answer;
        this.publicationDate = publicationDate;
    }

    public Question() {
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }
}
