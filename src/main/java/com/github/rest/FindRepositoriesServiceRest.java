package com.github.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.converters.ConverterJsonTo;
import com.github.exceptions.RestTemplateErrorException;
import com.github.jsons.Error;
import com.github.jsons.Root;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

@Service
public class FindRepositoriesServiceRest {

    private final RestTemplate restTemplate;
    private final String url;


    public FindRepositoriesServiceRest(final RestTemplate restTemplate,
                                       @Value("${url.services.api-github}") final String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    public List<Root> execute(final String user) {
        final var params = new HashMap<String, String>();
        params.put("user", user);

        final var uri = fromHttpUrl(url)
                .buildAndExpand(params);

        try {
            final var response = restTemplate.getForEntity(uri.toUriString(), String.class);
            return ConverterJsonTo.list(response.getBody(), new TypeReference<>() {
            });
        } catch (final HttpClientErrorException e) {
            final var error = ConverterJsonTo.object(e.getResponseBodyAsString(), Error.class);
            throw new RestTemplateErrorException(error, HttpStatus.valueOf(e.getRawStatusCode()));
        }
    }

}
