package com.lbuthman.springexercises.web.rest.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    private final String description;
    private final String entityName;

    public BadRequestException(String description, String entityName) {
        this.description = description;
        this.entityName = entityName;
    }
}
