package pl.mp.barbershopbookingapi.rest.internal.mapper;

import org.springframework.stereotype.Component;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.BookingEntity;
import pl.mp.barbershopbookingapi.rest.internal.dto.BookingDto;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.CreateBookingRequest;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.UpdateBookingRequest;

@Component
public class BookingMapper {
    //    createBooking
    public BookingEntity toBookingEntity(final CreateBookingRequest createBookingRequest) {
        return BookingEntity.builder()
                .startTime(createBookingRequest.getStartTime())
                .status(createBookingRequest.getStatus())
                .build();
    }

    //    updateBooking
    public BookingEntity toBookingEntity(final UpdateBookingRequest updateBookingRequest, final BookingEntity bookingEntity) {
        return bookingEntity.toBuilder()
                .id(updateBookingRequest.getId())
                .startTime(updateBookingRequest.getStartTime())
                .status(updateBookingRequest.getStatus())
                .build();
    }

    //    findBooking
    public BookingDto toBookingDto(final BookingEntity bookingEntity) {
        return BookingDto.builder()
                .id(bookingEntity.getId())
                .startTime(bookingEntity.getStartTime())
                .status(bookingEntity.getStatus().name())
                .creationDate(bookingEntity.getCreationDate())
                .modificationDate(bookingEntity.getModificationDate())
                .build();
    }
}
