package dev.patika.veterinaryManagementSystem.api;

import dev.patika.veterinaryManagementSystem.business.abstracts.IAnimalService;
import dev.patika.veterinaryManagementSystem.business.abstracts.ICustomerService;
import dev.patika.veterinaryManagementSystem.dto.request.customer.CustomerSaveRequest;
import dev.patika.veterinaryManagementSystem.dto.request.customer.CustomerUpdateRequest;
import dev.patika.veterinaryManagementSystem.dto.response.animal.AnimalResponse;
import dev.patika.veterinaryManagementSystem.dto.response.customer.CustomerResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/customers", produces = "application/json")
public class CustomerController {

    private final ICustomerService customerService;
    private final IAnimalService animalService; // ✅ EKLENDİ

    public CustomerController(ICustomerService customerService, IAnimalService animalService) {
        this.customerService = customerService;
        this.animalService = animalService; // ✅ EKLENDİ
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> save(@Valid @RequestBody CustomerSaveRequest request) {
        return new ResponseEntity<>(customerService.save(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(@PathVariable Long id, @Valid @RequestBody CustomerUpdateRequest request) {
        return ResponseEntity.ok(customerService.update(id, request));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAll() {
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<CustomerResponse>> getByName(@RequestParam String name) {
        return ResponseEntity.ok(customerService.getByName(name));
    }

    @GetMapping("/{customerId}/animals")
    public ResponseEntity<List<AnimalResponse>> getCustomerAnimals(@PathVariable Long customerId) {
        return ResponseEntity.ok(animalService.getByCustomerId(customerId));
    }


}
