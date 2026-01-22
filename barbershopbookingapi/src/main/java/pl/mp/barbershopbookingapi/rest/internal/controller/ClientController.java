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
import pl.mp.barbershopbookingapi.rest.internal.dto.ClientDto;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.CreateClientRequest;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.UpdateClientRequest;
import pl.mp.barbershopbookingapi.rest.internal.service.ClientService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public Page<ClientDto> getAllClients(Pageable pageable) {
        return clientService.getAllClients(pageable);
    }

    @GetMapping("/search")
    public List<ClientDto> get4MatchedClient(@RequestParam String firstName, @RequestParam String lastName) {
        return clientService.get4MatchedClients(firstName, lastName);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable("id") Integer id) {
        Optional<ClientDto> client = clientService.getClientById(id);

        if (client.isPresent()) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }
    }

    @PostMapping
    public ResponseEntity<?> createClient(@Valid @RequestBody CreateClientRequest createClientRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        clientService.createClient(createClientRequest);

        log.info("Client created");

        return ResponseEntity.status(HttpStatus.CREATED).body("Client created");
    }

    @PutMapping
    public ResponseEntity<?> updateClient(@Valid @RequestBody UpdateClientRequest updateClientRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        clientService.updateClient(updateClientRequest);

        log.info("Client updated");

        return ResponseEntity.ok("Client updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable("id") Integer id) {
        clientService.deleteClient(id);

        log.info("Client deleted");

        return ResponseEntity.ok("Client deleted");
    }

}
