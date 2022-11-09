package com.gestion.fidelizacion.repositorios;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.gestion.fidelizacion.entidades.Cliente;

public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long>{

}
