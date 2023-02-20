package com.example.CrudOperation.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.CrudOperation.domain.Cliente;
import com.example.CrudOperation.domain.Cuenta;
import com.example.CrudOperation.domain.Movimiento;
import com.example.CrudOperation.service.CuentaService;
import com.example.CrudOperation.service.MovimientoService;

@Controller
@RequestMapping(path = "/movimientos")
public class MovimientosController {
	@Autowired
	private MovimientoService service;

	@Autowired CuentaService serviceCuenta;

	@PostMapping(value ="/CrearMovimiento", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createMovement(@RequestBody Movimiento mov) {
		try {
			if(mov!=null) {			

				BigDecimal temp= null;
				List<Movimiento> _move = service.findByCount(mov.getNumerocuenta());
				Optional<Cuenta> count=serviceCuenta.findById(mov.getNumerocuenta());
				if(_move != null ) {
					temp=_move.get(_move.size()-1).getSaldo();					
				}
				else if (count.isPresent()) {					
					temp=count.get().getSaldoinicial();
				} 
				else {
					return new ResponseEntity<>("No hay cuentas creadas", HttpStatus.FAILED_DEPENDENCY);
				}

				switch (mov.getTipomovimiento()) {
				case "+":

					temp= temp.add(mov.getValor());
					break;
				case "-":
					int resultado = temp.compareTo(mov.getValor());
					if(resultado<0) {						
						return new ResponseEntity<>("Saldo insuficiente", HttpStatus.ACCEPTED);
					}
					temp= temp.subtract(mov.getValor());
					break;
				default:
					return new ResponseEntity<>("tipo de movimiento no valida", HttpStatus.CONFLICT);
				}
				Movimiento _mov = service.save(
						new Movimiento(
								mov.getFechamovimiento(),
								mov.getNumerocuenta(),
								mov.getDescripcion(),
								mov.getTipomovimiento(),							
								mov.getValor(),
								temp

								));

				return new ResponseEntity<>(_mov, HttpStatus.CREATED);
			}
			else {
				return new ResponseEntity<>("Error objeto de entrada", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/Listar")
	public ResponseEntity<?> getAllMovement() {
		try {
			List<Movimiento> lstMovements = service.listAll();
			if (lstMovements.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<List<Movimiento>>(lstMovements, HttpStatus.OK);}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

	@GetMapping("/ConsultaXCuenta/{id}")
	public ResponseEntity<?> findByCount(@PathVariable("id") long id) {
		try {
			if (id!=0L) {
				List<Movimiento> mov=service.findByCount(id);

				return new ResponseEntity<>(mov.get(mov.size()-1), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/Actualizar")
	public ResponseEntity<Movimiento> updateMovement(@RequestBody Movimiento mov) {

		Optional<Movimiento> movimientoData = service.findById(mov.getMovimientoid());

		if (movimientoData.isPresent()) {
			Movimiento _mov = movimientoData.get();
			_mov.setFechamovimiento(mov.getFechamovimiento());
			_mov.setNumerocuenta(mov.getNumerocuenta());
			_mov.setDescripcion(mov.getDescripcion());
			_mov.setTipomovimiento(mov.getTipomovimiento());
			_mov.setValor(mov.getValor());
			_mov.setSaldo(mov.getSaldo());
			return new ResponseEntity<>(service.save(_mov), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/Borrar/{id}")
	public ResponseEntity<?> deleteMovement(@PathVariable("id") long id) {
		try {
			if (id!=0L) {
				Movimiento mov=service.delete(id);
				
				return new ResponseEntity<>("Eliminado exitosamente", HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/Reporte/{id}")
	public ResponseEntity<?> reporteMovement(
			@PathVariable ("id") long id,
			@RequestParam ("fechaInicio") 
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaInicio,
			@RequestParam ("fechaFin") 
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaFin
			
			) {
		try {
			if (fechaInicio!=null&& fechaFin!=null) {
				List<Movimiento> mov=service.findByDate(fechaInicio, fechaFin);
				
				return new ResponseEntity<>(mov, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
