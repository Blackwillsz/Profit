package br.com.profit.forms;

import java.time.LocalDate;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.profit.annotation.Cpf;
import io.swagger.annotations.ApiModelProperty;

public class CadastroClientesForm {

	@NotBlank
	private String nomeCompleto;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	@NotBlank
	@Size(max = 14)
	@Cpf
	private String cpf;
	
	@NotBlank
	@Size(max = 9)
	private String rg;
	
	@NotBlank
	@Size(max = 2)
	private String ddd;
	
	@NotBlank
	private String numeroTelefone;
	
	@NotNull
	private UUID endereco;

	 @ApiModelProperty(notes = "Nome completo do cliente")
	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	 @ApiModelProperty(notes = "Data de nascimento do cliente")
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	 @ApiModelProperty(notes = "Cpf do cliente")
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	 @ApiModelProperty(notes = "Rg do cliente")
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	 @ApiModelProperty(notes = "Ddd do cliente")
	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	 @ApiModelProperty(notes = "Numero de telefone do cliente")
	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	 @ApiModelProperty(notes = "Endereço do cliente")
	public UUID getEndereco() {
		return endereco;
	}

	public void setEndereco(UUID endereco) {
		this.endereco = endereco;
	}
	
	
	
	
}
