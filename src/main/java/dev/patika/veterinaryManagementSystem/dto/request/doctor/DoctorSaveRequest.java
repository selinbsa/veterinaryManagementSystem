package dev.patika.veterinaryManagementSystem.dto.request.doctor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DoctorSaveRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Phone cannot be blank")
    private String phone;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank
    private String address;

    @NotBlank
    private String city;
}
