package com.marketplace.Service.endpoints;

import com.marketplace.Service.controllers.AuthorizationCheckController;
import com.marketplace.Service.controllers.ServiceController;
import com.marketplace.Service.models.SaleDTO;
import com.marketplace.Service.models.Service;
import com.marketplace.Service.models.UserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/marketplace/services")
public class ServiceRestEndpoint {

    private final ServiceController controller;
    private final AuthorizationCheckController authController;

    @Autowired
    public ServiceRestEndpoint(ServiceController controller, AuthorizationCheckController authController) {
        this.controller = controller;
        this.authController = authController;
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<Service> find(@PathVariable("serviceId") String id){
        Service searchedService = controller.find(id);
        return ResponseEntity.status(200).body(searchedService);
    }

    @PostMapping
    public ResponseEntity<Service> create(@RequestBody Service data, @RequestHeader("Authorization") String authorizationToken){
        UserInformation providerInformation = authController.geProviderInformation(authorizationToken);
        data.setProviderId(providerInformation.getId());
        data.setProviderName(providerInformation.getName());
        Service created = controller.create(data);
        return ResponseEntity.status(200).body(created);
    }

    @PostMapping("/sale")
    public ResponseEntity<Void> executeSale(@RequestBody List<SaleDTO> data){
        controller.executeSale(data);
        return ResponseEntity.status(200).build();
    }

    @PutMapping
    public ResponseEntity<Service> update(@RequestBody Service data, @RequestHeader("Authorization") String authorizationToken){
        UserInformation providerInformation = authController.geProviderInformation(authorizationToken);
        data.setProviderId(providerInformation.getId());
        data.setProviderName(providerInformation.getName());
        Service searchedService = controller.update(data);
        return ResponseEntity.status(200).body(searchedService);
    }

    @DeleteMapping("/{serviceId}")
    public ResponseEntity<String> delete(@PathVariable("serviceId") String id, @RequestHeader("Authorization") String authorizationToken){
        authController.checkIfProviderHasAccessToResource(authorizationToken, id);
        controller.delete(id);
        return ResponseEntity.status(200).body("Shopping Cart deleted successfully");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

}
