package com.openinnovations.searchservice.service;

import com.openinnovations.searchservice.model.FileProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FileProcessingService {

    @Autowired
    private RestTemplate restTemplate;

    public FileProcessing procesar(String filename) {
        return restTemplate.postForEntity(
                "http://35.237.68.44:3001?storageFileName="+filename, null,FileProcessing.class).getBody();
    }

}
