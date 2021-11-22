package com.marketplace.Marketplace.endpoints;

import com.marketplace.Marketplace.models.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/marketplace/authentication/")
public class AuthenticationRestEndpoint {

    private static final String URL = "http://authentication/marketplace/authentication";

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @PostMapping()
    public ResponseEntity<UserAuthentication> create(@RequestBody UserAuthentication data){
        return restTemplate.postForEntity(URL, data, UserAuthentication.class);
    }

    @PutMapping()
    public ResponseEntity<UserAuthentication> update(@RequestBody UserAuthentication data, @RequestHeader("Authorization") String headerToken){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", headerToken);
        HttpEntity<UserAuthentication> request = new HttpEntity<>(data, headers);
        return restTemplate.exchange(URL, HttpMethod.PUT, request, UserAuthentication.class);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> delete(@PathVariable("userId") String userId, @RequestHeader("Authorization") String headerToken){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", headerToken);
        return restTemplate.exchange(URL + userId, HttpMethod.DELETE, new HttpEntity<>(headers), String.class);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

}
