package com.example.CrudOperation.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CrudOperation.domain.Movimiento;
import com.example.CrudOperation.domain.Persona;
import com.example.CrudOperation.repository.MovimientoRepository;

@Service
public class MovimientoService {
	
	@Autowired
	private MovimientoRepository repo;
	
	
	public List <Movimiento> listAll(){		
		return repo.buscarTodo();
	}

	public Optional<Movimiento> findById(long id) {
		 System.out.println(Optional.ofNullable(repo.buscarXid(id)));
		 return Optional.ofNullable(repo.buscarXid(id));
	}
	
	public List <Movimiento> findByCount(long id) {
		 return repo.buscarXCuenta(id);
	}
	
	public Movimiento save(Movimiento _Movimiento) {
		return repo.insertar(_Movimiento);
	}
	
	public List <Movimiento> findByDate(Date ini,Date fin) {
		 return repo.buscarXFecha(ini,fin);
	}
	
	public Movimiento delete(long id) {
		Optional<Movimiento> cli = this.findById(id);					
		return repo.borrar(cli.get());
	}

}
