package com.keningarcia.sistema_entrega_productos_flota.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String username;
    private String nombre;
    private String apellido;
    private String email;
    private String role;
}
