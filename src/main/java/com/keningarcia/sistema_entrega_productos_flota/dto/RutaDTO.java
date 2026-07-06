package com.keningarcia.sistema_entrega_productos_flota.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RutaDTO {
    private Long id;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Double kilometrajeInicial;
    private Double kilometrajeFinal;
    private String estado;
    private Long unidadId;
    private String unidadPlaca;
    private Long usuarioId;
    private String usuarioNombre;
}
