package pl.mp.barbershopbookingapi.rest.internal.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BookingDto {
    private int id;
    private LocalDateTime startTime;
    private String status;
    private int clientId;
    private String clientName;
    private int employeeId;
    private String employeeName;
    private int serviceId;
    private String serviceName;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
}
