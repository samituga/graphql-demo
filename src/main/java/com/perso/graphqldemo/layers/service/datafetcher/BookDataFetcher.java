package com.perso.graphqldemo.layers.service.datafetcher;

import com.perso.graphqldemo.layers.repository.BookRepository;
import com.perso.graphqldemo.model.Book;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

@Component
public class BookDataFetcher implements DataFetcher<Book> {

    private final BookRepository repository;

    public BookDataFetcher(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book get(DataFetchingEnvironment environment) {
        String isn = environment.getArgument("id");
        return repository.getById(isn);
    }
}
