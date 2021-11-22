package com.marketplace.Shopping.controllers;

import com.marketplace.Shopping.exceptions.QuestionNotFoundException;
import com.marketplace.Shopping.exceptions.ReviewNotFoundException;
import com.marketplace.Shopping.models.Question;
import com.marketplace.Shopping.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class QuestionController {

    private final QuestionRepository repository;

    @Autowired
    public QuestionController(QuestionRepository repository) {
        this.repository = repository;
    }

    public Question find(String id){
        Optional<Question> response = repository.findById(id);
        if ( response.isPresent() ){
            return response.get();
        }
        throw new QuestionNotFoundException("The Question with id [" + id + "] was not found");
    }

    public Question create(Question data){
        return repository.insert(data);
    }

    public Question update(Question data){
        return repository.save(data);
    }

    public void delete(@PathVariable("QuestionId") String id){
        repository.deleteById(id);
    }

}
