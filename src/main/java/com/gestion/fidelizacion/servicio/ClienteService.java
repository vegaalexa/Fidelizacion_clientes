package com.gestion.fidelizacion.servicio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gestion.fidelizacion.entidades.Cliente;

public interface ClienteService {
         /*para listar todos los clientes*/
	public List<Cliente> findAll();
        
        /*para la paginacion*/
	public Page<Cliente> findAll(Pageable pageable);

	public void save(Cliente empleado);

	public Cliente findOne(Long id);

	public void delete(Long id);
}
