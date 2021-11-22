package com.marketplace.Marketplace.endpoints;

import com.marketplace.Marketplace.models.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/marketplace/purchase")
public class PurchaseRestEndpoint {

    private static final String URL = "http://shopping/marketplace/purchase/";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{purchaseId}")
    public ResponseEntity<Purchase> find(@PathVariable("purchaseId") String id, @RequestHeader("Authorization") String authorizationToken){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorizationToken);
        return restTemplate.exchange(URL+id, HttpMethod.GET, new HttpEntity<>(headers), Purchase.class);
    }

    @PostMapping
    public ResponseEntity<Purchase> create(@RequestBody Purchase data, @RequestHeader("Authorization") String authorizationToken){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorizationToken);
        HttpEntity<Purchase> request = new HttpEntity<>(data, headers);
        return restTemplate.exchange(URL, HttpMethod.POST, request, Purchase.class);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

}
