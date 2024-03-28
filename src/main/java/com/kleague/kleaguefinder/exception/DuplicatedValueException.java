package com.kleague.kleaguefinder.exception;

import static com.kleague.kleaguefinder.exception.ErrorCode.DUPLICATED_CODE;

public class DuplicatedValueException extends MainException {

  private static final String MESSAGE = DUPLICATED_CODE.getMessage();

  public DuplicatedValueException(String type, String field) {
    super(type, MESSAGE, field);
  }

  public DuplicatedValueException(String type, String field, Throwable cause) {
    super(type, MESSAGE, field, cause);
  }


  @Override
  public int getStatusCode() {
    return 400;
  }
}
