package com.keningarcia.sistema_entrega_productos_flota.service;

import com.keningarcia.sistema_entrega_productos_flota.dto.ClienteDTO;
import com.keningarcia.sistema_entrega_productos_flota.entity.Cliente;
import com.keningarcia.sistema_entrega_productos_flota.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteDTO create(Cliente cliente) {
        return toDTO(clienteRepository.save(cliente));
    }

    public List<ClienteDTO> findAll() {
        return clienteRepository.findAll().stream().map(this::toDTO).toList();
    }

    public ClienteDTO findById(Long id) {
        return toDTO(clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado")));
    }

    public ClienteDTO update(Long id, Cliente cliente) {
        Cliente existing = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        existing.setNombre(cliente.getNombre());
        existing.setDireccion(cliente.getDireccion());
        existing.setTelefono(cliente.getTelefono());
        existing.setEmail(cliente.getEmail());
        existing.setLatitud(cliente.getLatitud());
        existing.setLongitud(cliente.getLongitud());
        return toDTO(clienteRepository.save(existing));
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    private ClienteDTO toDTO(Cliente cliente) {
        return new ClienteDTO(cliente.getId(), cliente.getNombre(), cliente.getDireccion(),
                cliente.getTelefono(), cliente.getEmail(), cliente.getLatitud(), cliente.getLongitud());
    }
}
