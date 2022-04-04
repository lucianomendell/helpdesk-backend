package com.br.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.helpdesk.domain.Pessoa;
import com.br.helpdesk.domain.Tecnico;
import com.br.helpdesk.domain.dtos.TecnicoDTO;
import com.br.helpdesk.repositories.PessoaRepository;
import com.br.helpdesk.repositories.TecnicoRepository;
import com.br.helpdesk.services.exception.DataIntegrityViolationException;
import com.br.helpdesk.services.exception.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	public Tecnico findById(Integer id) {
		
		Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
		
		return tecnico.orElseThrow(() -> new ObjectNotFoundException("Objeto Não Encontrado! id:"+ id));
		
	}


	public List<Tecnico> findAll() {
		
		return tecnicoRepository.findAll();
	}


	public Tecnico create(TecnicoDTO objDTO) {		
		objDTO.setId(null);	
		
		validaPorCPFeEmail(objDTO);
		Tecnico newObj = new Tecnico(objDTO);
		
		return tecnicoRepository.save(newObj);
	}


	private void validaPorCPFeEmail(TecnicoDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() !=objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF Já Cadastrado no Sistema");
			
		}
		
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() !=objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail Já Cadastrado no Sistema");
			
		}
		
	}
}
