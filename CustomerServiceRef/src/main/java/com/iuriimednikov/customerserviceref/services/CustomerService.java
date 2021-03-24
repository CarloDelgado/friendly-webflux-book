package com.iuriimednikov.customerserviceref.services;

import com.iuriimednikov.customerserviceref.models.CustomerModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    
    Mono<CustomerModel> createCustomer (CustomerModel customer);
    
    Mono<CustomerModel> updateCustomer (CustomerModel customer);
    
    Mono<Void> removeCustomer (String customerId);
    
    Mono<CustomerModel> findCustomerById (String customerId);
    
    Flux<CustomerModel> findAllCustomers ();
}
