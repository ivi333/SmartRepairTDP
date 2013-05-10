package edu.uoc.tdp.pac4.beans;

public class Asseguradora  implements java.io.Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idasseguradora;
	private String cif;
	private String nom;
	private String adreca;
	private int telefon;
	public Asseguradora()
	{
		
		
	}
	public int getIdasseguradora() {
		return idasseguradora;
	}
	public void setIdasseguradora(int idasseguradora) {
		this.idasseguradora = idasseguradora;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdreca() {
		return adreca;
	}
	public void setAdreca(String adreca) {
		this.adreca = adreca;
	}
	public int getTelefon() {
		return telefon;
	}
	public void setTelefon(int telefon) {
		this.telefon = telefon;
	}
	
	
	
}
