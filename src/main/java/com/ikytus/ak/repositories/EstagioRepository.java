package com.ikytus.ak.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ikytus.ak.domain.Aluno;
import com.ikytus.ak.domain.Estagio;

public interface EstagioRepository extends PagingAndSortingRepository<Estagio, Long> {
	
	public Page<Estagio>findByAluno(Aluno aluno, Pageable pageable);

	
}
