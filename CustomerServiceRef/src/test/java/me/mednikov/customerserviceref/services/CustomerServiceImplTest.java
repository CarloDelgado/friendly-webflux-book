package me.mednikov.customerserviceref.services;

import me.mednikov.customerserviceref.models.CustomerModel;
import me.mednikov.customerserviceref.repositories.CustomerRepository;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    
    @Mock
    private CustomerRepository repository;
    
    @InjectMocks
    private CustomerServiceImpl service;

    @Test
    void createCustomerTest(){
        final CustomerModel customer = new CustomerModel("customerId", "Acme s.r.o", "jana.dvorakova@acme.cz", "CZ1234567", null, null);
        Mockito.when(repository.save(customer)).thenReturn(Mono.just(customer));
        StepVerifier.create(service.createCustomer(customer))
                .assertNext(result -> Assertions.assertThat(result).isEqualTo(customer))
                .verifyComplete();
    }
    
    @Test
    void findCustomerByIdSuccessTest(){
        final CustomerModel customer = new CustomerModel("customerId", "Acme s.r.o", "jana.dvorakova@acme.cz", "CZ1234567", null, null);
        final String customerId = customer.getCustomerId();
        Mockito.when(repository.findById(customerId)).thenReturn(Mono.just(customer));
        StepVerifier.create(service.findCustomerById(customerId))
                .assertNext(result -> Assertions.assertThat(result).isEqualTo(customer))
                .verifyComplete();
    }
    
    @Test
    void findCustomerByIdNotFoundTest(){
        final String customerId = "customerId";
        Mockito.when(repository.findById(customerId)).thenReturn(Mono.empty());
        StepVerifier.create(service.findCustomerById(customerId))
                .verifyComplete();
    }
    
    @Test
    void findAllCustomersTest(){
        List<CustomerModel> customers = List.of(
                new CustomerModel("customerId1", "Acme s.r.o", "jana.dvorakova@acme.cz", "CZ1234567", null, null),
                new CustomerModel("customerId2", "Acme s.r.o", "jana.dvorakova@acme.cz", "CZ1234567", null, null),
                new CustomerModel("customerId3", "Acme s.r.o", "jana.dvorakova@acme.cz", "CZ1234567", null, null),
                new CustomerModel("customerId4", "Acme s.r.o", "jana.dvorakova@acme.cz", "CZ1234567", null, null),
                new CustomerModel("customerId5", "Acme s.r.o", "jana.dvorakova@acme.cz", "CZ1234567", null, null)
        );
        
        Mockito.when(repository.findAll()).thenReturn(Flux.fromIterable(customers));
        StepVerifier.create(service.findAllCustomers()).expectNextCount(5).verifyComplete();
    }
}
