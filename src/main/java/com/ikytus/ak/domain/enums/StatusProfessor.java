package com.ikytus.ak.domain.enums;

public enum StatusProfessor {
	
	ATIVO(1, "Ativo"),
	DESLIGADO(2, "Desligado"),
	LICENCA(3, "Licença");
	
	private int cod;
	private String descricao;
	
	private StatusProfessor(int cod, String descricao) {
		this.cod = cod;
		this.descricao=descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static StatusProfessor toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		
		for (StatusProfessor x : StatusProfessor.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
