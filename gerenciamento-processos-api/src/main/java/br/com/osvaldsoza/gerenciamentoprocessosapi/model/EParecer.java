package br.com.osvaldsoza.gerenciamentoprocessosapi.model;

import lombok.Getter;

public enum EParecer {
	
	EM_ANDAMENTO("Em andamento"),
	DEFERIDO("Deferido"),
	INDEFERIDO("Indeferido");
	
	@Getter
	private String descricao;
	
	EParecer(String descricao) {
		this.descricao = descricao;
	}
}
