package com.gestion.fidelizacion.servicio;

import com.gestion.fidelizacion.entidades.BolsaPuntos;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BolsaPuntosService {
         /*para listar todos los clientes*/
	public List<BolsaPuntos> findAll();
        
        /*para la paginacion*/
	public Page<BolsaPuntos> findAll(Pageable pageable);

	public void save(BolsaPuntos bolsapuntos);

	public BolsaPuntos findOne(Long id);

	public void delete(Long id);
}
