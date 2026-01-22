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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ServiceBarberService {
    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    public Page<ServiceDto> getAllBarberServices(Pageable pageable) {
        return serviceRepository.findAll(pageable).map(serviceMapper::toServiceDto);
    }

    public List<ServiceDto> get4MatchedBarberServices(String service) {
        return serviceRepository.findTop4ByNameContainingIgnoreCase(service)
                .stream()
                .map(serviceMapper::toServiceDto)
                .collect(Collectors.toList());
    }

    public Optional<ServiceDto> getBarberServiceById(Integer id) {
        return serviceRepository.findById(id).map(serviceMapper::toServiceDto);
    }

    public void createBarber(CreateServiceRequest createServiceRequest) {
        serviceMapper.toServiceDto(
                serviceRepository.save(
                        serviceMapper.toServiceEntity(createServiceRequest)));
    }

    public void updateBarber(UpdateServiceRequest updateServiceRequest) {
        ServiceEntity serviceEntity = serviceRepository.findById(updateServiceRequest.getId()).orElseThrow();
        serviceMapper.toServiceDto(
                serviceRepository.save(
                        serviceMapper.toServiceEntity(updateServiceRequest, serviceEntity)));
    }

    public void deleteBarberService(Integer id) {
        serviceRepository.deleteById(id);
    }
}
