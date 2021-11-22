package com.marketplace.Service.controllers;

import com.marketplace.Service.exceptions.NotAuthorizedException;
import com.marketplace.Service.models.Service;
import com.marketplace.Service.models.UserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@org.springframework.stereotype.Service
public class AuthorizationCheckController {

    private final ServiceController serviceController;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public AuthorizationCheckController(ServiceController serviceController) {
        this.serviceController = serviceController;
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

    public UserInformation geProviderInformation(String tokenHeader){
        String userId = getRequesterUserId(tokenHeader);
        String url = "http://user/marketplace/users/" + userId;

        ResponseEntity<UserInformation> response = restTemplate.getForEntity(url, UserInformation.class);
        if ( response.getStatusCode().isError() ){
            throw new RuntimeException(response.getBody() + "");
        }
        if ( !response.hasBody() ){
            throw new RuntimeException("An unexpected error has occurred on User Service invocation");
        }
        if ( !response.getBody().getUserType().equals("Provider") ){
            throw new NotAuthorizedException("The User with id [" + userId + "] is not a Provider");
        }
        return response.getBody();
    }

    public void checkIfProviderHasAccessToResource(String tokenHeader, String serviceId){
        String userId = getRequesterUserId(tokenHeader);
        Service searchedService = serviceController.findById(serviceId);
        if ( !searchedService.getProviderId().equals(userId) ){
            throw new NotAuthorizedException("The User with id [" + userId + "] does not have access to the resource");
        }
    }


}
