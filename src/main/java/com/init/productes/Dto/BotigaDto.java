package com.init.productes.Dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.init.productes.entity.Botiga;
import com.init.productes.entity.Comanda;
import com.init.productes.entity.User;

public class BotigaDto {


	private String id;
	private String nom;
	private String descripcio;
	private String email;
	private String telefon;
	private String districte;
	private String longitud;
	private String latitud;
	private String iconUrl;
	private String mainImageUrl;
	private String numComandes;
	
	public BotigaDto(Botiga botiga) {//Constructor de botiga Dto
		this.id = String.valueOf(botiga.getId());
		this.nom = botiga.getNom();
		this.descripcio = botiga.getDescripcio();
		this.email = botiga.getEmail();
		this.telefon = botiga.getTelefon();
		this.longitud = String.valueOf(botiga.getLongitud());
		this.latitud = String.valueOf(botiga.getLatitud());
		this.iconUrl = botiga.getIconUrl();
		this.mainImageUrl = botiga.getMainImageUrl();
		this.districte = botiga.getDistricte();
		if(botiga.getComandesBotiga().isEmpty()) this.numComandes = "0";
		else {
			int x = 0;
			for (Comanda c : botiga.getComandesBotiga()) {
				if(!c.getEntregada()) x += 1;
			}
			numComandes = String.valueOf(x);
		}

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
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	public String getLatitud() {
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getMainImageUrl() {
		return mainImageUrl;
	}
	public void setMainImageUrl(String mainImageUrl) {
		this.mainImageUrl = mainImageUrl;
	}
	public String getDistricte() {
		return districte;
	}
	public void setDistricte(String districte) {
		this.districte = districte;
	}
	public String getNumComandes() {
		return numComandes;
	}
	public void setNumComandes(String numComandes) {
		this.numComandes = numComandes;
	}
	//ja està crec
}

