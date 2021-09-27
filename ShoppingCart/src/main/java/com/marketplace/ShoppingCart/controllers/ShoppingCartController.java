package com.marketplace.ShoppingCart.controllers;

import com.marketplace.ShoppingCart.models.ShoppingCart;
import com.marketplace.ShoppingCart.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Service
public class ShoppingCartController {

    @Autowired
    private ShoppingCartRepository repository;

    public ShoppingCart find(String id){
        return repository.findById(id).get();
    }

    @PostMapping
    public ShoppingCart create(ShoppingCart data){
        data.setLastModification( LocalDateTime.now() );
        return repository.insert(data);
    }

    @PutMapping
    public ShoppingCart update(ShoppingCart data){
        data.setLastModification( LocalDateTime.now() );
        return repository.save(data);
    }

    @DeleteMapping("/{shoppingCartId}")
    public void delete(@PathVariable("shoppingCartId") String id){
        repository.deleteById(id);
    }

}
