package com.marketplace.Shopping.controllers;

import com.marketplace.Shopping.exceptions.ReviewNotFoundException;
import com.marketplace.Shopping.models.Review;
import com.marketplace.Shopping.repositories.QuestionRepository;
import com.marketplace.Shopping.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class ReviewController {

    private final ReviewRepository repository;

    @Autowired
    public ReviewController(ReviewRepository repository) {
        this.repository = repository;
    }

    public Review find(String id){
        Optional<Review> response = repository.findById(id);
        if ( response.isPresent() ){
            return response.get();
        }
        throw new ReviewNotFoundException("The Review with id [" + id + "] was not found");
    }

    public Review create(Review data){
        return repository.insert(data);
    }

    public Review update(Review data){
        return repository.save(data);
    }

    public void delete(@PathVariable("ReviewId") String id){
        repository.deleteById(id);
    }

}
