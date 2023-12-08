package com.kleague.kleaguefinder.exception;

import org.jboss.jandex.Main;
import org.springframework.http.HttpStatus;

public class NoIdValueException extends MainException {


    private static final String MESSAGE = "일치하는 ID 값이 없습니다";

    public NoIdValueException() {
        super(MESSAGE);
    }

    public NoIdValueException(Long id, String entityName) {
        super(MESSAGE);
        addValidationFields(id,entityName);
    }

    public NoIdValueException(Throwable cause) {
        super(MESSAGE, cause);
    }

    public int statusCode() {
        return 404;
    }
}
