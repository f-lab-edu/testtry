package com.kleague.kleaguefinder.exception;

import lombok.Getter;

import static com.kleague.kleaguefinder.exception.ErrorCode.AnnotationMsg.*;


@Getter
public enum ErrorCode {

    NO_VALUE_CODE(NO_VALUE),
    DUPLICATED_CODE(DUPLICATED),
    NOT_NULL_CODE(NOT_NULL),
    NOT_BLANK_CODE(NOT_BLANK);

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public static class AnnotationMsg {
        public static final String NO_VALUE = "값이 존재하지 않습니다.";
        public static final String DUPLICATED = "이미 존재 하는 값입니다.";
        public static final String NOT_NULL= "NULL 값이 입력되면 안됩니다.";
        public static final String NOT_BLANK= "값이 입력되어야 합니다.";
    }

}
