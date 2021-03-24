package com.iuriimednikov.customerserviceref.repositories;

import com.iuriimednikov.customerserviceref.models.CustomerModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CustomerRepository extends ReactiveMongoRepository<CustomerModel, String> {
    
}
