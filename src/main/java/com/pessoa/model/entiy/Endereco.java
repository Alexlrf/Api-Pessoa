package com.pessoa.model.entiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "endereco")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_endereco")
	private Long id;

	@NotBlank(message = "Campo 'cep' não pode ser vazio ou nulo")
	@Size(min = 5, max = 10, message = "Campo 'cep' deve ter de 5 a 10 caracteres")
	@Column(nullable = false)
	private String cep;

	@NotBlank(message = "Campo 'numeroResidencia' não pode ser vazio ou nulo")
	@Size(min = 1, max = 10, message = "Campo 'numeroResidencia' deve ter de 1 a 10 caracteres")
	@Column(name = "numero_residencia", nullable = false)
	private String numeroResidencia;

	@Size(max = 30, message = "Campo 'complemento' deve ter de 1 a 30 caracteres")
	private String complemento;

	@NotBlank(message = "Campo 'logradouro' não pode ser vazio ou nulo")
	@Size(min = 2, max = 100, message = "Campo 'logradouro' deve ter de 2 a 100 caracteres")
	@Column(nullable = false)
	private String logradouro;

	@NotBlank(message = "Campo 'bairro' não pode ser vazio ou nulo")
	@Size(min = 2, max = 100, message = "Campo 'bairro' deve ter de 2 a 100 caracteres")
	@Column(nullable = false)
	private String bairro;

	@NotBlank(message = "Campo 'cidade' não pode ser vazio ou nulo")
	@Size(min = 2, max = 100, message = "Campo 'cidade' deve ter de 2 a 100 caracteres")
	@Column(nullable = false)
	private String cidade;

	@NotBlank(message = "Campo 'uf' não pode ser vazio ou nulo")
	@Size(min = 2, max = 2, message = "Campo 'uf' deve ter 2 caracteres")
	@Column(nullable = false)
	private String uf;

	@ManyToOne
	@JoinColumn(name = "cpf")
	@JsonBackReference
	private Pessoa pessoa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumeroResidencia() {
		return numeroResidencia;
	}

	public void setNumeroResidencia(String numeroResidencia) {
		this.numeroResidencia = numeroResidencia;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
