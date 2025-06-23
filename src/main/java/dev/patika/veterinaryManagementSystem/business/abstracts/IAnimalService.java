package dev.patika.veterinaryManagementSystem.business.abstracts;

import dev.patika.veterinaryManagementSystem.core.exceptions.AlreadyExistsException;
import dev.patika.veterinaryManagementSystem.core.exceptions.AnimalNotFoundException;
import dev.patika.veterinaryManagementSystem.core.exceptions.CustomerNotFoundException;
import dev.patika.veterinaryManagementSystem.core.exceptions.ResourceNotFoundException;
import dev.patika.veterinaryManagementSystem.dto.request.animal.AnimalSaveRequest;
import dev.patika.veterinaryManagementSystem.dto.request.animal.AnimalUpdateRequest;
import dev.patika.veterinaryManagementSystem.dto.response.animal.AnimalResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IAnimalService {
    AnimalResponse save(AnimalSaveRequest request) throws AlreadyExistsException;
    AnimalResponse update(Long id, AnimalUpdateRequest request) throws AnimalNotFoundException;
    void delete(Long id) throws AnimalNotFoundException;
    AnimalResponse getById(Long id) throws AnimalNotFoundException;
    List<AnimalResponse> getAll();
    List<AnimalResponse> getByName(String name);
    List<AnimalResponse> getByCustomerId(Long customerId) throws CustomerNotFoundException;
}

