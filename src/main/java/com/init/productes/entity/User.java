package com.init.productes.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Users")
public class User {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(name="nom", nullable=false, length=30)
	private String nom;
	@Column(name="telefon",length = 9)
	private String telefon;
	@Column(name="email")
	private String email;
	@Column(name="Botiguer",columnDefinition = "boolean default false")
	private Boolean esBotiguer;
	
	private List<Botiga> botiguesUsuari = new ArrayList<Botiga>(); // futura llista de botigues que gestiona l'usuari  
	//private List<String> comandesUsuari = new ArrayList<int>; //futura llista de comandes

	public long getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEsBotiguer() {
		return esBotiguer;
	}

	public void setEsBotiguer(Boolean esBotiguer) {
		this.esBotiguer = esBotiguer;
	}

	public List<Botiga> getBotiguesUsuari() {
		return botiguesUsuari;
	}

	public void setBotiguesUsuari(List<Botiga> botiguesUsuari) {
		this.botiguesUsuari = botiguesUsuari;
	}

	
}
