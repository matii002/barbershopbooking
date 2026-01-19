package pl.mp.barbershopbookingapi.rest.internal.mapper;

import org.springframework.stereotype.Component;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.ServiceEntity;
import pl.mp.barbershopbookingapi.rest.internal.dto.ServiceDto;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.CreateServiceRequest;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.UpdateServiceRequest;

@Component
public class ServiceMapper {
    //    create
    public ServiceEntity toServiceEntity(final CreateServiceRequest createServiceRequest) {
        return ServiceEntity.builder()
                .name(createServiceRequest.getName())
                .durationMinutes(createServiceRequest.getDurationMinutes())
                .price(createServiceRequest.getPrice())
                .build();
    }

    //    update
    public ServiceEntity toServiceEntity(final UpdateServiceRequest updateServiceRequest, final ServiceEntity serviceEntity) {
        return serviceEntity.toBuilder()
                .id(updateServiceRequest.getId())
                .name(updateServiceRequest.getName())
                .durationMinutes(updateServiceRequest.getDurationMinutes())
                .price(updateServiceRequest.getPrice())
                .build();
    }

    //    find
    public ServiceDto toServiceDto(final UpdateServiceRequest updateServiceRequest) {
        return ServiceDto.builder()
                .id(updateServiceRequest.getId())
                .name(updateServiceRequest.getName())
                .durationMinutes(updateServiceRequest.getDurationMinutes())
                .price(updateServiceRequest.getPrice())
                .build();
    }
}
