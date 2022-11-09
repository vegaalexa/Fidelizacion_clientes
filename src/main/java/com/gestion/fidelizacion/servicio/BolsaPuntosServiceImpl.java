package com.gestion.fidelizacion.servicio;

import com.gestion.fidelizacion.entidades.BolsaPuntos;
import com.gestion.fidelizacion.repositorios.BolsaPuntosRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BolsaPuntosServiceImpl implements BolsaPuntosService {

	@Autowired
	private BolsaPuntosRepository bolsapuntosRepository;
	@Override
	@Transactional(readOnly = true)
	public List<BolsaPuntos> findAll() {
		return (List<BolsaPuntos>) bolsapuntosRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<BolsaPuntos> findAll(Pageable pageable) {
		return bolsapuntosRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(BolsaPuntos empleado) {
		bolsapuntosRepository.save(empleado);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		bolsapuntosRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public BolsaPuntos findOne(Long id) {
		return bolsapuntosRepository.findById(id).orElse(null);
	}
	
}
