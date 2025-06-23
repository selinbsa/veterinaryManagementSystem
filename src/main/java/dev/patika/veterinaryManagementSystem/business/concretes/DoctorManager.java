package dev.patika.veterinaryManagementSystem.business.concretes;

import dev.patika.veterinaryManagementSystem.business.abstracts.IDoctorService;
import dev.patika.veterinaryManagementSystem.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinaryManagementSystem.core.exceptions.AlreadyExistsException;
import dev.patika.veterinaryManagementSystem.core.exceptions.DoctorNotFoundException;
import dev.patika.veterinaryManagementSystem.core.utils.Msg;
import dev.patika.veterinaryManagementSystem.dao.repositories.DoctorRepository;
import dev.patika.veterinaryManagementSystem.dto.request.doctor.DoctorSaveRequest;
import dev.patika.veterinaryManagementSystem.dto.request.doctor.DoctorUpdateRequest;
import dev.patika.veterinaryManagementSystem.dto.response.doctor.DoctorResponse;
import dev.patika.veterinaryManagementSystem.entities.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorManager implements IDoctorService {

    private final DoctorRepository doctorRepository;
    private final IModelMapperService modelMapper;

    public DoctorManager(DoctorRepository doctorRepository, IModelMapperService modelMapper) {
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DoctorResponse save(DoctorSaveRequest request) {
        if (doctorRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new AlreadyExistsException(Msg.DOCTOR_ALREADY_EXISTS);
        }

        Doctor doctor = modelMapper.forRequest().map(request, Doctor.class);
        return modelMapper.forResponse().map(doctorRepository.save(doctor), DoctorResponse.class);
    }

    @Override
    public DoctorResponse update(Long id, DoctorUpdateRequest request) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException(Msg.DOCTOR_NOT_FOUND));

        doctor.setName(request.getName());
        doctor.setPhone(request.getPhone());
        doctor.setEmail(request.getEmail());
        doctor.setAddress(request.getAddress());
        doctor.setCity(request.getCity());

        return modelMapper.forResponse().map(doctorRepository.save(doctor), DoctorResponse.class);
    }

    @Override
    public void delete(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException(Msg.DOCTOR_NOT_FOUND));
        doctorRepository.delete(doctor);
    }

    @Override
    public DoctorResponse getById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException(Msg.DOCTOR_NOT_FOUND));
        return modelMapper.forResponse().map(doctor, DoctorResponse.class);
    }

    @Override
    public List<DoctorResponse> getAll() {
        return doctorRepository.findAll()
                .stream()
                .map(doctor -> modelMapper.forResponse().map(doctor, DoctorResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorResponse> getByName(String name) {
        return doctorRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(doctor -> modelMapper.forResponse().map(doctor, DoctorResponse.class))
                .collect(Collectors.toList());
    }
}
