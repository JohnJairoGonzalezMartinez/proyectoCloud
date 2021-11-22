package com.marketplace.Service.controllers;

import com.marketplace.Service.models.CountryInformation;
import com.marketplace.Service.models.Location;
import com.marketplace.Service.models.LodgingService;
import com.marketplace.Service.models.TransportService;
import org.springframework.stereotype.Service;

@Service
public class LodgingServiceController {

    public LodgingServiceController() {
    }

    public LodgingService create(LodgingService data){
        setBasicServiceData(data);
        return data;
    }

    public LodgingService update(LodgingService data){
        setBasicServiceData(data);
        return data;
    }

    private void setBasicServiceData(LodgingService service){
        String searchString = service.getSearchString();
        searchString += ";" + service.getLodgingType();
        service.setSearchString( searchString.toLowerCase() );
    }
}
