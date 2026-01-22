package pl.mp.barbershopbookingapi.infrastructure.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.BookingEntity;
import pl.mp.barbershopbookingapi.infrastructure.database.repository.custom.BookingRepositoryCustom;

public interface BookingRepository extends JpaRepository<BookingEntity, Integer>, BookingRepositoryCustom {
}
