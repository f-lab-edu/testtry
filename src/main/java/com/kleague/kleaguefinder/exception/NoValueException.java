package com.kleague.kleaguefinder.exception;

import com.kleague.kleaguefinder.exception.MainException;

import static com.kleague.kleaguefinder.exception.ErrorCode.*;

public class NoValueException extends MainException {

    private static final String MESSAGE = NoValueCode.getMessage();

    public NoValueException(String type, String field) {
        super(type, MESSAGE, field);
    }

    public NoValueException(String type, String field, Throwable cause) {
        super(type,  MESSAGE, field , cause);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
