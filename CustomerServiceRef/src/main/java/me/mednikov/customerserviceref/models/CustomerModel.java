package me.mednikov.customerserviceref.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Value
@Document(collection = "customers")
public class CustomerModel {
    
    @Id String customerId;
    String companyName;
    String companyEmail;
    String taxId;
    AddressModel billingAddress;
    AddressModel shippingAddress;

    @JsonCreator
    public CustomerModel(
            @JsonProperty("customerId") String customerId, 
            @JsonProperty("companyName") String companyName, 
            @JsonProperty("companyEmail") String companyEmail, 
            @JsonProperty("taxId") String taxId, 
            @JsonProperty("billingAddress") AddressModel billingAddress, 
            @JsonProperty("shippingAddress") AddressModel shippingAddress) {
        this.customerId = customerId;
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.taxId = taxId;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }
       
}
