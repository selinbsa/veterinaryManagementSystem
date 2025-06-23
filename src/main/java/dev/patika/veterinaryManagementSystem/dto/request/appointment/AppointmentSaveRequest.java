package dev.patika.veterinaryManagementSystem.dto.request.appointment;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentSaveRequest {

    @NotNull(message = "Doctor ID cannot be blank")
    private Long doctorId;

    @NotNull(message = "Animal ID cannot be blank")
    private Long animalId;

    @NotNull(message = "Appointment date cannot be blank")
    private LocalDateTime appointmentDate;

}
