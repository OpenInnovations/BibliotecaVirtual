package com.openinnovations.searchservice.repository;

import com.openinnovations.searchservice.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface IBook extends MongoRepository<Book, String> {

    List<Book> findAllByKeywordsContains(List<String> keywords);

    List<Book> findAllByTitleContains(String title);

    List<Book> findAllByAuthorContains(String author);

    List<Book> findAllByCategoryContains(String category);

    List<Book> findAllByKeywordsContainsOrTitleContainsOrAuthorContainsOrCategoryContains(List<String> keywords, String title, String author, String category);

//    List<Book> findAllByTitleInOrAuthorInOrCategoryInOrKeywordsContains(List<String> title, List<String> author, List<String> category, List<String> keywords);
}
