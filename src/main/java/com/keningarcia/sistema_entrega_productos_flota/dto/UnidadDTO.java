package com.keningarcia.sistema_entrega_productos_flota.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnidadDTO {
    private Long id;
    private String placa;
    private String marca;
    private String modelo;
    private Integer anio;
    private String estado;
    private Double kilometrajeActual;
}
