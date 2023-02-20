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

import com.example.CrudOperation.domain.Cuenta;
import com.example.CrudOperation.service.CuentaService;



@Controller
@RequestMapping(path = "/cuentas")
public class CuentaController {

	@Autowired
	private CuentaService serviceCta;

	@PostMapping("crearCuenta")
	public ResponseEntity<?> createCount(@RequestBody Cuenta cta) {
		try {
			System.out.println(cta);
			Cuenta _cuenta = serviceCta.save(
					new Cuenta(cta.getNumerocuenta(), cta.getTipocuenta(),
							cta.getClienteid(),cta.getSaldoinicial(),cta.getEstado())
					);
			return new ResponseEntity<>(_cuenta, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/Listar")
	public ResponseEntity<List<Cuenta>> getAllCount() {
		try {
			List<Cuenta> lstClient = serviceCta.listAll();
			if (lstClient.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<List<Cuenta>>(lstClient, HttpStatus.OK);}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@PutMapping("/Actualizar")
	public ResponseEntity<Cuenta> updateCount(@RequestBody Cuenta cta) {

		Optional<Cuenta> ctaData = serviceCta.findById(cta.getNumerocuenta());

		if (ctaData.isPresent()) {
			Cuenta _cuenta = ctaData.get();
			_cuenta.setSaldoinicial(cta.getSaldoinicial());
			_cuenta.setTipocuenta(cta.getTipocuenta());
			_cuenta.setEstado(cta.getEstado());
			_cuenta.setClienteid(cta.getClienteid());
			return new ResponseEntity<>(serviceCta.save(_cuenta), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/Borrar/{id}")
	public ResponseEntity<Cuenta> deleteCount(@PathVariable("id") Long id) {
		Optional<Cuenta> cuentaData = serviceCta.findById(id);

		if (id!=0L) {
			return new ResponseEntity<>(serviceCta.delete(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
