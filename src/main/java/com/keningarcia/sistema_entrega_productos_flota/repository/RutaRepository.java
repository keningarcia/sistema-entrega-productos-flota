package com.keningarcia.sistema_entrega_productos_flota.repository;

import com.keningarcia.sistema_entrega_productos_flota.entity.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RutaRepository extends JpaRepository<Ruta, Long> {
    List<Ruta> findByUsuarioId(Long usuarioId);
    List<Ruta> findByUnidadId(Long unidadId);
}
