package com.perso.graphqldemo.layers.repository;

import com.perso.graphqldemo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, String> {


}
