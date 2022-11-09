package com.gestion.fidelizacion.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String apellido;

    @NotNull
    private int nro_doc;

    @NotEmpty
    private String tipo_doc;

    @NotEmpty
    private String nacionalidad;

    @NotEmpty
    @Email
    private String email;

    @NotNull
    private int telefono;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_nac;

    public Cliente() {
            super();
    }

    public Cliente(Long id) {
        super();    
        this.id = id;
    }
    
    
    public Cliente(Long id, String nombre, String apellido, int nro_doc, String tipo_doc, String nacionalidad, String email, int telefono, Date fecha_nac) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nro_doc = nro_doc;
        this.tipo_doc = tipo_doc;
        this.nacionalidad = nacionalidad;
        this.email = email;
        this.telefono = telefono;
        this.fecha_nac = fecha_nac;
    }

    public Cliente(String nombre, String apellido, int nro_doc, String tipo_doc, String nacionalidad, String email, int telefono, Date fecha_nac) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nro_doc = nro_doc;
        this.tipo_doc = tipo_doc;
        this.nacionalidad = nacionalidad;
        this.email = email;
        this.telefono = telefono;
        this.fecha_nac = fecha_nac;
    }
    
    
    public Long getId() {
            return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getNro_doc() {
        return nro_doc;
    }

    public void setNro_doc(int nro_doc) {
        this.nro_doc = nro_doc;
    }

    public String getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(String tipo_doc) {
        this.tipo_doc = tipo_doc;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Date getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(Date fecha_nac) {
        this.fecha_nac = fecha_nac;
    }
}
