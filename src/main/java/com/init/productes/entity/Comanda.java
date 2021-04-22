package com.init.productes.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	@OneToMany(cascade = CascadeType.PERSIST, // no borrem el producte al treurel de la botiga
			orphanRemoval = false) // no borrem orfes
	private List<Producte> productesComanda =  new ArrayList<Producte>();;
	

	protected Comanda() {
	}	

	public List<Producte> getProductesComanda() {
		return productesComanda;
	}

	public void setProductesComanda(List<Producte> productesComanda) {
		this.productesComanda = productesComanda;
	}

	public Comanda(Boolean entregada, List<Producte> productesComanda) {
		super();
		this.entregada = entregada;
		this.productesComanda = productesComanda;
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
	
	public void addProducte(Producte producte) {
		productesComanda.add(producte);
	}
}
