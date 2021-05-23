package com.init.productes.Dto;

public class ProducteIdQuantitatDto {
	private String producteId;
	private String Quantitat;
	
	public ProducteIdQuantitatDto() {
		super();
	}
	public ProducteIdQuantitatDto(String producteId, String quantitat) {
		super();
		this.producteId = producteId;
		Quantitat = quantitat;
	}
	public String getProducteId() {
		return producteId;
	}
	public void setProducteId(String producteId) {
		this.producteId = producteId;
	}
	public String getQuantitat() {
		return Quantitat;
	}
	public void setQuantitat(String quantitat) {
		Quantitat = quantitat;
	}
	
	

}
