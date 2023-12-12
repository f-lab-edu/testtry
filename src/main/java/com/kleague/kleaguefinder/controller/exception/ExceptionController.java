package com.kleague.kleaguefinder.controller.exception;

import lombok.extern.slf4j.Slf4j;
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

}
