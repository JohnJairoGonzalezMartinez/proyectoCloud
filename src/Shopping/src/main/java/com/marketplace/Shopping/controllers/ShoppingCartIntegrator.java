package com.marketplace.Shopping.controllers;

import com.marketplace.Shopping.models.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ShoppingCartIntegrator {

    @Autowired
    private RestTemplate restTemplate;

    public ShoppingCartIntegrator(){}

    public ShoppingCart findAssociatedShoppingCart(String authorizationHeader, String cartId){
        String url = "http://shoppingcart/marketplace/shoppingcart/" + cartId ;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorizationHeader);
        ResponseEntity<ShoppingCart> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(headers), ShoppingCart.class);
        if ( response.getStatusCode().isError() ){
            if ( response.hasBody() ){
                throw new RuntimeException("An unexpected error has occurred");
            }
            throw new RuntimeException( response.getBody().toString() );
        }
        return response.getBody();
    }

}
