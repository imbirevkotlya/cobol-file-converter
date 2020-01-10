package com.epam.lemon.exception;

public class InvalidDataException extends RuntimeException {

  public InvalidDataException(String message) {
    super(message);
  }

  public InvalidDataException(Throwable cause) {
    super(cause);
  }
}
