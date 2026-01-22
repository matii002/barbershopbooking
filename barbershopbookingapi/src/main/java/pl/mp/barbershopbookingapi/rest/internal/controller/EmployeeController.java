package pl.mp.barbershopbookingapi.rest.internal.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import pl.mp.barbershopbookingapi.rest.internal.dto.EmployeeDto;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.CreateEmployeeRequest;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.UpdateEmployeeRequest;
import pl.mp.barbershopbookingapi.rest.internal.service.EmployeeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public Page<EmployeeDto> getAllEmployees(Pageable pageable) {
        return employeeService.getAllEmployees(pageable);
    }

    @GetMapping("/search")
    public List<EmployeeDto> get4MatchedEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.get4MatchedEmployee(firstName, lastName);
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
    public ResponseEntity<?> createEmployee(@Valid @RequestBody CreateEmployeeRequest createEmployeeRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        employeeService.createEmployee(createEmployeeRequest);

        log.info("Employee created");

        return ResponseEntity.status(HttpStatus.CREATED).body("Employee created");
    }

    @PutMapping
    public ResponseEntity<?> updateEmployee(@Valid @RequestBody UpdateEmployeeRequest updateEmployeeRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

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
