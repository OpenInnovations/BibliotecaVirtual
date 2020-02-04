package com.openinnovations.fileprocessinglite.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Set;

@Data
public class Book {
    private String isbn;
    private HashMap<String, String> info;
    private Integer numberPages;
    private Long size;
    private Set<String> keywords;
}
