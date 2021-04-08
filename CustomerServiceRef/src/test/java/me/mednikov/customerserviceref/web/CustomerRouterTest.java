package me.mednikov.customerserviceref.web;

import me.mednikov.customerserviceref.web.CustomerHandler;
import me.mednikov.customerserviceref.web.CustomerRouter;
import me.mednikov.customerserviceref.models.CustomerModel;
import me.mednikov.customerserviceref.services.CustomerService;
import java.net.URI;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CustomerHandler.class, CustomerRouter.class})
@WebFluxTest
class CustomerRouterTest {
    
    @Autowired private ApplicationContext context;
    @MockBean private CustomerService service;
    
    private WebTestClient client;
    
    @BeforeEach
    void setup(){
        client = WebTestClient.bindToApplicationContext(context).build();
    }
    
    @Test
    void postCustomerTest(){
        final CustomerModel customer = createMockCustomer("customerId");
        Mockito.when(service.createCustomer(customer)).thenReturn(Mono.just(customer));
        client.post()
                .uri(URI.create("/customers"))
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(customer), CustomerModel.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CustomerModel.class)
                .value(result -> Assertions.assertThat(result).isEqualTo(customer));
    }
    
    @Test
    void getCustomerByIdSuccessTest(){
        final String customerId = "customerId";
        final CustomerModel customer = createMockCustomer(customerId);
        Mockito.when(service.findCustomerById(customerId)).thenReturn(Mono.just(customer));
        client.get()
                .uri(URI.create("/customer/customerId"))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CustomerModel.class)
                .value(result -> Assertions.assertThat(result).isEqualTo(customer));
    }
    
    @Test
    void getCustomerByIdNotFoundTest(){
        final String customerId = "customerId";
        Mockito.when(service.findCustomerById(customerId)).thenReturn(Mono.empty());
        client.get()
                .uri(URI.create("/customer/customerId"))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }
    
    @Test
    void getAllCustomersTest(){
        final List<CustomerModel> customers = List.of(
                createMockCustomer("customerId1"),
                createMockCustomer("customerId2"),
                createMockCustomer("customerId3"),
                createMockCustomer("customerId4"),
                createMockCustomer("customerId5")
        );
        
        Mockito.when(service.findAllCustomers()).thenReturn(Flux.fromIterable(customers));
        
        client.get()
                .uri(URI.create("/customers"))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(CustomerModel.class)
                .hasSize(customers.size())
                .isEqualTo(customers);
    }
    
    private CustomerModel createMockCustomer(String id){
        CustomerModel customer = new CustomerModel(id, "Acme s.r.o", "jana.dvorakova@acme.cz", "CZ1234567", null, null);
        return customer;
    }
}
