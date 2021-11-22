package com.marketplace.Authentication.controllers;

import com.marketplace.Authentication.controllers.exceptions.UserNotFoundException;
import com.marketplace.Authentication.models.UserAuthentication;
import com.marketplace.Authentication.repositories.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationController {

    private final AuthenticationRepository repository;
    private final PasswordController passwordController;

    @Autowired
    public AuthenticationController(AuthenticationRepository repository, PasswordController passwordController) {
        this.repository = repository;
        this.passwordController = passwordController;
    }

    public UserAuthentication create(UserAuthentication data){
        String encryptedPassword = passwordController.encryptPassword(data.getPassword());
        data.setPassword( encryptedPassword );
        return repository.insert(data);
    }

    public UserAuthentication findByEmail(String email){
        Optional<UserAuthentication> auth = repository.findByEmail(email);
        if ( auth.isPresent() ){
            return auth.get();
        }
        throw new UserNotFoundException("The user with email [" + email + "] was not found");
    }

    public UserAuthentication findByUserId(String userId){
        Optional<UserAuthentication> auth = repository.findByUserId(userId);
        if ( auth.isPresent() ){
            return auth.get();
        }
        throw new UserNotFoundException("The user with userId [" + userId + "] was not found");
    }

    public UserAuthentication update(UserAuthentication update){
        String encryptedPassword = passwordController.encryptPassword(update.getPassword());
        update.setPassword( encryptedPassword );
        return repository.save(update);
    }

    public void deleteByEmail(String emailToDelete){
        repository.deleteById(emailToDelete);
    }

    public void deleteByUserId(String userId){
        String email = findByUserId(userId).getEmail();
        deleteByEmail(email);

    }

}
