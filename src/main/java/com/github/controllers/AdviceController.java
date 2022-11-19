package com.github.controllers;

import com.github.exceptions.JsonException;
import com.github.exceptions.RestTemplateErrorException;
import com.github.jsons.Error;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(RestTemplateErrorException.class)
    public ResponseEntity<Error> handlerRestTemplateErrorException(final RestTemplateErrorException exception) {
        return new ResponseEntity(exception.getError(), exception.getHttpStatus());
    }

    @ExceptionHandler(JsonException.class)
    public ResponseEntity<Error> handlerJsonException(final JsonException exception) {
        final var response = Error.from(exception.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}
