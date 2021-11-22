package com.marketplace.Service.controllers;

import com.marketplace.Service.models.CountryInformation;
import com.marketplace.Service.models.Location;
import com.marketplace.Service.models.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransportServiceController {

    private final CountryInformationController countryController;

    @Autowired
    public TransportServiceController(CountryInformationController countryController) {
        this.countryController = countryController;
    }

    public TransportService create(TransportService data){
        setBasicServiceData(data);
        return data;
    }

    public TransportService update(TransportService data){
        setBasicServiceData(data);
        return data;
    }

    private void setBasicServiceData(TransportService service){
        Location location = service.getDestiny();
        CountryInformation countryInformation =  countryController.getCountryInformation(location.getCountry());
        location.setCountryExtraInformation( countryInformation );
        String searchString = service.getSearchString();
        searchString += ";" + service.getDestiny() + ";" + service.getTransportType();
        service.setSearchString( searchString.toLowerCase() );
    }
}
