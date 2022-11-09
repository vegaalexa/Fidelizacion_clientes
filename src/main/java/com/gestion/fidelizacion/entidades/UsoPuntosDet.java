package com.gestion.fidelizacion.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "uso_puntos_det")
public class UsoPuntosDet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int puntaje_utilizado;

    @ManyToOne
    @JoinColumn(name = "uso_punto_cab_id")
    private UsoPuntosCab uso_punto_cab_id;
    
    @ManyToOne
    @JoinColumn(name = "bolsa_punto_id")
    private BolsaPuntos bolsaPuntos;

    
    public UsoPuntosDet() {
            super();
    }

    public UsoPuntosDet(Long id, int puntaje_utilizado, UsoPuntosCab uso_punto_cab_id, BolsaPuntos bolsaPuntos) {
        this.id = id;
        this.puntaje_utilizado = puntaje_utilizado;
        this.uso_punto_cab_id = uso_punto_cab_id;
        this.bolsaPuntos = bolsaPuntos;
    }

    public UsoPuntosDet(int puntaje_utilizado, UsoPuntosCab uso_punto_cab_id, BolsaPuntos bolsaPuntos) {
        this.puntaje_utilizado = puntaje_utilizado;
        this.uso_punto_cab_id = uso_punto_cab_id;
        this.bolsaPuntos = bolsaPuntos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPuntaje_utilizado() {
        return puntaje_utilizado;
    }

    public void setPuntaje_utilizado(int puntaje_utilizado) {
        this.puntaje_utilizado = puntaje_utilizado;
    }

    public UsoPuntosCab getUso_punto_cab_id() {
        return uso_punto_cab_id;
    }

    public void setUso_punto_cab_id(UsoPuntosCab uso_punto_cab_id) {
        this.uso_punto_cab_id = uso_punto_cab_id;
    }

    public BolsaPuntos getBolsaPuntos() {
        return bolsaPuntos;
    }

    public void setBolsaPuntos(BolsaPuntos bolsaPuntos) {
        this.bolsaPuntos = bolsaPuntos;
    }
  
}
