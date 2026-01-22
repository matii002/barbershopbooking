package pl.mp.barbershopbookingapi.infrastructure.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.ClientEntity;

import java.util.List;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {
    List<ClientEntity> findTop4ByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(
            String firstName,
            String lastName
    );
}
