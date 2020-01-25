package com.openinnovations.searchservice.repository;

import com.openinnovations.searchservice.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBook extends MongoRepository<Book, Long> {

    List<Book> findAllByKeywordsContains(String category);

    List<Book> findAllByTitleContains(String title);

    List<Book> findAllByAuthorContains(String author);

    List<Book> findAllByCategoryContains(String category);
}
