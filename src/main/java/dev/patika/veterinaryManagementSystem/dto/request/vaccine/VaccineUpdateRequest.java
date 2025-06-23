package dev.patika.veterinaryManagementSystem.dto.request.vaccine;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccineUpdateRequest {
    @Positive
    private Long id;

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
