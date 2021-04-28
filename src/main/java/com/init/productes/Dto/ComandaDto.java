package com.init.productes.Dto;

import com.init.productes.entity.Comanda;

public class ComandaDto {
	
	private long id;
	private Boolean preparada;
	private Boolean entregada;
	private double costTotal;
	
	
	public ComandaDto(Comanda comanda) {
		this.id = comanda.getId();
		this.entregada = comanda.getEntregada();
		this.preparada = comanda.getPreparada();
		this.costTotal = comanda.getCostTotal();
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
