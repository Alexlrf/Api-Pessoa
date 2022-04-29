package com.pessoa.model.dto;

import java.util.Date;
import java.util.List;

import com.pessoa.model.entiy.Endereco;
import com.pessoa.model.entiy.Pessoa;
import com.pessoa.model.entiy.Profissao;
import com.pessoa.model.entiy.Telefone;

public class PessoaDto {

	private Long id;
	private String nome;
	private String cpf;
	private String email;
	private Date dataNascimento;
	private Profissao profissao;
	private Telefone telefones;
	private List<Endereco> enderecos;	

	public PessoaDto(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.cpf = pessoa.getCpf();
		this.email = pessoa.getEmail();
		this.dataNascimento = pessoa.getDataNascimento();
		this.profissao = pessoa.getProfissao();
		this.telefones = pessoa.getTelefone();
		this.enderecos = pessoa.getEnderecos();
	}
	
	public PessoaDto() {
		 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Profissao getProfissao() {
		return profissao;
	}

	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}

	public Telefone getTelefones() {
		return telefones;
	}

	public void setTelefones(Telefone telefones) {
		this.telefones = telefones;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

}
