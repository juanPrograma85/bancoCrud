package com.example.CrudOperation.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Persona")
public class Persona implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1124499011343805259L;

	@Id
	private Long identificacion;

	@Column(name = "nombre",length = 100)
	private String nombre;

	@Column(name = "genero",length = 15, nullable = true)
	private String genero;

	@Column(name = "edad", nullable = true)
	private int edad;

	@Column(name = "direccion",length = 100, nullable = true)
	private String direccion;

	@Column(name = "telefono",length = 10, nullable = true)
	private String telefono;

	@OneToOne(mappedBy = "persona")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Cliente cli;

	public Long getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(Long identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Cliente getCli() {
		return cli;
	}

	public void setCli(Cliente cli) {
		this.cli = cli;
	}

	@Override
	public String toString() {
		return "Persona [identificacion=" + identificacion + ", nombre=" + nombre + ", genero=" + genero + ", edad="
				+ edad + ", direccion=" + direccion + ", telefono=" + telefono + ", cli=" + cli + "]";
	}
	
}
