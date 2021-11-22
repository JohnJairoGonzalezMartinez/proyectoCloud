package com.marketplace.User.controllers;

import com.marketplace.User.exceptions.IllegalUserTypeException;
import com.marketplace.User.exceptions.UserNotFoundException;
import com.marketplace.User.models.*;
import com.marketplace.User.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class UserController {

    private final UserRepository repository;
    private final CustomerController customerController;
    private final ProviderController providerController;

    @Autowired
    public UserController(UserRepository repository, CustomerController customerController, ProviderController providerController) {
        this.repository = repository;
        this.customerController = customerController;
        this.providerController = providerController;
    }

    public User find(String id){
        Optional<User> response = repository.findById(id);
        if ( response.isPresent() ){
            return response.get();
        }
        throw new UserNotFoundException("The user with id [" + id + "] was not found");
    }

    public boolean checkIfUserExists(String userId, String role){
        User user = find(userId);
        return user.getUserType().equalsIgnoreCase(role.trim());
    }

    public User create(User data){
        if ( data.getUserType().equals("Customer") ){
            data = customerController.create((Customer)data);
        }
        else if ( data.getUserType().equals("Provider") ){
            data = providerController.create((Provider) data);
        }
        else{
            throw new IllegalUserTypeException("The user [" + data.getUserType() + "] is not allowed");
        }
        return repository.insert(data);
    }

    public User update(User data){
        if ( data.getUserType().equals("Customer") ){
            data = customerController.update((Customer)data);
        }
        else if ( data.getUserType().equals("Provider") ){
            data = providerController.update((Provider) data);
        }
        else{
            throw new IllegalUserTypeException("The user [" + data.getUserType() + "] is not allowed");
        }
        return repository.save(data);
    }

    public void delete(@PathVariable("UserId") String id){
        repository.deleteById(id);
    }

}
