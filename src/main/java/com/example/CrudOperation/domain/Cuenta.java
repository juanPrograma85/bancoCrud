package com.example.CrudOperation.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Cuenta")
public class Cuenta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8767030707399039530L;


	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "numerocuenta",columnDefinition="numeric(19, 0)", nullable = false)
	private Long numerocuenta;

	@Column(name = "tipocuenta",columnDefinition="char",length = 20, nullable = true)
	private String tipocuenta;

	@Column(name = "clienteid", nullable = true)
	private Integer clienteid;

	@Column(name = "saldoinicial", columnDefinition="decimal", precision=18, scale=0,nullable = true)
	private BigDecimal saldoinicial;

	@Column(name = "estado", nullable = true)
	private Boolean estado;


	public Cuenta() {
	}

	public Cuenta(Long numerocuenta, String tipocuenta, Integer clienteid, BigDecimal saldoinicial, Boolean estado) {
		super();
		this.numerocuenta = numerocuenta;
		this.tipocuenta = tipocuenta;
		this.clienteid = clienteid;
		this.saldoinicial = saldoinicial;
		this.estado = estado;
	}

	public Long getNumerocuenta() {
		return numerocuenta;
	}

	public void setNumerocuenta(Long numerocuenta) {
		this.numerocuenta = numerocuenta;
	}

	public String getTipocuenta() {
		return tipocuenta;
	}

	public void setTipocuenta(String tipocuenta) {
		this.tipocuenta = tipocuenta;
	}

	public Integer getClienteid() {
		return clienteid;
	}

	public void setClienteid(Integer clienteid) {
		this.clienteid = clienteid;
	}

	public BigDecimal getSaldoinicial() {
		return saldoinicial;
	}

	public void setSaldoinicial(BigDecimal saldoinicial) {
		this.saldoinicial = saldoinicial;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cuenta [numerocuenta=" + numerocuenta + ", tipocuenta=" + tipocuenta + ", clienteid=" + clienteid
				+ ", saldoinicial=" + saldoinicial + ", estado=" + estado + "]";
	}
}
