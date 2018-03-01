package com.ikytus.ak.repositories;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.ak.domain.Funcionario;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository <Funcionario, Long> {
	
	@Transactional
	public Funcionario findByEmail(String email);
	
	public Page<Funcionario>findByNomeContainingIgnoreCase(String nome, Pageable pageable);
	
}
