package com.marketplace.Service.controllers;

import com.marketplace.Service.exceptions.IllegalServiceTypeException;
import com.marketplace.Service.exceptions.NotQuantityAvailableException;
import com.marketplace.Service.exceptions.ServiceNotFoundException;
import com.marketplace.Service.models.*;
import com.marketplace.Service.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServiceController {

    private final ServiceRepository repository;
    private final EcologicalExcursionServiceController ecologicalController;
    private final FoodServiceController foodController;
    private final LodgingServiceController lodgingController;
    private final TransportServiceController transportController;
    private final CountryInformationController countryController;

    @Autowired
    public ServiceController(ServiceRepository repository, EcologicalExcursionServiceController ecologicalController, FoodServiceController foodController, LodgingServiceController lodgingController, TransportServiceController transportController, CountryInformationController countryController) {
        this.repository = repository;
        this.ecologicalController = ecologicalController;
        this.foodController = foodController;
        this.lodgingController = lodgingController;
        this.transportController = transportController;
        this.countryController = countryController;
    }

    public Service findById(String id){
        Optional<Service> response = repository.findById(id);
        if ( response.isPresent() ){
            return response.get();
        }
        throw new ServiceNotFoundException("The Service with id [" + id + "] was not found");
    }

    public List<Service> findByProviderId(String id){
        return repository.findAllByProviderId(id);
    }

    public List<Service> findBySearchString(String string){
        String searchString = ".*" + string.toLowerCase(Locale.ROOT).trim().replace(" ", ".*") + ".*";
        return repository.findAllBySearchStringRegex(searchString);
    }

    public List<Service> findByServiceType(String type){
        return repository.findAllByServiceType(type);
    }

    public Service create(Service data){
        setBasicServiceData(data);
        if ( data.getServiceType().equals("EcologicalExcursion") ){
            data = ecologicalController.create((EcologicalExcursionService) data);
        }
        else if ( data.getServiceType().equals("Food") ){
            data = foodController.create((FoodService) data);
        }
        else if ( data.getServiceType().equals("Transport") ){
            data = transportController.create((TransportService) data);
        }
        else if ( data.getServiceType().equals("Lodging") ){
            data = lodgingController.create((LodgingService) data);
        }
        else{
            throw new IllegalServiceTypeException("The Service [" + data.getServiceType() + "] is not allowed");
        }
        return repository.insert(data);
    }

    public Service update(Service data){
        setBasicServiceData(data);
        if ( data.getServiceType().equals("EcologicalExcursion") ){
            data = ecologicalController.update((EcologicalExcursionService) data);
        }
        else if ( data.getServiceType().equals("Food") ){
            data = foodController.update((FoodService) data);
        }
        else if ( data.getServiceType().equals("Transport") ){
            data = transportController.update((TransportService) data);
        }
        else if ( data.getServiceType().equals("Lodging") ){
            data = lodgingController.update((LodgingService) data);
        }
        else {
            throw new IllegalServiceTypeException("The Service [" + data.getServiceType() + "] is not allowed");
        }
        return repository.save(data);
    }

    public void setBasicServiceData(Service service){
        Location location = service.getLocation();
        CountryInformation countryInformation =  countryController.getCountryInformation(location.getCountry());
        location.setCountryExtraInformation( countryInformation );
        String searchString = service.getServiceType() + ";" + service.getProviderName() + ";" + service.getLocation()
                + ";" + service.getDescription() ;
        searchString = searchString.toLowerCase();
        service.setSearchString(searchString);

    }

    public void delete(@PathVariable("ServiceId") String id){
        repository.deleteById(id);
    }

    public void executeSale(List<SaleDTO> soldServices) {
        List<String> ids = soldServices.stream().map( e-> e.getServiceId() ).collect(Collectors.toList());
        Iterable<Service> services = repository.findAllById(ids);
        for(SaleDTO itPurchase: soldServices){
            boolean found = false;
            for(Service itSale: services){
                if ( itSale.getId().equals(itPurchase.getServiceId()) ){
                    if ( itSale.getQuantityAvailable() < itPurchase.getQuantity() ){
                        throw new NotQuantityAvailableException("The service with id [" + itSale.getId() + "] has not quantity available");
                    }
                    found = true;
                    break;
                }
            }
            if (!found){
                throw new ServiceNotFoundException("The service with id [" + itPurchase.getServiceId() + "] was not found");
            }
        }
        for(SaleDTO itSale: soldServices){
            for(Service itService: services){
                if ( itService.getId().equals(itSale.getServiceId()) ){
                    itService.setQuantityAvailable( itService.getQuantityAvailable() - itSale.getQuantity() );
                    break;
                }
            }
        }
        repository.saveAll(services);
    }
}
