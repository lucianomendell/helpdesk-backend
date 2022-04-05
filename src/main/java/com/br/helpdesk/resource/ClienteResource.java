package com.br.helpdesk.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.helpdesk.domain.Cliente;
import com.br.helpdesk.domain.dtos.ClienteDTO;
import com.br.helpdesk.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;
	
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
		
		Cliente cliente = this.clienteService.findById(id);		
		return ResponseEntity.ok().body(new ClienteDTO(cliente));
	}

	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>>findAll(){
		List<Cliente> list = this.clienteService.findAll();
		
		//Convertendo em uma DTO
		List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
		
	}
	
	
	@PostMapping
	public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO objDTO){
		
		Cliente newObj = this.clienteService.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Integer id,@Valid @RequestBody ClienteDTO objDTO){
		
		Cliente obj = this.clienteService.update(id, objDTO);
		return ResponseEntity.ok().body(new ClienteDTO(obj));

	}
	
	@DeleteMapping(value ="/{id}")
	public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id){		
		this.clienteService.delete(id);
		
		return ResponseEntity.noContent().build();		
		
	}
	
}
