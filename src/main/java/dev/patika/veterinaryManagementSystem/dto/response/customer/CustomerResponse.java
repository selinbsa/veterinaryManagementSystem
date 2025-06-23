package dev.patika.veterinaryManagementSystem.dto.response.customer;


import dev.patika.veterinaryManagementSystem.dto.response.animal.AnimalResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String city;

    private List<AnimalResponse> animals;
}
