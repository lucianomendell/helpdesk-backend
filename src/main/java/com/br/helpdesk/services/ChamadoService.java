package com.br.helpdesk.services;

import java.time.LocalDate;
import java.time.LocalTime;
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
		
		if(objDTO.getStatus().equals(2)) {
			
			chamado.setDataFechamento(LocalDate.now());
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


	public Chamado update(Integer id, @Valid ChamadoDTO objDTO) {
		objDTO.setId(id);
		
		Chamado oldObj = findById(id);
		
		//Se existir É invocado uma outro método que realiza o Update
		oldObj = newChamado(objDTO);				
		
		//Por meio do Id o Hibernate compreende que é uma atualização
		return chamadoRepository.save(oldObj);
	}

}
