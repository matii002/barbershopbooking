package pl.mp.barbershopbookingapi.rest.internal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.ServiceEntity;
import pl.mp.barbershopbookingapi.infrastructure.database.repository.ServiceRepository;
import pl.mp.barbershopbookingapi.rest.internal.dto.ServiceDto;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.CreateServiceRequest;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.UpdateServiceRequest;
import pl.mp.barbershopbookingapi.rest.internal.mapper.ServiceMapper;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ServiceBarberService {
    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    public Page<ServiceDto> getAllService(Pageable pageable) {
        return serviceRepository.findAll(pageable).map(serviceMapper::toServiceDto);
    }

    public Optional<ServiceDto> getServiceById(Integer id) {
        return serviceRepository.findById(id).map(serviceMapper::toServiceDto);
    }

    public ServiceDto createService(CreateServiceRequest createServiceRequest) {
        return serviceMapper.toServiceDto(
                serviceRepository.save(
                        serviceMapper.toServiceEntity(createServiceRequest)));
    }

    public ServiceDto updateService(UpdateServiceRequest updateServiceRequest) {
        ServiceEntity serviceEntity = serviceRepository.findById(updateServiceRequest.getId()).orElseThrow();
        return serviceMapper.toServiceDto(
                serviceRepository.save(
                        serviceMapper.toServiceEntity(updateServiceRequest, serviceEntity)));
    }
}
