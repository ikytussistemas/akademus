package com.ikytus.ak.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ikytus.ak.domain.Aluno;
import com.ikytus.ak.domain.Estagio;

public interface EstagioRepository extends PagingAndSortingRepository<Estagio, Long> {
	
	public List<Estagio>findByAluno(Aluno aluno);

	
}
