package pl.mp.barbershopbookingapi.rest.internal.mapper;

import org.springframework.stereotype.Component;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.ClientEntity;
import pl.mp.barbershopbookingapi.rest.internal.dto.ClientDto;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.CreateClientRequest;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.UpdateClientRequest;

@Component
public class ClientMapper {
    //    createClient
    public ClientEntity toClientEntity(final CreateClientRequest createClientRequest) {
        return ClientEntity.builder()
                .firstName(createClientRequest.getFirstName())
                .lastName(createClientRequest.getLastName())
                .email(createClientRequest.getEmail())
                .phone(createClientRequest.getPhone())
                .build();
    }

    //    updateClient
    public ClientEntity toClientEntity(final UpdateClientRequest updateClientRequest, final ClientEntity clientEntity) {
        return clientEntity.toBuilder()
                .id(updateClientRequest.getId())
                .firstName(updateClientRequest.getFirstName())
                .lastName(updateClientRequest.getLastName())
                .email(updateClientRequest.getEmail())
                .phone(updateClientRequest.getPhone())
                .build();
    }

    //    findClient
    public ClientDto toClientDto(final ClientEntity clientEntity) {
        return ClientDto.builder()
                .id(clientEntity.getId())
                .firstName(clientEntity.getFirstName())
                .lastName(clientEntity.getLastName())
                .email(clientEntity.getEmail())
                .phone(clientEntity.getPhone())
                .creationDate(clientEntity.getCreationDate())
                .modificationDate(clientEntity.getModificationDate())
                .build();
    }
}
