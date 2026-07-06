package com.keningarcia.sistema_entrega_productos_flota.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RutaRequestDTO {
    @NotNull
    private Double kilometrajeInicial;
    @NotNull
    private Long unidadId;
    @NotNull
    private Long usuarioId;
}
