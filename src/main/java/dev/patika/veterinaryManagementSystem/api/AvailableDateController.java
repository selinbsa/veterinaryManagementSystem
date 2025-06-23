package dev.patika.veterinaryManagementSystem.api;

import dev.patika.veterinaryManagementSystem.business.abstracts.IAvailableDateService;
import dev.patika.veterinaryManagementSystem.dto.request.availableDate.AvailableDateSaveRequest;
import dev.patika.veterinaryManagementSystem.dto.request.availableDate.AvailableDateUpdateRequest;
import dev.patika.veterinaryManagementSystem.dto.response.availableDate.AvailableDateResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/available-dates", produces = "application/json")
public class AvailableDateController {

    private final IAvailableDateService availableDateService;

    public AvailableDateController(IAvailableDateService availableDateService) {
        this.availableDateService = availableDateService;
    }

    @PostMapping
    public ResponseEntity<AvailableDateResponse> save(@Valid @RequestBody AvailableDateSaveRequest request) {
        return new ResponseEntity<>(availableDateService.save(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvailableDateResponse> update(@PathVariable Long id, @Valid @RequestBody AvailableDateUpdateRequest request) {
        return ResponseEntity.ok(availableDateService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        availableDateService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvailableDateResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(availableDateService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<AvailableDateResponse>> getAll() {
        return ResponseEntity.ok(availableDateService.getAll());
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AvailableDateResponse>> getByDoctorId(@PathVariable Long doctorId) {
        return ResponseEntity.ok(availableDateService.getByDoctorId(doctorId));
    }
}
