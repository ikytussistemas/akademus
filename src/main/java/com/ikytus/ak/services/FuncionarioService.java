package com.ikytus.ak.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ikytus.ak.domain.Funcionario;
import com.ikytus.ak.domain.enums.Perfil;
import com.ikytus.ak.domain.enums.TipoUsuario;
import com.ikytus.ak.repositories.FuncionarioRepository;
import com.ikytus.ak.util.Tools;
import com.ikytus.ak.util.pageable.pageConfig;

@Service
public class FuncionarioService implements AbstractService<Funcionario> {

	@Autowired
	private FuncionarioRepository repository;
	
	@Autowired
	private pageConfig pconfig;

	@Autowired
	private PasswordEncoder pe;
	
	@Autowired
	private Tools tl;

	public void salvar(Funcionario funcionario) {
		setAtributes(funcionario);
		this.repository.save(funcionario);
	}

	public void salvar(Funcionario funcionario, String foto) {
		funcionario.setImg(foto);
		setAtributes(funcionario);
		this.repository.save(funcionario);
	}

	public Page<Funcionario> findByName(String nome, Optional<Integer> pageSize, Optional<Integer> page, Pageable pageable,
			String ordem) {
		return repository.findByNomeContainingIgnoreCase(Optional.ofNullable(nome).orElse("%"),
				pconfig.showPage(pageSize, page, pageable, Optional.ofNullable(ordem).orElse("nome")));
	}
		
	public Funcionario findOne(Long codigo) {
		return repository.findOne(codigo);
	}

	public void delete(Long codigo) {
		repository.delete(codigo);
	}

	private void setAtributes(Funcionario funcionario) {
		funcionario.setNome(funcionario.getNome().toUpperCase());
		funcionario.setEndereco(funcionario.getEndereco().toUpperCase());
		funcionario.setBairro(funcionario.getBairro().toUpperCase());
		funcionario.setEmail(funcionario.getEmail().toLowerCase());
		if (!funcionario.getSenha().trim().isEmpty()) {
			funcionario.setSenha(pe.encode(funcionario.getSenha()));
		} else {
			funcionario.setSenha(pe.encode(funcionario.getFuncional()));
		}
		funcionario.setTipo(TipoUsuario.FUNCIONARIO);
		
		if (!funcionario.getPerfis().contains(Perfil.FUNCIONARIO)) {
			funcionario.addPerfil(Perfil.FUNCIONARIO);
		}
	}

	public Funcionario getFuncionarioLogado() {
		Funcionario funcionario = (Funcionario) tl.getUsuario();
		return funcionario;
	}
}