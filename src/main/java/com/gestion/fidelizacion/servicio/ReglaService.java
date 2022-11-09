package com.gestion.fidelizacion.servicio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gestion.fidelizacion.entidades.Regla;

public interface ReglaService {
         /*para listar todos los clientes*/
	public List<Regla> findAll();
        
        /*para la paginacion*/
	public Page<Regla> findAll(Pageable pageable);

	public void save(Regla regla);

	public Regla findOne(Long id);

	public void delete(Long id);
}
