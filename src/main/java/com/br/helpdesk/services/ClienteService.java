package com.br.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.helpdesk.domain.Pessoa;
import com.br.helpdesk.domain.Cliente;
import com.br.helpdesk.domain.dtos.ClienteDTO;
import com.br.helpdesk.repositories.PessoaRepository;
import com.br.helpdesk.repositories.ClienteRepository;
import com.br.helpdesk.services.exception.DataIntegrityViolationException;
import com.br.helpdesk.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository ClienteRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	public Cliente findById(Integer id) {
		
		Optional<Cliente> Cliente = ClienteRepository.findById(id);
		
		return Cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto Não Encontrado! id:"+ id));
		
	}


	public List<Cliente> findAll() {
		
		return ClienteRepository.findAll();
	}


	public Cliente create(ClienteDTO objDTO) {		
		objDTO.setId(null);	
		
		validaPorCPFeEmail(objDTO);
		Cliente newObj = new Cliente(objDTO);
		
		return ClienteRepository.save(newObj);
	}


	private void validaPorCPFeEmail(ClienteDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() !=objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF Já Cadastrado no Sistema");
			
		}
		
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() !=objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail Já Cadastrado no Sistema");
			
		}
		
	}


	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		
		objDTO.setId(id);		
		
		//verificando se existe pelo Id
		Cliente oldObj = findById(id);
		validaPorCPFeEmail(objDTO);		
		
		oldObj = new Cliente(objDTO);
		return ClienteRepository.save(oldObj);
		
	}


	public void delete(Integer id) {
		Cliente obj = findById(id);
		
		if(obj.getChamados().size() > 0) {
			
			throw new DataIntegrityViolationException("Cliente possui Chamado associado a ele!");
			
		}			
			this.ClienteRepository.delete(obj);		
		
	}
}
