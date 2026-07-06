package com.keningarcia.sistema_entrega_productos_flota.repository;

import com.keningarcia.sistema_entrega_productos_flota.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
