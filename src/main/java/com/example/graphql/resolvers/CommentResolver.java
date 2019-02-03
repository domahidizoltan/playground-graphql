package com.example.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.graphql.model.Comment;
import com.example.graphql.model.User;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class CommentResolver implements GraphQLResolver<Comment> {

    public CompletableFuture<User> getUser(Comment comment, DataFetchingEnvironment dfe) {
        var dataloader = ResolverHelper.<User>getLoader(dfe, "userDataLoader");

        log.info("Resolving user " + comment.getUser().getId());
        return dataloader.load(comment.getUser().getId());
    }
}
