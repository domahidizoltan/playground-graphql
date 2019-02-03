package com.example.graphql;

import com.example.graphql.repository.BookRepository;
import com.example.graphql.repository.SecretRepository;
import com.example.graphql.repository.UserRepository;
import com.example.graphql.resolvers.CommentResolver;
import com.example.graphql.resolvers.Query;
import com.example.graphql.resolvers.UserResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlApplication.class, args);
	}

	@Bean
	public UserResolver userResolver(SecretRepository secretRepository, BookRepository bookRepository) {
		return new UserResolver(secretRepository, bookRepository);
	}

	@Bean
	public CommentResolver commentResolver() {
		return new CommentResolver();
	}

	@Bean
	public Query query(UserRepository userRepository) {
		return new Query(userRepository);
	}
}

