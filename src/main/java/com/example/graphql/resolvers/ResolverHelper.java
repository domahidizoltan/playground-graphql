package com.example.graphql.resolvers;

import graphql.schema.DataFetchingEnvironment;
import graphql.servlet.GraphQLContext;
import org.dataloader.DataLoader;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

final public class ResolverHelper {

    private ResolverHelper() {}

    public static <T> DataLoader<Integer, T> getLoader(DataFetchingEnvironment dfe, String name) {
        return ((GraphQLContext) dfe.getContext())
            .getDataLoaderRegistry().get()
            .getDataLoader(name);
    }

    public static <T> CompletableFuture<List<T>> toResultsFuture(List<CompletableFuture<T>> futures) {
        var futureArray = new CompletableFuture[futures.size()];
        var resultsFuture = CompletableFuture.allOf(futures.toArray(futureArray))
            .thenApply(allDone -> futures.stream()
                .map(future -> future.join())
                .collect(Collectors.toList())
            );

        return resultsFuture;
    }
}
