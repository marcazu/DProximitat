package com.init.productes.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javassist.compiler.ast.Pair;

@Entity
@Table(name="Comandes")
public class Comanda {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(name="preparada")
	private Boolean preparada;
	
	@Column(name="entregada")
	private Boolean entregada;
	
	@Column(name="costTotal")
	private Double costTotal; 
	
	@OneToMany(cascade = CascadeType.PERSIST, // no borrem el producte al treurel de la botiga
			orphanRemoval = false) // no borrem orfes
	private List<Producte> productesComanda =  new ArrayList<Producte>();
	
	@ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User UserOwner;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name= "botiga_id")
	private Botiga botigaCompra;
	
	private List<String> quantitats = new ArrayList<String>();
	

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

	public User getUserOwner() {
		return UserOwner;
	}

	public void setUserOwner(User userOwner) {
		UserOwner = userOwner;
	}

	public Botiga getBotigaCompra() {
		return botigaCompra;
	}

	public void setBotigaCompra(Botiga botigaCompra) {
		this.botigaCompra = botigaCompra;
	}

	public Boolean getPreparada() {
		return preparada;
	}

	public void setPreparada(Boolean preparada) {
		this.preparada = preparada;
	}

	public Double getCostTotal() {
		return costTotal;
	}

	public void setCostTotal(Double costTotal) {
		this.costTotal = costTotal;
	}

	public void addPreuTotal(Double preu) {
		this.costTotal = this.costTotal + preu;
		
	}

	public List<String> getQuantitats() {
		return quantitats;
	}

	public void setQuantitats(List<String> quantitats) {
		this.quantitats = quantitats;
	}
	
	public void addQuantiat(String q) {
		quantitats.add(q);
	}
	
}
