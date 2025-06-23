package dev.patika.veterinaryManagementSystem.core.exceptions;

public class CustomerNotFoundException extends RuntimeException {
  public CustomerNotFoundException(String message) {
    super(message);
  }
}