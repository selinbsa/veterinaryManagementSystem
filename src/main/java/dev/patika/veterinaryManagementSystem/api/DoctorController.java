package dev.patika.veterinaryManagementSystem.api;

import dev.patika.veterinaryManagementSystem.business.abstracts.IDoctorService;
import dev.patika.veterinaryManagementSystem.dto.request.doctor.DoctorSaveRequest;
import dev.patika.veterinaryManagementSystem.dto.request.doctor.DoctorUpdateRequest;
import dev.patika.veterinaryManagementSystem.dto.response.doctor.DoctorResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/doctors", produces = "application/json")
public class DoctorController {

    private final IDoctorService doctorService;

    public DoctorController(IDoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<DoctorResponse> save(@Valid @RequestBody DoctorSaveRequest request) {
        return new ResponseEntity<>(doctorService.save(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponse> update(@PathVariable Long id, @Valid @RequestBody DoctorUpdateRequest request) {
        return ResponseEntity.ok(doctorService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        doctorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponse>> getAll() {
        return ResponseEntity.ok(doctorService.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<DoctorResponse>> getByName(@RequestParam String name) {
        return ResponseEntity.ok(doctorService.getByName(name));
    }
}
