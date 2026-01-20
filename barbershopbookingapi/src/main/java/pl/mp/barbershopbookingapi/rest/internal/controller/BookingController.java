package pl.mp.barbershopbookingapi.rest.internal.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import pl.mp.barbershopbookingapi.rest.internal.dto.BookingDto;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.CreateBookingRequest;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.UpdateBookingRequest;
import pl.mp.barbershopbookingapi.rest.internal.service.BookingService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("booking")
public class BookingController {
    private final BookingService bookingService;

    @GetMapping
    public Page<BookingDto> getAllBooking(Pageable pageable) {
        return bookingService.getAllBooking(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable("id") Integer id) {
        Optional<BookingDto> booking = bookingService.getBookingById(id);

        if (booking.isPresent()) {
            return ResponseEntity.ok(booking);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@Valid @RequestBody CreateBookingRequest createBookingRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        bookingService.createBooking(createBookingRequest);

        log.info("Booked");

        return ResponseEntity.status(HttpStatus.CREATED).body("Booked");
    }

    @PutMapping
    public ResponseEntity<?> updateBooking(@Valid @RequestBody UpdateBookingRequest updateBookingRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        bookingService.updateBooking(updateBookingRequest);

        log.info("Booking updated");

        return ResponseEntity.ok("Booking updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable("id") Integer id) {
        bookingService.deleteBooking(id);

        log.info("Booking deleted");

        return ResponseEntity.ok("Booking deleted");
    }
}
