package br.com.osvaldsoza.gerenciamentoprocessosapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.osvaldsoza.gerenciamentoprocessosapi.controller.openapi.UsuarioControllerOpenApi;
import br.com.osvaldsoza.gerenciamentoprocessosapi.model.Usuario;
import br.com.osvaldsoza.gerenciamentoprocessosapi.service.UsuarioService;
import br.com.osvaldsoza.gerenciamentoprocessosapi.utils.exceptions.EntidadeEmUsoException;
import br.com.osvaldsoza.gerenciamentoprocessosapi.utils.exceptions.EntidadeNaoEncontradoException;

@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class UsuarioController implements UsuarioControllerOpenApi{

	@Autowired
	private UsuarioService service;

	@GetMapping
	public List<Usuario> listarTodosUsuarios() {
		return service.findAll();
	}
	
	@GetMapping("/tipousuario/{tipoUsuario}")
	public List<Usuario> listarTodosUsuariosPeloTipo(@PathVariable String tipoUsuario) {
		return service.findByTipoUsuario(tipoUsuario);
	}

	@GetMapping("/{usuarioId}")
	public ResponseEntity<?> buscarUsuarioPeloId(@PathVariable Long usuarioId) {
		try {
			var usuario = service.findById(usuarioId);
			return ResponseEntity.status(HttpStatus.OK).body(usuario);
		} catch (EntidadeNaoEncontradoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity<?> salvarUsuario(@RequestBody Usuario usuario) {
		try {
			usuario = service.save(usuario);
			return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PutMapping("/{usuarioId}")
	public ResponseEntity<?> atualizar(@PathVariable Long usuarioId, @RequestBody Usuario usuario) {
		try {
			 usuario = service.atualizar(usuarioId, usuario);
			return ResponseEntity.status(HttpStatus.OK).body(usuario);
		} catch (EntidadeNaoEncontradoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{usuarioId}")
	public ResponseEntity<?> removerUsuario(@PathVariable Long usuarioId){
		try {
			service.Remover(usuarioId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (EntidadeNaoEncontradoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}
