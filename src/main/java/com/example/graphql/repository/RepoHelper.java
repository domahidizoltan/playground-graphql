package com.example.graphql.repository;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
final public class RepoHelper {

    private RepoHelper() {}

    public static String idsToQuery(List<Integer> ids) {
        var query = ids.stream()
            .map(id -> "id=" + id)
            .collect(Collectors.joining("&"));
        log.info("query: " + query);
        return query;
    }
}
