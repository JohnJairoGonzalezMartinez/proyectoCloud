package com.marketplace.ShoppingCart.controllers;

import com.marketplace.ShoppingCart.models.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthorizationCheckController {

    @Autowired
    private ShoppingCartController controller;

    @Autowired
    private RestTemplate restTemplate;

    public AuthorizationCheckController(){}

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    public void assertUserAuthorization(String tokenHeader, String resourceId) throws NotAuthorizedException{
        String userId = getRequesterUserId(tokenHeader);
        ShoppingCart searched = controller.find(resourceId);
        if ( !searched.getCustomerId().equals(userId) ){
            throw new NotAuthorizedException("The user with id [" + userId + "] does not have access to the resource" );
        }
    }

    public String getRequesterUserId(String tokenHeader) throws NotAuthorizedException{
        String url = "http://authentication/marketplace/authentication/session/userId/" + tokenHeader;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if ( response.getStatusCode().isError() ){
            throw new NotAuthorizedException(response.getBody());
        }
        return response.getBody();
    }

}
