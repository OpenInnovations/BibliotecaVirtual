package com.openinnovations.searchservice.model;

import java.util.Date;
import java.util.List;

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
    private List<String> keywords;
    private Date uploadedDate;
    private Boolean hidden;
    private String category;
}
