package dev.patika.veterinaryManagementSystem.dto.response.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {

    private Long id;
    private Long doctorId;
    private String doctorName;
    private Long animalId;
    private String animalName;
    private LocalDateTime appointmentDate;

}
