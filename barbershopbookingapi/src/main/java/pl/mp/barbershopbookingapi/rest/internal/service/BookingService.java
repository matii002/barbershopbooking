package pl.mp.barbershopbookingapi.rest.internal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.mp.barbershopbookingapi.infrastructure.Status;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.BookingEntity;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.ClientEntity;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.EmployeeEntity;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.ServiceEntity;
import pl.mp.barbershopbookingapi.infrastructure.database.repository.BookingRepository;
import pl.mp.barbershopbookingapi.infrastructure.database.repository.ClientRepository;
import pl.mp.barbershopbookingapi.infrastructure.database.repository.EmployeeRepository;
import pl.mp.barbershopbookingapi.infrastructure.database.repository.ServiceRepository;
import pl.mp.barbershopbookingapi.rest.internal.dto.BookingDto;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.CreateBookingRequest;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.UpdateBookingRequest;
import pl.mp.barbershopbookingapi.rest.internal.mapper.BookingMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final ServiceRepository serviceRepository;
    private final BookingMapper bookingMapper;

    public Page<BookingDto> getAllBookings(Pageable pageable) {
        return bookingRepository.findAll(pageable).map(bookingMapper::toBookingDto);
    }

    public List<BookingDto> getFilteredBookings(
            LocalDateTime startTime,
            Status status,
            String client,
            String employee,
            String service
    ) {
        List<BookingEntity> filteredBookings = bookingRepository.findBookingByFilters(
                startTime,
                status,
                client,
                employee,
                service
        );

        return filteredBookings.stream().map(bookingMapper::toBookingDto).collect(Collectors.toList());
    }

    public Optional<BookingDto> getBookingById(Integer id) {
        return bookingRepository.findById(id).map(bookingMapper::toBookingDto);
    }

    public void createBooking(CreateBookingRequest createBookingRequest) {
        ClientEntity client = clientRepository.findById(createBookingRequest.getClientId()).orElseThrow();
        EmployeeEntity employee = employeeRepository.findById(createBookingRequest.getEmployeeId()).orElseThrow();
        ServiceEntity service = serviceRepository.findById(createBookingRequest.getServiceId()).orElseThrow();

        bookingMapper.toBookingDto(bookingRepository.save(
                        bookingMapper.toBookingEntity(
                                createBookingRequest,
                                client,
                                employee,
                                service
                        )
                )
        );
    }

    public void updateBooking(UpdateBookingRequest updateBookingRequest) {
        ClientEntity client = clientRepository.findById(updateBookingRequest.getClientId()).orElseThrow();
        EmployeeEntity employee = employeeRepository.findById(updateBookingRequest.getEmployeeId()).orElseThrow();
        ServiceEntity service = serviceRepository.findById(updateBookingRequest.getServiceId()).orElseThrow();

        BookingEntity bookingEntity = bookingRepository.findById(updateBookingRequest.getId()).orElseThrow();
        bookingMapper.toBookingDto(
                bookingRepository.save(
                        bookingMapper.toBookingEntity(
                                updateBookingRequest,
                                bookingEntity,
                                client,
                                employee,
                                service
                        )
                )
        );
    }

    public void deleteBooking(Integer id) {
        bookingRepository.deleteById(id);
    }
}
