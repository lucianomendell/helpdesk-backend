package com.br.helpdesk.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.helpdesk.domain.Tecnico;
import com.br.helpdesk.domain.dtos.TecnicoDTO;
import com.br.helpdesk.services.TecnicoService;

@RestController
@RequestMapping(value="/tecnicos")
public class TecnicoResource {
	
	@Autowired
	private TecnicoService tecnicoService;
	
	
	@GetMapping(value="/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
		
		Tecnico tecnico = this.tecnicoService.findById(id);		
		return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
	}

}
