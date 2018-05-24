package com.wnsilveira.financeiro.domain.enums;

public enum TipoFrequencia {
	
	MENSAL(1, "Mensal"),
	DIARIO(2, "Diário");
//	SEMANAL(3, "Semanal"),
//	QUINZENAL(4, "Quinzenal"),
//	SEMESTRAL(5, "Semestral"),
//	ANUAL(6, "Anual");
	
	private int cod;
	private String descricao;
	
	private TipoFrequencia(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoFrequencia toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (TipoFrequencia tipoFrequencia: TipoFrequencia.values()) {
			if (cod.equals(tipoFrequencia.getCod())) {
				return tipoFrequencia;
			}
		}
		throw new IllegalArgumentException("Código inválido: " + cod);
	}
}
