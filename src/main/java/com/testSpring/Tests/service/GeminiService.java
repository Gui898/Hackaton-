package com.testSpring.Tests.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GeminiService {

    private static final String API_KEY = "AIzaSyCauJcpq47pg8rD3X1FRWIzZ3MWkkmUCxc";
    private static final String GEMINI_URL =
            "https://generativelanguage.googleapis.com/v1beta/models/{model}:generateContent?key=" + API_KEY;

    public String generate(String model, String prompt) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> body = new HashMap<>();
        body.put("contents", new Object[]{
                Map.of("parts", new Object[]{Map.of("text", prompt)})
        });

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> response = restTemplate.exchange(
                GEMINI_URL,
                HttpMethod.POST,
                new HttpEntity<>(body, headers),
                String.class,
                model
        );

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        return root.path("candidates").get(0).path("content").path("parts").get(0).path("text").asText();
    }
}
