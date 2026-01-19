package pl.mp.barbershopbookingapi.rest.internal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.mp.barbershopbookingapi.infrastructure.database.entity.ClientEntity;
import pl.mp.barbershopbookingapi.infrastructure.database.repository.ClientRepository;
import pl.mp.barbershopbookingapi.rest.internal.dto.ClientDto;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.CreateClientRequest;
import pl.mp.barbershopbookingapi.rest.internal.dto.request.UpdateClientRequest;
import pl.mp.barbershopbookingapi.rest.internal.mapper.ClientMapper;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public Page<ClientDto> getAllClient(Pageable pageable) {
        return clientRepository.findAll(pageable).map(clientMapper::toClientDto);
    }

    public Optional<ClientDto> getClientById(Integer id) {
        return clientRepository.findById(id).map(clientMapper::toClientDto);
    }

    public ClientDto createClient(CreateClientRequest createClientRequest) {
        return clientMapper.toClientDto(
                clientRepository.save(
                        clientMapper.toClientEntity(createClientRequest)
                )
        );
    }

    public ClientDto updateClient(UpdateClientRequest updateClientRequest) {
        ClientEntity clientEntity = clientRepository.findById(updateClientRequest.getId()).orElseThrow();
        return clientMapper.toClientDto(
                clientRepository.save(
                        clientMapper.toClientEntity(updateClientRequest, clientEntity)
                )
        );
    }

    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }
}
