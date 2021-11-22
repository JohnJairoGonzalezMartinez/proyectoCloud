package com.marketplace.Shopping.controllers;

import com.marketplace.Shopping.exceptions.NotAuthorizedException;
import com.marketplace.Shopping.models.Question;
import com.marketplace.Shopping.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthorizationCheckController {

    private final QuestionController questionController;
    private final ReviewController reviewController;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public AuthorizationCheckController(QuestionController purchaseController, ReviewController reviewController) {
        this.questionController = purchaseController;
        this.reviewController = reviewController;
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    public String getRequesterUserId(String tokenHeader) throws NotAuthorizedException{
        String url = "http://authentication/marketplace/authentication/session/userId/" + tokenHeader;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if ( response.getStatusCode().isError() ){
            throw new NotAuthorizedException(response.getBody());
        }
        return response.getBody();
    }

    public void checkIfUserHasAccessToReview(String tokenHeader, String reviewId){
        String userId = getRequesterUserId(tokenHeader);
        Review searchedReview = reviewController.find(reviewId);
        if ( !searchedReview.getCustomerId().equals(userId) ){
            throw new NotAuthorizedException("The User with id [" + userId + "] does not have access to the resource");
        }
    }

    public void checkIfUserHasAccessToQuestion(String tokenHeader, String reviewId){
        String userId = getRequesterUserId(tokenHeader);
        Question searchedQuestion = questionController.find(reviewId);
        if ( !searchedQuestion.getCustomerId().equals(userId) ){
            throw new NotAuthorizedException("The User with id [" + userId + "] does not have access to the resource");
        }
    }

}
