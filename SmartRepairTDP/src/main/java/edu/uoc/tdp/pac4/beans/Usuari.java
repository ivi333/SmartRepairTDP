package edu.uoc.tdp.pac4.beans;

import java.io.Serializable;
import java.util.Date;

public class Usuari implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int taller;
	private String usuari;
	private String perfil;
	private String nif;
	private String nom;
	private String cognoms;
	private String contrasenya;
	private boolean actiu;
	private Date dataAlta;
	private Date dataModificacio;
	private Date dataBaixa;
	private int reparacionsAssignades;
	
	public Usuari () {
		
	}

	public Usuari (int id, int taller, String usuari, String perfil,
			String nif, String nom, String cognoms, String contrasenya,
			boolean actiu, Date dataAlta, Date dataModificacio, Date dataBaixa,
			int reparacionAssignades){
		this.id = id;
		this.taller = taller;
		this.usuari = usuari;
		this.perfil = perfil;
		this.nif = nif;
		this.nom = nom;
		this.cognoms = cognoms;
		this.contrasenya = contrasenya;
		this.actiu = actiu;
		this.dataAlta = dataAlta;
		this.dataModificacio = dataModificacio;
		this.dataBaixa = dataBaixa;
		this.reparacionsAssignades = reparacionAssignades;
		
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTaller() {
		return taller;
	}

	public void setTaller(int taller) {
		this.taller = taller;
	}

	public String getUsuari() {
		return usuari;
	}

	public void setUsuari(String usuari) {
		this.usuari = usuari;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getNif () {
		return this.nif;
	}
	
	public void setNif (String nif) {
		this.nif = nif; 
	}
	
	public String getNom () {
		return this.nom;
	}
	
	public void setNom (String nom){
		this.nom = nom;
	}
	
	public String getCognoms (){
		return this.cognoms;
	}
	
	public void setCognoms (String cognoms) {
		this.cognoms =  cognoms;
	}
	
	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	public boolean isActiu() {
		return actiu;
	}

	public void setActiu(boolean actiu) {
		this.actiu = actiu;
	}

	public Date getDataAlta() {
		return dataAlta;
	}

	public void setDataAlta(Date dataAlta) {
		this.dataAlta = dataAlta;
	}

	public Date getDataModificacio() {
		return dataModificacio;
	}

	public void setDataModificacio(Date dataModificacio) {
		this.dataModificacio = dataModificacio;
	}

	public Date getDataBaixa() {
		return dataBaixa;
	}

	public void setDataBaixa(Date dataBaixa) {
		this.dataBaixa = dataBaixa;
	}

	public int getReparacionsAssignades() {
		return reparacionsAssignades;
	}

	public void setReparacionsAssignades(int reparacionsAssignades) {
		this.reparacionsAssignades = reparacionsAssignades;
	}

	public String getNomCognoms () {
		return this.nom + " " + this.cognoms;
	}
	
	public String toString () {
		return "ID = " + this.id + " taller = " + this.taller + " usuari = " + this.usuari
				+ " perfil = " + this.perfil + " nif = " + this.nif + " nom = "+ this.nom
				+ " cognoms = "+ this.cognoms + " contrasenya = " + this.contrasenya
				+ " actiu = "  + this.actiu + " dataAlta = "  + this.dataAlta
				+ " dataModificacio = " + this.dataModificacio + " dataBaixa = " + this.dataBaixa
				+ " reparacionsAssignades = "+ this.reparacionsAssignades;
	}
	
	
}
