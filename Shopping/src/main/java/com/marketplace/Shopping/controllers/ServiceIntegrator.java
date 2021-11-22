package com.marketplace.Shopping.controllers;

import com.marketplace.Shopping.models.CartItem;
import com.marketplace.Shopping.models.PurchaseDTO;
import com.marketplace.Shopping.models.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceIntegrator {

    @Autowired
    private RestTemplate restTemplate;

    public void tryToPurchaseShoppingCart(ShoppingCart shoppingCart){
        String url = "http://service/marketplace/services/sale";
        List<CartItem> purchaseList = shoppingCart.getItems();
        ResponseEntity<Void> response = restTemplate.postForEntity(url, purchaseList, Void.class);
        if ( response.getStatusCode().isError() ){
            if ( response.hasBody() ){
                throw new RuntimeException("An unexpected error has occurred");
            }
            throw new RuntimeException( response.getBody().toString() );
        }
    }

}
