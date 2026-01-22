package pl.mp.barbershopbookingapi.infrastructure.database.repository.custom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.mp.barbershopbookingapi.infrastructure.Status;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.BookingEntity;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.ClientEntity;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.EmployeeEntity;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.ServiceEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class BookingRepositoryCustomImpl implements BookingRepositoryCustom {
    private final EntityManager em;

    public List<BookingEntity> findBookingByFilters(LocalDateTime startTime, Status status, ClientEntity client, EmployeeEntity employee, ServiceEntity service) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BookingEntity> cq = cb.createQuery(BookingEntity.class);

        Root<BookingEntity> booking = cq.from(BookingEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        if(startTime != null) {
            predicates.add(cb.equal(booking.get("startTime"), startTime));
        }
        if(status != null) {
            cb.equal(booking.get("status"), status);
        }
        if(client != null) {
            cb.equal(booking.get("client"), client);
        }
        if(employee != null) {
            cb.equal(booking.get("employee"), employee);
        }
        if(service != null) {
            cb.equal(booking.get("service"), service);
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }
}
