package com.openinnovations.fileprocessing.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Set;

@Data
public class Book {
    private String isbn;
    private HashMap<String, String> info;
    private SentimentResult sentimentResult;
    private Integer numberPages;
    private Long size;
    private Set<String> keywords;
}
