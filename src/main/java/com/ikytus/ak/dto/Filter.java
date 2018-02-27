package com.ikytus.ak.dto;

import java.util.Date;

import com.ikytus.ak.domain.Curso;

public class Filter {
	
	private String nome;
	
	private String curso;
	
	private String semestre;
						
	private Date datainicial;
	
	private Date datafinal;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public Date getDatainicial() {
		return datainicial;
	}

	public void setDatainicial(Date datainicial) {
		this.datainicial = datainicial;
	}

	public Date getDatafinal() {
		return datafinal;
	}

	public void setDatafinal(Date datafinal) {
		this.datafinal = datafinal;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

}
