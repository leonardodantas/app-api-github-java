package com.github.exceptions;

import com.github.jsons.Error;
import org.springframework.http.HttpStatus;

public class RestTemplateErrorException extends RuntimeException {

    private final Error error;
    private final HttpStatus httpStatus;

    public RestTemplateErrorException(final Error error, final HttpStatus httpStatus) {
        this.error = error;
        this.httpStatus = httpStatus;
    }

    public Error getError() {
        return error;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
