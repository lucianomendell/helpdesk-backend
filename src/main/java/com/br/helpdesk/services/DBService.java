package com.br.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.helpdesk.domain.Chamado;
import com.br.helpdesk.domain.Cliente;
import com.br.helpdesk.domain.Tecnico;
import com.br.helpdesk.domain.enums.PerfilEnum;
import com.br.helpdesk.domain.enums.PrioridadeEnum;
import com.br.helpdesk.domain.enums.StatusEnum;
import com.br.helpdesk.repositories.ChamadoRepository;
import com.br.helpdesk.repositories.ClienteRepository;
import com.br.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public void instanciaDB() {
		
		Tecnico tec1 = new Tecnico(null, "Valdir Cezar", "908.496.580-98", "valdir@mail.com", encoder.encode("123"));
		tec1.AddPerfi(PerfilEnum.ADMIN);
		
		Tecnico tec2 = new Tecnico(null, "Lucas Cliente ", "435.169.540-43", "lucas@mail.com", encoder.encode("123"));
		tec1.AddPerfi(PerfilEnum.CLIENTE);
		
		Cliente cli1 = new Cliente(null, "Linus Torvalds", "951.697.650-61", "torvalds@mail.com", encoder.encode("123"));
		
		
		Chamado c1 = new Chamado(null, PrioridadeEnum.MEDIA, StatusEnum.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tec1, cli1);		
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		tecnicoRepository.saveAll(Arrays.asList(tec2));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));	
		
	}

}
