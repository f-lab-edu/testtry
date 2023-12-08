package com.kleague.kleaguefinder.exception;

import com.kleague.kleaguefinder.response.exception.ValidationTuple;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class MainException extends RuntimeException{

    List<ValidationTuple> validationTupleList = new ArrayList<>();

    public MainException(String message) {
        super(message);
    }

    public MainException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int statusCode();

    public void addValidationFields(Long id,String entityName){
        validationTupleList.add(new ValidationTuple(id, entityName));
    };

}
