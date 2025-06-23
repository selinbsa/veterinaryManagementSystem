package dev.patika.veterinaryManagementSystem.core.exceptions;

public class DoctorNotAvailableException extends RuntimeException {
    public DoctorNotAvailableException(String message) {
        super(message);
    }
}
