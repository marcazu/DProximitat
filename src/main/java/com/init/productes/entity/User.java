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
import javax.persistence.OneToOne;
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
	@Column(name="Botiguer",columnDefinition = "boolean default false") // no posa en false
	private Boolean esBotiguer;
	@Column(name="fireBaseUId")
	private String firebaseUId;
	
	@ManyToOne(cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	@JoinColumn(name= "botiga_id")
	private Botiga botigaPropietari;// botiga que gestiona l'usuari  
	
	@OneToMany(cascade = CascadeType.ALL,
			orphanRemoval = false
			) // no volem borrar productes de la BD si els eliminem del carro de la compra
	private List<Producte> carro = new ArrayList<Producte>();

	@OneToMany(mappedBy ="userOwner", cascade = CascadeType.ALL,fetch=FetchType.LAZY) // cascade si borrem el user es borraran les seves comandes
	private List<Comanda> comandesUsuari = new ArrayList<Comanda>(); //futura llista de comandes
	
	
	
	public User() {
	}
	

	public User(String nom, String telefon, String email, Boolean esBotiguer,Botiga botigaUsuari,
			List<Producte> carro, List<Comanda> comandesUsuari,String firebaseUId,List<Comanda> comandesUsuariEntregades) {
		super();
		this.nom = nom;
		this.telefon = telefon;
		this.email = email;
		this.esBotiguer = esBotiguer;
		//this.botigaUsuari = botigaPropietari;
		this.carro = carro;
		this.comandesUsuari = comandesUsuari;
		this.firebaseUId = firebaseUId;
		this.comandesUsuari = comandesUsuariEntregades;
	}

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
	
	public Botiga getBotigaUsuari() {
		return botigaPropietari;
	}

	public void setBotigaUsuari(Botiga botigaUsuari) {
		this.botigaPropietari = botigaUsuari;
	}


	public void addBotigaUser(Botiga botiga) {
		this.botigaPropietari = botiga;
	}
	
	public List<Producte> getCarro() {
		return carro;
	}

	public void setCarro(List<Producte> carro) {
		this.carro = carro;
	}
	
	public void addProducteCarro(Producte producte) {
		carro.add(producte);
	}
	
	public void addComanda(Comanda comanda) {
		comandesUsuari.add(comanda);
	}

	public List<Comanda> getComandesUsuari() {
		return comandesUsuari;
	}

	public void setComandesUsuari(List<Comanda> comandesUsuari) {
		this.comandesUsuari = comandesUsuari;
	}

	public String getFirebaseUId() {
		return firebaseUId;
	}

	public void setFirebaseUId(String firebaseUId) {
		this.firebaseUId = firebaseUId;
	}


	public Botiga getBotigaPropietari() {
		return botigaPropietari;
	}


	public void setBotigaPropietari(Botiga botigaPropietari) {
		this.botigaPropietari = botigaPropietari;
	}
	

	
	

	
	
}
