package br.com.osvaldsoza.gerenciamentoprocessosapi.model;

import lombok.Getter;

public enum ETipoUsuario {
	
	ADMINISTRADOR("Administrados"),
	USUARIO_TRIADOR("Usuário Triador"),
	USUARIO_FINALIZADOR("Usuário Finalizador");
	
	@Getter
	private String descricao;
	
	ETipoUsuario(String descricao) {
		this.descricao = descricao;
	}

}
