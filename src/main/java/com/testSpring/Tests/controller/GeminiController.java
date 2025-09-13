package com.testSpring.Tests.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testSpring.Tests.service.GeminiService;

@CrossOrigin(origins = "*") // libera para qualquer frontend
@RestController
@RequestMapping("/api/gemini")
public class GeminiController {

    private static final String API_KEY = "AIzaSyCauJcpq47pg8rD3X1FRWIzZ3MWkkmUCxc";
    private static final String GEMINI_URL =
            "https://generativelanguage.googleapis.com/v1beta/models/{model}:generateContent?key=" + API_KEY;
   
    @Autowired
    private GeminiService geminiService;

    @PostMapping("/generate")
    public String generate(@RequestBody PromptRequest request) throws Exception {
        return geminiService.generate(request.getModel(), request.getPrompt());
    }

    public static class PromptRequest {
        private String model;
        private String prompt;
        public String getModel() { return model; }
        public void setModel(String model) { this.model = model; }
        public String getPrompt() { return prompt; }
        public void setPrompt(String prompt) { this.prompt = prompt; }
    }
}
