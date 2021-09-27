package com.init.productes.Dto;

public class ProducteQuantiatDto {
	
	private String comandaId;
	private String producteId;
	private String quantitat;
	private String comentari;
	
	
	public ProducteQuantiatDto() {
		super();
	}
	public String getComandaId() {
		return comandaId;
	}
	public void setComandaId(String comandaId) {
		this.comandaId = comandaId;
	}
	public String getProducteId() {
		return producteId;
	}
	public void setProducteId(String producteId) {
		this.producteId = producteId;
	}
	public String getQuantitat() {
		return quantitat;
	}
	public void setQuantitat(String quantitat) {
		this.quantitat = quantitat;
	}
	public String getComentari() {
		return comentari;
	}
	public void setComentari(String comentari) {
		this.comentari = comentari;
	}
	
	
	

}
