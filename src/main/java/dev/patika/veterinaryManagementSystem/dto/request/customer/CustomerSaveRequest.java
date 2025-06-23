package dev.patika.veterinaryManagementSystem.dto.request.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerSaveRequest {

    @NotNull(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Phone cannot be bank")
    private String phone;

    @Email(message = "Email should be valid")
    private String email;

    private String address;

    private String city;


}
