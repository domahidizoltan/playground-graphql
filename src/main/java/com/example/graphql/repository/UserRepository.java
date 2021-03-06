package com.example.graphql.repository;

import com.example.graphql.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class UserRepository {

    private static final String USERS_URL = "http://localhost:8001/users";
    private ObjectMapper objectMapper;

    public UserRepository(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<User> getUsers() {
        var userListType = new TypeReference<List<User>>() {};
        try {
            return objectMapper.readValue(new URL(USERS_URL), userListType);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<User> getUsers(List<Integer> ids) {
        var query = RepoHelper.idsToQuery(ids);
        var results = new ArrayList<User>();
        try {
            var resultArray = objectMapper.readValue(new URL(USERS_URL + "?" + query), User[].class);
            results.addAll(List.of(resultArray));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    public Optional<User> getUser(Integer id) {
        try {
            var user = objectMapper.readValue(new URL(USERS_URL + "/" + id), User.class);
            return Optional.of(user);
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
