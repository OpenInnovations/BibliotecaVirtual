package com.openinnovations.searchservice.model;

import java.util.Date;
import java.util.List;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Books")
@Data
public class Book {

    @Id
    private long _id;
    private String title;
    private String filename;
    private String author;
    private String url;
    List<String> keywords;
    Date uploadedDate = new Date();
    boolean hidden;
    String category;
}
