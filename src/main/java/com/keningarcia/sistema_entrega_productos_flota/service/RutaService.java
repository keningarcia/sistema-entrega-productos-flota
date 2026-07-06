package com.keningarcia.sistema_entrega_productos_flota.service;

import com.keningarcia.sistema_entrega_productos_flota.dto.RutaDTO;
import com.keningarcia.sistema_entrega_productos_flota.dto.RutaRequestDTO;
import com.keningarcia.sistema_entrega_productos_flota.entity.Ruta;
import com.keningarcia.sistema_entrega_productos_flota.entity.Unidad;
import com.keningarcia.sistema_entrega_productos_flota.entity.User;
import com.keningarcia.sistema_entrega_productos_flota.repository.RutaRepository;
import com.keningarcia.sistema_entrega_productos_flota.repository.UnidadRepository;
import com.keningarcia.sistema_entrega_productos_flota.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RutaService {

    private final RutaRepository rutaRepository;
    private final UnidadRepository unidadRepository;
    private final UserRepository userRepository;

    public RutaService(RutaRepository rutaRepository, UnidadRepository unidadRepository, UserRepository userRepository) {
        this.rutaRepository = rutaRepository;
        this.unidadRepository = unidadRepository;
        this.userRepository = userRepository;
    }

    public RutaDTO iniciarRuta(RutaRequestDTO request) {
        Unidad unidad = unidadRepository.findById(request.getUnidadId())
                .orElseThrow(() -> new RuntimeException("Unidad no encontrada"));
        User usuario = userRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Ruta ruta = new Ruta();
        ruta.setFechaInicio(LocalDateTime.now());
        ruta.setKilometrajeInicial(request.getKilometrajeInicial());
        ruta.setEstado("EN_CURSO");
        ruta.setUnidad(unidad);
        ruta.setUsuario(usuario);

        unidad.setEstado("EN_RUTA");
        unidad.setKilometrajeActual(request.getKilometrajeInicial());
        unidadRepository.save(unidad);

        return toDTO(rutaRepository.save(ruta));
    }

    public RutaDTO terminarRuta(Long id, Double kilometrajeFinal) {
        Ruta ruta = rutaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada"));

        ruta.setFechaFin(LocalDateTime.now());
        ruta.setKilometrajeFinal(kilometrajeFinal);
        ruta.setEstado("COMPLETADA");

        Unidad unidad = ruta.getUnidad();
        unidad.setEstado("DISPONIBLE");
        unidad.setKilometrajeActual(kilometrajeFinal);
        unidadRepository.save(unidad);

        return toDTO(rutaRepository.save(ruta));
    }

    public List<RutaDTO> findAll() {
        return rutaRepository.findAll().stream().map(this::toDTO).toList();
    }

    public RutaDTO findById(Long id) {
        return toDTO(rutaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada")));
    }

    private RutaDTO toDTO(Ruta ruta) {
        return new RutaDTO(
                ruta.getId(), ruta.getFechaInicio(), ruta.getFechaFin(),
                ruta.getKilometrajeInicial(), ruta.getKilometrajeFinal(),
                ruta.getEstado(), ruta.getUnidad().getId(), ruta.getUnidad().getPlaca(),
                ruta.getUsuario().getId(), ruta.getUsuario().getNombre()
        );
    }
}
