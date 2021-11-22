package com.marketplace.Service.controllers;

import com.marketplace.Service.models.FoodService;
import com.marketplace.Service.models.LodgingService;
import com.marketplace.Service.models.TransportService;
import org.springframework.stereotype.Service;

@Service
public class FoodServiceController {

    public FoodServiceController() {
    }

    public FoodService create(FoodService data){
        setBasicServiceData(data);
        return data;
    }

    public FoodService update(FoodService data){
        setBasicServiceData(data);
        return data;
    }

    private void setBasicServiceData(FoodService service){
        String searchString = service.getSearchString();
        searchString += ";" + service.getFoodType();
        service.setSearchString( searchString.toLowerCase() );
    }
}
