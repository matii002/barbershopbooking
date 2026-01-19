package pl.mp.barbershopbookingapi.rest.internal.mapper;

import org.springframework.stereotype.Component;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.EmployeeEntity;
import pl.mp.barbershopbookingapi.rest.internal.dto.EmployeeDto;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.CreateEmployeeRequest;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.UpdateEmployeeRequest;

@Component
public class EmployeeMapper {
    //    createEmployee
    public EmployeeEntity toEmployeeEntity(final CreateEmployeeRequest createEmployeeRequest) {
        return EmployeeEntity.builder()
                .firstName(createEmployeeRequest.getFirstName())
                .lastName(createEmployeeRequest.getLastName())
                .email(createEmployeeRequest.getEmail())
                .phone(createEmployeeRequest.getPhone())
                .build();
    }

    //    updateEmployee
    public EmployeeEntity toEmployeeEntity(final UpdateEmployeeRequest updateEmployeeRequest, final EmployeeEntity employeeEntity) {
        return employeeEntity.toBuilder()
                .id(updateEmployeeRequest.getId())
                .firstName(updateEmployeeRequest.getFirstName())
                .lastName(updateEmployeeRequest.getLastName())
                .email(updateEmployeeRequest.getEmail())
                .phone(updateEmployeeRequest.getPhone())
                .build();
    }

    //    findEmployee
    public EmployeeDto toEmployeeDto(final EmployeeEntity employeeEntity) {
        return EmployeeDto.builder()
                .id(employeeEntity.getId())
                .firstName(employeeEntity.getFirstName())
                .lastName(employeeEntity.getLastName())
                .email(employeeEntity.getEmail())
                .phone(employeeEntity.getPhone())
                .build();
    }
}
