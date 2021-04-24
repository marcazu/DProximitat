package com.init.productes.Dto;

import com.init.productes.entity.User;

public class UserDto {
	
	private long id;
	private String nom;
	private String telefon;
	private String email;
	private Boolean esbotiguer;
	
	
	public UserDto() {
		super();
	}

	public UserDto(User user) {
		this.id = user.getId();
		this.nom = user.getNom();
		this.telefon = user.getTelefon();
		this.email = user.getEmail();
		this.esbotiguer = user.getEsBotiguer();
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

	public Boolean getEsbotiguer() {
		return esbotiguer;
	}

	public void setEsbotiguer(Boolean esbotiguer) {
		this.esbotiguer = esbotiguer;
	}
	
	

}
