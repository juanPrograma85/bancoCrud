package com.example.CrudOperation.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.CrudOperation.domain.Cliente;

@Repository("clienteRepository")
public class ClienteRepository {
	@PersistenceContext
	private EntityManager em;

	public List<Cliente> buscarTodo() {
		return em.createQuery("select c from Cliente c", Cliente.class).getResultList();
	}

	public Cliente buscarXid(long id) {
	    return em.find(Cliente.class, id);
	  }
	
	@Transactional
	  public Cliente borrar(Cliente cli) {
	     em.remove(em.merge(cli));
		return cli;
	  }
	  @Transactional
	  public Cliente insertar(Cliente cli) {
	     em.persist(cli);
		return cli;
	  }
	  
	  @Transactional
	  public void actualizar(Cliente cli) {
	     em.merge(cli);
	  }
}
