package com.marketplace.Marketplace.endpoints;

import com.marketplace.Marketplace.models.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/marketplace/shoppingcart")
public class ShoppingCartRestEndpoint {

    private static final String URL = "http://shoppingcart/marketplace/shoppingcart/";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{shoppingCartId}")
    public ResponseEntity<ShoppingCart> find(@PathVariable("shoppingCartId") String id, @RequestHeader("Authorization") String authorizationToken){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorizationToken);
        HttpEntity<Void> request = new HttpEntity<>(headers);
        return restTemplate.exchange(URL+id, HttpMethod.GET, request, ShoppingCart.class);
    }

    @PostMapping
    public ResponseEntity<ShoppingCart> create(@RequestBody ShoppingCart data, @RequestHeader("Authorization") String authorizationToken){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorizationToken);
        HttpEntity<ShoppingCart> request = new HttpEntity<>(data, headers);
        return restTemplate.exchange(URL, HttpMethod.POST, request, ShoppingCart.class);
    }

    @PutMapping
    public ResponseEntity<ShoppingCart> update(@RequestBody ShoppingCart data, @RequestHeader("Authorization") String authorizationToken){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorizationToken);
        HttpEntity<ShoppingCart> request = new HttpEntity<>(data, headers);
        return restTemplate.exchange(URL, HttpMethod.PUT, request, ShoppingCart.class);
    }

    @DeleteMapping("/{shoppingCartId}")
    public ResponseEntity<String> delete(@PathVariable("shoppingCartId") String id, @RequestHeader("Authorization") String authorizationToken){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorizationToken);
        HttpEntity<Void> request = new HttpEntity<>(headers);
        return restTemplate.exchange(URL+id, HttpMethod.DELETE, request, String.class);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

}
