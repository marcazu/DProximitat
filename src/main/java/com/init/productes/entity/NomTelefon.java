package com.init.productes.entity;

public class NomTelefon {
	
	private String userId;
	private String nom;
	private String telefon;
	
	
	public NomTelefon() {
		super();
	}

	
	public NomTelefon(String userId, String nom, String email) {
		super();
		this.userId = userId;
		this.nom = nom;
		this.telefon = email;
	}


	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return telefon;
	}
	public void setEmail(String email) {
		this.telefon = email;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
