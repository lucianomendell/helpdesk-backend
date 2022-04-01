package com.br.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
	
	

}
