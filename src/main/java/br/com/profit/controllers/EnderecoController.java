package br.com.profit.controllers;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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

import br.com.profit.forms.EnderecoForm;
import br.com.profit.forms.EnderecoUpdateForm;
import br.com.profit.models.Endereco;
import br.com.profit.services.EnderecoService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/endereco")
public class EnderecoController {
	
	private EnderecoService enderecoService;
	
	public EnderecoController(EnderecoService enderecoService) {
		this.enderecoService = enderecoService;
	}
	
	@PostMapping
	public ResponseEntity<Object> cadastrarEndereco(@RequestBody @Valid EnderecoForm enderecoForm){
		var endereco = new Endereco();
		BeanUtils.copyProperties(enderecoForm, endereco);
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.salvar(endereco));
	}
	
	@GetMapping
	public ResponseEntity<Object> buscarTodosEnderecos(){
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.buscarTodosEnderecos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> buscarPorId(@PathVariable UUID id){
		Optional<Endereco> enderecoOptional = enderecoService.buscarPorId(id);
		if (!enderecoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(enderecoOptional.get());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizarEndereco(@RequestBody @Valid EnderecoUpdateForm enderecoUpdateForm, @PathVariable UUID id) {
		Optional<Endereco> enderecoUpdate = enderecoService.buscarPorId(id);
		if (!enderecoUpdate.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado");
		}
		var endereco = new Endereco();
		BeanUtils.copyProperties(enderecoUpdateForm, endereco);
		endereco.setId(enderecoUpdate.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.salvar(endereco));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletarEndereco(@PathVariable UUID id){
		Optional<Endereco> enderecoDel = enderecoService.buscarPorId(id);
		if (!enderecoDel.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado!");
		}
		enderecoService.deletarEndereco(enderecoDel.get());
		return ResponseEntity.status(HttpStatus.OK).body("Endereço deletado com sucesso!");
	}
}


