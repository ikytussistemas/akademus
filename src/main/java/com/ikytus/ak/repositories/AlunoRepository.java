package com.ikytus.ak.repositories;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.ak.domain.Aluno;
import com.ikytus.ak.domain.TurmaEstagio;

@Repository
public interface AlunoRepository extends PagingAndSortingRepository <Aluno, Long> {
	
	@Transactional
	public Aluno findByEmail(String email);
	
	public Page<Aluno>findByNomeContainingIgnoreCase(String nome, Pageable pageable);
			
	public Page<Aluno>findByCursoNomeContainingAndSemestreContainingAndNomeContainingIgnoreCase
					  (String curso,String semestre,String nome, Pageable pageable);
	
	public Page<Aluno>findByEstagiosTurma(TurmaEstagio turma, Pageable pageable);
	
}
