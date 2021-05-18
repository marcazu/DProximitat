package com.init.productes.Dto;

import com.init.productes.entity.Producte;

public class ProducteDto {
	
	private String id;
	private String nom;
	private String descripcio;
	private String tipus;
	private String preu;
	private String iconaProducte;
	
	public ProducteDto(Producte producte) {
		this.id = String.valueOf(producte.getId());
		this.nom = producte.getNom();
		this.descripcio = producte.getDescripcio();
		this.tipus = producte.getTipus();
		this.preu = (String.valueOf(producte.getPreu()));
		this.iconaProducte = producte.getIconaProducte();
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescripcio() {
		return descripcio;
	}
	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}
	public String getTipus() {
		return tipus;
	}
	public void setTipus(String tipus) {
		this.tipus = tipus;
	}
	public String getPreu() {
		return preu;
	}
	public void setPreu(String preu) {
		this.preu = preu;
	}
	public String getIconaProducte() {
		return iconaProducte;
	}
	public void setIconaProducte(String iconaProducte) {
		this.iconaProducte = iconaProducte;
	}
	
	

}


