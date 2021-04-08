package me.mednikov.customerserviceref.repositories;

import me.mednikov.customerserviceref.models.CustomerModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CustomerRepository extends ReactiveMongoRepository<CustomerModel, String> {
    
}
