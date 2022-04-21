package com.pessoa.model.entiy;

import java.math.BigDecimal;

public class Profissao {

	private Long id;
	private String nomeProfissao;
	private Boolean clt;
	private BigDecimal rendaMensal;

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

}
