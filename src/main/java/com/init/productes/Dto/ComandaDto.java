package com.init.productes.Dto;

import javax.persistence.Column;

import com.init.productes.entity.Comanda;

public class ComandaDto {
	
	private long id;
	private Boolean entregada;
	
	public ComandaDto(Comanda comanda) {
		this.id = comanda.getId();
		this.entregada = comanda.getEntregada();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Boolean getEntregada() {
		return entregada;
	}
	public void setEntregada(Boolean entregada) {
		this.entregada = entregada;
	}
	
	

}
