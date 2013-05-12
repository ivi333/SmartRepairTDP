package edu.uoc.tdp.pac4.beans;

public class Proveidor implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idproveidor;
	private String nom;
	
	public Proveidor (int idproveidor, String nom){
		
		this.idproveidor = idproveidor;
		this.nom = nom;
		
	}

	public Proveidor() {
		// TODO Auto-generated constructor stub
	}

	public int getIdproveidor() {
		return idproveidor;
	}

	public void setIdproveidor(int idproveidor) {
		this.idproveidor = idproveidor;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
