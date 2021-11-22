package com.marketplace.Marketplace.endpoints;

import com.marketplace.Marketplace.models.SaleDTO;
import com.marketplace.Marketplace.models.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/marketplace/services")
public class ServiceRestEndpoint {

    private static final String URL = "http://service/marketplace/services/";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{serviceId}")
    public ResponseEntity<Service> findById(@PathVariable("serviceId") String id){
        return restTemplate.getForEntity(URL+id, Service.class);
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<Service[]> findByProviderId(@PathVariable("providerId") String id){
        return restTemplate.getForEntity(URL+"provider/"+id, Service[].class);
    }

    @GetMapping("/type/{servicetype}")
    public ResponseEntity<Service[]> findByServiceType(@PathVariable("servicetype") String type){
        return restTemplate.getForEntity(URL+"type/"+type, Service[].class);
    }

    @GetMapping("/search/{searchstring}")
    public ResponseEntity<Service[]> findBySearchString(@PathVariable("searchstring") String searchString){
        return restTemplate.getForEntity(URL+"search/"+searchString, Service[].class);
    }

    @PostMapping
    public ResponseEntity<Service> create(@RequestBody Service data, @RequestHeader("Authorization") String authorizationToken){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorizationToken);
        HttpEntity<Service> request = new HttpEntity<>(data, headers);
        return restTemplate.exchange(URL, HttpMethod.POST, request, Service.class);
    }

    @PostMapping("/sale")
    public ResponseEntity<Void> executeSale(@RequestBody List<SaleDTO> data){
        return restTemplate.postForEntity(URL+"sale", data, Void.class);
    }

    @PutMapping
    public ResponseEntity<Service> update(@RequestBody Service data, @RequestHeader("Authorization") String authorizationToken){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorizationToken);
        HttpEntity<Service> request = new HttpEntity<>(data, headers);
        return restTemplate.exchange(URL, HttpMethod.PUT, request, Service.class);
    }

    @DeleteMapping("/{serviceId}")
    public ResponseEntity<String> delete(@PathVariable("serviceId") String id, @RequestHeader("Authorization") String authorizationToken){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorizationToken);
        HttpEntity<Void> request = new HttpEntity<>(headers);
        return restTemplate.exchange(URL, HttpMethod.DELETE, request, String.class);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

}
