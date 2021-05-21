package com.init.productes.Dto;

import com.init.productes.entity.Comanda;

public class ComandaDto {
	
	private String id;
	private Boolean preparada;
	private Boolean entregada;
	private String costTotal;
	
	
	public ComandaDto(Comanda comanda) {
		this.id = String.valueOf(comanda.getId());
		this.entregada = comanda.getEntregada();
		this.preparada = comanda.getPreparada();
		this.costTotal = String.valueOf(comanda.getCostTotal());
	}
	
	public String getId() {
		return id;
	}
	public void setId(long id) {
		this.id = String.valueOf(id);
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean getEntregada() {
		return entregada;
	}
	public void setEntregada(Boolean entregada) {
		this.entregada = entregada;
	}

	public Boolean getPreparada() {
		return preparada;
	}

	public void setPreparada(Boolean preparada) {
		this.preparada = preparada;
	}

	public String getCostTotal() {
		return costTotal;
	}

	public void setCostTotal(double costTotal) {
		this.costTotal = String.valueOf(costTotal);
	}
	
	public void setCostTotal(String costTotal) {
		this.costTotal = costTotal;
	}
	

}
