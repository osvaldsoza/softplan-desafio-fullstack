package br.com.osvaldsoza.gerenciamentoprocessosapi.controller.openapi;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.osvaldsoza.gerenciamentoprocessosapi.model.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Usuários")
public interface UsuarioControllerOpenApi {
	
	@ApiOperation("Lista os usuarios")
	public List<Usuario> listarTodosUsuarios();
	
	@ApiOperation("Busca um usuario pelo seu tipo")
	@ApiResponses({ @ApiResponse(code = 400, message = "Tipo do usuario inválido"),
		@ApiResponse(code = 404, message = "usuario não encontrado") })
	public List<Usuario> listarTodosUsuariosPeloTipo(
			@ApiParam(value = "Tipo do usuario", example = "1") String tipoUsuario);
	
	@ApiOperation("Busca um usuario")
	@ApiResponses({ @ApiResponse(code = 400, message = "ID do usuario inválido"),
		@ApiResponse(code = 404, message = "usuario não encontrado") })
	public ResponseEntity<?> buscarUsuarioPeloId(
			@ApiParam(value = "ID do usuario", example = "1") Long usuarioID);

	@ApiOperation("Cadastra um usuario")
	@ApiResponses({ @ApiResponse(code = 201, message = "usuario cadastrado") })
	public ResponseEntity<?> salvarUsuario(
			@ApiParam(name = "corpo", value = "Representação de um novo usuario") Usuario usuario);

	@ApiOperation("Atualiza um usuario")
	@ApiResponses({ @ApiResponse(code = 200, message = "usuario atualizado"),
		@ApiResponse(code = 404, message = "usuario não encontrado") })
	public ResponseEntity<?> atualizar(
			@ApiParam(value = "ID de um usuario", example = "1") Long usuarioId,
			@ApiParam(name = "corpo", value = "Representação de um novo usuario com novos dados") Usuario usuario);

	@ApiOperation("Exclui um usuario")
	@ApiResponses({ @ApiResponse(code = 204, message = "usuario excluído"),
		@ApiResponse(code = 404, message = "usuario não encontrado") })
	public ResponseEntity<?> removerUsuario(
			@ApiParam(value = "ID do usuario", example = "1") Long usuarioId);

}
