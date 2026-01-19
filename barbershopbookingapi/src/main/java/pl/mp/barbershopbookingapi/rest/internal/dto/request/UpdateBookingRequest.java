package pl.mp.barbershopbookingapi.rest.internal.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pl.mp.barbershopbookingapi.infrastructure.Status;

import java.time.LocalDateTime;

@Getter
public class UpdateBookingRequest {
    @NotNull(message = "id can not be null")
    private Integer id;
    @Future(message = "startTime must be in future")
    @NotNull(message = "startTime is required")
    private LocalDateTime startTime;
    @NotNull(message = "status is required")
    private Status status;
}
