package com.gestion.fidelizacion.repositorios;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.gestion.fidelizacion.entidades.Vencimiento;

public interface VencimientoRepository extends PagingAndSortingRepository<Vencimiento, Long>{

}
