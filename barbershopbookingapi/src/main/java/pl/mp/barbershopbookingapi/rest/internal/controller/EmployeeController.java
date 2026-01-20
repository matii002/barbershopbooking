package pl.mp.barbershopbookingapi.rest.internal.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mp.barbershopbookingapi.rest.internal.dto.EmployeeDto;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.CreateEmployeeRequest;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.UpdateEmployeeRequest;
import pl.mp.barbershopbookingapi.rest.internal.service.EmployeeService;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public Page<EmployeeDto> getAllEmployee(Pageable pageable) {
        return employeeService.getAllEmployee(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") Integer id) {
        Optional<EmployeeDto> employee = employeeService.getEmployeeById(id);

        if (employee.isPresent()) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }
    }

    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest) {
        employeeService.createEmployee(createEmployeeRequest);

        log.info("Employee created");

        return ResponseEntity.status(HttpStatus.CREATED).body("Employee created");
    }

    @PutMapping
    public ResponseEntity<String> updateEmployee(@RequestBody UpdateEmployeeRequest updateEmployeeRequest) {
        employeeService.updateEmployee(updateEmployeeRequest);

        log.info("Employee updated");

        return ResponseEntity.ok("Employee updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Integer id) {
        employeeService.deleteEmployee(id);

        log.info("Employee deleted");

        return ResponseEntity.ok("Employee deleted");
    }
}
