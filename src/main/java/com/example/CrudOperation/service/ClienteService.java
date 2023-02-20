package com.example.CrudOperation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CrudOperation.domain.Cliente;
import com.example.CrudOperation.domain.Persona;
import com.example.CrudOperation.repository.ClienteRepository;
import com.example.CrudOperation.repository.PersonaRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private PersonaRepository repoPersona;


	public List <Cliente> listAll(){		
		return repo.buscarTodo();
	}

	public Optional<Cliente> findById(long id) {
		return Optional.ofNullable(repo.buscarXid(id));
	}

	public Cliente save(Cliente _cliente) {
		return repo.insertar(_cliente);
	}

	public Persona save(Persona _persona) {
		return repoPersona.insertar(_persona);
	}

	public Cliente delete(long id) {
		Optional<Cliente> cli = this.findById(id);					
		return repo.borrar(cli.get());
	}

	public Persona delete(Persona persona) {		
		return repoPersona.borrar(persona);
	}
}
