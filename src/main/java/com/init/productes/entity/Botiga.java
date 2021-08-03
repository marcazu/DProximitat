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
	@Column(name="districte")
	private String districte;
	@Column(name="longitud")
	private double longitud;
	@Column(name="latitud")
	private double latitud;
	@Column(name="icon")
	private String iconUrl;
	@Column(name="mainImageUrl")
	private String mainImageUrl;
	
	@ManyToOne(cascade = CascadeType.PERSIST) // no borrem el producte al treurel de la botiga)
	@JoinColumn(name = "user_id")
	private User Botiguer;
	
	//La idea es que contingui els productes de la botiga
	@OneToMany(cascade = CascadeType.PERSIST, // no borrem el producte al treurel de la botiga
			orphanRemoval = false) // no borrem orfes
	private List<Producte> productesBotiga = new ArrayList<Producte>();
	
	@OneToMany(mappedBy ="botigaCompra",
			cascade = CascadeType.PERSIST, // no borrem la comanda al treure la comanda
			orphanRemoval = false)// no borrem orfes de moment
	private List<Comanda> comandesBotiga = new ArrayList<Comanda>();
	
	protected Botiga() {
		
	}
	
	public Botiga(String nom, String descripcio, String email, String telefon, double longitud, double latitud,
			 List<Producte> productesBotiga, String iconUrl, String mainImageUrl, String districte) {
		super();
		this.nom = nom;
		this.descripcio = descripcio;
		this.email = email;
		this.telefon = telefon;
		this.longitud = longitud;
		this.latitud = latitud;
		///this.botiguer = botiguer;
		this.productesBotiga = productesBotiga;
		this.iconUrl = iconUrl;
		this.mainImageUrl = mainImageUrl;
		this.districte = districte;
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
	public String deleteProducteBotiga(Producte producte) {
		// TODO Auto-generated method stub
		for (int i = 0; i < productesBotiga.size(); i ++) {
			Producte p = productesBotiga.get(i);
			if(p.getId() == producte.getId()) {
				productesBotiga.remove(i);
				return "S'ha eliminat el producte amb Id:" + p.getId();
			}
		}
		return "No s'ha trobat el producte amb Id:"+ producte.getId();
		
	}
	public User getBotiguer() {
		return Botiguer;
	}
	public void setBotiguer(User botiguer) {
		Botiguer = botiguer;
	}
	public void addComanda(Comanda comanda) {
		comandesBotiga.add(comanda);
	}
	public List<Comanda> getComandesBotiga() {
		return comandesBotiga;
	}
	public void setComandesBotiga(List<Comanda> comandesBotiga) {
		this.comandesBotiga = comandesBotiga;
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


	
	

	
	

}
