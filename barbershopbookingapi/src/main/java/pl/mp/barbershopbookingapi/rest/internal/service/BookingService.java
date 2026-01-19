package pl.mp.barbershopbookingapi.rest.internal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.BookingEntity;
import pl.mp.barbershopbookingapi.infrastructure.database.repository.BookingRepository;
import pl.mp.barbershopbookingapi.rest.internal.dto.BookingDto;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.CreateBookingRequest;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.UpdateBookingRequest;
import pl.mp.barbershopbookingapi.rest.internal.mapper.BookingMapper;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public Page<BookingDto> getAllBooking(Pageable pageable) {
        return bookingRepository.findAll(pageable).map(bookingMapper::toBookingDto);
    }

    public Optional<BookingDto> getBookingById(Integer id) {
        return bookingRepository.findById(id).map(bookingMapper::toBookingDto);
    }

    public BookingDto createBooking(CreateBookingRequest createBookingRequest) {
        return bookingMapper.toBookingDto(bookingRepository.save(bookingMapper.toBookingEntity(createBookingRequest)));
    }

    public BookingDto updateBooking(UpdateBookingRequest updateBookingRequest) {
        BookingEntity bookingEntity = bookingRepository.findById(updateBookingRequest.getId()).orElseThrow();
        return bookingMapper.toBookingDto(
                bookingRepository.save(
                bookingMapper.toBookingEntity(updateBookingRequest, bookingEntity)));
    }

    public void deleteBooking(Integer id) {
        bookingRepository.deleteById(id);
    }
}
