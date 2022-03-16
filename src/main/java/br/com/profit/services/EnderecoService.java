package br.com.profit.services;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.profit.models.Endereco;
import br.com.profit.repositories.EnderecoRepository;

@Service
public class EnderecoService {
	
	private EnderecoRepository enderecoRepository;
	
	public EnderecoService(EnderecoRepository enderecoRepository) {
		this.enderecoRepository = enderecoRepository;
	}

	@Transactional
	public Object salvar(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

	public Object buscarTodosEnderecos() {
		return enderecoRepository.findAll();
	}

	public Optional<Endereco> buscarPorId(UUID id) {
		return enderecoRepository.findById(id);
	}

	public void deletarEndereco(Endereco endereco) {
		enderecoRepository.delete(endereco);
	}

}
