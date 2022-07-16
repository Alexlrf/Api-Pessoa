package com.pessoa.model.entiy;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "pessoa")
public class Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pessoa")
	private Long id;

	@NotBlank(message = "Campo 'nome' não pode ser vazio ou nulo")
	@Size(min = 2, max = 100, message = "Campo 'nome' deve ter de 2 a 100 caracteres")
	@Column(nullable = false)
	private String nome;

	@NotBlank(message = "Campo 'cpf' não pode ser vazio ou nulo")
	@Size(min = 11, max = 11, message = "Campo 'cpf' deve ter 11 caracteres")
	@Column(nullable = false)
	private String cpf;

	@NotBlank(message = "Campo 'email' não pode ser vazio ou nulo")
	@Email(message = "Campo 'email' deve conter um email válido")
	@Column(nullable = false)
	private String email;

	@Column(name = "data_nascimento", nullable = false)
	@NotNull(message = "Campo 'dataNascimento' não pode ser vazio ou nulo")
	private Date dataNascimento;

	@Valid
	@NotNull(message = "Campo 'profissao' não pode ser vazio ou nulo")
	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.PERSIST, optional = false)
	@JsonManagedReference
	private Profissao profissao;

	@Valid
	@NotNull(message = "Campo 'telefone' não pode ser vazio ou nulo")
	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.PERSIST, optional = false)
	@JsonManagedReference
	private Telefone telefone;

	@Valid
	@NotNull(message = "Campo 'enderecos' não pode ser vazio ou nulo")
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.PERSIST)
	@JsonManagedReference
	private List<Endereco> enderecos;

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

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefones(Telefone telefone) {
		this.telefone = telefone;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

}
