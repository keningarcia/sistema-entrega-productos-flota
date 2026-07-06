package com.keningarcia.sistema_entrega_productos_flota.controller;

import com.keningarcia.sistema_entrega_productos_flota.dto.RutaDTO;
import com.keningarcia.sistema_entrega_productos_flota.dto.RutaRequestDTO;
import com.keningarcia.sistema_entrega_productos_flota.service.RutaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rutas")
public class RutaController {

    private final RutaService rutaService;

    public RutaController(RutaService rutaService) {
        this.rutaService = rutaService;
    }

    @PostMapping("/iniciar")
    public ResponseEntity<RutaDTO> iniciarRuta(@Valid @RequestBody RutaRequestDTO request) {
        return ResponseEntity.ok(rutaService.iniciarRuta(request));
    }

    @PutMapping("/{id}/terminar")
    public ResponseEntity<RutaDTO> terminarRuta(@PathVariable Long id, @RequestParam Double kilometrajeFinal) {
        return ResponseEntity.ok(rutaService.terminarRuta(id, kilometrajeFinal));
    }

    @GetMapping
    public ResponseEntity<List<RutaDTO>> findAll() {
        return ResponseEntity.ok(rutaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RutaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(rutaService.findById(id));
    }
}
