package com.openinnovations.fileprocessing.model;

import lombok.Data;

@Data
public class SentimentClassification {
    private Double veryPositive;
    private Double positive;
    private Double neutral;
    private Double negative;
    private Double veryNegative;

}
