package dev.patika.veterinaryManagementSystem.business.abstracts;

import dev.patika.veterinaryManagementSystem.dto.request.availableDate.AvailableDateSaveRequest;
import dev.patika.veterinaryManagementSystem.dto.request.availableDate.AvailableDateUpdateRequest;
import dev.patika.veterinaryManagementSystem.dto.response.availableDate.AvailableDateResponse;

import java.util.List;

public interface IAvailableDateService {

    AvailableDateResponse save(AvailableDateSaveRequest request);

    AvailableDateResponse update(Long id, AvailableDateUpdateRequest request);

    void delete(Long id);

    AvailableDateResponse getById(Long id);

    List<AvailableDateResponse> getAll();

    List<AvailableDateResponse> getByDoctorId(Long doctorId);
}
