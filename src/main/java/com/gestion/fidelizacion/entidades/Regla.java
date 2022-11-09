package com.gestion.fidelizacion.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "reglas")
public class Regla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int limite_inferior;

    @NotNull
    private int limite_superior;

    @NotNull
    private int monto_por_punto;
    
    private int puntos_calculados;

    public Regla() {
            super();
    }

    public Regla(Long id) {
        super();    
        this.id = id;
    }

    public Regla(Long id, int limite_inferior, int limite_superior, int monto_por_punto, int puntos_calculados) {
        this.id = id;
        this.limite_inferior = limite_inferior;
        this.limite_superior = limite_superior;
        this.monto_por_punto = monto_por_punto;
        this.puntos_calculados = puntos_calculados;
    }

    public Regla(int limite_inferior, int limite_superior, int monto_por_punto, int puntos_calculados) {
        this.limite_inferior = limite_inferior;
        this.limite_superior = limite_superior;
        this.monto_por_punto = monto_por_punto;
        this.puntos_calculados = puntos_calculados;
    }

    public Long getId() {
        return id;
    }

    public int getLimite_inferior() {
        return limite_inferior;
    }

    public int getLimite_superior() {
        return limite_superior;
    }

    public int getMonto_por_punto() {
        return monto_por_punto;
    }
    
    public int getPuntos_calculados() {
        return puntos_calculados;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLimite_inferior(int limite_inferior) {
        this.limite_inferior = limite_inferior;
    }

    public void setLimite_superior(int limite_superior) {
        this.limite_superior = limite_superior;
    }

    public void setMonto_por_punto(int monto_por_punto) {
        this.monto_por_punto = monto_por_punto;
    }
    
     public void setPuntos_calculados(int puntos_calculados) {
        this.puntos_calculados = puntos_calculados;
    }

}
