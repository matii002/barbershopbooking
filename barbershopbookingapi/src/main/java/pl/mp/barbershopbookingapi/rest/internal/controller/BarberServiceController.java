package pl.mp.barbershopbookingapi.rest.internal.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mp.barbershopbookingapi.rest.internal.dto.ServiceDto;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.CreateServiceRequest;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.UpdateServiceRequest;
import pl.mp.barbershopbookingapi.rest.internal.service.ServiceBarberService;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/barber-service")
public class BarberServiceController {
    private final ServiceBarberService serviceBarberService;

    @GetMapping
    public Page<ServiceDto> getAllBarberService(Pageable pageable) {
        return serviceBarberService.getAllBarberService(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBarberServiceById(@PathVariable("id") Integer id) {
        Optional<ServiceDto> service = serviceBarberService.getBarberServiceById(id);

        if (service.isPresent()) {
            return ResponseEntity.ok(service);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }
    }

    @PostMapping
    public ResponseEntity<String> createBarberService(@RequestBody CreateServiceRequest createServiceRequest) {
        serviceBarberService.createBarber(createServiceRequest);

        log.info("Barber service created");

        return ResponseEntity.status(HttpStatus.CREATED).body("Barber service created");
    }

    @PutMapping
    public ResponseEntity<String> updateBarberService(@RequestBody UpdateServiceRequest updateServiceRequest) {
        serviceBarberService.updateBarber(updateServiceRequest);

        log.info("Barber service updated");

        return ResponseEntity.ok("Barber service updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBarberService(@PathVariable("id") Integer id) {
        serviceBarberService.deleteBarberService(id);

        log.info("Barber service deleted");

        return ResponseEntity.ok("Barber service deleted");
    }
}
