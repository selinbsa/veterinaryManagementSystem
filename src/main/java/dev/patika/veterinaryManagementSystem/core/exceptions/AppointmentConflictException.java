package dev.patika.veterinaryManagementSystem.core.exceptions;

public class AppointmentConflictException extends RuntimeException {
    public AppointmentConflictException(String message) {
        super(message);
    }
}
