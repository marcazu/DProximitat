package com.init.productes.Dto;

import java.util.List;

// dto que conté la informació que rebem per linkar una comanda amb user i botiga
public class ComandaLinkarDto {
	
	private String userID;
	private String botigaID;
	private String dataExpedicio;
	private List<ProducteQuantiatDto> producteQuantitat;
	
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getBotigaID() {
		return botigaID;
	}
	public void setBotigaID(String botigaID) {
		this.botigaID = botigaID;
	}
	public List<ProducteQuantiatDto> getProducteQuantitat() {
		return producteQuantitat;
	}
	public void setProducteQuantitat(List<ProducteQuantiatDto> producteQuantitat) {
		this.producteQuantitat = producteQuantitat;
	}
	public String getDataExpedicio() {
		return dataExpedicio;
	}
	public void setDataExpedicio(String dataExpedicio) {
		this.dataExpedicio = dataExpedicio;
	}
	
	
	
	

}
