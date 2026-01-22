package pl.mp.barbershopbookingapi.infrastructure.database.repository.custom;

import pl.mp.barbershopbookingapi.infrastructure.Status;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.BookingEntity;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.ClientEntity;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.EmployeeEntity;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.ServiceEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepositoryCustom {
    List<BookingEntity> findBookingByFilters(LocalDateTime startTime, Status status, ClientEntity client, EmployeeEntity employee, ServiceEntity service);
}
