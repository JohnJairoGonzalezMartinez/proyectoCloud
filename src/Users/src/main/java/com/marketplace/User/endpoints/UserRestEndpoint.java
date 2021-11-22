package com.marketplace.User.endpoints;

import com.marketplace.User.controllers.AuthorizationCheckController;
import com.marketplace.User.controllers.UserController;
import com.marketplace.User.models.CreateUserDTO;
import com.marketplace.User.models.User;
import com.marketplace.User.models.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/marketplace/users")
public class UserRestEndpoint {

    private UserController controller;
    private AuthorizationCheckController authController;

    @Autowired
    public UserRestEndpoint(UserController controller, AuthorizationCheckController authController) {
        this.controller = controller;
        this.authController = authController;
    }

    @GetMapping("/{UserId}")
    public ResponseEntity<User> find(@PathVariable("UserId") String id){
        User searchedUser = controller.find(id);
        return ResponseEntity.status(200).body(searchedUser);
    }

    @GetMapping("/{UserId}/{usertype}")
    public ResponseEntity<Boolean> checkIfUserExists(@PathVariable("UserId") String id, @PathVariable("usertype") String userType){
        boolean response = controller.checkIfUserExists(id, userType);
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody CreateUserDTO data){
        UserAuthentication request = new UserAuthentication(null,data.getEmail(), data.getPassword());
        UserAuthentication authResponse = this.authController.createUserInAuthenticationService(request);
        User userData = data.getUserData();
        userData.setEmail( authResponse.getEmail() );
        userData.setId( authResponse.getUserId() );
        User created = controller.create(userData);
        return ResponseEntity.status(200).body(created);
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User data, @RequestHeader("Authorization") String authorizationToken){
        authController.checkAuthorizationHeader(authorizationToken, data.getId());
        User searchedUser = controller.update(data);
        return ResponseEntity.status(200).body(searchedUser);
    }

    @DeleteMapping("/{UserId}")
    public ResponseEntity<String> delete(@PathVariable("UserId") String id, @RequestHeader("Authorization") String authorizationToken){
        authController.checkAuthorizationHeader(authorizationToken, id);
        controller.delete(id);
        return ResponseEntity.status(200).body("User deleted successfully");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

}
