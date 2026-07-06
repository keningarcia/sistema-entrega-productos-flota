package com.keningarcia.sistema_entrega_productos_flota.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(length = 255)
    private String direccion;

    @Column(length = 20)
    private String telefono;

    @Column(length = 150)
    private String email;

    private Double latitud;

    private Double longitud;
}
