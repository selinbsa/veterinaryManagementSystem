package dev.patika.veterinaryManagementSystem.business.concretes;

import dev.patika.veterinaryManagementSystem.business.abstracts.IAvailableDateService;
import dev.patika.veterinaryManagementSystem.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinaryManagementSystem.core.exceptions.AlreadyExistsException;
import dev.patika.veterinaryManagementSystem.core.exceptions.AvailableDateNotFoundException;
import dev.patika.veterinaryManagementSystem.core.exceptions.DoctorNotFoundException;
import dev.patika.veterinaryManagementSystem.core.utils.Msg;
import dev.patika.veterinaryManagementSystem.dao.repositories.AvailableDateRepository;
import dev.patika.veterinaryManagementSystem.dao.repositories.DoctorRepository;
import dev.patika.veterinaryManagementSystem.dto.request.availableDate.AvailableDateSaveRequest;
import dev.patika.veterinaryManagementSystem.dto.request.availableDate.AvailableDateUpdateRequest;
import dev.patika.veterinaryManagementSystem.dto.response.appointment.AppointmentResponse;
import dev.patika.veterinaryManagementSystem.dto.response.availableDate.AvailableDateResponse;
import dev.patika.veterinaryManagementSystem.entities.Appointment;
import dev.patika.veterinaryManagementSystem.entities.AvailableDate;
import dev.patika.veterinaryManagementSystem.entities.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvailableDateManager implements IAvailableDateService {

    private final AvailableDateRepository availableDateRepository;
    private final DoctorRepository doctorRepository;
    private final IModelMapperService modelMapper;

    public AvailableDateManager(AvailableDateRepository availableDateRepository,
                                DoctorRepository doctorRepository,
                                IModelMapperService modelMapper) {
        this.availableDateRepository = availableDateRepository;
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AvailableDateResponse save(AvailableDateSaveRequest request) {
        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new DoctorNotFoundException(Msg.DOCTOR_NOT_FOUND));

        boolean exists = availableDateRepository.findByDoctorIdAndAvailableDate(doctor.getId(), request.getAvailableDate()).size() > 0;
        if (exists) {
            throw new AlreadyExistsException(Msg.AVAILABLE_DATE_ALREADY_EXISTS);
        }

        AvailableDate availableDate = modelMapper.forRequest().map(request, AvailableDate.class);
        availableDate.setDoctor(doctor);

        return modelMapper.forResponse().map(availableDateRepository.save(availableDate), AvailableDateResponse.class);
    }

    @Override
    public AvailableDateResponse update(Long id, AvailableDateUpdateRequest request) {
        AvailableDate availableDate = availableDateRepository.findById(id)
                .orElseThrow(() -> new AvailableDateNotFoundException(Msg.AVAILABLE_DATE_NOT_FOUND));

        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new DoctorNotFoundException(Msg.DOCTOR_NOT_FOUND));

        availableDate.setAvailableDate(request.getAvailableDate());
        availableDate.setDoctor(doctor);

        return modelMapper.forResponse().map(availableDateRepository.save(availableDate), AvailableDateResponse.class);
    }

    @Override
    public void delete(Long id) {
        AvailableDate availableDate = availableDateRepository.findById(id)
                .orElseThrow(() -> new AvailableDateNotFoundException(Msg.AVAILABLE_DATE_NOT_FOUND));
        availableDateRepository.delete(availableDate);
    }

    @Override
    public AvailableDateResponse getById(Long id) {
        AvailableDate availableDate = availableDateRepository.findById(id)
                .orElseThrow(() -> new AvailableDateNotFoundException("AvailableDate with id " + id + " not found"));
        return modelMapper.forResponse().map(availableDate, AvailableDateResponse.class);
    }

    @Override
    public List<AvailableDateResponse> getAll() {
        return availableDateRepository.findAll()
                .stream()
                .map(availableDate -> modelMapper.forResponse().map(availableDate, AvailableDateResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AvailableDateResponse> getByDoctorId(Long doctorId) {
        return availableDateRepository.findByDoctorId(doctorId)
                .stream()
                .map(availableDate -> modelMapper.forResponse().map(availableDate, AvailableDateResponse.class))
                .collect(Collectors.toList());
    }
}
