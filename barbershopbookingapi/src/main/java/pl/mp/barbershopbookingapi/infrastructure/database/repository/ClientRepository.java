package pl.mp.barbershopbookingapi.infrastructure.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {
}
