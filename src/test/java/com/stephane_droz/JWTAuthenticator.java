package com.stephane_droz;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MultivaluedMap;

import java.io.IOException;

public class JWTAuthenticator implements ClientRequestFilter {
    private String jwt;

    public JWTAuthenticator(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public void filter(ClientRequestContext clientRequestContext) throws IOException {
        MultivaluedMap<String, Object> headers = clientRequestContext.getHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, jwt);
    }
}
