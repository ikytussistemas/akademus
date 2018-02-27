package com.ikytus.ak.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikytus.ak.domain.Estagio;
import com.ikytus.ak.repositories.EstagioRepository;

@Service
public class EstagioService implements AbstractService<Estagio> {
	
	@Autowired
	private EstagioRepository repository;
	
	public void salvar(Estagio estagio){
		this.repository.save(estagio);
	}
		
	public void salvar(Estagio estagio, String foto){
		this.repository.save(estagio);
	}
	
	public Estagio findOne(Long codigo) {
		return repository.findOne(codigo);
	}
	
	public void delete(Long codigo) {
		repository.delete(codigo);
	}
}
