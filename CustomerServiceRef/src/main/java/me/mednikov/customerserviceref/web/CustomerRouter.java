package me.mednikov.customerserviceref.web;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

//@Configuration
class CustomerRouter {
    
//    @Bean
    RouterFunction<ServerResponse> customerEndpoint(CustomerHandler handler){
        return RouterFunctions
                .route(POST("/customers").and(accept(MediaType.APPLICATION_JSON)),
                        handler::postCustomer)
                .andRoute(GET("/customers").and(accept(MediaType.APPLICATION_JSON)),
                        handler::getAllCustomers)
                .andRoute(GET("/customer/{id}").and(accept(MediaType.APPLICATION_JSON)),
                        handler::getCustomerById)
                .andRoute(DELETE("/customer/{id}").and(accept(MediaType.APPLICATION_JSON)),
                        handler::deleteCustomer)
                .andRoute(PUT("/customer/{id}").and(accept(MediaType.APPLICATION_JSON)),
                        handler::putCustomer);
                
    }
    
//    @Bean
    CorsWebFilter corsWebFilter(){
        CorsConfiguration configuration = new CorsConfiguration();
        
//        configuration.applyPermitDefaultValues();
        
        configuration.setAllowedOrigins(List.of("http://localhost:8080"));
        configuration.setAllowedMethods(List.of("POST", "GET", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("X-USER-ID"));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return new CorsWebFilter(source);
    }
    
}
