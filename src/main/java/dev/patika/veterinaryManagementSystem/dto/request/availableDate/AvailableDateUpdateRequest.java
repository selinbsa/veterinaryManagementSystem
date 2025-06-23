package dev.patika.veterinaryManagementSystem.dto.request.availableDate;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AvailableDateUpdateRequest {

    @NotNull(message = "Doctor ID cannot be blank")
    private Long doctorId;

    @NotNull(message = "Available date cannot be blank")
    private LocalDate availableDate;

}
