package dev.patika.veterinaryManagementSystem.dto.response.vaccine;

import java.time.LocalDate;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccineResponse {
    private Long id;
    private String name;
    private String code;
    private LocalDate protectionStartDate;
    private LocalDate protectionFinishDate;
    private Long animalId;  // Sadece animalId gönderiyoruz, detaylı animal bilgisi istersen ayrı dto kullanılır
}
