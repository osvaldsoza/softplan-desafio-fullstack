package br.com.osvaldsoza.gerenciamentoprocessosapi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.osvaldsoza.gerenciamentoprocessosapi.model.EParecer;
import br.com.osvaldsoza.gerenciamentoprocessosapi.model.Processo;
import br.com.osvaldsoza.gerenciamentoprocessosapi.repository.IProcessoRepository;
import br.com.osvaldsoza.gerenciamentoprocessosapi.repository.IUsuarioRepository;
import br.com.osvaldsoza.gerenciamentoprocessosapi.utils.exceptions.EntidadeNaoEncontradoException;
import br.com.osvaldsoza.gerenciamentoprocessosapi.utils.exceptions.RequisicaoException;

@Service
public class ProcessoService {
	
	@Autowired
	private IProcessoRepository repository;
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	public List<Processo> findAll(){
		return repository.findAll();
	}
	
	public List<Processo> findByParecer(String parecer) {
		List<Processo> processos = new ArrayList<>();
	
		if (EParecer.EM_ANDAMENTO.getDescricao().contains(parecer)) {
			processos = repository.findByParecer(EParecer.EM_ANDAMENTO);
		}
		if (EParecer.DEFERIDO.getDescricao().contains(parecer)) {
			processos = repository.findByParecer(EParecer.DEFERIDO);
		}
		if (EParecer.INDEFERIDO.getDescricao().contains(parecer)) {
			processos = repository.findByParecer(EParecer.INDEFERIDO);
		}	
		return processos;
	}
	
	public Processo findById(Long processoId) {
		return repository.findById(processoId).orElseThrow(() -> new EntidadeNaoEncontradoException(
				String.format("O Processo de ccódigo %d não existe", processoId)));
	}
	
	public Processo salvar(Processo processo) {
		Long usuarioId = processo.getUsuario().getId();

		var usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new EntidadeNaoEncontradoException(
				String.format("CADASTRO NÃO REALIZADO! Usuário de código %d não existe.", usuarioId)));

		processo.setUsuario(usuario);
		try {
			processo = repository.save(processo);
		} catch (Exception e) {
			throw new RequisicaoException(String.format("Requisição inválida!"));
		}

		return processo;
	}
	
	public Processo atualizar(Long processoId, Processo processo) {
		var processoAtual = findById(processoId);

		processoAtual.setDataBaixa(LocalDateTime.now());
		BeanUtils.copyProperties(processo, processoAtual, "id","dataBaixa","dataEntrada");
		return salvar(processoAtual);
	}

	public void Remover(Long processoId) {
		try {
			repository.deleteById(processoId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradoException(String.format("processo de código %d não encontrado.", processoId));
		}
	}

}
