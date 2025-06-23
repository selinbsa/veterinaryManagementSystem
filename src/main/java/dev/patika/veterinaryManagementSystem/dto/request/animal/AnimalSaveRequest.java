package dev.patika.veterinaryManagementSystem.dto.request.animal;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnimalSaveRequest {

    @NotNull(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Species cannot be blank")
    private String species;

    @NotNull(message = "Breed cannot be blank")
    private String breed;

    @NotNull(message = "Gender cannot be blank")
    private String gender;

    @NotNull(message = "Colour cannot be blank")
    private String colour;

    @NotNull(message = "Date of birth cannot be blank")
    private LocalDate dateOfBirth;

    @NotNull(message = "Customer ID cannot be null")
    private Long customerId;
}
