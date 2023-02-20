package com.example.CrudOperation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CrudOperation.domain.Cuenta;
import com.example.CrudOperation.repository.CuentaRepository;

@Service
public class CuentaService {

	@Autowired
	private CuentaRepository repoCta;
	
	public List <Cuenta> listAll(){		
		return repoCta.buscarTodo();
	}

	public Optional<Cuenta> findById(long id) {
		return Optional.ofNullable(repoCta.buscarXid(id));
	}

	public Cuenta save(Cuenta _Cuenta) {
		return repoCta.insertar(_Cuenta);
	}
	
	public Cuenta delete(Long id) {
		Cuenta cta = new Cuenta();
		cta.setNumerocuenta(id);
		return repoCta.borrar(cta);
	}
	
}
