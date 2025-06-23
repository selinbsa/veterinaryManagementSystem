package dev.patika.veterinaryManagementSystem.core.utils;

public class Msg {

    // Doctor
    public static final String DOCTOR_NOT_FOUND = "Doctor not found!";
    public static final String DOCTOR_ALREADY_EXISTS = "Doctor already exists with this email.";
    public static final String DOCTOR_NOT_AVAILABLE = "Doctor does not work on this date!";

    // Customer
    public static final String CUSTOMER_NOT_FOUND = "Customer not found!";
    public static final String CUSTOMER_ALREADY_EXISTS = "Customer already exists with this email.";

    // Animal
    public static final String ANIMAL_NOT_FOUND = "Animal not found!";
    public static final String ANIMAL_ALREADY_EXISTS = "Animal already exists with this name for this customer.";

    // Vaccine
    public static final String VACCINE_NOT_FOUND = "Vaccine not found!";
    public static final String VACCINE_ALREADY_PROTECTED = "This vaccine is still active and cannot be applied again.";

    // Appointment
    public static final String APPOINTMENT_NOT_FOUND = "Appointment not found!";
    public static final String APPOINTMENT_CONFLICT = "This doctor already has an appointment at the given time!";
    public static final String DOCTOR_NOT_AVAILABLE_ON_DATE = "Doctor is not available on the selected date!";

    // Available Date
    public static final String AVAILABLE_DATE_NOT_FOUND = "Available date not found!";
    public static final String AVAILABLE_DATE_ALREADY_EXISTS = "This available date is already registered for the doctor.";

    // Generic
    public static final String ID_NOT_FOUND = "Record not found with the provided ID.";



}
