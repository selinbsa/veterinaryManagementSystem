package dev.patika.veterinaryManagementSystem.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // CustomerNotFoundException için tek handler (çakışmayı önler)
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handleCustomerNotFound(CustomerNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Diğer NotFound exceptionlar için genel handler (CustomerNotFoundException hariç)
    @ExceptionHandler({AnimalNotFoundException.class, DoctorNotFoundException.class, ResourceNotFoundException.class})
    public ResponseEntity<String> handleNotFound(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<String> handleAlreadyExists(AlreadyExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProtectionDateInvalidException.class)
    public ResponseEntity<String> handleProtectionDateInvalid(ProtectionDateInvalidException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AppointmentConflictException.class)
    public ResponseEntity<String> handleAppointmentConflict(AppointmentConflictException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AppointmentTimeInvalidException.class)
    public ResponseEntity<String> handleAppointmentTimeInvalid(AppointmentTimeInvalidException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Diğer tüm beklenmeyen istisnalar için genel handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AvailableDateNotFoundException.class)
    public ResponseEntity<String> handleAvailableDateNotFoundException(AvailableDateNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AppointmentNotFoundException.class)
    public ResponseEntity<String> handleAppointmentNotFoundException(AppointmentNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
