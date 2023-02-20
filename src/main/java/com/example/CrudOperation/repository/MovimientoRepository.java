package com.example.CrudOperation.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.CrudOperation.domain.Movimiento;

@Repository("movimientoRepository")
public class MovimientoRepository {
	@PersistenceContext
	private EntityManager em;

	public List<Movimiento> buscarTodo() {
		return em.createQuery("select m from Movimiento m", Movimiento.class).getResultList();
	}

	public Movimiento buscarXid(long id) {
		return em.find(Movimiento.class, id);
	}

	public List<Movimiento> buscarXCuenta(long numerocuenta) {
		TypedQuery<Movimiento> movimiento= em.createQuery("select m from Movimiento m where m.numerocuenta=:numerocuenta", Movimiento.class);
		movimiento.setParameter("numerocuenta", numerocuenta);
		return movimiento.getResultList();
	}

	public List<Movimiento> buscarXFecha(Date ini, Date fin) {
		TypedQuery<Movimiento> movimiento= em.createQuery("SELECT m.fechamovimiento, "
				+ "p.nombre, cta.numerocuenta, cta.tipocuenta,"
				+ "cta.saldoinicial,cta.estado,m.valor ,"
				+ " m.saldo FROM Movimiento m INNER JOIN Cuenta cta "
				+ "ON m.numerocuenta=cta.numerocuenta INNER JOIN Cliente cli on"
				+ " cli.clienteid=cta.clienteid INNER JOIN Persona p on"
				+ " p.identificacion=cli.persona where "
				+ "m.fechamovimiento between :ini and :fin ", Movimiento.class);
		movimiento.setParameter("ini", ini);
		movimiento.setParameter("fin", fin);
		return movimiento.getResultList();
	}
	@Transactional
	public Movimiento borrar(Movimiento mov) {
		em.remove(em.merge(mov));
		return mov;
	}
	@Transactional
	public Movimiento insertar(Movimiento mov) {
		em.persist(mov);
		return mov;
	}

	@Transactional
	public void actualizar(Movimiento mov) {
		em.merge(mov);
	}

}
