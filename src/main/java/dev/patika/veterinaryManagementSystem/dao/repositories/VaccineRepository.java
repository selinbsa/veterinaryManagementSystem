package dev.patika.veterinaryManagementSystem.dao.repositories;

import dev.patika.veterinaryManagementSystem.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

    List<Vaccine> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);
    List<Vaccine> findByAnimalId(Long animalId);
    boolean existsByNameAndCodeAndAnimalIdAndProtectionFinishDateAfter(String name, String code, Long animalId, LocalDate date);

}
