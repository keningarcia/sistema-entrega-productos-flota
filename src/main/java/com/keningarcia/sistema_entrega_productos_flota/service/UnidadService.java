package com.keningarcia.sistema_entrega_productos_flota.service;

import com.keningarcia.sistema_entrega_productos_flota.dto.UnidadDTO;
import com.keningarcia.sistema_entrega_productos_flota.entity.Unidad;
import com.keningarcia.sistema_entrega_productos_flota.repository.UnidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadService {

    private final UnidadRepository unidadRepository;

    public UnidadService(UnidadRepository unidadRepository) {
        this.unidadRepository = unidadRepository;
    }

    public UnidadDTO create(Unidad unidad) {
        if (unidad.getEstado() == null) unidad.setEstado("DISPONIBLE");
        if (unidad.getKilometrajeActual() == null) unidad.setKilometrajeActual(0.0);
        return toDTO(unidadRepository.save(unidad));
    }

    public List<UnidadDTO> findAll() {
        return unidadRepository.findAll().stream().map(this::toDTO).toList();
    }

    public UnidadDTO findById(Long id) {
        return toDTO(unidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unidad no encontrada")));
    }

    public UnidadDTO update(Long id, Unidad unidad) {
        Unidad existing = unidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unidad no encontrada"));
        existing.setPlaca(unidad.getPlaca());
        existing.setMarca(unidad.getMarca());
        existing.setModelo(unidad.getModelo());
        existing.setAnio(unidad.getAnio());
        existing.setEstado(unidad.getEstado());
        existing.setKilometrajeActual(unidad.getKilometrajeActual());
        return toDTO(unidadRepository.save(existing));
    }

    public void delete(Long id) {
        unidadRepository.deleteById(id);
    }

    private UnidadDTO toDTO(Unidad unidad) {
        return new UnidadDTO(unidad.getId(), unidad.getPlaca(), unidad.getMarca(),
                unidad.getModelo(), unidad.getAnio(), unidad.getEstado(), unidad.getKilometrajeActual());
    }
}
