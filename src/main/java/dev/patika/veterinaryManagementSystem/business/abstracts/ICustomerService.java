package dev.patika.veterinaryManagementSystem.business.abstracts;

import dev.patika.veterinaryManagementSystem.core.exceptions.AlreadyExistsException;
import dev.patika.veterinaryManagementSystem.core.exceptions.CustomerNotFoundException;
import dev.patika.veterinaryManagementSystem.dto.request.customer.CustomerSaveRequest;
import dev.patika.veterinaryManagementSystem.dto.request.customer.CustomerUpdateRequest;
import dev.patika.veterinaryManagementSystem.dto.response.animal.AnimalResponse;
import dev.patika.veterinaryManagementSystem.dto.response.customer.CustomerResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ICustomerService {
    CustomerResponse save(CustomerSaveRequest request) throws AlreadyExistsException;
    CustomerResponse update(Long id, CustomerUpdateRequest request) throws CustomerNotFoundException;
    void delete(Long id) throws CustomerNotFoundException;
    CustomerResponse getById(Long id) throws CustomerNotFoundException;
    List<CustomerResponse> getAll();
    List<CustomerResponse> getByName(String name);
    List<AnimalResponse> getAnimalsByCustomerId(Long customerId) throws CustomerNotFoundException;

}

