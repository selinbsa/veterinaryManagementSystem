package dev.patika.veterinaryManagementSystem.core.exceptions;

public class AnimalNotFoundException extends RuntimeException {
    public AnimalNotFoundException(String message) {
        super(message);
    }
}
