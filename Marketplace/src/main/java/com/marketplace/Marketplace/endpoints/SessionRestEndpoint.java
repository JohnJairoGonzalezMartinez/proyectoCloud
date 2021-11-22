package com.marketplace.Marketplace.endpoints;

import com.marketplace.Marketplace.models.LoginData;
import com.marketplace.Marketplace.models.LoginResponse;
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
@RequestMapping("/marketplace/authentication/")
public class SessionRestEndpoint {

    private static final String URL = "http://authentication/marketplace/authentication/";

    @Autowired
    private RestTemplate restTemplate;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginData loginData){
        return restTemplate.postForEntity(URL+"login", loginData, LoginResponse.class);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String headerToken){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", headerToken);
        HttpEntity<Void> request = new HttpEntity<>(headers);
        return restTemplate.exchange(URL+"logout", HttpMethod.POST, request, String.class);
    }

    @GetMapping("/session/userId/{token}")
    public ResponseEntity<String> getIdFromSessionToken(@PathVariable("token") String token){
        return restTemplate.getForEntity(URL+"session/userId/"+token,String.class);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception e){
        return ResponseEntity.status(400).body(e.getMessage());
    }


}
