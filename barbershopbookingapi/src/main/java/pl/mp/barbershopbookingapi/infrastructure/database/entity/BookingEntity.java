package pl.mp.barbershopbookingapi.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking")
public class BookingEntity {
    @Id
    private int id;
    @Column(nullable = false)
    private LocalDateTime startTime;
    @Column(nullable = false)
    private String status;//utworzyć ENUM ze statusami
    @Builder.Default
    private LocalDateTime creationDate = LocalDateTime.now();
    private LocalDateTime modificationDate;

    @PreUpdate
    private void onUpdate() {
        modificationDate = LocalDateTime.now();
    }
    //private ClientEntity client;
    //private EmployeeEntity employee;
    //private ServiceEntity service;
}
