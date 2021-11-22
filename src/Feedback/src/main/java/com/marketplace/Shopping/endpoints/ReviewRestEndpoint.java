package com.marketplace.Shopping.endpoints;

import com.marketplace.Shopping.controllers.*;
import com.marketplace.Shopping.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/marketplace/feedback/reviews")
public class ReviewRestEndpoint {

    private final ReviewController controller;
    private final AuthorizationCheckController authController;
    private final ServiceIntegrator serviceIntegrator;

    @Autowired
    public ReviewRestEndpoint(ReviewController controller, AuthorizationCheckController authController, ServiceIntegrator serviceIntegrator) {
        this.controller = controller;
        this.authController = authController;
        this.serviceIntegrator = serviceIntegrator;
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> find(@PathVariable("reviewId") String id){
        Review searchedReview = controller.find(id);
        return ResponseEntity.status(200).body(searchedReview);
    }

    @PostMapping
    public ResponseEntity<Review> create(@RequestBody Review data, @RequestHeader("Authorization") String authorizationToken){
        serviceIntegrator.checkIfServiceExists(data.getServiceId());
        Review created = controller.create(data);
        return ResponseEntity.status(200).body(created);
    }

    @PutMapping
    public ResponseEntity<Review> update(@RequestBody Review data, @RequestHeader("Authorization") String authorizationToken){
        authController.checkIfUserHasAccessToReview(authorizationToken, data.getId());
        Review updatedReview = controller.update(data);
        return ResponseEntity.status(200).body(updatedReview);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> delete(@PathVariable("reviewId") String id, @RequestHeader("Authorization") String authorizationToken){
        authController.checkIfUserHasAccessToReview(authorizationToken, id);
        controller.delete(id);
        return ResponseEntity.status(200).body("Review deleted successfully");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

}
