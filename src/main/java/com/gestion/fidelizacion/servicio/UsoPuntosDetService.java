package com.gestion.fidelizacion.servicio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gestion.fidelizacion.entidades.UsoPuntosDet;

public interface UsoPuntosDetService {
         /*para listar todos los detalles*/
	public List<UsoPuntosDet> findAll();
        
        /*para la paginacion*/
	public Page<UsoPuntosDet> findAll(Pageable pageable);

	public void save(UsoPuntosDet usopuntosdet);

	public UsoPuntosDet findOne(Long id);

	public void delete(Long id);
}
