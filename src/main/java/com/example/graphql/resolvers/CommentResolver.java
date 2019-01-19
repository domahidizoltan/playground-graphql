package com.example.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.graphql.model.Comment;
import com.example.graphql.model.User;
import com.example.graphql.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommentResolver implements GraphQLResolver<Comment> {


    private UserRepository userRepository;

    public CommentResolver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Comment comment) {
        var id = comment.getUser().getId();
        log.info("Resolving user " + id);
        return userRepository.getUser(id).orElse(null);
    }

}
