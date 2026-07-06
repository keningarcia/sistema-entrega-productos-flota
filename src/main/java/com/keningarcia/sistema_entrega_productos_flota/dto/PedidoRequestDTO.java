package com.keningarcia.sistema_entrega_productos_flota.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PedidoRequestDTO {
    private String codigo;
    private String descripcion;
    private Integer cantidad;
    @NotNull
    private Long clienteId;
    @NotNull
    private Long rutaId;
}
