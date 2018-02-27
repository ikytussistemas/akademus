package com.ikytus.ak.util.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ikytus.ak.domain.Usuario;
import com.ikytus.ak.domain.enums.Perfil;
import com.ikytus.ak.repositories.UsuarioRepository;
import com.ikytus.ak.util.security.model.UsuarioSistema;

@Component
public class IkUserDetailsService implements UserDetailsService {
		
	@Autowired
	private UsuarioRepository usuarios;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Usuario usuario = usuarios.findByEmail(email);
		UsuarioSistema user = null;
		
		if(usuario !=null){
			user = new UsuarioSistema(usuario,getGrupos(usuario));
		}
		return user;
	}
	
	
	private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Perfil perfil: usuario.getPerfis()){
			authorities.add(new SimpleGrantedAuthority(perfil.getDescricao().toUpperCase()));
		}
		return authorities;
	}
}
