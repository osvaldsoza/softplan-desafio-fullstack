package br.com.osvaldsoza.gerenciamentoprocessosapi.utils.exceptions;

public class EntidadeNaoEncontradoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
}
