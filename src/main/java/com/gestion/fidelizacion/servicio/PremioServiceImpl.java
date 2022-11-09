package com.gestion.fidelizacion.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.fidelizacion.entidades.Premio;
import com.gestion.fidelizacion.repositorios.PremioRepository;

@Service
public class PremioServiceImpl implements PremioService {

	@Autowired
	private PremioRepository premioRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Premio> findAll() {
		return (List<Premio>) premioRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Premio> findAll(Pageable pageable) {
		return premioRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Premio premio) {
		premioRepository.save(premio);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		premioRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Premio findOne(Long id) {
		return premioRepository.findById(id).orElse(null);
	}	
}
