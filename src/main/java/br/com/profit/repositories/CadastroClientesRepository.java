package br.com.profit.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.profit.models.CadastroClientes;

@Repository
public interface CadastroClientesRepository extends JpaRepository<CadastroClientes, UUID>{

	boolean existsByCpf(String cpf);
	boolean existsByRg(String rg);

}
