package com.marketplace.Authentication.repositories;

import com.marketplace.Authentication.models.UserAuthentication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthenticationRepository extends MongoRepository<UserAuthentication, String> {

    Optional<UserAuthentication> findByUserId(String userId);
    Optional<UserAuthentication> findByEmail(String userId);

}
