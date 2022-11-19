package com.github.controllers;

import com.github.exceptions.RestTemplateErrorException;
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
}
