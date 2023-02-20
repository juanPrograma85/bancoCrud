package com.example.CrudOperation.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Cliente")
public class Cliente implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5243803789119999867L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clienteid;
	
	@Column(name = "contraseña",length = 12, nullable = true)
	private String contraseña;
	
	@Column(name = "estado", nullable = true)
	private Boolean estado;
	
	@OneToOne
	@JoinColumn(name="personaidentificacion")
	private Persona persona;
	
	public Cliente() {
	}
	
	public Cliente( String contraseña, Boolean estado, Persona persona ) {
		super();
		this.contraseña = contraseña;
		this.estado = estado;
		this.persona=persona;
		
	}
	
	public Cliente(Long clienteid, String contraseña, Boolean estado, Persona persona) {
		super();
		this.clienteid = clienteid;
		this.contraseña = contraseña;
		this.estado = estado;
		this.persona=persona;
	}
	
	
	@Override
	public String toString() {
		return "Cliente [clienteid=" + clienteid + ", contraseña=" + contraseña + ", estado=" + estado + "]";
	}

	public Long getClienteid() {
		return clienteid;
	}

	public void setClienteid(Long clienteid) {
		this.clienteid = clienteid;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	

}
