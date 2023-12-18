package com.kleague.kleaguefinder.response.exception;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ErrorResponse {

    private String statusCode;
    private String message;
    private List<ValidationTuple> validationTuples = new ArrayList<>();

    @Builder
    public ErrorResponse(String statusCode, String message, List<ValidationTuple> validationTuples) {
        this.statusCode = statusCode;
        this.message = message;
        this.validationTuples = validationTuples;
    }
}
