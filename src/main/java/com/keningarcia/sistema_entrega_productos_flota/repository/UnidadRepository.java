package com.keningarcia.sistema_entrega_productos_flota.repository;

import com.keningarcia.sistema_entrega_productos_flota.entity.Unidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnidadRepository extends JpaRepository<Unidad, Long> {
    Optional<Unidad> findByPlaca(String placa);
}
