package com.init.productes.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="botigues")
public class Botiga {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(name="nom", nullable=false, length=30)
	private String nom;
	@Column(name="descripcio")
	private String descripcio;
	@Column(name="email", nullable = false)
	private String email;
	@Column(name="telefon", nullable = false, length = 9)
	private String telefon;
	@Column(name="longitud")
	private double longitud;
	@Column(name="latitud")
	private double latitud;
	
	@ManyToOne(cascade = CascadeType.PERSIST) // no borrem el producte al treurel de la botiga)
	@JoinColumn(name = "user_id")
	private User Botiguer;
	
	//La idea es que contingui els productes de la botiga
	@OneToMany(cascade = CascadeType.PERSIST, // no borrem el producte al treurel de la botiga
			orphanRemoval = false) // no borrem orfes
	private List<Producte> productesBotiga = new ArrayList<Producte>();
	
	protected Botiga() {
		
	}
	
	public Botiga(String nom, String descripcio, String email, String telefon, double longitud, double latitud,
			 List<Producte> productesBotiga) {
		super();
		this.nom = nom;
		this.descripcio = descripcio;
		this.email = email;
		this.telefon = telefon;
		this.longitud = longitud;
		this.latitud = latitud;
		///this.botiguer = botiguer;
		this.productesBotiga = productesBotiga;
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
	public List<Producte> getProductesBotiga() {
		return productesBotiga;
	}
	public void setProductesBotiga(List<Producte> productesBotiga) {
		this.productesBotiga = productesBotiga;
	}
	public void addProducteBotiga(Producte producte) {
		productesBotiga.add(producte);
	}
	public User getBotiguer() {
		return Botiguer;
	}
	public void setBotiguer(User botiguer) {
		Botiguer = botiguer;
	}
	
	
	

	
	

}
