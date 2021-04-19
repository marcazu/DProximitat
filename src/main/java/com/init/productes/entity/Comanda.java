package com.init.productes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javassist.compiler.ast.Pair;

@Entity
@Table(name="Comandes")
public class Comanda {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(name="entregada")
	private Boolean entregada;
	

	protected Comanda() {
	}
	
	public Comanda(Boolean entregada) {
		super();
		this.entregada = entregada;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Boolean getEntregada() {
		return entregada;
	}

	public void setEntregada(Boolean entregada) {
		this.entregada = entregada;
	}
	
	

}
