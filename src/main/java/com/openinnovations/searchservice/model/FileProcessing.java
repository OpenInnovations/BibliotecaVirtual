package com.openinnovations.searchservice.model;

import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.HashMap;

@Data
public class FileProcessing {

    // private String isb;
    // private HashMap<String, String> info;
    // private Integer numberPages;
    // private Long size;
    // private Set<String> keywords;

    private List<String> keywords;
    private Sentiment sentiment;


}
