package com.keningarcia.sistema_entrega_productos_flota.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rutas")
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    private Double kilometrajeInicial;
    private Double kilometrajeFinal;

    @Column(length = 20)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "unidad_id", nullable = false)
    private Unidad unidad;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;
}
