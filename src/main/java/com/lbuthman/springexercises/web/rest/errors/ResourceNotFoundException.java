package com.lbuthman.springexercises.web.rest.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private final String description;
    private final String entityName;

    public ResourceNotFoundException(String description, String entityName) {
        this.description = description;
        this.entityName = entityName;
    }

    public String getDescription() {
        return description;
    }

    public String getEntityName() {
        return entityName;
    }
}
