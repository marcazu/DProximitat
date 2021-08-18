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
import javax.persistence.OneToOne;
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
	private List<ProducteQuantitat> productesComanda =  new ArrayList<ProducteQuantitat>();
	
	@ManyToOne(cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User UserOwner;
	
	@ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	@JoinColumn(name= "botiga_id")
	private Botiga botigaCompra;
	
	//ara funciona


	public List<ProducteQuantitat> getProductesComanda() {
		return productesComanda;
	}

	public void setProductesComanda(List<ProducteQuantitat> productesComanda) {
		this.productesComanda = productesComanda;
	}

	public Comanda() {
		// creem una coamnda buida
		super();
		this.entregada = false;
		this.preparada = false;
		this.costTotal = 0.0;
	}

	public Comanda(long id, Boolean preparada, Boolean entregada, Double costTotal,
			List<ProducteQuantitat> productesComanda, User userOwner, Botiga botigaCompra) {
		super();
		this.id = id;
		this.preparada = preparada;
		this.entregada = entregada;
		this.costTotal = costTotal;
		this.productesComanda = productesComanda;
		UserOwner = userOwner;
		this.botigaCompra = botigaCompra;
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
	
	public void addProducteQuantitat(ProducteQuantitat producteQuantitat) {
		productesComanda.add(producteQuantitat);
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
	
}
