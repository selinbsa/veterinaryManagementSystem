package dev.patika.veterinaryManagementSystem.api;

import dev.patika.veterinaryManagementSystem.business.abstracts.IAppointmentService;
import dev.patika.veterinaryManagementSystem.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.veterinaryManagementSystem.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.veterinaryManagementSystem.dto.response.appointment.AppointmentResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/appointments", produces = "application/json")
public class AppointmentController {

    private final IAppointmentService appointmentService;

    public AppointmentController(IAppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<AppointmentResponse> save(@Valid @RequestBody AppointmentSaveRequest request) {
        AppointmentResponse response = appointmentService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponse> update(@PathVariable Long id, @Valid @RequestBody AppointmentUpdateRequest request) {
        AppointmentResponse response = appointmentService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        appointmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponse> getById(@PathVariable Long id) {
        AppointmentResponse response = appointmentService.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> getAll() {
        List<AppointmentResponse> list = appointmentService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/search/doctor")
    public ResponseEntity<List<AppointmentResponse>> getByDoctorIdAndDateRange(
            @RequestParam Long doctorId,
            @RequestParam String start,
            @RequestParam String end) {
        LocalDateTime startDate = LocalDateTime.parse(start);
        LocalDateTime endDate = LocalDateTime.parse(end);
        List<AppointmentResponse> list = appointmentService.getByDoctorIdAndDateRange(doctorId, startDate, endDate);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/search/animal")
    public ResponseEntity<List<AppointmentResponse>> getByAnimalIdAndDateRange(
            @RequestParam Long animalId,
            @RequestParam String start,
            @RequestParam String end) {
        LocalDateTime startDate = LocalDateTime.parse(start);
        LocalDateTime endDate = LocalDateTime.parse(end);
        List<AppointmentResponse> list = appointmentService.getByAnimalIdAndDateRange(animalId, startDate, endDate);
        return ResponseEntity.ok(list);
    }
}
