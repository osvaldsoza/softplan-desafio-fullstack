package br.com.osvaldsoza.gerenciamentoprocessosapi.controller.openapi;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.osvaldsoza.gerenciamentoprocessosapi.model.Processo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Processos")
public interface ProcessoControllerOpenApi {

	@ApiOperation("Lista todos os processos")
	public List<Processo> listarTodosProcessos();

	
	@ApiOperation("Lista todos os processos com base no seu parecer")
	@ApiResponses({ @ApiResponse(code = 400, message = "Parecer do processo inválido"),
			@ApiResponse(code = 404, message = "Processo não encontrado") })
	public List<Processo> listarTodosUsuariosPeloParecer(@ApiParam(value = "ID de um processo", example = "1") String parecer);

	@ApiOperation("Busca um processo")
	@ApiResponses({ @ApiResponse(code = 400, message = "ID do processo inválido"),
			@ApiResponse(code = 404, message = "processo não encontrado") })
	public ResponseEntity<?> buscarProcessoPeloId(@ApiParam(value = "ID de um processo", example = "1") Long processoID);

	@ApiOperation("Cadastra um processo")
	@ApiResponses({ @ApiResponse(code = 201, message = "processo cadastrado") })
	public ResponseEntity<?> salvarProcesso(
			@ApiParam(name = "corpo", value = "Representação de um novo processo") Processo processo);

	@ApiOperation("Atualiza um processo")
	@ApiResponses({ @ApiResponse(code = 200, message = "processo atualizado"),
			@ApiResponse(code = 404, message = "processo não encontrado") })
	public ResponseEntity<?> atualizarProcesso(@ApiParam(value = "ID de um processo", example = "1") Long processoId,
			@ApiParam(name = "corpo", value = "Representação de um novo processo com novos dados") Processo processo);

	@ApiOperation("Exclui um processo")
	@ApiResponses({ @ApiResponse(code = 204, message = "processo excluído"),
			@ApiResponse(code = 404, message = "processo não encontrado") })
	public ResponseEntity<?> removerProcesso(@ApiParam(value = "ID do processo", example = "1") Long processoId);

}
