package dev.patika.veterinaryManagementSystem.dto.request.vaccine;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccineSaveRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String code;

    @NotNull
    private LocalDate protectionStartDate;

    @NotNull
    private LocalDate protectionFinishDate;

    @NotNull(message = "Animal ID cannot be null")
    private Long animalId;
}
