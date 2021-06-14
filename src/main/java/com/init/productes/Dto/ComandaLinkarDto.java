package com.init.productes.Dto;
// dto que conté la informació que rebem per linkar una comanda amb user i botiga
public class ComandaLinkarDto {
	
	private String userID;
	private String botigaID;
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
	
	

}
