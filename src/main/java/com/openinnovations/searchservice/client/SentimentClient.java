package com.openinnovations.searchservice.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class SentimentClient {
    public static Object analizar(MultiValueMap<String, String> payloads) {
        final String uri = "https://apis.paralleldots.com/v4/emotion";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(payloads, headers);
        return restTemplate.postForObject(uri, request, Object.class);
    }
}
