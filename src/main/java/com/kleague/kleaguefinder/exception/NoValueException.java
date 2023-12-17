package com.kleague.kleaguefinder.exception;

import static com.kleague.kleaguefinder.exception.ErrorCode.NO_VALUE_CODE;

public class NoValueException extends MainException {

    private static final String MESSAGE = NO_VALUE_CODE.getMessage();

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
