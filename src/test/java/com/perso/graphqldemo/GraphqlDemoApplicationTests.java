package com.perso.graphqldemo;

import com.perso.graphqldemo.layers.repository.BookRepository;
import com.perso.graphqldemo.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootTest
class GraphqlDemoApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    BookRepository bookRepository;

    @Test
    public void loadthings() {
        Stream.of(
                new Book(UUID.randomUUID().toString(), "Book Name", "Publisher", new String[]{"Someone", "oi"}, LocalDateTime.now()),
                new Book(UUID.randomUUID().toString(), "Book Name1", "Publisher1", new String[]{"Someone1"}, LocalDateTime.now()),
                new Book(UUID.randomUUID().toString(), "Book Name2", "Publisher2", new String[]{"Someone2", "oi2"}, LocalDateTime.now()),
                new Book(UUID.randomUUID().toString(), "Book Name3", "Publisher3", new String[]{"Someone3"}, LocalDateTime.now())
        ).forEach(e -> bookRepository.save(e));
    }
}
