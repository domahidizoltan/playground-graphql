package com.example.graphql.repository;

import com.example.graphql.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

@Slf4j
public class BookRepository {

    private static final String BOOKS_URL = "http://localhost:8002/books/";
    private ObjectMapper objectMapper;

    public BookRepository(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Optional<Book> getBook(Integer id) {
        try {
            var book = objectMapper.readValue(new URL(BOOKS_URL + id), Book.class);
            return Optional.of(book);
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
