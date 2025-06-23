package dev.patika.veterinaryManagementSystem.core.exceptions;

public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException(String message) {
        super(message);
    }
}
