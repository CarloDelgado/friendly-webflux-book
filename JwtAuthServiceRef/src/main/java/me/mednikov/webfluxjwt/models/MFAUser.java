package me.mednikov.webfluxjwt.models;

import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Value
@Document(collection = "mfa_users")
public class MFAUser {
    
    @Id String userId;
    String email;
    String hash;
    String salt;
    String secretKey;
}
