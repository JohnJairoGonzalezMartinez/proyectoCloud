package com.marketplace.ShoppingCart.endpoints;

import com.marketplace.ShoppingCart.controllers.AuthorizationCheckController;
import com.marketplace.ShoppingCart.controllers.ShoppingCartController;
import com.marketplace.ShoppingCart.models.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/marketplace/shoppingcart")
public class ShoppingCartRestEndpoint {

    @Autowired
    private ShoppingCartController controller;

    @Autowired
    private AuthorizationCheckController authController;

    @GetMapping("/{shoppingCartId}")
    public ResponseEntity<ShoppingCart> find(@PathVariable("shoppingCartId") String id, @RequestHeader("Authorization") String authorizationToken){
        authController.assertUserAuthorization(authorizationToken, id);
        ShoppingCart searchedCart = controller.find(id);
        return ResponseEntity.status(200).body(searchedCart);
    }

    @PostMapping
    public ResponseEntity<ShoppingCart> create(@RequestBody ShoppingCart data, @RequestHeader("Authorization") String authorizationToken){
        ShoppingCart created = controller.create(data);
        return ResponseEntity.status(200).body(created);
    }

    @PutMapping
    public ResponseEntity<ShoppingCart> update(@RequestBody ShoppingCart data, @RequestHeader("Authorization") String authorizationToken){
        authController.assertUserAuthorization(authorizationToken, data.get_id());
        ShoppingCart searchedCart = controller.update(data);
        return ResponseEntity.status(200).body(searchedCart);
    }

    @DeleteMapping("/{shoppingCartId}")
    public ResponseEntity<String> delete(@PathVariable("shoppingCartId") String id, @RequestHeader("Authorization") String authorizationToken){
        authController.assertUserAuthorization(authorizationToken, id);
        controller.delete(id);
        return ResponseEntity.status(200).body("Shopping Cart deleted successfully");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

}
