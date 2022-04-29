package com.pessoa.model.entiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "telefone")
public class Telefone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_telefone")
	private Long id;

	@NotBlank(message = "Campo 'celular' não pode ser vazio ou nulo")
	@Size(max = 12, message = "Campo 'celular' deve ter no máximo 12 caracteres")
	@Column(nullable = false)
	private String celular;

	@Size(max = 12, message = "Campo 'telefoneFixo' deve ter no máximo 12 caracteres")
	@Column(name = "telefone_fixo")
	private String telefoneFixo;

	@OneToOne
	@JoinColumn(name = "cpf")
	@JsonBackReference
	private Pessoa pessoa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
