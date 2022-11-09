package com.gestion.fidelizacion.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.fidelizacion.entidades.Regla;
import com.gestion.fidelizacion.repositorios.ReglaRepository;

@Service
public class ReglaServiceImpl implements ReglaService {

	@Autowired
	private ReglaRepository reglaRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Regla> findAll() {
		return (List<Regla>) reglaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Regla> findAll(Pageable pageable) {
		return reglaRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Regla regla) {
		reglaRepository.save(regla);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		reglaRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Regla findOne(Long id) {
		return reglaRepository.findById(id).orElse(null);
	}
	
}
