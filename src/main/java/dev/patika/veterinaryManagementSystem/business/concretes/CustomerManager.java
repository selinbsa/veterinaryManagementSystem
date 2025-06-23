package dev.patika.veterinaryManagementSystem.business.concretes;

import dev.patika.veterinaryManagementSystem.business.abstracts.ICustomerService;
import dev.patika.veterinaryManagementSystem.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinaryManagementSystem.core.exceptions.AlreadyExistsException;
import dev.patika.veterinaryManagementSystem.core.exceptions.CustomerNotFoundException;
import dev.patika.veterinaryManagementSystem.core.utils.Msg;
import dev.patika.veterinaryManagementSystem.dao.repositories.CustomerRepository;
import dev.patika.veterinaryManagementSystem.dto.request.customer.CustomerSaveRequest;
import dev.patika.veterinaryManagementSystem.dto.request.customer.CustomerUpdateRequest;
import dev.patika.veterinaryManagementSystem.dto.response.animal.AnimalResponse;
import dev.patika.veterinaryManagementSystem.dto.response.customer.CustomerResponse;
import dev.patika.veterinaryManagementSystem.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerManager implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final IModelMapperService modelMapper;

    public CustomerManager(CustomerRepository customerRepository, IModelMapperService modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CustomerResponse save(CustomerSaveRequest request) {
        Optional<Customer> customerFromDb = customerRepository.findByEmail(request.getEmail());
        if (customerFromDb.isPresent()) {
            throw new AlreadyExistsException(Msg.CUSTOMER_ALREADY_EXISTS);
        }

        Customer customer = modelMapper.forRequest().map(request, Customer.class);
        return modelMapper.forResponse().map(customerRepository.save(customer), CustomerResponse.class);
    }

    @Override
    public CustomerResponse update(Long id, CustomerUpdateRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(Msg.CUSTOMER_NOT_FOUND));

        customer.setName(request.getName());
        customer.setPhone(request.getPhone());
        customer.setEmail(request.getEmail());
        customer.setAddress(request.getAddress());
        customer.setCity(request.getCity());

        return modelMapper.forResponse().map(customerRepository.save(customer), CustomerResponse.class);
    }

    @Override
    public void delete(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(Msg.CUSTOMER_NOT_FOUND));
        customerRepository.delete(customer);
    }

    @Override
    public CustomerResponse getById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(Msg.CUSTOMER_NOT_FOUND));
        return modelMapper.forResponse().map(customer, CustomerResponse.class);
    }

    @Override
    public List<CustomerResponse> getAll() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> modelMapper.forResponse().map(customer, CustomerResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerResponse> getByName(String name) {
        List<Customer> customers = customerRepository.findByNameContainingIgnoreCase(name);
        if (customers.isEmpty()) {
            throw new CustomerNotFoundException("Name ile müşteri bulunamadı: " + name);
        }
        return customers.stream()
                .map(customer -> modelMapper.forResponse().map(customer, CustomerResponse.class))
                .collect(Collectors.toList());
    }
    @Override
    public List<AnimalResponse> getAnimalsByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(Msg.CUSTOMER_NOT_FOUND));
        return customer.getAnimals()
                .stream()
                .map(animal -> modelMapper.forResponse().map(animal, AnimalResponse.class))
                .collect(Collectors.toList());
    }

}
