package com.keningarcia.sistema_entrega_productos_flota.repository;

import com.keningarcia.sistema_entrega_productos_flota.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByRutaId(Long rutaId);
    List<Pedido> findByClienteId(Long clienteId);
}
