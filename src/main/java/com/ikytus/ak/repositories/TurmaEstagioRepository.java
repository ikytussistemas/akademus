package com.ikytus.ak.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.ak.domain.TurmaEstagio;

@Repository
public interface TurmaEstagioRepository extends PagingAndSortingRepository <TurmaEstagio, Long> {
	
	public Page<TurmaEstagio>findBySemestreContains(String semestre, Pageable pageable);
	
	public List<TurmaEstagio>findBySemestre(String semestre);
	
	public List<TurmaEstagio>findByDisponivelContains(String Semestre);
	
	public List<TurmaEstagio>findByDisponivelContaining(String Semestre);
}
