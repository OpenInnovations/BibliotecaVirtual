package com.openinnovations.fileprocessing.model;

import lombok.Data;



@Data
public class SentimentResult {
    private Integer sentimentScore;
    private String sentimentType;
    private SentimentClassification sentimentClass;
}
