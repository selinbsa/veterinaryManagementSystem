package dev.patika.veterinaryManagementSystem.core.config.modelMapper;

import dev.patika.veterinaryManagementSystem.business.abstracts.IDoctorService;
import dev.patika.veterinaryManagementSystem.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinaryManagementSystem.dao.repositories.DoctorRepository;
import dev.patika.veterinaryManagementSystem.dto.request.animal.AnimalSaveRequest;
import dev.patika.veterinaryManagementSystem.dto.request.availableDate.AvailableDateSaveRequest;
import dev.patika.veterinaryManagementSystem.dto.request.availableDate.AvailableDateUpdateRequest;
import dev.patika.veterinaryManagementSystem.dto.request.customer.CustomerSaveRequest;
import dev.patika.veterinaryManagementSystem.dto.request.customer.CustomerUpdateRequest;
import dev.patika.veterinaryManagementSystem.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.veterinaryManagementSystem.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.veterinaryManagementSystem.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.veterinaryManagementSystem.dto.response.availableDate.AvailableDateResponse;
import dev.patika.veterinaryManagementSystem.dto.response.doctor.DoctorResponse;
import dev.patika.veterinaryManagementSystem.entities.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelMapperManager implements IModelMapperService {

    private final ModelMapper modelMapper;

    public ModelMapperManager() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);


        // Customer için id atla
        this.modelMapper.createTypeMap(CustomerSaveRequest.class, Customer.class)
                .addMappings(mapper -> mapper.skip(Customer::setId));

        this.modelMapper.createTypeMap(CustomerUpdateRequest.class, Customer.class)
                .addMappings(mapper -> mapper.skip(Customer::setId));

        // Animal için id atla
        this.modelMapper.createTypeMap(AnimalSaveRequest.class, Animal.class)
                .addMappings(mapper -> mapper.skip(Animal::setId));

        // Vaccine için id atla
        this.modelMapper.createTypeMap(VaccineSaveRequest.class, Vaccine.class)
                .addMappings(mapper -> mapper.skip(Vaccine::setId));

        // AvailableDate için id atla
        this.modelMapper.createTypeMap(AvailableDateSaveRequest.class, AvailableDate.class)
                .addMappings(mapper -> mapper.skip(AvailableDate::setId));
        this.modelMapper.createTypeMap(AvailableDateUpdateRequest.class, AvailableDate.class)
                .addMappings(mapper -> mapper.skip(AvailableDate::setId));

        // Appointment için id atla (burayı ekledik)
        // Appointment mapping için PropertyMap ile kesin kontrol
        this.modelMapper.addMappings(new PropertyMap<AppointmentSaveRequest, Appointment>() {
            @Override
            protected void configure() {
                skip(destination.getId());      // id atla
                skip(destination.getDoctor());  // doctor nesnesini atla
                skip(destination.getAnimal());  // animal nesnesini atla
            }
        });

        this.modelMapper.addMappings(new PropertyMap<AppointmentUpdateRequest, Appointment>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getDoctor());
                skip(destination.getAnimal());
            }
        });


    }

    @Override
    public ModelMapper forRequest() {
        return this.modelMapper;
    }

    @Override
    public ModelMapper forResponse() {
        return this.modelMapper;
    }
}
