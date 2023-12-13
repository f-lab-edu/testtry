package com.kleague.kleaguefinder.controller.exception;

import com.kleague.kleaguefinder.exception.MainException;
import com.kleague.kleaguefinder.exception.NoValueException;
import com.kleague.kleaguefinder.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.jboss.jandex.Main;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(IllegalStateException.class)
    @ResponseBody
    public ResponseEntity<String> IllegalStateExceptionController(IllegalStateException e) {

        return ResponseEntity.status(404).body(e.getMessage());

    }

    @ExceptionHandler(MainException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> NoValueException(MainException e) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(e.getStatusCode())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(e.getStatusCode()).body(errorResponse);
    }

}
