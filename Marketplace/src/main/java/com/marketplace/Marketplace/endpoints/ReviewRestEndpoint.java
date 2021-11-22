package com.marketplace.Marketplace.endpoints;

import com.marketplace.Marketplace.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/marketplace/feedback/reviews")
public class ReviewRestEndpoint {

    private static final String URL = "http://feedback/marketplace/feedback/reviews/";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> find(@PathVariable("reviewId") String id){
        return restTemplate.getForEntity(URL+id, Review.class);
    }

    @PostMapping
    public ResponseEntity<Review> create(@RequestBody Review data, @RequestHeader("Authorization") String authorizationToken){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorizationToken);
        HttpEntity<Review> request = new HttpEntity<>(data, headers);
        return restTemplate.exchange(URL, HttpMethod.POST, request, Review.class);
    }

    @PutMapping
    public ResponseEntity<Review> update(@RequestBody Review data, @RequestHeader("Authorization") String authorizationToken){        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorizationToken);
        HttpEntity<Review> request = new HttpEntity<>(data, headers);
        return restTemplate.exchange(URL, HttpMethod.PUT, request, Review.class);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> delete(@PathVariable("reviewId") String id, @RequestHeader("Authorization") String authorizationToken){
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
