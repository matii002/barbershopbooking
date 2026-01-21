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
    @NotNull(message = "clientId is required")
    private Integer clientId;
    @NotNull(message = "employeeId is required")
    private Integer employeeId;
    @NotNull(message = "serviceId is required")
    private Integer serviceId;
}
