package com.ikytus.ak.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ikytus.ak.domain.Aluno;
import com.ikytus.ak.domain.Estagio;
import com.ikytus.ak.domain.TurmaEstagio;

public interface EstagioRepository extends PagingAndSortingRepository<Estagio, Long> {
	
	public List<Estagio>findByAluno(Aluno aluno);
	
	public Page<Estagio>findByTurma(TurmaEstagio turma, Pageable pageable);

	
}
