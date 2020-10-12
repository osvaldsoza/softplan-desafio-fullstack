package br.com.osvaldsoza.gerenciamentoprocessosapi.controller;

import java.util.List;

import javax.validation.Valid;

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

import br.com.osvaldsoza.gerenciamentoprocessosapi.controller.openapi.ProcessoControllerOpenApi;
import br.com.osvaldsoza.gerenciamentoprocessosapi.model.Processo;
import br.com.osvaldsoza.gerenciamentoprocessosapi.service.ProcessoService;
import br.com.osvaldsoza.gerenciamentoprocessosapi.utils.exceptions.EntidadeNaoEncontradoException;
import br.com.osvaldsoza.gerenciamentoprocessosapi.utils.exceptions.RequisicaoException;

@CrossOrigin
@RestController
@RequestMapping("/processo")
public class ProcessoController implements ProcessoControllerOpenApi{
	
	@Autowired
	private ProcessoService service;
	
	@GetMapping
	public List<Processo> listarTodosProcessos(){
		return service.findAll();
	}
	
	@GetMapping("/parecer/{parecer}")
	public List<Processo> listarTodosUsuariosPeloParecer(@PathVariable String parecer) {
		return service.findByParecer(parecer);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarProcessoPeloId(@PathVariable(name = "id") Long processoId) {
		try {
			var processo = service.findById(processoId);
			return ResponseEntity.status(HttpStatus.OK).body(processo);
		} catch (EntidadeNaoEncontradoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity<?> salvarProcesso(@Valid @RequestBody Processo processo) {
		try {
			processo = service.salvar(processo);
			return ResponseEntity.status(HttpStatus.CREATED).body(processo);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarProcesso(@PathVariable(name = "id") Long processoId, @RequestBody Processo processo){
		try {
			processo = service.atualizar(processoId, processo);
			return ResponseEntity.status(HttpStatus.OK).body(processo);
		} catch (EntidadeNaoEncontradoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch (RequisicaoException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{processoId}")
	public ResponseEntity<?> removerProcesso(@PathVariable Long processoId){
		try {
			service.Remover(processoId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (EntidadeNaoEncontradoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
