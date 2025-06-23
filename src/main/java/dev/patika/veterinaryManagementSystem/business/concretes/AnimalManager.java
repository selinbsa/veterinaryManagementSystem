package dev.patika.veterinaryManagementSystem.business.concretes;

import dev.patika.veterinaryManagementSystem.business.abstracts.IAnimalService;
import dev.patika.veterinaryManagementSystem.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinaryManagementSystem.core.exceptions.AlreadyExistsException;
import dev.patika.veterinaryManagementSystem.core.exceptions.AnimalNotFoundException;
import dev.patika.veterinaryManagementSystem.core.exceptions.CustomerNotFoundException;
import dev.patika.veterinaryManagementSystem.core.utils.Msg;
import dev.patika.veterinaryManagementSystem.dao.repositories.AnimalRepository;
import dev.patika.veterinaryManagementSystem.dao.repositories.CustomerRepository;
import dev.patika.veterinaryManagementSystem.dto.request.animal.AnimalSaveRequest;
import dev.patika.veterinaryManagementSystem.dto.request.animal.AnimalUpdateRequest;
import dev.patika.veterinaryManagementSystem.dto.response.animal.AnimalResponse;
import dev.patika.veterinaryManagementSystem.entities.Animal;
import dev.patika.veterinaryManagementSystem.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalManager implements IAnimalService {

    private final AnimalRepository animalRepository;
    private final CustomerRepository customerRepository;
    private final IModelMapperService modelMapper;

    public AnimalManager(AnimalRepository animalRepository, CustomerRepository customerRepository, IModelMapperService modelMapper) {
        this.animalRepository = animalRepository;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AnimalResponse save(AnimalSaveRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException(Msg.CUSTOMER_NOT_FOUND));

        System.out.println("saving");

        boolean exists = animalRepository.existsByNameAndCustomerId(request.getName(), request.getCustomerId());
        if (exists) {
            throw new AlreadyExistsException(Msg.ANIMAL_ALREADY_EXISTS);
        }

        Animal animal = modelMapper.forRequest().map(request, Animal.class);
        animal.setCustomer(customer);

        return modelMapper.forResponse().map(animalRepository.save(animal), AnimalResponse.class);
    }


    @Override
    public AnimalResponse update(Long id, AnimalUpdateRequest request) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new AnimalNotFoundException(Msg.ANIMAL_NOT_FOUND));

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException(Msg.CUSTOMER_NOT_FOUND));

        animal.setName(request.getName());
        animal.setSpecies(request.getSpecies());
        animal.setBreed(request.getBreed());
        animal.setGender(request.getGender());
        animal.setColour(request.getColour());
        animal.setDateOfBirth(request.getDateOfBirth());
        animal.setCustomer(customer);

        return modelMapper.forResponse().map(animalRepository.save(animal), AnimalResponse.class);
    }

    @Override
    public void delete(Long id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new AnimalNotFoundException(Msg.ANIMAL_NOT_FOUND));
        animalRepository.delete(animal);
    }

    @Override
    public AnimalResponse getById(Long id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new AnimalNotFoundException(Msg.ANIMAL_NOT_FOUND));
        return modelMapper.forResponse().map(animal, AnimalResponse.class);
    }

    @Override
    public List<AnimalResponse> getAll() {
        return animalRepository.findAll()
                .stream()
                .map(animal -> modelMapper.forResponse().map(animal, AnimalResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AnimalResponse> getByName(String name) {
        return animalRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(animal -> modelMapper.forResponse().map(animal, AnimalResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AnimalResponse> getByCustomerId(Long customerId) {
        return animalRepository.findByCustomerId(customerId)
                .stream()
                .map(animal -> modelMapper.forResponse().map(animal, AnimalResponse.class))
                .collect(Collectors.toList());
    }


}
