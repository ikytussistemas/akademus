package com.ikytus.ak.domain.enums;

public enum Periodos {
	
	PRIMEIRO(1, "1º"),
	SEGUNDO(2, "2º"),
	TERCEIRO(3, "3º"),
	QUARTO(4,"4º"),
	QUINTO(5,"5º"),
	SEXTO(6,"6º"),
	SETIMO(7,"7º"),
	OITAVO(8,"8º"),
	NONO(9,"9º"),
	DECIMO(10,"10º");
	
	private int cod;
	private String descricao;
	
	private Periodos(int cod, String descricao) {
		this.cod = cod;
		this.descricao=descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Periodos toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		
		for (Periodos x : Periodos.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
