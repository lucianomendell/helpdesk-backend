package com.br.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.helpdesk.domain.Chamado;
import com.br.helpdesk.repositories.ChamadoRepository;
import com.br.helpdesk.services.exception.ObjectNotFoundException;

@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	
	
	public Chamado findById(Integer id) {
		
		Optional<Chamado> obj = this.chamadoRepository.findById(id);
		
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! ID "+id));
	}

}
