package com.marketplace.Shopping.endpoints;

import com.marketplace.Shopping.controllers.AuthorizationCheckController;
import com.marketplace.Shopping.controllers.PurchaseController;
import com.marketplace.Shopping.controllers.ServiceIntegrator;
import com.marketplace.Shopping.controllers.ShoppingCartIntegrator;
import com.marketplace.Shopping.models.Purchase;
import com.marketplace.Shopping.models.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/marketplace/purchase")
public class PurchaseRestEndpoint {

    private final PurchaseController controller;
    private final AuthorizationCheckController authController;
    private final ServiceIntegrator serviceIntegrator;
    private final ShoppingCartIntegrator cartIntegrator;

    @Autowired
    public PurchaseRestEndpoint(PurchaseController controller, AuthorizationCheckController authController, ShoppingCartIntegrator cartIntegrator, ServiceIntegrator serviceIntegrator) {
        this.controller = controller;
        this.authController = authController;
        this.cartIntegrator = cartIntegrator;
        this.serviceIntegrator = serviceIntegrator;
    }

    @GetMapping("/{purchaseId}")
    public ResponseEntity<Purchase> find(@PathVariable("purchaseId") String id, @RequestHeader("Authorization") String authorizationToken){
        authController.checkIfUserHasAccessToResource(authorizationToken, id);
        Purchase searchedPurchase = controller.find(id);
        return ResponseEntity.status(200).body(searchedPurchase);
    }

    @PostMapping
    public ResponseEntity<Purchase> create(@RequestBody Purchase data, @RequestHeader("Authorization") String authorizationToken){
        ShoppingCart cart = cartIntegrator.findAssociatedShoppingCart(authorizationToken, data.getCartId());
        serviceIntegrator.tryToPurchaseShoppingCart(cart);
        Purchase created = controller.create(data);
        return ResponseEntity.status(200).body(created);
    }

    /*
    @PutMapping
    public ResponseEntity<Purchase> update(@RequestBody Purchase data, @RequestHeader("Authorization") String authorizationToken){
        UserInformation providerInformation = authController.geProviderInformation(authorizationToken);
        Purchase searchedPurchase = controller.update(data);
        return ResponseEntity.status(200).body(searchedPurchase);
    }
     */

    /*
    @DeleteMapping("/{purchaseId}")
    public ResponseEntity<String> delete(@PathVariable("purchaseId") String id, @RequestHeader("Authorization") String authorizationToken){
        authController.checkIfProviderHasAccessToResource(authorizationToken, id);
        controller.delete(id);
        return ResponseEntity.status(200).body("Purchase deleted successfully");
    }
     */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

}
