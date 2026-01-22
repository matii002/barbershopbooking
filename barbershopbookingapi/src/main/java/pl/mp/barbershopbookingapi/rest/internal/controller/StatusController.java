package pl.mp.barbershopbookingapi.rest.internal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mp.barbershopbookingapi.infrastructure.Status;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/status")
public class StatusController {
    @GetMapping
    public List<Status> getStatuses() {
        return Arrays.asList(Status.values());
    }
}
