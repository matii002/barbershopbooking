package pl.mp.barbershopbookingapi.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "service")
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int durationMinutes;
    private double price;
    @Builder.Default
    private LocalDateTime creationDate = LocalDateTime.now();
    private LocalDateTime modificationDate;

    @PreUpdate
    private void onUpdate() {
        modificationDate = LocalDateTime.now();
    }
}
