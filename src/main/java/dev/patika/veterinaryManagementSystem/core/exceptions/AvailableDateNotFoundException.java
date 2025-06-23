package dev.patika.veterinaryManagementSystem.core.exceptions;

public class AvailableDateNotFoundException extends RuntimeException {
    public AvailableDateNotFoundException(String message) {
        super(message);
    }
}
