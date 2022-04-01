package com.br.helpdesk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.helpdesk.domain.Chamado;
import com.br.helpdesk.domain.Cliente;
import com.br.helpdesk.domain.Tecnico;
import com.br.helpdesk.domain.enums.PerfilEnum;
import com.br.helpdesk.domain.enums.PrioridadeEnum;
import com.br.helpdesk.domain.enums.StatusEnum;
import com.br.helpdesk.repositories.ChamadoRepository;
import com.br.helpdesk.repositories.ClienteRepository;
import com.br.helpdesk.repositories.TecnicoRepository;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner{
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ChamadoRepository chamadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Tecnico tec1 = new Tecnico(null, "Valdir Cezar", "908.496.580-98", "valdir@mail.com", "123");
		tec1.AddPerfi(PerfilEnum.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Linus Torvalds", "951.697.650-61", "torvalds@mail.com", "123");
		
		
		Chamado c1 = new Chamado(null, PrioridadeEnum.MEDIA, StatusEnum.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tec1, cli1);
		
		
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
		
	}

}
