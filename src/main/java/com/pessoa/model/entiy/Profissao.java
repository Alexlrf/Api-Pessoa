package com.pessoa.model.entiy;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "profissao")
public class Profissao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_profissao")
	private Long id;

	@NotBlank(message = "Campo 'nomeProfissao' não pode ser vazio ou nulo")
	@Size(min = 2, max = 100, message = "Campo 'nomeProfissao' deve ter de 2 a 100 caracteres")
	@Column(name = "nome_profissao", nullable = false)
	private String nomeProfissao;

	@NotNull(message = "Campo 'clt' não pode ser vazio ou nulo")
	private Boolean clt;

	@NotNull(message = "Campo 'rendaMensal' não pode ser vazio ou nulo")
	@Column(name = "renda_mensal", nullable = false)
	private BigDecimal rendaMensal;

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

	public String getNomeProfissao() {
		return nomeProfissao;
	}

	public void setNomeProfissao(String nomeProfissao) {
		this.nomeProfissao = nomeProfissao;
	}

	public Boolean getClt() {
		return clt;
	}

	public void setClt(Boolean clt) {
		this.clt = clt;
	}

	public BigDecimal getRendaMensal() {
		return rendaMensal;
	}

	public void setRendaMensal(BigDecimal rendaMensal) {
		this.rendaMensal = rendaMensal;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
