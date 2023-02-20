package com.example.CrudOperation.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.CrudOperation.domain.Persona;

@Repository("personaRepository")
public class PersonaRepository {

	@PersistenceContext
	private EntityManager em;

	public List<Persona> buscarTodo() {
		return em.createQuery("select p from Persona p", Persona.class).getResultList();
	}

	public Persona buscarXid(long id) {
		return em.find(Persona.class, id);
	}

	@Transactional
	public Persona borrar(Persona persona) {
		em.remove(em.merge(persona));
		return persona;
	}
	
	@Transactional
	public Persona insertar(Persona persona) {
		em.persist(persona);
		return persona;
	}

	@Transactional
	public void actualizar(Persona persona) {
		em.merge(persona);
	}

}
