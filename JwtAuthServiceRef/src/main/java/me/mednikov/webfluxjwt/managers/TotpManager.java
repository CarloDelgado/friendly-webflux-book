package me.mednikov.webfluxjwt.managers;

public interface TotpManager {

    String generateSecret ();

    boolean validateCode (String code, String secret);

}
