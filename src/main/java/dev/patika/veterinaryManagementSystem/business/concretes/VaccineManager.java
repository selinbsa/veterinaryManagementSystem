package dev.patika.veterinaryManagementSystem.business.concretes;

import dev.patika.veterinaryManagementSystem.business.abstracts.IVaccineService;
import dev.patika.veterinaryManagementSystem.core.exceptions.ProtectionDateInvalidException;
import dev.patika.veterinaryManagementSystem.dao.repositories.AnimalRepository;
import dev.patika.veterinaryManagementSystem.dao.repositories.VaccineRepository;
import dev.patika.veterinaryManagementSystem.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.veterinaryManagementSystem.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.veterinaryManagementSystem.dto.response.vaccine.VaccineResponse;
import dev.patika.veterinaryManagementSystem.entities.Animal;
import dev.patika.veterinaryManagementSystem.entities.Vaccine;
import dev.patika.veterinaryManagementSystem.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinaryManagementSystem.core.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VaccineManager implements IVaccineService {

    private final VaccineRepository vaccineRepository;
    private final AnimalRepository animalRepository;
    private final IModelMapperService modelMapper;

    public VaccineManager(VaccineRepository vaccineRepository, AnimalRepository animalRepository, IModelMapperService modelMapper) {
        this.vaccineRepository = vaccineRepository;
        this.animalRepository = animalRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public VaccineResponse save(VaccineSaveRequest request) {

        // Aynı isim, kod, hayvanda ve koruyuculuk bitiş tarihi henüz geçmemiş aşı var mı kontrol et
        boolean exists = vaccineRepository.existsByNameAndCodeAndAnimalIdAndProtectionFinishDateAfter(
                request.getName(),
                request.getCode(),
                request.getAnimalId(),
                LocalDate.now()
        );

        if (exists) {
            throw new ProtectionDateInvalidException("Aynı tipte koruyuculuğu bitmemiş aşı mevcut, yeni aşı kaydı engellendi.");
        }

        Animal animal = animalRepository.findById(request.getAnimalId())
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found with id: " + request.getAnimalId()));

        Vaccine vaccine = modelMapper.forRequest().map(request, Vaccine.class);
        vaccine.setAnimal(animal);
        Vaccine savedVaccine = vaccineRepository.save(vaccine);
        return modelMapper.forResponse().map(savedVaccine, VaccineResponse.class);
    }



    @Override
    public VaccineResponse update(Long id, VaccineUpdateRequest request) {

        // Güncelleme sırasında kendi kaydını dışlamak için ek kontrol yapıyoruz:
        boolean exists = vaccineRepository.existsByNameAndCodeAndAnimalIdAndProtectionFinishDateAfter(
                request.getName(),
                request.getCode(),
                request.getAnimalId(),
                LocalDate.now()
        );

        Vaccine existingVaccine = vaccineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vaccine not found with id: " + id));

        // Eğer aktif başka aşı varsa ve o bizim güncellemek istediğimiz aşı değilse engelle
        if (exists && !(
                existingVaccine.getName().equals(request.getName()) &&
                        existingVaccine.getCode().equals(request.getCode()) &&
                        existingVaccine.getAnimal().getId() == request.getAnimalId())
        ) {
            throw new ProtectionDateInvalidException("Aynı tipte koruyuculuğu bitmemiş başka aşı mevcut, güncelleme engellendi.");
        }

        Animal animal = animalRepository.findById(request.getAnimalId())
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found with id: " + request.getAnimalId()));

        existingVaccine.setName(request.getName());
        existingVaccine.setCode(request.getCode());
        existingVaccine.setProtectionStartDate(request.getProtectionStartDate());
        existingVaccine.setProtectionFinishDate(request.getProtectionFinishDate());
        existingVaccine.setAnimal(animal);

        Vaccine updatedVaccine = vaccineRepository.save(existingVaccine);
        return modelMapper.forResponse().map(updatedVaccine, VaccineResponse.class);
    }


    @Override
    public void delete(Long id) {
        Vaccine vaccine = vaccineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vaccine not found with id: " + id));
        vaccineRepository.delete(vaccine);
    }

    @Override
    public VaccineResponse getById(Long id) {
        Vaccine vaccine = vaccineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vaccine not found with id: " + id));
        return modelMapper.forResponse().map(vaccine, VaccineResponse.class);
    }

    @Override
    public List<VaccineResponse> getAll() {
        return vaccineRepository.findAll()
                .stream()
                .map(vaccine -> modelMapper.forResponse().map(vaccine, VaccineResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<VaccineResponse> getVaccinesByProtectionFinishDateRange(LocalDate startDate, LocalDate endDate) {
        List<Vaccine> vaccines = vaccineRepository.findByProtectionFinishDateBetween(startDate, endDate);
        return vaccines.stream()
                .map(vaccine -> modelMapper.forResponse().map(vaccine, VaccineResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<VaccineResponse> getByAnimalId(Long animalId) {
        List<Vaccine> vaccines = vaccineRepository.findByAnimalId(animalId);
        return vaccines.stream()
                .map(vaccine -> modelMapper.forResponse().map(vaccine, VaccineResponse.class))
                .collect(Collectors.toList());
    }


}
