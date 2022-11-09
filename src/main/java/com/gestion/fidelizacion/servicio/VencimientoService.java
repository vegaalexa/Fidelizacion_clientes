package com.gestion.fidelizacion.servicio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gestion.fidelizacion.entidades.Vencimiento;

public interface VencimientoService {
         /*para listar todos los clientes*/
	public List<Vencimiento> findAll();
        
        /*para la paginacion*/
	public Page<Vencimiento> findAll(Pageable pageable);

	public void save(Vencimiento vencimiento);

	public Vencimiento findOne(Long id);

	public void delete(Long id);
}
