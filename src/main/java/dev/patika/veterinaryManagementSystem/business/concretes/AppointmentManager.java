package dev.patika.veterinaryManagementSystem.business.concretes;

import dev.patika.veterinaryManagementSystem.business.abstracts.IAppointmentService;
import dev.patika.veterinaryManagementSystem.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinaryManagementSystem.core.exceptions.*;
import dev.patika.veterinaryManagementSystem.dao.repositories.AppointmentRepository;
import dev.patika.veterinaryManagementSystem.dao.repositories.AnimalRepository;
import dev.patika.veterinaryManagementSystem.dao.repositories.AvailableDateRepository;
import dev.patika.veterinaryManagementSystem.dao.repositories.DoctorRepository;
import dev.patika.veterinaryManagementSystem.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.veterinaryManagementSystem.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.veterinaryManagementSystem.dto.response.appointment.AppointmentResponse;
import dev.patika.veterinaryManagementSystem.entities.Appointment;
import dev.patika.veterinaryManagementSystem.entities.AvailableDate;
import dev.patika.veterinaryManagementSystem.entities.Doctor;
import dev.patika.veterinaryManagementSystem.entities.Animal;
import dev.patika.veterinaryManagementSystem.core.utils.Msg;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentManager implements IAppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final AnimalRepository animalRepository;
    private final AvailableDateRepository availableDateRepository;
    private final IModelMapperService modelMapper;

    public AppointmentManager(AppointmentRepository appointmentRepository,
                              DoctorRepository doctorRepository,
                              AnimalRepository animalRepository,
                              AvailableDateRepository availableDateRepository,
                              IModelMapperService modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.animalRepository = animalRepository;
        this.availableDateRepository = availableDateRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AppointmentResponse save(AppointmentSaveRequest request) {
        LocalDateTime appointmentDate = request.getAppointmentDate();

        // Saat başı kontrolü
        if (appointmentDate.getMinute() != 0 || appointmentDate.getSecond() != 0 || appointmentDate.getNano() != 0) {
            throw new AppointmentTimeInvalidException("Randevu sadece saat başı yapılabilir. Dakika ve saniye 0 olmalıdır.");
        }

        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new DoctorNotFoundException(Msg.DOCTOR_NOT_FOUND));
        Animal animal = animalRepository.findById(request.getAnimalId())
                .orElseThrow(() -> new AnimalNotFoundException(Msg.ANIMAL_NOT_FOUND));

        LocalDate appointmentDay = appointmentDate.toLocalDate();

        List<AvailableDate> availableDates = availableDateRepository.findByDoctorIdAndAvailableDate(doctor.getId(), appointmentDay);
        if (availableDates.isEmpty()) {
            throw new DoctorNotAvailableException(Msg.DOCTOR_NOT_AVAILABLE);
        }

        boolean isConflicting = appointmentRepository.existsByDoctorIdAndAppointmentDate(doctor.getId(), appointmentDate);
        if (isConflicting) {
            throw new AppointmentConflictException(Msg.APPOINTMENT_CONFLICT);
        }

        Appointment appointment = modelMapper.forRequest().map(request, Appointment.class);
        appointment.setDoctor(doctor);
        appointment.setAnimal(animal);

        return modelMapper.forResponse().map(appointmentRepository.save(appointment), AppointmentResponse.class);
    }

    @Override
    public AppointmentResponse update(Long id, AppointmentUpdateRequest request) {
        LocalDateTime appointmentDate = request.getAppointmentDate();

        // Saat başı kontrolü
        if (appointmentDate.getMinute() != 0 || appointmentDate.getSecond() != 0 || appointmentDate.getNano() != 0) {
            throw new AppointmentTimeInvalidException("Randevu sadece saat başı yapılabilir. Dakika ve saniye 0 olmalıdır.");
        }

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new DoctorNotFoundException(Msg.DOCTOR_NOT_FOUND));
        Animal animal = animalRepository.findById(request.getAnimalId())
                .orElseThrow(() -> new AnimalNotFoundException(Msg.ANIMAL_NOT_FOUND));

        LocalDate appointmentDay = appointmentDate.toLocalDate();

        List<AvailableDate> availableDates = availableDateRepository.findByDoctorIdAndAvailableDate(doctor.getId(), appointmentDay);
        if (availableDates.isEmpty()) {
            throw new DoctorNotAvailableException(Msg.DOCTOR_NOT_AVAILABLE);
        }

        boolean isConflicting = appointmentRepository.existsByDoctorIdAndAppointmentDate(doctor.getId(), appointmentDate);
        if (isConflicting && !appointment.getAppointmentDate().equals(appointmentDate)) {
            throw new AppointmentConflictException(Msg.APPOINTMENT_CONFLICT);
        }

        appointment.setDoctor(doctor);
        appointment.setAnimal(animal);
        appointment.setAppointmentDate(appointmentDate);

        return modelMapper.forResponse().map(appointmentRepository.save(appointment), AppointmentResponse.class);
    }


    @Override
    public void delete(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with id: " + id));
        appointmentRepository.delete(appointment);
    }

    @Override
    public AppointmentResponse getById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with id: " + id));
        return modelMapper.forResponse().map(appointment, AppointmentResponse.class);
    }


    @Override
    public List<AppointmentResponse> getAll() {
        return appointmentRepository.findAll()
                .stream()
                .map(appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponse> getByDoctorIdAndDateRange(Long doctorId, LocalDateTime start, LocalDateTime end) {
        doctorRepository.findById(doctorId)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor with id " + doctorId + " not found"));

        List<Appointment> appointments = appointmentRepository.findByDoctorIdAndAppointmentDateBetween(doctorId, start, end);

        if (appointments.isEmpty()) {
            throw new AppointmentNotFoundException("No appointments found for doctor with id " + doctorId + " in the specified date range.");
        }

        return appointments.stream()
                .map(appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .collect(Collectors.toList());
    }


    @Override
    public List<AppointmentResponse> getByAnimalIdAndDateRange(Long animalId, LocalDateTime start, LocalDateTime end) {
        animalRepository.findById(animalId)
                .orElseThrow(() -> new AnimalNotFoundException("Animal with id " + animalId + " not found"));

        List<Appointment> appointments = appointmentRepository.findByAnimalIdAndAppointmentDateBetween(animalId, start, end);

        if (appointments.isEmpty()) {
            throw new AppointmentNotFoundException("No appointments found for animal with id " + animalId + " in the specified date range.");
        }

        return appointments.stream()
                .map(appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .collect(Collectors.toList());
    }


}
