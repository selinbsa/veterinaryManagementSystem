package dev.patika.veterinaryManagementSystem.dto.response.availableDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateResponse {

    private Long id;
    private Long doctorId;
    private String doctorName;
    private LocalDate availableDate;

}
