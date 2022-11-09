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
@Table(name = "uso_puntos_cab")
public class UsoPuntosCab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int puntaje_utilizado;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "premio_id")
    private Premio premio;
    
    public UsoPuntosCab() {
            super();
    }

    public UsoPuntosCab(Long id, Date fecha) {
        super();    
        this.id = id;
        this.fecha = new Date();//para cargar fecha actual
    }

    public UsoPuntosCab(Long id, int puntaje_utilizado, Date fecha, Cliente cliente, Premio premio) {
        this.id = id;
        this.puntaje_utilizado = puntaje_utilizado;
        this.fecha = fecha;
        this.cliente = cliente;
        this.premio = premio;
    }

    public UsoPuntosCab(int puntaje_utilizado, Date fecha, Cliente cliente, Premio premio) {
        this.puntaje_utilizado = puntaje_utilizado;
        this.fecha = fecha;
        this.cliente = cliente;
        this.premio = premio;
    }

    public Long getId() {
        return id;
    }

    public int getPuntaje_utilizado() {
        return puntaje_utilizado;
    }

    public Date getFecha() {
        return fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Premio getPremio() {
        return premio;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPuntaje_utilizado(int puntaje_utilizado) {
        this.puntaje_utilizado = puntaje_utilizado;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setPremio(Premio premio) {
        this.premio = premio;
    }
  
}
