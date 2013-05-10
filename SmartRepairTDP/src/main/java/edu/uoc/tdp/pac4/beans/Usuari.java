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
	private String contrasenya;
	private boolean actiu;
	private Date dataAlta;
	private Date dataModificacio;
	private Date dataBaixa;
	private int reparacionsAssignades;
	
	public Usuari () {
		
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
	

	
	
}
