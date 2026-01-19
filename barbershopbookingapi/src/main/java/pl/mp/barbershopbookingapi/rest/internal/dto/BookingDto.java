package pl.mp.barbershopbookingapi.rest.internal.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BookingDto {
    private LocalDateTime startTime;
    private String Status;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
}
