package com.ikytus.ak.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ikytus.ak.controler.TestController;

public class GeraSenha {
	
	
	@Autowired
	private static TestController teste;
	
	static int soma = teste.somar(3, 4);
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public static void main(String[] args){
	System.out.println(new BCryptPasswordEncoder().encode("123"));
		
	
		
	}

}
