package dev.patika.veterinaryManagementSystem.core.config.modelMapper;


import dev.patika.veterinaryManagementSystem.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.veterinaryManagementSystem.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.veterinaryManagementSystem.entities.Appointment;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<AppointmentSaveRequest, Appointment>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getDoctor());
                skip(destination.getAnimal());
            }
        });

        modelMapper.addMappings(new PropertyMap<AppointmentUpdateRequest, Appointment>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getDoctor());
                skip(destination.getAnimal());
            }
        });


        modelMapper.getConfiguration().setMatchingStrategy(org.modelmapper.convention.MatchingStrategies.STANDARD);

        return modelMapper;
    }


}
