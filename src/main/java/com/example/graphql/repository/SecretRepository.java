package com.example.graphql.repository;

import com.example.graphql.model.Secret;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

@Slf4j
public class SecretRepository {

    private static final String SECRETS_URL = "http://localhost:8003/secrets/";
    private ObjectMapper objectMapper;

    public SecretRepository(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Optional<Secret> getSecret(Integer id) {
        try {
            var secret = objectMapper.readValue(new URL(SECRETS_URL + id), Secret.class);
            return Optional.of(secret);
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
