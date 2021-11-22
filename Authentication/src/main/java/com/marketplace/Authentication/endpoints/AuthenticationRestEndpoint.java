package com.marketplace.Authentication.endpoints;

import com.marketplace.Authentication.controllers.AuthenticationController;
import com.marketplace.Authentication.controllers.SessionController;
import com.marketplace.Authentication.models.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/marketplace/authentication")
public class AuthenticationRestEndpoint {

    private final AuthenticationController authenticationController;
    private final SessionController sessionController;

    @Autowired
    public AuthenticationRestEndpoint(AuthenticationController authenticationController, SessionController sessionController) {
        this.authenticationController = authenticationController;
        this.sessionController = sessionController;
    }

    @PostMapping()
    public ResponseEntity<UserAuthentication> create(@RequestBody UserAuthentication data){
        UserAuthentication authentication = authenticationController.create(data);
        authentication.setPassword(null);
        return ResponseEntity.status(200).body(authentication);
    }

    @PutMapping()
    public ResponseEntity<UserAuthentication> update(@RequestBody UserAuthentication data, @RequestHeader("Authorization") String headerToken){
        String simpleToken = sessionController.getTokenFromHeader(headerToken);
        sessionController.getIdIfUserHasActiveSession(simpleToken);
        UserAuthentication authentication = authenticationController.update(data);
        authentication.setPassword(null);
        return ResponseEntity.status(200).body(authentication);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> delete(@PathVariable("userId") String userId, @RequestHeader("Authorization") String headerToken){
        String simpleToken = sessionController.getTokenFromHeader(headerToken);
        String requesterUserId = sessionController.getIdIfUserHasActiveSession(simpleToken);
        authenticationController.deleteByUserId(requesterUserId);
        return ResponseEntity.status(200).body("User removed from the authentication service");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

}
