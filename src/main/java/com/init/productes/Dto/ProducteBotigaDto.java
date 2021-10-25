package com.init.productes.Dto;

public class ProducteBotigaDto {
		
		private String botigaId;
		private String nom;
		private String descripcio;
		private String tipus;
		private String preu;
		private String iconaProducte;
		
		

		public ProducteBotigaDto() {
			super();
		}
		public ProducteBotigaDto(String botigaId, String nom, String descripcio, String tipus, String preu,
				String iconaProducte) {
			super();
			this.botigaId = botigaId;
			this.nom = nom;
			this.descripcio = descripcio;
			this.tipus = tipus;
			this.preu = preu;
			this.iconaProducte = iconaProducte;
		}
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		public String getDescripcio() {
			return descripcio;
		}
		public void setDescripcio(String descripcio) {
			this.descripcio = descripcio;
		}
		public String getTipus() {
			return tipus;
		}
		public void setTipus(String tipus) {
			this.tipus = tipus;
		}
		public String getPreu() {
			return preu;
		}
		public void setPreu(String preu) {
			this.preu = preu;
		}
		public String getIconaProducte() {
			return iconaProducte;
		}
		public void setIconaProducte(String iconaProducte) {
			this.iconaProducte = iconaProducte;
		}
		public String getBotigaId() {
			return botigaId;
		}
		public void setBotigaId(String botigaId) {
			this.botigaId = botigaId;
		}
		
		
}



