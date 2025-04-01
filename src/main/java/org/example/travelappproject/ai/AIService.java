package org.example.travelappproject.ai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AIService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    @Value("${gemini.api.model-name}")
    private String modelName;

    @Value("${gemini.api.url}")
    private String url;


    public String getResponse(String question) {
        String apiUrl = url + modelName + ":generateContent?key=" + apiKey;
        try {
            String requestBody = "{\"contents\": [{\"parts\": [{\"text\": \"" + question + "\"}]}]}";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, entity, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                String responseBody = response.getBody();
                return getText(responseBody);
            } else {
                System.err.println("Error: " + response.getStatusCode());
                System.err.println("Response Body: " + response.getBody());
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    private String getText(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(json);
        JsonNode candidatesNode = rootNode.path("candidates");
        if (candidatesNode.isArray() && !candidatesNode.isEmpty()) {
            JsonNode firstCandidate = candidatesNode.get(0);
            JsonNode contentNode = firstCandidate.path("content");
            if (contentNode.isObject()) {
                JsonNode partsNode = contentNode.path("parts");
                if (partsNode.isArray() && !partsNode.isEmpty()) {
                    JsonNode firstPart = partsNode.get(0);
                    JsonNode textNode = firstPart.path("text");
                    if (textNode.isTextual()) {
                        return textNode.asText();
                    }
                }
            }
        }
        return null;
    }
}

