package com.marketplace.User.controllers;

import com.marketplace.User.exceptions.AuthenticationCreateException;
import com.marketplace.User.exceptions.NotAuthorizedException;
import com.marketplace.User.models.User;
import com.marketplace.User.models.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthorizationCheckController {

    private final UserController userController;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public AuthorizationCheckController(UserController userController) {
        this.userController = userController;
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    public void checkAuthorizationHeader(String tokenHeader, String resourceId) throws NotAuthorizedException {
        String userId = getRequesterUserId(tokenHeader);
        User searched = userController.find(resourceId);
        if ( !searched.getId().equals(userId) ){
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

    public UserAuthentication createUserInAuthenticationService(UserAuthentication userData){
        String url = "http://authentication/marketplace/authentication/";
        ResponseEntity<UserAuthentication> response = restTemplate.postForEntity(url,userData, UserAuthentication.class);
        if ( response.getStatusCode().isError() ){
            throw new AuthenticationCreateException(response.getBody().toString());
        }
        return response.getBody();
    }


}
