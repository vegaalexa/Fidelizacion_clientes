package com.gestion.fidelizacion.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.fidelizacion.entidades.UsoPuntosCab;
import com.gestion.fidelizacion.repositorios.UsoPuntosCabRepository;

@Service
public class UsoPuntosCabServiceImpl implements UsoPuntosCabService {

	@Autowired
	private UsoPuntosCabRepository usopuntoscabRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<UsoPuntosCab> findAll() {
		return (List<UsoPuntosCab>) usopuntoscabRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<UsoPuntosCab> findAll(Pageable pageable) {
		return usopuntoscabRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(UsoPuntosCab usopuntoscab) {
		usopuntoscabRepository.save(usopuntoscab);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		usopuntoscabRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public UsoPuntosCab findOne(Long id) {
		return usopuntoscabRepository.findById(id).orElse(null);
	}
	
}
