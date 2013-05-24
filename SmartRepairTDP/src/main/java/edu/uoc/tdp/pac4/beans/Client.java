package edu.uoc.tdp.pac4.beans;

import java.io.Serializable;
import java.util.Date;

public class Client implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nom;
	private String cognoms;
	private String adreca;
	private String nif;
	private String poblacio;
	private int codiPostal;
	private int numClient;
	private Date dataAlta;
	private int idasseguradora;
	private String marca;
	private String tipus;
	private String num_chasis;
	private String model;
	private String matricula;
	private String color;
	private Date anyo;
	private Asseguradora asseguradora;
	
	public Client ()
	{
		
		
	}
	
	  	public int getIdasseguradora() {
		return idasseguradora;
	}

	public void setIdasseguradora(int idasseguradora) {
		this.idasseguradora = idasseguradora;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getTipus() {
		return tipus;
	}

	public void setTipus(String tipus) {
		this.tipus = tipus;
	}

	public String getNum_chasis() {
		return num_chasis;
	}

	public void setNum_chasis(String num_chasis) {
		this.num_chasis = num_chasis;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Date getAnyo() {
		return anyo;
	}

	public void setAnyo(Date anyo) {
		this.anyo = anyo;
	}

	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCognoms() {
		return cognoms;
	}
	public void setCognoms(String cognoms) {
		this.cognoms = cognoms;
	}
	public String getAdreca() {
		return adreca;
	}
	public void setAdreca(String adreca) {
		this.adreca = adreca;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getPoblacio() {
		return poblacio;
	}
	public void setPoblacio(String poblacio) {
		this.poblacio = poblacio;
	}
	public int getCodiPostal() {
		return codiPostal;
	}
	public void setCodiPostal(int codiPostal) {
		this.codiPostal = codiPostal;
	}
	public int getNumClient() {
		return numClient;
	}
	public void setNumClient(int numClient) {
		this.numClient = numClient;
	}
	public Date getDataAlta() {
		return dataAlta;
	}
	public void setDataAlta(Date dataAlta) {
		this.dataAlta = dataAlta;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Asseguradora getAsseguradora() {
		return asseguradora;
	}

	public void setAsseguradora(Asseguradora asseguradora) {
		this.asseguradora = asseguradora;
	}

	
}

