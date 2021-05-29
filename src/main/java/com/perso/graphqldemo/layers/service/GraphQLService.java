package com.perso.graphqldemo.layers.service;

import com.perso.graphqldemo.layers.service.datafetcher.AllBooksDataFetcher;
import com.perso.graphqldemo.layers.service.datafetcher.BookDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class GraphQLService {

    @Value("classpath:books.graphql")
    private Resource resource;

    private GraphQL graphQL;


    private final AllBooksDataFetcher allBooksDataFetcher;
    private final BookDataFetcher bookDataFetcher;

    public GraphQLService(AllBooksDataFetcher allBooksDataFetcher, BookDataFetcher bookDataFetcher) {
        this.allBooksDataFetcher = allBooksDataFetcher;
        this.bookDataFetcher = bookDataFetcher;
    }


    @PostConstruct
    private void loadSchema() throws IOException {

        File schemaFile = resource.getFile();

        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query",
                        typeWiring -> typeWiring.dataFetcher("allBooks", allBooksDataFetcher)
                                .dataFetcher("book", bookDataFetcher))
                .build();
    }

    public Resource getSchema() {
        return resource;
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}
