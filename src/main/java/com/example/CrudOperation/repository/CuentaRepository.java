package com.example.CrudOperation.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.CrudOperation.domain.Cuenta;


@Repository("cuentaRepository")
public class CuentaRepository {


	@PersistenceContext
	private EntityManager entity;

	public List<Cuenta> buscarTodo() {
		return entity.createQuery("select c from Cuenta c", Cuenta.class).getResultList();
	}

	public Cuenta buscarXid(long id) {
		return entity.find(Cuenta.class, id);
	}

	@Transactional
	public Cuenta borrar(Cuenta cta) {
		entity.remove(entity.merge(cta));
		return cta;
	}
	@Transactional
	public Cuenta insertar(Cuenta cta) {
		entity.persist(cta);
		return cta;
	}

	@Transactional
	public void actualizar(Cuenta cta) {
		entity.merge(cta);
	}
}
