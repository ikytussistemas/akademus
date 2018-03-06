package com.ikytus.ak.domain.enums;

public enum TituloProfessor {
	
	ESPECIALISTA(1, "Especialista"),
	MESTRE(2, "Mestre"),
	DOUTOR(3, "Doutor(a)");
	
	private int cod;
	private String descricao;
	
	private TituloProfessor(int cod, String descricao) {
		this.cod = cod;
		this.descricao=descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TituloProfessor toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		
		for (TituloProfessor x : TituloProfessor.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
