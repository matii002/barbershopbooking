package pl.mp.barbershopbookingapi.rest.internal.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class EmployeeDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
}
