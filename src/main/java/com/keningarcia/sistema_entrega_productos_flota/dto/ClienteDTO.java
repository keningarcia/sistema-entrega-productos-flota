package com.keningarcia.sistema_entrega_productos_flota.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private Double latitud;
    private Double longitud;
}
