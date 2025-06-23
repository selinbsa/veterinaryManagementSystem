package dev.patika.veterinaryManagementSystem.dto.response.animal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalResponse {

    private long id;

    private String name;

    private String species;

    private String breed;

    private String gender;

    private String colour;

    private LocalDate dateOfBirth;

    private String customerName;

}
