package pl.mp.barbershopbookingapi.rest.internal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.EmployeeEntity;
import pl.mp.barbershopbookingapi.infrastructure.database.repository.EmployeeRepository;
import pl.mp.barbershopbookingapi.rest.internal.dto.EmployeeDto;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.CreateEmployeeRequest;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.UpdateEmployeeRequest;
import pl.mp.barbershopbookingapi.rest.internal.mapper.EmployeeMapper;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public Page<EmployeeDto> getAllEmployee(Pageable pageable) {
        return employeeRepository.findAll(pageable).map(employeeMapper::toEmployeeDto);
    }

    public Optional<EmployeeDto> getEmployeeById(Integer id) {
        return employeeRepository.findById(id).map(employeeMapper::toEmployeeDto);
    }

    public void createEmployee(CreateEmployeeRequest createEmployeeRequest) {
        employeeMapper.toEmployeeDto(
                employeeRepository.save(
                        employeeMapper.toEmployeeEntity(createEmployeeRequest)
                )
        );
    }

    public void updateEmployee(UpdateEmployeeRequest updateEmployeeRequest) {
        EmployeeEntity employeeEntity = employeeRepository.findById(updateEmployeeRequest.getId()).orElseThrow();
        employeeMapper.toEmployeeDto(
                employeeRepository.save(
                        employeeMapper.toEmployeeEntity(updateEmployeeRequest, employeeEntity)
                )
        );
    }

    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }
}
