package com.gestion.fidelizacion.servicio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gestion.fidelizacion.entidades.Premio;

public interface PremioService {
         /*para listar todos los clientes*/
	public List<Premio> findAll();
        
        /*para la paginacion*/
	public Page<Premio> findAll(Pageable pageable);

	public void save(Premio premio);

	public Premio findOne(Long id);

	public void delete(Long id);
}
