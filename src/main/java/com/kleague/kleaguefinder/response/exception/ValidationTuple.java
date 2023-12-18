package com.kleague.kleaguefinder.response.exception;

import lombok.Getter;

@Getter
public class ValidationTuple {

    private Long id;
    private String entityName;

    public ValidationTuple(Long id, String entityName) {
        this.id = id;
        this.entityName = entityName;
    }
}
