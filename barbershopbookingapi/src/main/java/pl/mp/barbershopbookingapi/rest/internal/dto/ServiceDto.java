package pl.mp.barbershopbookingapi.rest.internal.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class ServiceDto {
    private int id;
    private String name;
    private int durationMinutes;
    private BigDecimal price;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
}
