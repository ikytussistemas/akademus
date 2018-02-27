package com.ikytus.ak.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ikytus.ak.domain.Usuario;
import com.ikytus.ak.util.security.model.UsuarioSistema;

@Component
public class Tools {
		
	public Tools() {}
	
	public Usuario getUsuario(){
			Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication(); 
			UsuarioSistema user = (UsuarioSistema) authentication.getPrincipal();
			return user.getUsuario();
	}
}
