package com.kleague.kleaguefinder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND )
public class NoValueException extends RuntimeException{

    private static final String MESSAGE = "일치하는 값이 없습니다";

    public NoValueException() {
        super(MESSAGE);
    }

    public NoValueException(Throwable cause) {
        super(MESSAGE, cause);
    }

}
