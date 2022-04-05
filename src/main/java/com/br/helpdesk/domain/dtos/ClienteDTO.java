package com.br.helpdesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.br.helpdesk.domain.Cliente;
import com.br.helpdesk.domain.Tecnico;
import com.br.helpdesk.domain.enums.PerfilEnum;

public class ClienteDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	protected Integer id;
	
	@NotNull(message = "O campo NOME é requerido")
	protected String nome;	
	
	@NotNull(message = "O campo CPF é requerido")
	protected String cpf;	
	
	@NotNull(message = "O campo EMAIL é requerido")
	protected String email;	
	
	@NotNull(message = "O campo SENHA é requerido")
	protected String senha;	
	
	protected Set<Integer> perfis = new HashSet<>();	
	protected LocalDate dataCriacao = LocalDate.now();
	
	
	public ClienteDTO() {
		
		super();
		addPerfil(PerfilEnum.CLIENTE);
	}
	
	
	public ClienteDTO(Cliente cliente) {
		super();
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.email = cliente.getEmail();
		this.senha = cliente.getSenha();		
		this.perfis = cliente.getPerfis().stream().map(x ->x.getCodigo()).collect(Collectors.toSet()); //Tranformando um perfil em um Integer
		this.dataCriacao = cliente.getDataCriacao();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
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


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public Set<PerfilEnum> getPerfis() {		
		
		return perfis.stream().map(x ->PerfilEnum.toEnum(x)).collect(Collectors.toSet());
	}


	public void addPerfil(PerfilEnum perfis) {
		this.perfis.add(perfis.getCodigo());
	}


	public LocalDate getDataCriacao() {
		return dataCriacao;
	}


	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
}
