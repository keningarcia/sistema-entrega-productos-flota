package com.keningarcia.sistema_entrega_productos_flota.controller;

import com.keningarcia.sistema_entrega_productos_flota.dto.PedidoDTO;
import com.keningarcia.sistema_entrega_productos_flota.dto.PedidoRequestDTO;
import com.keningarcia.sistema_entrega_productos_flota.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> create(@Valid @RequestBody PedidoRequestDTO request) {
        return ResponseEntity.ok(pedidoService.create(request));
    }

    @GetMapping("/ruta/{rutaId}")
    public ResponseEntity<List<PedidoDTO>> findByRuta(@PathVariable Long rutaId) {
        return ResponseEntity.ok(pedidoService.findByRuta(rutaId));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<PedidoDTO> actualizarEstado(@PathVariable Long id, @RequestParam String estado) {
        return ResponseEntity.ok(pedidoService.actualizarEstado(id, estado.toUpperCase()));
    }
}
