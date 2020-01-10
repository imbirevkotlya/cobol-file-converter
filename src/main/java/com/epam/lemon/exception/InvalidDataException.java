package com.epam.lemon.exception;

/**
 * Main library exception, represents the invalid data in the incoming dataset:
 * missing fields format, inconsistency between copybook and raw data and so on.
 */
public class InvalidDataException extends RuntimeException {

  /**
   * Main exception constructor.
   * @param message is a formed message about the problem
   */
  public InvalidDataException(String message) {
    super(message);
  }
}
