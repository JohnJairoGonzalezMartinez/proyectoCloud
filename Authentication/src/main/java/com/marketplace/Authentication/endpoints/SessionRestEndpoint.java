package com.marketplace.Authentication.endpoints;

import com.marketplace.Authentication.controllers.SessionController;
import com.marketplace.Authentication.models.LoginData;
import com.marketplace.Authentication.models.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/marketplace/authentication/")
public class SessionRestEndpoint {

    private final SessionController sessionController;

    @Autowired
    public  SessionRestEndpoint(SessionController sessionController){
        this.sessionController = sessionController;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginData loginData){
        LoginResponse token = this.sessionController.login(loginData);
        return ResponseEntity.status(200).body(token);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String headerToken){
        this.sessionController.logout(headerToken);
        return ResponseEntity.status(200).body("Session closed successfully");
    }

    @GetMapping("/session/userId/{token}")
    public ResponseEntity<String> getIdFromSessionToken(@PathVariable("token") String token){
        String simpleToken = this.sessionController.getTokenFromHeader(token);
        String userId = this.sessionController.getIdIfUserHasActiveSession(simpleToken);
        return ResponseEntity.status(200).body(userId);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

}
