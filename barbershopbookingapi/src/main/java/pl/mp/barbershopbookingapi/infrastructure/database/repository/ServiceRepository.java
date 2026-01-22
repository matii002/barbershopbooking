package pl.mp.barbershopbookingapi.infrastructure.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.ServiceEntity;

import java.util.List;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Integer> {
    List<ServiceEntity> findTop4ByNameContainingIgnoreCase(String name);
}
