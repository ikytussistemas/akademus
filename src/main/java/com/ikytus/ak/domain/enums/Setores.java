package com.ikytus.ak.domain.enums;

public enum Setores {
	
	ATENDIMENTOACADEMICO(1, "Atendimento Acadêmico"),
	FINANCEIRO(2, "Financeiro"),
	COORDENACAO(3,"Coordenação"),
	DIRECAO(4,"Direção"),
	LABORATORIOS(5,"Laboratórios"),
	DP(6,"DP"),
	GERENCIAUNIDADE(7,"Gerência de Unidade"),
	BIBLIOTECA(8, "Biblioteca"),
	NPJ(9, "NPJ"),
	ESTAGIO(10, "estágio");
		
	private int cod;
	private String descricao;
	
	private Setores(int cod, String descricao) {
		this.cod = cod;
		this.descricao=descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Setores toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		
		for (Setores x : Setores.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
