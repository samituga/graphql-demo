package com.perso.graphqldemo.layers.api;

import com.perso.graphqldemo.layers.service.GraphQLService;
import graphql.ExecutionResult;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/graphQL")
@RestController
public class Schema {

    private final GraphQLService graphQLService;

    public Schema(GraphQLService graphQLService) {
        this.graphQLService = graphQLService;
    }


    @PostMapping
    public ResponseEntity<Object> getSchema(@RequestBody String query) {
        System.out.println(query);
        ExecutionResult result = graphQLService.getGraphQL().execute(query);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
}
