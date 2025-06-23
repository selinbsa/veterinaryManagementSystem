package dev.patika.veterinaryManagementSystem.api;

import dev.patika.veterinaryManagementSystem.business.abstracts.IVaccineService;
import dev.patika.veterinaryManagementSystem.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.veterinaryManagementSystem.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.veterinaryManagementSystem.dto.response.vaccine.VaccineResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/vaccines", produces = "application/json")
public class VaccineController {

    private final IVaccineService vaccineService;

    public VaccineController(IVaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @PostMapping
    public ResponseEntity<VaccineResponse> save(@Valid @RequestBody VaccineSaveRequest request) {
        return new ResponseEntity<>(vaccineService.save(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VaccineResponse> update(@PathVariable Long id, @Valid @RequestBody VaccineUpdateRequest request) {
        return ResponseEntity.ok(vaccineService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vaccineService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VaccineResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(vaccineService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<VaccineResponse>> getAll() {
        return ResponseEntity.ok(vaccineService.getAll());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<VaccineResponse>> getVaccinesByProtectionFinishDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        List<VaccineResponse> vaccines = vaccineService.getVaccinesByProtectionFinishDateRange(startDate, endDate);
        return ResponseEntity.ok(vaccines);
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<VaccineResponse>> getVaccinesByAnimalId(@PathVariable Long animalId) {
        return ResponseEntity.ok(vaccineService.getByAnimalId(animalId));
    }


}
