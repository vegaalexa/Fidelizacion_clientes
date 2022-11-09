package com.gestion.fidelizacion.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.fidelizacion.entidades.UsoPuntosDet;
import com.gestion.fidelizacion.repositorios.UsoPuntosDetRepository;

@Service
public class UsoPuntosDetServiceImpl implements UsoPuntosDetService {

	@Autowired
	private UsoPuntosDetRepository usopuntosdetRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<UsoPuntosDet> findAll() {
		return (List<UsoPuntosDet>) usopuntosdetRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<UsoPuntosDet> findAll(Pageable pageable) {
		return usopuntosdetRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(UsoPuntosDet usopuntoscab) {
		usopuntosdetRepository.save(usopuntoscab);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		usopuntosdetRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public UsoPuntosDet findOne(Long id) {
		return usopuntosdetRepository.findById(id).orElse(null);
	}
	
}
