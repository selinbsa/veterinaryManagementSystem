package dev.patika.veterinaryManagementSystem.core.exceptions;

public class AppointmentTimeInvalidException extends RuntimeException {
    public AppointmentTimeInvalidException(String message) {
        super(message);
    }
}
