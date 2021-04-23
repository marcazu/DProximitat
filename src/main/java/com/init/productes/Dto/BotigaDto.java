package com.init.productes.Dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.init.productes.entity.Botiga;
import com.init.productes.entity.User;

public class BotigaDto {


	private long id;
	private String nom;
	private String descripcio;
	private String email;
	private String telefon;
	private double longitud;
	private double latitud;
	
	public BotigaDto(Botiga botiga) {//Constructor de botiga Dto
		this.id = botiga.getId();
		this.nom = botiga.getNom();
		this.descripcio = botiga.getDescripcio();
		this.email = botiga.getEmail();
		this.telefon = botiga.getTelefon();
		this.longitud = botiga.getLongitud();
		this.latitud = botiga.getLatitud();

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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	
	
}

