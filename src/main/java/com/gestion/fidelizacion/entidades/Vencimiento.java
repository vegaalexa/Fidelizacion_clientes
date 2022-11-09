package com.gestion.fidelizacion.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "vencimientos")
public class Vencimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_ini_validez;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_fin_validez;

    public Vencimiento() {
            super();
    }

    public Long getId() {
            return id;
    }

    public Date getFecha_ini_validez() {
        return fecha_ini_validez;
    }

    public void setFecha_ini_validez(Date fecha_ini_validez) {
        this.fecha_ini_validez = fecha_ini_validez;
    }
    
     public Date getFecha_fin_validez() {
        return fecha_fin_validez;
    }

    public void setFecha_fin_validez(Date fecha_fin_validez) {
        this.fecha_fin_validez = fecha_fin_validez;
    }
        

}
