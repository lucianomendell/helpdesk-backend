package com.br.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ManyToAny;

import com.br.helpdesk.domain.dtos.ClienteDTO;
import com.br.helpdesk.domain.dtos.TecnicoDTO;
import com.br.helpdesk.domain.enums.PerfilEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cliente extends Pessoa {
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Chamado> chamados = new ArrayList<>();

	public Cliente() {
		super();
		AddPerfi(PerfilEnum.CLIENTE);
		
	}

	public Cliente(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		AddPerfi(PerfilEnum.CLIENTE);
	}
	
	
	public Cliente(ClienteDTO tecnico) {
		super();
		this.id = tecnico.getId();
		this.nome = tecnico.getNome();
		this.cpf = tecnico.getCpf();
		this.email = tecnico.getEmail();
		this.senha = tecnico.getSenha();		
		this.perfis = tecnico.getPerfis().stream().map(x ->x.getCodigo()).collect(Collectors.toSet()); //Tranformando um perfil em um Integer
		this.dataCriacao = tecnico.getDataCriacao();
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}
	

}
