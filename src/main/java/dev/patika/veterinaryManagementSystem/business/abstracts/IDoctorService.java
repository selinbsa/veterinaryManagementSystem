package dev.patika.veterinaryManagementSystem.business.abstracts;

import dev.patika.veterinaryManagementSystem.core.exceptions.AlreadyExistsException;
import dev.patika.veterinaryManagementSystem.core.exceptions.DoctorNotFoundException;
import dev.patika.veterinaryManagementSystem.dto.request.doctor.DoctorSaveRequest;
import dev.patika.veterinaryManagementSystem.dto.request.doctor.DoctorUpdateRequest;
import dev.patika.veterinaryManagementSystem.dto.response.doctor.DoctorResponse;

import java.util.List;

public interface IDoctorService {
    DoctorResponse save(DoctorSaveRequest request) throws AlreadyExistsException;
    DoctorResponse update(Long id, DoctorUpdateRequest request) throws DoctorNotFoundException;
    void delete(Long id) throws DoctorNotFoundException;
    DoctorResponse getById(Long id) throws DoctorNotFoundException;
    List<DoctorResponse> getAll();
    List<DoctorResponse> getByName(String name);
}
