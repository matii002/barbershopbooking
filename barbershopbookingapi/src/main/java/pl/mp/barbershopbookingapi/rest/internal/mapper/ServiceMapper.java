package pl.mp.barbershopbookingapi.rest.internal.mapper;

import org.springframework.stereotype.Component;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.ServiceEntity;
import pl.mp.barbershopbookingapi.rest.internal.dto.ServiceDto;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.CreateServiceRequest;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.UpdateServiceRequest;

@Component
public class ServiceMapper {
    public ServiceEntity toServiceEntity(final CreateServiceRequest createServiceRequest) {
        return ServiceEntity.builder()
                .name(createServiceRequest.getName())
                .durationMinutes(createServiceRequest.getDurationMinutes())
                .price(createServiceRequest.getPrice())
                .build();
    }

    public ServiceEntity toServiceEntity(final UpdateServiceRequest updateServiceRequest, final ServiceEntity serviceEntity) {
        return serviceEntity.toBuilder()
                .id(updateServiceRequest.getId())
                .name(updateServiceRequest.getName())
                .durationMinutes(updateServiceRequest.getDurationMinutes())
                .price(updateServiceRequest.getPrice())
                .build();
    }

    public ServiceDto toServiceDto(final ServiceEntity serviceEntity) {
        return ServiceDto.builder()
                .id(serviceEntity.getId())
                .name(serviceEntity.getName())
                .durationMinutes(serviceEntity.getDurationMinutes())
                .price(serviceEntity.getPrice())
                .creationDate(serviceEntity.getCreationDate())
                .modificationDate(serviceEntity.getModificationDate())
                .build();
    }
}
