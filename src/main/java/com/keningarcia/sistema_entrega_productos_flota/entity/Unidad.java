package com.keningarcia.sistema_entrega_productos_flota.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "unidades")
public class Unidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String placa;

    @Column(length = 50)
    private String marca;

    @Column(length = 50)
    private String modelo;

    private Integer anio;

    @Column(length = 30)
    private String estado;

    private Double kilometrajeActual;
}
