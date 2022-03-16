package br.com.profit.controllers;

import java.util.List;
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

import br.com.profit.forms.CadastroClientesForm;
import br.com.profit.forms.CadastroClientesUpdateForm;
import br.com.profit.models.CadastroClientes;
import br.com.profit.services.CadastroClientesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/profit")
@Api(tags = "Clientes", description = "Api cadastro de cliente")
public class CadastroClientesController {

	private CadastroClientesService cadastroClientesService;
	
	public CadastroClientesController(CadastroClientesService cadastroClientesService) {
		this.cadastroClientesService = cadastroClientesService;
	}
	
	@ApiOperation(value = "Cadastra um cliente novo")
	@PostMapping
	public ResponseEntity<Object> cadastrarCliente(@RequestBody @Valid CadastroClientesForm cadClientesForm) {
		
		if(cadastroClientesService.existsByCpf(cadClientesForm.getCpf())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe um cadastro com esse cpf.");
		}
		if(cadastroClientesService.existsByRg(cadClientesForm.getRg())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe um cadastro com esse rg.");
		}
		
		var cadastroClientes = new CadastroClientes();
		BeanUtils.copyProperties(cadClientesForm, cadastroClientes);
		return ResponseEntity.status(HttpStatus.CREATED).body(cadastroClientesService.salvar(cadastroClientes));
	}
	
	@ApiOperation(value = "Busca todos os clientes cadastrado")
	@GetMapping
	public ResponseEntity<List<CadastroClientes>> buscarTodosOsClientes(){
		return ResponseEntity.status(HttpStatus.OK).body(cadastroClientesService.buscarTodosOsClientes());
	}
	
	@ApiOperation(value = "Busca um clientes cadastrado pelo id")
	@GetMapping("/{id}")
	public ResponseEntity<Object> buscarPorId(@PathVariable UUID id){
		Optional<CadastroClientes> optional = cadastroClientesService.buscarPorId(id); 
		if (!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	}
	
	
	@ApiOperation(value = "Atualiza dados de um cliente")
	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizarDadosCliente(@PathVariable UUID id, @RequestBody @Valid CadastroClientesUpdateForm updateForm ){
		Optional<CadastroClientes> updateOptional = cadastroClientesService.buscarPorId(id);
		if (!updateOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
		}
		var cadastroClientes = new CadastroClientes();
		BeanUtils.copyProperties(updateForm, cadastroClientes);
		cadastroClientes.setId(updateOptional.get().getId());
		cadastroClientes.setCpf(updateOptional.get().getCpf());
		cadastroClientes.setRg(updateOptional.get().getRg());
		return ResponseEntity.status(HttpStatus.OK).body(cadastroClientesService.salvar(cadastroClientes));
	}
	
	@ApiOperation(value = "Deleta um cliente pelo id")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletarCliente(@PathVariable UUID id){
		Optional<CadastroClientes> deleteOptional = cadastroClientesService.buscarPorId(id);
		if (!deleteOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
		}
		cadastroClientesService.deletar(deleteOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso!");
	}
	
}
