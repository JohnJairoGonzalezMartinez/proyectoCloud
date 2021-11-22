package com.marketplace.Marketplace.endpoints;

import com.marketplace.Marketplace.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/marketplace/feedback/questions")
public class QuestionRestEndpoint {

    private static final String URL = "http://feedback/marketplace/feedback/questions/";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{questionId}")
    public ResponseEntity<Question> find(@PathVariable("questionId") String id){
        return restTemplate.getForEntity(URL+id, Question.class);
    }

    @PostMapping
    public ResponseEntity<Question> create(@RequestBody Question data, @RequestHeader("Authorization") String authorizationToken){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorizationToken);
        HttpEntity<Question> request = new HttpEntity<>(data, headers);
        return restTemplate.exchange(URL, HttpMethod.POST, request, Question.class);
    }

    @PutMapping
    public ResponseEntity<Question> update(@RequestBody Question data, @RequestHeader("Authorization") String authorizationToken){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorizationToken);
        HttpEntity<Question> request = new HttpEntity<>(data, headers);
        return restTemplate.exchange(URL, HttpMethod.PUT, request, Question.class);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<String> delete(@PathVariable("questionId") String id, @RequestHeader("Authorization") String authorizationToken){
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
