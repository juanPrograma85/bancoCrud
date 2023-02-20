package com.example.CrudOperation.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.CrudOperation.domain.Cliente;
import com.example.CrudOperation.domain.Persona;
import com.example.CrudOperation.service.ClienteService;

@Controller
@RequestMapping(path = "/clientes")
public class ClienteController {
	@Autowired
	private ClienteService service;

	@PostMapping(value ="/crear", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createCustomer(@RequestBody Cliente cliente) {
		try {

			Persona persona=service.save(cliente.getPersona());			

			Cliente _cliente = service.save(new Cliente(cliente.getContraseña(), cliente.getEstado(),cliente.getPersona()));

			return new ResponseEntity<>(_cliente, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/Listar")
	public ResponseEntity<?> getAllCustomer() {
		try {
			List<Cliente> lstClient = service.listAll();
			if (lstClient.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<List<Cliente>>(lstClient, HttpStatus.OK);}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

	@PutMapping("/Actualizar")
	public ResponseEntity<Cliente> updateCustomer(@RequestBody Cliente cli) {

		Optional<Cliente> clienteData = service.findById(cli.getClienteid());

		if (clienteData.isPresent()) {
			Cliente _cliente = clienteData.get();
			_cliente.setContraseña(cli.getContraseña());
			_cliente.setEstado(cli.getEstado());
			return new ResponseEntity<>(service.save(_cliente), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/Borrar/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("id") long id) {
		try {
			Optional<Cliente> clienteData = service.findById(id);
			if (id!=0L) {
				Cliente cli=service.delete(id);
				service.delete(clienteData.get().getPersona());
				return new ResponseEntity<>("Eliminado exitosamente", HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
