package pl.mp.barbershopbookingapi.infrastructure.database.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.mp.barbershopbookingapi.infrastructure.Status;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.BookingEntity;

import java.time.LocalDateTime;

public interface BookingRepositoryCustom {
    Page<BookingEntity> findBookingByFilters(
            Pageable pageable,
            LocalDateTime startTime,
            Status status,
            String client,
            String employee,
            String service
    );
}
