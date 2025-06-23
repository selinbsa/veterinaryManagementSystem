package dev.patika.veterinaryManagementSystem.business.abstracts;

import dev.patika.veterinaryManagementSystem.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.veterinaryManagementSystem.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.veterinaryManagementSystem.dto.response.vaccine.VaccineResponse;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {
    VaccineResponse save(VaccineSaveRequest request);
    VaccineResponse update(Long id, VaccineUpdateRequest request);
    void delete(Long id);
    VaccineResponse getById(Long id);
    List<VaccineResponse> getAll();
    List<VaccineResponse> getVaccinesByProtectionFinishDateRange(LocalDate startDate, LocalDate endDate);
    List<VaccineResponse> getByAnimalId(Long animalId);
}
