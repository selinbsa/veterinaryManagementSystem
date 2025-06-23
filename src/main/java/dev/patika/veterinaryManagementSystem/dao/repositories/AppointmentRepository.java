package dev.patika.veterinaryManagementSystem.dao.repositories;

import dev.patika.veterinaryManagementSystem.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorIdAndAppointmentDateBetween(Long doctorId, LocalDateTime start, LocalDateTime end);

    List<Appointment> findByAnimalIdAndAppointmentDateBetween(Long animalId, LocalDateTime start, LocalDateTime end);

    boolean existsByDoctorIdAndAppointmentDate(Long doctorId, LocalDateTime appointmentDate);
}
