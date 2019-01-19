package com.example.graphql.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Book {
    private String title;
    private List<Comment> comments;

    @JsonIgnore
    private Integer id;
    public Book(Integer id){
        this.id = id;
    }
}
