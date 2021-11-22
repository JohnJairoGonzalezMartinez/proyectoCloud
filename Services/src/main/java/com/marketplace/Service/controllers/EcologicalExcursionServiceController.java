package com.marketplace.Service.controllers;

import com.marketplace.Service.models.EcologicalExcursionService;
import com.marketplace.Service.models.FoodService;
import org.springframework.stereotype.Service;

@Service
public class EcologicalExcursionServiceController {

    public EcologicalExcursionServiceController() {
    }

    public EcologicalExcursionService create(EcologicalExcursionService data){
        setBasicServiceData(data);
        return data;
    }

    public EcologicalExcursionService update(EcologicalExcursionService data){
        setBasicServiceData(data);
        return data;
    }

    private void setBasicServiceData(EcologicalExcursionService service){
        String searchString = service.getSearchString();
        searchString += ";" + service.getExcursionType() + ";" + service.getEcosystem() + ";" + service.getWeather();
        service.setSearchString( searchString.toLowerCase() );
    }
}
