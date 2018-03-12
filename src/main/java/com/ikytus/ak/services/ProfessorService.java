package com.ikytus.ak.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ikytus.ak.domain.Curso;
import com.ikytus.ak.domain.Professor;
import com.ikytus.ak.repositories.CursoRepository;
import com.ikytus.ak.repositories.ProfessorRepository;
import com.ikytus.ak.util.pageable.pageConfig;

@Service
public class ProfessorService implements AbstractService<Professor> {
	
	@Autowired
	private ProfessorRepository repository;
	
	@Autowired
	private CursoRepository cursoRepository;
			
	@Autowired
	private pageConfig pconfig;
			
	public void salvar(Professor Professor){
		Professor.setNome(Professor.getNome().toUpperCase());
		this.repository.save(Professor);
	}
	
	public void salvar(Professor Professor, String imagem) {
		Professor.setImg(imagem);
		Professor.setNome(Professor.getNome().toUpperCase());
		this.repository.save(Professor);
	}
	
	public Page<Professor> findByName(String nome, Optional<Integer> pageSize,	Optional<Integer> page, Pageable pageable, String ordem) {
		return repository.findByNomeContainingIgnoreCase(Optional.ofNullable(nome).orElse("%"),
				pconfig.showPage(pageSize, page, pageable, Optional.ofNullable(ordem).orElse("nome")));
	}
	
	public Professor findOne(Long codigo) {
		return repository.findOne(codigo);
	}
	
	public void delete(Long codigo) {
		repository.delete(codigo);
	}
	
	public List<Curso> getCursos(){
		return cursoRepository.findAll();
	}
	
}
