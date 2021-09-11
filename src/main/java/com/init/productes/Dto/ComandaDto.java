package com.init.productes.Dto;

import com.init.productes.entity.Comanda;

public class ComandaDto {
	
	private String id;
	private Boolean entregada;
	private String costTotal;
	// noves variables
	
	private String botigaName;
    private String propietari;
    private String dataExpedicio;
	
	
	
	public ComandaDto(Comanda comanda) {
		this.id = String.valueOf(comanda.getId());
		this.entregada = comanda.getEntregada();
		this.costTotal = "0";
		this.botigaName = comanda.getBotigaName();
		this.propietari = comanda.getUserName();
		this.dataExpedicio = comanda.getDataExpedicio();
		
	}
	
	public String getId() {
		return id;
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

	public String getCostTotal() {
		return costTotal;
	}

	public void setCostTotal(double costTotal) {
		this.costTotal = String.valueOf(costTotal);
	}
	
	public void setCostTotal(String costTotal) {
		this.costTotal = costTotal;
	}

	public String getBotigaName() {
		return botigaName;
	}

	public void setBotigaName(String botigaName) {
		this.botigaName = botigaName;
	}

	public String getPropietari() {
		return propietari;
	}

	public void setPropietari(String propietari) {
		this.propietari = propietari;
	}

	public String getDataDExpedicio() {
		return dataExpedicio;
	}

	public void setDataDExpedicio(String dataDExpedicio) {
		this.dataExpedicio = dataDExpedicio;
	}
	
	
	

}
