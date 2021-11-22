package com.marketplace.Shopping.endpoints;

import com.marketplace.Shopping.controllers.AuthorizationCheckController;
import com.marketplace.Shopping.controllers.QuestionController;
import com.marketplace.Shopping.controllers.ServiceIntegrator;
import com.marketplace.Shopping.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/marketplace/feedback/questions")
public class QuestionRestEndpoint {

    private final QuestionController controller;
    private final AuthorizationCheckController authController;
    private final ServiceIntegrator serviceIntegrator;

    @Autowired
    public QuestionRestEndpoint(QuestionController controller, AuthorizationCheckController authController, ServiceIntegrator serviceIntegrator) {
        this.controller = controller;
        this.authController = authController;
        this.serviceIntegrator = serviceIntegrator;
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<Question> find(@PathVariable("questionId") String id){
        Question searchedQuestion = controller.find(id);
        return ResponseEntity.status(200).body(searchedQuestion);
    }

    @PostMapping
    public ResponseEntity<Question> create(@RequestBody Question data, @RequestHeader("Authorization") String authorizationToken){
        serviceIntegrator.checkIfServiceExists(data.getServiceId());
        Question created = controller.create(data);
        return ResponseEntity.status(200).body(created);
    }

    @PutMapping
    public ResponseEntity<Question> update(@RequestBody Question data, @RequestHeader("Authorization") String authorizationToken){
        authController.checkIfUserHasAccessToQuestion(authorizationToken, data.getId());
        Question updatedQuestion = controller.update(data);
        return ResponseEntity.status(200).body(updatedQuestion);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<String> delete(@PathVariable("questionId") String id, @RequestHeader("Authorization") String authorizationToken){
        authController.checkIfUserHasAccessToQuestion(authorizationToken, id);
        controller.delete(id);
        return ResponseEntity.status(200).body("Question deleted successfully");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

}
