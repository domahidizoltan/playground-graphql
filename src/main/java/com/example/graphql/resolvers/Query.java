package com.example.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.graphql.model.User;
import com.example.graphql.repository.UserRepository;

import java.util.List;

public class Query implements GraphQLQueryResolver {

    private UserRepository userRepository;

    public Query(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> users() {
        return userRepository.getUsers();
    }
}