package com.init.productes.Dto;

import javax.persistence.Column;

import com.init.productes.entity.Producte;

public class ProducteDto {
	
	private long id;
	private String nom;
	private String descripcio;
	private String tipus;
	
	public ProducteDto(Producte producte) {
		this.id = producte.getId();
		this.nom = producte.getNom();
		this.descripcio = producte.getDescripcio();
		this.tipus = producte.getTipus();
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	
	

}


