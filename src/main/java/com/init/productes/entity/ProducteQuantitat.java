package com.init.productes.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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

	@EmbeddedId
	private ProducteQuantitatID producteQuantiatID;
	
	@Column(name="quantitat")
	private int quantitat;
	
	
	public ProducteQuantitat(ProducteQuantitatID producteQuantiatID, int quantitat) {
		this.producteQuantiatID = producteQuantiatID;
		this.quantitat = quantitat;
	}


	public ProducteQuantitatID getProducteQuantiatID() {
		return producteQuantiatID;
	}


	public void setProducteQuantiatID(ProducteQuantitatID producteQuantiatID) {
		this.producteQuantiatID = producteQuantiatID;
	}


	public int getQuantitat() {
		return quantitat;
	}


	public void setQuantitat(int quantitat) {
		this.quantitat = quantitat;
	}
	
	
	
}
