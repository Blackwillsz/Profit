package br.com.profit.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.profit.models.CadastroClientes;
import br.com.profit.repositories.CadastroClientesRepository;

@Service
public class CadastroClientesService {
	
	private CadastroClientesRepository cadastroClientesRepository;
	
	public CadastroClientesService(CadastroClientesRepository cadastroClientesRepository) {
		this.cadastroClientesRepository = cadastroClientesRepository;
	}

	@Transactional
	public CadastroClientes salvar(CadastroClientes cadastroClientes) {
		return cadastroClientesRepository.save(cadastroClientes);
	}

	public boolean existsByCpf(String cpf) {
		return cadastroClientesRepository.existsByCpf(cpf);
	}

	public boolean existsByRg(String rg) {
		return cadastroClientesRepository.existsByRg(rg);
	}

	public List<CadastroClientes> buscarTodosOsClientes() {
		return cadastroClientesRepository.findAll();
	}

	public Optional<CadastroClientes> buscarPorId(UUID id) {
		return cadastroClientesRepository.findById(id);
	}

	@Transactional
	public void deletar(CadastroClientes cadastroClientes) {
		cadastroClientesRepository.delete(cadastroClientes);
		
	}


}
