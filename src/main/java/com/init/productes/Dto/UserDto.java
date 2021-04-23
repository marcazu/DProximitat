package com.init.productes.Dto;

import com.init.productes.entity.User;

public class UserDto {
	
	private long id;
	private String nom;
	private String telefon;
	private String email;
	private Boolean esbotiguer;
	
	public UserDto(User user) {
		this.id = user.getId();
		this.nom = user.getNom();
		this.telefon = user.getTelefon();
		this.email = user.getEmail();
		this.esbotiguer = user.getEsBotiguer();
	}
	
	

}
