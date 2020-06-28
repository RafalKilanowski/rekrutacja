package com.recruitment.shared.exceptions;

public class ResourceNotExistsException extends RuntimeException {

  public ResourceNotExistsException(String message) {
    super(message);
  }
}
