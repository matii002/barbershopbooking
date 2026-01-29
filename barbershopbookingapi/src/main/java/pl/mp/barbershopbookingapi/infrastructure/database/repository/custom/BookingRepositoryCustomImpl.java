package pl.mp.barbershopbookingapi.infrastructure.database.repository.custom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.mp.barbershopbookingapi.infrastructure.Status;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.BookingEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class BookingRepositoryCustomImpl implements BookingRepositoryCustom {
    private final EntityManager em;

    public Page<BookingEntity> findBookingByFilters(
            Pageable pageable,
            LocalDateTime startTime,
            Status status,
            String client,
            String employee,
            String service
    ) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BookingEntity> cq = cb.createQuery(BookingEntity.class);
        Root<BookingEntity> booking = cq.from(BookingEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        if (startTime != null) {
            predicates.add(cb.equal(booking.get("startTime"), startTime));
        }
        if (status != null) {
            predicates.add(cb.equal(booking.get("status"), status));
        }
        if (client != null) {
            String[] parts = client.trim().split("\\s", 2);
            String firstName = parts[0];
            String lastName = parts.length > 1 ? parts[1] : null;

            predicates.add(cb.like(booking.get("client").get("firstName"), firstName.toLowerCase() + "%"));

            if (lastName != null) {
                predicates.add(cb.like(booking.get("client").get("lastName"), lastName.toLowerCase() + "%"));
            }
        }
        if (employee != null) {
            String[] parts = employee.trim().split("\\s", 2);
            String firstName = parts[0];
            String lastName = parts.length > 1 ? parts[1] : null;

            predicates.add(cb.like(booking.get("employee").get("firstName"), firstName.toLowerCase() + "%"));

            if (lastName != null) {
                predicates.add(cb.like(booking.get("employee").get("lastName"), lastName.toLowerCase() + "%"));
            }
        }
        if (service != null) {
            predicates.add(cb.like(booking.get("service").get("name"), service.toLowerCase() + "%"));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        TypedQuery<BookingEntity> tq = em.createQuery(cq);
        tq.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        tq.setMaxResults(pageable.getPageSize());

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        countQuery.select(cb.count(countQuery.from(BookingEntity.class)));
        Long totalRows = em.createQuery(countQuery).getSingleResult();

        return new PageImpl<BookingEntity>(tq.getResultList(), pageable, totalRows);
    }
}
