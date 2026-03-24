package com.laboratorio.labo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "laboratorios")
public class Laboratorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del laboratorio es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @NotNull(message = "La capacidad es obligatoria")
    @Min(value = 1, message = "La capacidad mínima debe ser de 1 persona")
    private Integer capacidad;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "^(OPERATIVO|MANTENIMIENTO|INACTIVO)$", 
             message = "El estado debe ser: OPERATIVO, MANTENIMIENTO o INACTIVO")
    private String estado;
}