package pl.mp.barbershopbookingapi.rest.internal.mapper;

import org.springframework.stereotype.Component;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.BookingEntity;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.ClientEntity;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.EmployeeEntity;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.ServiceEntity;
import pl.mp.barbershopbookingapi.rest.internal.dto.BookingDto;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.CreateBookingRequest;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.UpdateBookingRequest;

@Component
public class BookingMapper {
    //    createBooking
    public BookingEntity toBookingEntity(final CreateBookingRequest createBookingRequest,
                                         final ClientEntity client,
                                         final EmployeeEntity employee,
                                         final ServiceEntity service
    ) {
        return BookingEntity.builder()
                .startTime(createBookingRequest.getStartTime())
                .status(createBookingRequest.getStatus())
                .client(client)
                .employee(employee)
                .service(service)
                .build();
    }

    //    updateBooking
    public BookingEntity toBookingEntity(final UpdateBookingRequest updateBookingRequest,
                                         final BookingEntity bookingEntity,
                                         final ClientEntity client,
                                         final EmployeeEntity employee,
                                         final ServiceEntity service
    ) {
        return bookingEntity.toBuilder()
                .id(updateBookingRequest.getId())
                .startTime(updateBookingRequest.getStartTime())
                .status(updateBookingRequest.getStatus())
                .client(client)
                .employee(employee)
                .service(service)
                .build();
    }

    //    findBooking
    public BookingDto toBookingDto(final BookingEntity bookingEntity) {
        return BookingDto.builder()
                .id(bookingEntity.getId())
                .startTime(bookingEntity.getStartTime())
                .status(bookingEntity.getStatus().name())
                .clientId(bookingEntity.getClient().getId())
                .clientName(bookingEntity.getClient().getFirstName() + " " + bookingEntity.getClient().getLastName())
                .employeeId(bookingEntity.getEmployee().getId())
                .employeeName(bookingEntity.getEmployee().getFirstName() + " " + bookingEntity.getEmployee().getLastName())
                .serviceId(bookingEntity.getService().getId())
                .serviceName(bookingEntity.getService().getName())
                .creationDate(bookingEntity.getCreationDate())
                .modificationDate(bookingEntity.getModificationDate())
                .build();
    }
}
