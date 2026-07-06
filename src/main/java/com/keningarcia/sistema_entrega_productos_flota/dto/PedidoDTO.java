package com.keningarcia.sistema_entrega_productos_flota.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private Long id;
    private String codigo;
    private String descripcion;
    private Integer cantidad;
    private String estado;
    private Long clienteId;
    private String clienteNombre;
    private Long rutaId;
}
