package dev.patika.veterinaryManagementSystem.dao.repositories;

import dev.patika.veterinaryManagementSystem.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AvailableDateRepository extends JpaRepository<AvailableDate, Long> {

    List<AvailableDate> findByDoctorId(Long doctorId);

    List<AvailableDate> findByDoctorIdAndAvailableDate(Long doctorId, LocalDate availableDate);
}
