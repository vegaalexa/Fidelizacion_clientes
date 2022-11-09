package com.gestion.fidelizacion.servicio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gestion.fidelizacion.entidades.UsoPuntosCab;

public interface UsoPuntosCabService {
         /*para listar todos los clientes*/
	public List<UsoPuntosCab> findAll();
        
        /*para la paginacion*/
	public Page<UsoPuntosCab> findAll(Pageable pageable);

	public void save(UsoPuntosCab usopuntoscab);

	public UsoPuntosCab findOne(Long id);

	public void delete(Long id);
}
