package com.init.productes.Dto;

public class ProducteQuantiatDto {
	
	private long comandaId;
	private long producteId;
	private int quantitat;
	
	
	public ProducteQuantiatDto(long comandaId, long producteId, int quantitat) {
		super();
		this.comandaId = comandaId;
		this.producteId = producteId;
		this.quantitat = quantitat;
	}
	public ProducteQuantiatDto() {
		super();
	}
	public long getComandaId() {
		return comandaId;
	}
	public void setComandaId(long comandaId) {
		this.comandaId = comandaId;
	}
	public long getProducteId() {
		return producteId;
	}
	public void setProducteId(long producteId) {
		this.producteId = producteId;
	}
	public int getQuantitat() {
		return quantitat;
	}
	public void setQuantitat(int quantitat) {
		this.quantitat = quantitat;
	}
	
	
	

}
