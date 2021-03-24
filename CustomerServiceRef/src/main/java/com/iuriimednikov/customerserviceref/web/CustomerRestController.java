package com.iuriimednikov.customerserviceref.web;

import com.iuriimednikov.customerserviceref.models.CustomerModel;
import com.iuriimednikov.customerserviceref.services.CustomerService;
import lombok.Value;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Value
@RestController
@RequestMapping(
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class CustomerRestController {
    
    CustomerService customerService;
    
    @GetMapping("/customer/{id}")
    Publisher<ResponseEntity<CustomerModel>> getCustomerById (@PathVariable("id") String id){
        return customerService.findCustomerById(id)
                .map(customer -> ResponseEntity.ok(customer))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }
    
    @PostMapping("/customers")
    Publisher<ResponseEntity<CustomerModel>> postCustomer (@RequestBody CustomerModel customer) {
        return customerService.createCustomer(customer).map(result -> ResponseEntity.ok(result));
    }
    
    @PutMapping("/customer/{id}")
    Publisher<ResponseEntity<CustomerModel>> putCustomer (@RequestBody CustomerModel customer) {
        return customerService.createCustomer(customer).map(result -> ResponseEntity.ok(result));
    }
    
    @GetMapping("/customers")
    Publisher<CustomerModel> getAllCustomers(){
        return customerService.findAllCustomers();
    }
    
    @DeleteMapping("/customer/{id}")
    Publisher<Void> deleteCustomer(@PathVariable("id") String id){
        return customerService.removeCustomer(id);
    }
}
