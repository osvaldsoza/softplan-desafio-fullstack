package br.com.osvaldsoza.gerenciamentoprocessosapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.osvaldsoza.gerenciamentoprocessosapi.model.ETipoUsuario;
import br.com.osvaldsoza.gerenciamentoprocessosapi.model.Usuario;
import br.com.osvaldsoza.gerenciamentoprocessosapi.repository.IUsuarioRepository;
import br.com.osvaldsoza.gerenciamentoprocessosapi.utils.exceptions.EntidadeEmUsoException;
import br.com.osvaldsoza.gerenciamentoprocessosapi.utils.exceptions.EntidadeNaoEncontradoException;

@Service
public class UsuarioService {

	@Autowired
	private IUsuarioRepository repository;

	public List<Usuario> findAll() {
		return repository.findAll();
	}

	public List<Usuario> findByTipoUsuario(String tipoUsuario) {
		List<Usuario> usuarios = new ArrayList<>();
	
		if (ETipoUsuario.ADMINISTRADOR.getDescricao().contains(tipoUsuario)) {
			usuarios = repository.findByTipo(ETipoUsuario.ADMINISTRADOR);
		}
		if (ETipoUsuario.USUARIO_FINALIZADOR.getDescricao().contains(tipoUsuario)) {
			usuarios = repository.findByTipo(ETipoUsuario.USUARIO_FINALIZADOR);
		}
		if (ETipoUsuario.USUARIO_TRIADOR.getDescricao().contains(tipoUsuario)) {
			usuarios = repository.findByTipo(ETipoUsuario.USUARIO_TRIADOR);
		}
			
		return usuarios;
	}

	public Usuario findById(Long usuarioId) {
		return repository.findById(usuarioId).orElseThrow(() -> new EntidadeNaoEncontradoException(
				String.format("O Usuário de código %d não existe", usuarioId)));
	}

	public Usuario save(Usuario usuario) {
		return repository.save(usuario);
	}

	public Usuario atualizar(Long usuarioId, Usuario usuario) {

		var usuarioAtual = findById(usuarioId);

		BeanUtils.copyProperties(usuario, usuarioAtual, "id");
		return save(usuarioAtual);
	}

	public void Remover(Long usuarioId) {
		try {
			repository.deleteById(usuarioId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradoException(String.format("Usuário de código %d não encontrado.", usuarioId));
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Exclusão não permitida! Restaurante de código %d está em uso.", usuarioId));
		}
	}

}
