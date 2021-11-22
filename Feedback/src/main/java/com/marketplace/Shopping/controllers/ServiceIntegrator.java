package com.marketplace.Shopping.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ServiceIntegrator {

    @Autowired
    private RestTemplate restTemplate;

    public void checkIfServiceExists(String serviceId){
        String url = "http://service/marketplace/services/" + serviceId;
        ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
        if ( response.getStatusCode().isError() ){
            if ( response.hasBody() ){
                throw new RuntimeException("An unexpected error has occurred");
            }
            throw new RuntimeException( response.getBody().toString() );
        }
    }

}
