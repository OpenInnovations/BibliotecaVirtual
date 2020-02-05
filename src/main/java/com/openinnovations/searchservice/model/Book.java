package com.openinnovations.searchservice.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.google.cloud.storage.BlobId;


@Document(collection = "Books")
@Data
public class Book {

    @Id
    public String id;
    private String title;
    private BlobId blobId;
    private String author;
    private String description;
    private String url;

    private String isbn;
    private HashMap<String, String> info;
    private Integer numberPages;
    private Long size;
    private Set<String> keywords;
    private Object sentiment;

    private Date uploadedDate;
    private Boolean hidden;
    private String category;
}
