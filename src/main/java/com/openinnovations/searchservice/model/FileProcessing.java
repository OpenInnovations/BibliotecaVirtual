package com.openinnovations.searchservice.model;

import lombok.Data;

import java.util.Set;
import java.util.HashMap;

@Data
public class FileProcessing {

    private String isbn;
    private HashMap<String, String> info;
    private Integer numberPages;
    private Long size;
    private Set<String> keywords;
    private Object sentiment;

}
