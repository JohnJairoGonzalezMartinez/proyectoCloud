package com.marketplace.Shopping.controllers;

import com.marketplace.Shopping.exceptions.PurchaseNotFoundException;
import com.marketplace.Shopping.models.Purchase;
import com.marketplace.Shopping.models.ShoppingCart;
import com.marketplace.Shopping.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class PurchaseController {

    private final PurchaseRepository repository;

    @Autowired
    public PurchaseController(PurchaseRepository repository) {
        this.repository = repository;
    }

    public Purchase find(String id){
        Optional<Purchase> response = repository.findById(id);
        if ( response.isPresent() ){
            return response.get();
        }
        throw new PurchaseNotFoundException("The Purchase with id [" + id + "] was not found");
    }

    public Purchase create(Purchase data){
        return repository.insert(data);
    }

    public Purchase update(Purchase data){
        return repository.save(data);
    }

    public void delete(@PathVariable("PurchaseId") String id){
        repository.deleteById(id);
    }

}
