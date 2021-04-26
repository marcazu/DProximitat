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
import javax.persistence.OneToMany;
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
	
	@OneToMany(
			mappedBy = "Botiguer", //HA DE TENIR EL MATEIX NOM QUE EL CAMP DE LA BOTIGA!!!!
			cascade = CascadeType.PERSIST, // propaga casi tottes les funciones menys la de borrar el producte
			orphanRemoval = false // no volem eliminar orfes
			)
	private List<Botiga> botiguesUsuari = new ArrayList<Botiga>(); // futura llista de botigues que gestiona l'usuari  
	
	@OneToMany(cascade = CascadeType.ALL,
			orphanRemoval = false
			) // no volem borrar productes de la BD si els eliminem del carro de la compra
	private List<Producte> carro = new ArrayList<Producte>();

	@OneToMany(mappedBy ="UserOwner", cascade = CascadeType.ALL,fetch=FetchType.LAZY) // cascade si borrem el user es borraran les seves comandes
	private List<Comanda> comandesUsuari = new ArrayList<Comanda>(); //futura llista de comandes
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public User(String nom, String telefon, String email, Boolean esBotiguer, List<Botiga> botiguesUsuari,
			List<Producte> carro, List<Comanda> comandesUsuari) {
		super();
		this.nom = nom;
		this.telefon = telefon;
		this.email = email;
		this.esBotiguer = esBotiguer;
		this.botiguesUsuari = botiguesUsuari;
		this.carro = carro;
		this.comandesUsuari = comandesUsuari;
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

	public List<Botiga> getBotiguesUsuari() {
		return botiguesUsuari;
	}

	public void setBotiguesUsuari(List<Botiga> botiguesUsuari) {
		this.botiguesUsuari = botiguesUsuari;
	}
	
	public void addBotigaUser(Botiga botiga) {
		botiguesUsuari.add(botiga);
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

	
	
}
