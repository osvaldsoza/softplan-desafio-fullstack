package br.com.osvaldsoza.gerenciamentoprocessosapi.utils.exceptions;

public class RequisicaoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public RequisicaoException(String mensagem) {
		super(mensagem);
	}

}
