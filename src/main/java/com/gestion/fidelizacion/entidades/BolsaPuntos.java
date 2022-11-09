
package com.gestion.fidelizacion.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "bolsa_puntos")
public class BolsaPuntos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_asignacion_puntaje;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_caducidad_puntaje;
    
    private int puntaje_asignado;

    private int puntaje_utilizado;
    
    private int saldo_puntos;
    
    @NotNull
    private double monto_operacion;
    
    /*Relacion muchos a uno con la entidad Cliente*/
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    /*Relacion uno a muchos con Uso puntos detalles
    @OneToMany
    @JoinColumn(name = "usopuntosDet_id")
    private UsoPuntosDet usopuntosDet;
    */
    
    public BolsaPuntos() {
        super();
    }

    public BolsaPuntos(Long id, Date fecha_asignacion_puntaje, Date fecha_caducidad_puntaje, int puntaje_asignado, int puntaje_utilizado, int saldo_puntos, double monto_operacion, Cliente cliente) {
        this.id = id;
        this.fecha_asignacion_puntaje = fecha_asignacion_puntaje;
        this.fecha_caducidad_puntaje = fecha_caducidad_puntaje;
        this.puntaje_asignado = puntaje_asignado;
        this.puntaje_utilizado = puntaje_utilizado;
        this.saldo_puntos = saldo_puntos;
        this.monto_operacion = monto_operacion;
        this.cliente = cliente;
    }

    public BolsaPuntos(Date fecha_asignacion_puntaje, Date fecha_caducidad_puntaje, int puntaje_asignado, int puntaje_utilizado, int saldo_puntos, double monto_operacion, Cliente cliente) {
        this.fecha_asignacion_puntaje = fecha_asignacion_puntaje;
        this.fecha_caducidad_puntaje = fecha_caducidad_puntaje;
        this.puntaje_asignado = puntaje_asignado;
        this.puntaje_utilizado = puntaje_utilizado;
        this.saldo_puntos = saldo_puntos;
        this.monto_operacion = monto_operacion;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha_asignacion_puntaje() {
        return fecha_asignacion_puntaje;
    }

    public void setFecha_asignacion_puntaje(Date fecha_asignacion_puntaje) {
        this.fecha_asignacion_puntaje = fecha_asignacion_puntaje;
    }

    public Date getFecha_caducidad_puntaje() {
        return fecha_caducidad_puntaje;
    }

    public void setFecha_caducidad_puntaje(Date fecha_caducidad_puntaje) {
        this.fecha_caducidad_puntaje = fecha_caducidad_puntaje;
    }

    public int getPuntaje_asignado() {
        return puntaje_asignado;
    }

    public void setPuntaje_asignado(int puntaje_asignado) {
        this.puntaje_asignado = puntaje_asignado;
    }

    public int getPuntaje_utilizado() {
        return puntaje_utilizado;
    }

    public void setPuntaje_utilizado(int puntaje_utilizado) {
        this.puntaje_utilizado = puntaje_utilizado;
    }

    public int getSaldo_puntos() {
        return saldo_puntos;
    }

    public void setSaldo_puntos(int saldo_puntos) {
        this.saldo_puntos = saldo_puntos;
    }

    public double getMonto_operacion() {
        return monto_operacion;
    }

    public void setMonto_operacion(double monto_operacion) {
        this.monto_operacion = monto_operacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
   
    
}
