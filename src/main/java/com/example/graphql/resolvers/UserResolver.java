package com.example.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.graphql.model.Book;
import com.example.graphql.model.Secret;
import com.example.graphql.model.User;
import com.example.graphql.repository.BookRepository;
import com.example.graphql.repository.SecretRepository;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
public class UserResolver implements GraphQLResolver<User> {

    private SecretRepository secretRepository;
    private BookRepository bookRepository;

    public UserResolver(SecretRepository secretRepository, BookRepository bookRepository) {
        this.secretRepository = secretRepository;
        this.bookRepository = bookRepository;
    }

    public CompletableFuture<Secret> getSecret(User user, DataFetchingEnvironment dfe) {
        var dataloader = ResolverHelper.<Secret>getLoader(dfe, "secretDataLoader");

        if (user.getSecret() != null) {
            log.info("Resolving secret " + user.getSecret().getId());
            return dataloader.load(user.getSecret().getId());
        } else {
            return CompletableFuture.completedFuture(null);
        }
    }

    public CompletableFuture<List<Book>> getBooks(User user, DataFetchingEnvironment dfe) {
        var dataloader = ResolverHelper.<Book>getLoader(dfe, "bookDataLoader");

        var futures = user.getBooks().stream()
            .peek(book -> log.info("Resolving book " + book.getId()))
            .map(book -> dataloader.load(book.getId()))
            .collect(Collectors.toList());

        return ResolverHelper.toResultsFuture(futures);
    }

}
