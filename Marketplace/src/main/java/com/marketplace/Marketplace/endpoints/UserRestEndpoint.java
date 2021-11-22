package com.marketplace.Marketplace.endpoints;

import com.marketplace.Marketplace.models.CreateUserDTO;
import com.marketplace.Marketplace.models.Question;
import com.marketplace.Marketplace.models.ShoppingCart;
import com.marketplace.Marketplace.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/marketplace/users")
public class UserRestEndpoint {

    private static final String URL = "http://user/marketplace/users/";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{userId}")
    public ResponseEntity<User> find(@PathVariable("userId") String id, @RequestHeader("Authorization") String authorizationToken){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorizationToken);
        HttpEntity<Void> request = new HttpEntity<>(headers);
        return restTemplate.exchange(URL+id, HttpMethod.GET, request, User.class);
    }

    @GetMapping("/{userId}/{usertype}")
    public ResponseEntity<Boolean> checkIfUserExists(@PathVariable("userId") String id, @PathVariable("usertype") String userType){
        return restTemplate.getForEntity(URL+id+"/"+userType, Boolean.class);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody CreateUserDTO data){
        return restTemplate.postForEntity(URL,data,User.class);
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User data, @RequestHeader("Authorization") String authorizationToken){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorizationToken);
        HttpEntity<User> request = new HttpEntity<>(data, headers);
        return restTemplate.exchange(URL, HttpMethod.PUT, request, User.class);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> delete(@PathVariable("userId") String id, @RequestHeader("Authorization") String authorizationToken){
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
