package com.example.graphql.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@NoArgsConstructor
public class Secret {
    private String secret1;
    private String secret2;

    @JsonIgnore
    private Integer id;
    public Secret(Integer id){
        this.id = id;
    }
}
