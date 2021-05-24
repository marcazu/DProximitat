package com.init.productes.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="producteQuantitat")
public class ProducteQuantitat {
	// aquesta entitat serveix per realcionar comanda amb producte, tinguent per cada comanda un llistat
	//de productes amb la seva quantitat, fent més facil la relació amb el Front

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(name="quantitat")
	private int quantitat;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id")
	private Producte p;

	
	public ProducteQuantitat(long id, int quantitat, Producte p) {
		super();
		this.id = id;
		this.quantitat = quantitat;
		this.p = p;
	}
	
	public ProducteQuantitat() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getQuantitat() {
		return quantitat;
	}

	public void setQuantitat(int quantitat) {
		this.quantitat = quantitat;
	}

	public Producte getProducte() {
		return p;
	}

	public void setProducte(Producte p) {
		this.p = p;
	}

	
}
