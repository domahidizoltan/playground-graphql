package com.example.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.graphql.model.Book;
import com.example.graphql.model.Secret;
import com.example.graphql.model.User;
import com.example.graphql.repository.BookRepository;
import com.example.graphql.repository.SecretRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class UserResolver implements GraphQLResolver<User> {

    private SecretRepository secretRepository;
    private BookRepository bookRepository;

    public UserResolver(SecretRepository secretRepository, BookRepository bookRepository) {
        this.secretRepository = secretRepository;
        this.bookRepository = bookRepository;
    }

    public Secret getSecret(User user) {
        var secret = user.getSecret().getId();
        log.info("Resolving secret " + secret);
        return secretRepository.getSecret(secret).orElse(null);
    }

    public List<Book> getBooks(User user) {
        return user.getBooks().stream()
            .peek(book -> log.info("Resolving book " + book.getId()))
            .map(book -> bookRepository.getBook(book.getId()).orElse(null))
            .collect(Collectors.toList());
    }

}
