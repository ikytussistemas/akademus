package com.ikytus.ak.services;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ikytus.ak.domain.Usuario;
import com.ikytus.ak.repositories.UsuarioRepository;
import com.ikytus.ak.util.security.IkUserDetails;

@Service
public class UsuarioService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UsuarioRepository usuarioRepository;
		
	public void salvar(Usuario usuario){
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		usuario.setNome(usuario.getNome().toUpperCase());
		this.usuarioRepository.save(usuario);
	}
	
	public static IkUserDetails authenticated() {
		try {
			System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			return (IkUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}
