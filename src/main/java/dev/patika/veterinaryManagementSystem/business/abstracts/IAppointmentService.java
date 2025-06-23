package dev.patika.veterinaryManagementSystem.business.abstracts;

import dev.patika.veterinaryManagementSystem.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.veterinaryManagementSystem.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.veterinaryManagementSystem.dto.response.appointment.AppointmentResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {

    AppointmentResponse save(AppointmentSaveRequest request);

    AppointmentResponse update(Long id, AppointmentUpdateRequest request);

    void delete(Long id);

    AppointmentResponse getById(Long id);

    List<AppointmentResponse> getAll();

    List<AppointmentResponse> getByDoctorIdAndDateRange(Long doctorId, LocalDateTime start, LocalDateTime end);

    List<AppointmentResponse> getByAnimalIdAndDateRange(Long animalId, LocalDateTime start, LocalDateTime end);
}
