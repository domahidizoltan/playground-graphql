package com.example.graphql;

import com.example.graphql.model.Book;
import com.example.graphql.model.Secret;
import com.example.graphql.model.User;
import com.example.graphql.repository.BookRepository;
import com.example.graphql.repository.SecretRepository;
import com.example.graphql.repository.UserRepository;
import graphql.servlet.GraphQLContext;
import graphql.servlet.GraphQLContextBuilder;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderOptions;
import org.dataloader.DataLoaderRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.HandshakeRequest;
import java.util.concurrent.CompletableFuture;

@Component
public class ContextBuilder implements GraphQLContextBuilder {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecretRepository secretRepository;

    @Override
    public GraphQLContext build(HttpServletRequest httpServletRequest) {
        return withRegistry(new GraphQLContext(httpServletRequest));
    }

    @Override
    public GraphQLContext build(HandshakeRequest handshakeRequest) {
        return withRegistry(new GraphQLContext(handshakeRequest));
    }

    @Override
    public GraphQLContext build() {
        return withRegistry(new GraphQLContext());
    }

    private GraphQLContext withRegistry(GraphQLContext context) {
        context.setDataLoaderRegistry(buildDataLoaderRegistry());
        return context;
    }

    private DataLoaderRegistry buildDataLoaderRegistry() {
        var dataLoaderRegistry = new DataLoaderRegistry();
        dataLoaderRegistry.register("bookDataLoader", makeBookDataLoader());
        dataLoaderRegistry.register("userDataLoader", makeUserDataLoader());
        dataLoaderRegistry.register("secretDataLoader", makeSecretDataLoader());
        return dataLoaderRegistry;
    }

    private DataLoader<Integer, Book> makeBookDataLoader() {
        return new DataLoader<>(ids ->
                CompletableFuture.supplyAsync(() -> bookRepository.getBooks(ids))
        );
    }

    private DataLoader<Integer, User> makeUserDataLoader() {
        return DataLoader.newDataLoader(ids ->
            CompletableFuture.supplyAsync(() -> userRepository.getUsers(ids)),
            new DataLoaderOptions().setCachingEnabled(false)
        );
    }

    private DataLoader<Integer, Secret> makeSecretDataLoader() {
        return DataLoader.newDataLoader(ids ->
                CompletableFuture.supplyAsync(() -> secretRepository.getSecrets(ids))
        );
    }
}
