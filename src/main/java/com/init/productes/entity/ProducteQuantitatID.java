package com.init.productes.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/* necesitem aquesta calse  perque faci de id a la relació producte quantiat on la id de la taula
 * es la id de la comanda i la id del producte
 * 
 * 
 */

@Embeddable
public class ProducteQuantitatID implements Serializable{
	
	//hello there
	@Column(name = "comandaId")
	private Long comandaId;
	
	@Column(name = "producteId")
	private Long producteId;
	

	public ProducteQuantitatID() {
		
	}
	
public ProducteQuantitatID(Long comandaId, Long producteId) {
	this.producteId = producteId;
	this.comandaId = comandaId;
	}

public Long getProducteId() {
	return producteId;
}

public void setProducteId(Long producteId) {
	this.producteId = producteId;
}

public Long getComandaId() {
	return comandaId;
}

public void setComandaId(Long comandaId) {
	this.comandaId = comandaId;
}

// funcions autocompletades que s'han de posar
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((comandaId == null) ? 0 : comandaId.hashCode());
	result = prime * result + ((producteId == null) ? 0 : producteId.hashCode());
	return result;
}
// una altre funció autocompletada
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	ProducteQuantitatID other = (ProducteQuantitatID) obj;
	if (comandaId == null) {
		if (other.comandaId != null)
			return false;
	} else if (!comandaId.equals(other.comandaId))
		return false;
	if (producteId == null) {
		if (other.producteId != null)
			return false;
	} else if (!producteId.equals(other.producteId))
		return false;
	return true;
}


}
