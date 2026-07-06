package com.keningarcia.sistema_entrega_productos_flota.controller;

import com.keningarcia.sistema_entrega_productos_flota.dto.UnidadDTO;
import com.keningarcia.sistema_entrega_productos_flota.entity.Unidad;
import com.keningarcia.sistema_entrega_productos_flota.service.UnidadService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unidades")
public class UnidadController {

    private final UnidadService unidadService;

    public UnidadController(UnidadService unidadService) {
        this.unidadService = unidadService;
    }

    @GetMapping
    public ResponseEntity<List<UnidadDTO>> findAll() {
        return ResponseEntity.ok(unidadService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(unidadService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UnidadDTO> create(@Valid @RequestBody Unidad unidad) {
        return ResponseEntity.ok(unidadService.create(unidad));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadDTO> update(@PathVariable Long id, @Valid @RequestBody Unidad unidad) {
        return ResponseEntity.ok(unidadService.update(id, unidad));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        unidadService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
