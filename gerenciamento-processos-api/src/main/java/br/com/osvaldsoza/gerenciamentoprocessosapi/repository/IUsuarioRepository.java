package br.com.osvaldsoza.gerenciamentoprocessosapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.osvaldsoza.gerenciamentoprocessosapi.model.ETipoUsuario;
import br.com.osvaldsoza.gerenciamentoprocessosapi.model.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{
	
	List<Usuario>findByTipo(ETipoUsuario tipo);

}
