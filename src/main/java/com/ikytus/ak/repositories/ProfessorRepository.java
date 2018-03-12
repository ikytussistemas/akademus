package com.ikytus.ak.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ikytus.ak.domain.Professor;

public interface ProfessorRepository extends PagingAndSortingRepository<Professor, Long> {
	
	public Page<Professor>findByNomeContainingIgnoreCase(String nome, Pageable pageable);
	
	public List<Professor> findByCoordenadorTrueOrderByNome();
}
