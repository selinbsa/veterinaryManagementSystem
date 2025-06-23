package dev.patika.veterinaryManagementSystem.dto.request.animal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalUpdateRequest {


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
