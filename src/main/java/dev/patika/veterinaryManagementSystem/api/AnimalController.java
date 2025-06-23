package dev.patika.veterinaryManagementSystem.api;

import dev.patika.veterinaryManagementSystem.business.abstracts.IAnimalService;
import dev.patika.veterinaryManagementSystem.dto.request.animal.AnimalSaveRequest;
import dev.patika.veterinaryManagementSystem.dto.request.animal.AnimalUpdateRequest;
import dev.patika.veterinaryManagementSystem.dto.response.animal.AnimalResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/animals", produces = "application/json")
public class AnimalController {
    private final IAnimalService animalService;

    public AnimalController(IAnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public ResponseEntity<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest request) {
        return new ResponseEntity<>(animalService.save(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalResponse> update(@PathVariable Long id, @Valid @RequestBody AnimalUpdateRequest request) {
        return ResponseEntity.ok(animalService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        animalService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(animalService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<AnimalResponse>> getAll() {
        return ResponseEntity.ok(animalService.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<AnimalResponse>> getByName(@RequestParam String name) {
        return ResponseEntity.ok(animalService.getByName(name));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<AnimalResponse>> getByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(animalService.getByCustomerId(customerId));
    }

}
