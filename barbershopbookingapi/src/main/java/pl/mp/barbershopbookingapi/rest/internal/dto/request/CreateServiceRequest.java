package pl.mp.barbershopbookingapi.rest.internal.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreateServiceRequest {
    @NotBlank(message = "name can not be blank")
    private String name;
    @Max(message = "max value is 600", value = 600)
    @NotNull(message = "durationMinutes is required")
    private Integer durationMinutes;
    @Digits(message = "price should be valid", integer = 10, fraction = 2)
    private BigDecimal price;
}
