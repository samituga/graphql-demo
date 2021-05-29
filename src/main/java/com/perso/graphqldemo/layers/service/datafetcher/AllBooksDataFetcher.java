package com.perso.graphqldemo.layers.service.datafetcher;

import com.perso.graphqldemo.layers.repository.BookRepository;
import com.perso.graphqldemo.model.Book;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllBooksDataFetcher  implements DataFetcher<List<Book>> {

    private final BookRepository repository;

    public AllBooksDataFetcher(BookRepository bookRepository) {
        this.repository = bookRepository;
    }

    @Override
    public List<Book> get(DataFetchingEnvironment environment) {
        return repository.findAll();
    }
}
