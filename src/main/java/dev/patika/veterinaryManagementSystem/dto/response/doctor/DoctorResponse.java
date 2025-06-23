package dev.patika.veterinaryManagementSystem.dto.response.doctor;

import dev.patika.veterinaryManagementSystem.dto.response.availableDate.AvailableDateResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponse {
    private long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String city;

    List<AvailableDateResponse> availableDates;

}
