package br.com.profit.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "ENDERECO")
public class Endereco implements Serializable {
	
	private static final long serialVersion = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Version
	private Long jversion;
	
	@Column(nullable = false, length = 100)
	private String rua;
	
	@Column(nullable = false, length = 10)
	private String numero;
	
	@Column(nullable = false, length = 100)
	private String bairro;
	
	@Pattern(regexp = "\\d{5}-\\d{3}")
	@Column(nullable = false, unique = true, length = 9)
	private String cep;
	
	@Column(length = 100)
	private String referencia;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cadastro_clientes_id", referencedColumnName = "id")
	private CadastroClientes cadastroClientes;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Long getJversion() {
		return jversion;
	}

	public void setJversion(Long jversion) {
		this.jversion = jversion;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	
	

}
