package com.marketplace.Service.repositories;

import com.marketplace.Service.models.Service;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends MongoRepository<Service, String> {

    public List<Service> findAllByProviderId(String providerId);
    public List<Service> findAllByServiceType(String serviceType);
    public List<Service> findAllBySearchStringRegex(String regex);

}
