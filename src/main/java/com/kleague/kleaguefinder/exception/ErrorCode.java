package com.kleague.kleaguefinder.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    NoValueCode("값이 존재하지 않습니다."),
    DuplicatedCode("이미 존재 하는 값입니다.");

    private String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
