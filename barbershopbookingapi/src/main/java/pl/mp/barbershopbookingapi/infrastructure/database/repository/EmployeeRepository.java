package pl.mp.barbershopbookingapi.infrastructure.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    List<EmployeeEntity> findTop4ByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(
            String firstName,
            String lastName
    );
}
