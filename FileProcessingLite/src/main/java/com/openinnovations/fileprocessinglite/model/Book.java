package com.openinnovations.fileprocessinglite.model;


import java.util.HashMap;
import java.util.Set;

public class Book {
    private String isbn;
    private HashMap<String, String> info;
    private Integer numberPages;
    private Long size;
    private Set<String> keywords;
    private Object sentiment;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public HashMap<String, String> getInfo() {
        return info;
    }

    public void setInfo(HashMap<String, String> info) {
        this.info = info;
    }

    public Integer getNumberPages() {
        return numberPages;
    }

    public void setNumberPages(Integer numberPages) {
        this.numberPages = numberPages;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }

    public Object getSentiment() {
        return sentiment;
    }

    public void setSentiment(Object sentiment) {
        this.sentiment = sentiment;
    }
}
