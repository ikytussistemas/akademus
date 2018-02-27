package com.ikytus.ak.util.security.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.ikytus.ak.domain.Usuario;
import com.ikytus.ak.domain.enums.Perfil;

public class UsuarioSistema extends User {
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;

	public UsuarioSistema(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public String getPermissao() {
		String gr="";
		for(Perfil perfil : usuario.getPerfis()) {
			 gr = perfil.getDescricao();
		}
		return gr;
	}
}
