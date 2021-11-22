package com.marketplace.Shopping.controllers;

import com.marketplace.Shopping.exceptions.NotAuthorizedException;
import com.marketplace.Shopping.models.Purchase;
import com.marketplace.Shopping.models.UserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthorizationCheckController {

    private final PurchaseController purchaseController;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public AuthorizationCheckController(PurchaseController purchaseController) {
        this.purchaseController = purchaseController;
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
    
    public void checkIfUserHasAccessToResource(String tokenHeader, String purchaseId){
        String userId = getRequesterUserId(tokenHeader);
        Purchase searchedPurchase = purchaseController.find(purchaseId);
        if ( !searchedPurchase.getUserId().equals(userId) ){
            throw new NotAuthorizedException("The User with id [" + userId + "] does not have access to the resource");
        }
    }


}
