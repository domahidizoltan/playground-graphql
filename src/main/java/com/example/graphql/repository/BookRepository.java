package com.example.graphql.repository;

import com.example.graphql.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class BookRepository {

    private static final String BOOKS_URL = "http://localhost:8002/books";
    private ObjectMapper objectMapper;

    public BookRepository(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Book> getBooks(List<Integer> ids) {
        var query = RepoHelper.idsToQuery(ids);
        var results = new ArrayList<Book>();
        try {
            var resultArray = objectMapper.readValue(new URL(BOOKS_URL + "?" + query), Book[].class);
            results.addAll(List.of(resultArray));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }
}
