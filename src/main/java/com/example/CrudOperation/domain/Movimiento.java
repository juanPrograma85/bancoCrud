package com.example.CrudOperation.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Movimiento")
public class Movimiento implements Serializable {

	public Movimiento() {

	}

	public Movimiento(  Long numerocuenta, 
			String tipomovimiento, BigDecimal valor, BigDecimal saldo) {
		super();

		this.numerocuenta = numerocuenta;
		this.tipomovimiento = tipomovimiento;
		this.valor = valor;
		this.saldo = saldo;
	}

	public Movimiento(Date fechamovimiento, String nombre, Long numerocuenta, String tipocuenta,
			BigDecimal saldoinicial, Boolean estado, BigDecimal valor, BigDecimal saldo) {
		super();
		this.fechamovimiento = fechamovimiento;		
		this.numerocuenta = numerocuenta;		
		this.valor = valor;
		this.saldo = saldo;
	}



	public Movimiento(Date fechamovimiento, Long numerocuenta, String descripcion,
			String tipomovimiento, BigDecimal valor, BigDecimal saldo) {
		super();
		this.fechamovimiento = fechamovimiento;
		this.numerocuenta = numerocuenta;
		this.descripcion = descripcion;
		this.tipomovimiento = tipomovimiento;
		this.valor = valor;
		this.saldo = saldo;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 2928070209884782911L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long movimientoid;

	@Column(name = "fechamovimiento", nullable = true)
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date fechamovimiento;

	@Column(name = "numerocuenta", nullable = false)
	private Long numerocuenta;

	@Column(name = "descripcion", nullable = true)
	private String descripcion;

	@Column(name = "tipomovimiento", nullable = true)
	private String tipomovimiento;

	@Column(name = "valor", nullable = true)
	private BigDecimal valor;

	@Column(name = "saldo", nullable = true)
	private BigDecimal saldo;

	public Long getMovimientoid() {
		return movimientoid;
	}

	public void setMovimientoid(Long movimientoid) {
		this.movimientoid = movimientoid;
	}

	public Date getFechamovimiento() {
		return fechamovimiento;
	}

	public void setFechamovimiento(Date fechamovimiento) {
		this.fechamovimiento = fechamovimiento;
	}

	public Long getNumerocuenta() {
		return numerocuenta;
	}

	public void setNumerocuenta(Long numerocuenta) {
		this.numerocuenta = numerocuenta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipomovimiento() {
		return tipomovimiento;
	}

	public void setTipomovimiento(String tipomovimiento) {
		this.tipomovimiento = tipomovimiento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "Movimiento [movimientoid=" + movimientoid + ", fechamovimiento=" + fechamovimiento + ", numerocuenta="
				+ numerocuenta + ", descripcion=" + descripcion + ", tipomovimiento=" + tipomovimiento + ", valor="
				+ valor + ", saldo=" + saldo + "]";
	}
}
