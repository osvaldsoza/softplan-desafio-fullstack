package br.com.osvaldsoza.gerenciamentoprocessosapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.osvaldsoza.gerenciamentoprocessosapi.model.EParecer;
import br.com.osvaldsoza.gerenciamentoprocessosapi.model.Processo;

@Repository
public interface IProcessoRepository extends JpaRepository<Processo, Long>{
	
	List<Processo>findByParecer(EParecer parecer);

}
