package com.gestion.fidelizacion.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "premios")
public class Premio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    private String descripcion;

    @NotNull
    private int puntos_requeridos;

    public Premio() {
            super();
    }

    public Long getId() {
            return id;
    }

    public Premio(Long id, String descripcion, int puntos_requeridos) {
        this.id = id;
        this.descripcion = descripcion;
        this.puntos_requeridos = puntos_requeridos;
    }

    public Premio(String descripcion, int puntos_requeridos) {
        this.descripcion = descripcion;
        this.puntos_requeridos = puntos_requeridos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPuntos_requeridos() {
        return puntos_requeridos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPuntos_requeridos(int puntos_requeridos) {
        this.puntos_requeridos = puntos_requeridos;
    }


}
