package pl.mp.barbershopbookingapi.infrastructure.database.repository.custom;

import pl.mp.barbershopbookingapi.infrastructure.Status;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.BookingEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepositoryCustom {
    List<BookingEntity> findBookingByFilters(
            LocalDateTime startTime,
            Status status,
            String client,
            String employee,
            String service
    );
}
