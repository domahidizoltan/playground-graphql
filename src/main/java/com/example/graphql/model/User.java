package com.example.graphql.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private List<Book> books;
    private String email;
    private String location;
    private Secret secret;

    @JsonIgnore
    private Integer id;
    public User(Integer id){
        this.id = id;
    }
}
