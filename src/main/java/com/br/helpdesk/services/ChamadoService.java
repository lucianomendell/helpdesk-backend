package com.br.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.helpdesk.domain.Chamado;
import com.br.helpdesk.domain.Cliente;
import com.br.helpdesk.domain.Tecnico;
import com.br.helpdesk.domain.dtos.ChamadoDTO;
import com.br.helpdesk.domain.enums.PrioridadeEnum;
import com.br.helpdesk.domain.enums.StatusEnum;
import com.br.helpdesk.repositories.ChamadoRepository;
import com.br.helpdesk.services.exception.ObjectNotFoundException;

@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	
	
	public Chamado findById(Integer id) {
		
		Optional<Chamado> obj = this.chamadoRepository.findById(id);
		
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! ID "+id));
	}


	public List<Chamado> findAll() {		
		
		return chamadoRepository.findAll();
	}


	public Chamado create(@Valid ChamadoDTO objDTO) {
		
		return chamadoRepository.save(newChamado(objDTO));
	}
	
	
	//Popula CHAMADO 
	private Chamado newChamado(ChamadoDTO objDTO) {
		
		Tecnico tecnico = tecnicoService.findById(objDTO.getTecnico());
		
		Cliente cliente = clienteService.findById(objDTO.getCliente());
		
		Chamado chamado = new Chamado();
		
		if(objDTO.getId() !=null) {
			
			chamado.setId(objDTO.getId());
		}
		
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		
		//no DTO é recebida o Códio e não a descrição, será necessário realizar uma conversão.
		//No chamado é esperado um ENUM, será necessário usar o método que busca o ENUM pelo ID		
		chamado.setPrioridade(PrioridadeEnum.toEnum(objDTO.getPrioridade()));
		chamado.setStatus(StatusEnum.toEnum(objDTO.getStatus()));
		chamado.setTitulo(objDTO.getTitulo());
		chamado.setObservacao(objDTO.getObservacao());
		
		return chamado;
	}

}
