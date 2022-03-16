package br.com.profit.forms;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CadastroClientesUpdateForm {
	
	@NotBlank
	private String nomeCompleto;
	
	@NotBlank
	@Size(max = 2)
	private String ddd;
	
	@NotBlank
	private String numeroTelefone;
	
	@NotNull
	private UUID endereco;

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public UUID getEndereco() {
		return endereco;
	}

	public void setEndereco(UUID endereco) {
		this.endereco = endereco;
	}
	
	

}
