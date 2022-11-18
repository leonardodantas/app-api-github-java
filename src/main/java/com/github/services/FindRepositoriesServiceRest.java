package com.github.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.models.Root;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

@Service
public class FindRepositoriesServiceRest {

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;


    public FindRepositoriesServiceRest(final ObjectMapper objectMapper, final RestTemplate restTemplate) {
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
    }

    public List<Root> execute(final String user) {
        final var params = new HashMap<String, String>();
        params.put("user", user);

        final var uri = fromHttpUrl("https://api.github.com/users/{user}/repos")
                .buildAndExpand(params);

        final var response = restTemplate.getForEntity(uri.toUriString(), String.class);
        return getList(response);
    }

    private List<Root> getList(final ResponseEntity<String> response) {
        try {
            return objectMapper.readValue(response.getBody(), new TypeReference<>() {
            });
        } catch (final JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
