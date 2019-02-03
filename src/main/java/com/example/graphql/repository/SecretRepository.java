package com.example.graphql.repository;

import com.example.graphql.model.Secret;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class SecretRepository {

    private static final String SECRETS_URL = "http://localhost:8003/secrets";
    private ObjectMapper objectMapper;

    public SecretRepository(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Secret> getSecrets(List<Integer> ids) {
        var query = RepoHelper.idsToQuery(ids);
        var results = new ArrayList<Secret>();
        try {
            var resultArray = objectMapper.readValue(new URL(SECRETS_URL + "?" + query), Secret[].class);
            results.addAll(List.of(resultArray));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }
}
