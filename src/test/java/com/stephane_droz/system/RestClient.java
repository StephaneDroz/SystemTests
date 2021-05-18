package com.stephane_droz.system;

import jakarta.json.stream.JsonGenerator;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import org.glassfish.jersey.jsonp.JsonProcessingFeature;

public class RestClient {

    public RestClient() {
    }

    public Client getConfiguredClient() {
        return ClientBuilder.newBuilder()
                .register(JsonProcessingFeature.class)
                .property(JsonGenerator.PRETTY_PRINTING, true)
                .build();
    }

    public WebTarget getWebTarget(String URL){
        return getConfiguredClient().target(URL);
    }

    public Client getConfiguredClient(String jwt) {
        return ClientBuilder.newBuilder()
                .register(JsonProcessingFeature.class)
                .property(JsonGenerator.PRETTY_PRINTING, true)
                .register(new JWTAuthenticator(jwt))
                .build();
    }
}
