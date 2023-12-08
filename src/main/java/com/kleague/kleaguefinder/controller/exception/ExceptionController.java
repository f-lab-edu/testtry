package com.kleague.kleaguefinder.controller.exception;


import com.kleague.kleaguefinder.exception.MainException;
import com.kleague.kleaguefinder.response.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = MainException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> mainExceptionController(MainException e) {

        log.info("Exception Handler 진입");

        int statusCode = e.statusCode();

        ErrorResponse body = ErrorResponse.builder()
                .statusCode(String.valueOf(statusCode))
                .message(e.getMessage())
                .validationTuples(e.getValidationTupleList())
                .build();

       return ResponseEntity.status(statusCode).body(body);

    }
}
