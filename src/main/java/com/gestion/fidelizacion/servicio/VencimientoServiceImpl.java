package com.gestion.fidelizacion.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.fidelizacion.entidades.Vencimiento;
import com.gestion.fidelizacion.repositorios.VencimientoRepository;

@Service
public class VencimientoServiceImpl implements VencimientoService {

	@Autowired
	private VencimientoRepository vencimientoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Vencimiento> findAll() {
		return (List<Vencimiento>) vencimientoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Vencimiento> findAll(Pageable pageable) {
		return vencimientoRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Vencimiento vencimiento) {
		vencimientoRepository.save(vencimiento);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		vencimientoRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Vencimiento findOne(Long id) {
		return vencimientoRepository.findById(id).orElse(null);
	}
	
}
