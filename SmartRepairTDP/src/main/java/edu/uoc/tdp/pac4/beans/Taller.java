package edu.uoc.tdp.pac4.beans;

import java.io.Serializable;
import java.util.Date;

public class Taller implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String cif;
	private String adreca;
	private int capacitat;
	private int capTaller;
	private String telefon;
	private String web;
	private boolean actiu;
	private Date dataApertura;
	private Date dataModificacio;
	private Date dataBaixa;
	
	public Taller () {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getAdreca() {
		return adreca;
	}

	public void setAdreca(String adreca) {
		this.adreca = adreca;
	}

	public int getCapacitat() {
		return capacitat;
	}

	public void setCapacitat(int capacitat) {
		this.capacitat = capacitat;
	}

	public int getCapTaller() {
		return capTaller;
	}

	public void setCapTaller(int capTaller) {
		this.capTaller = capTaller;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public boolean isActiu() {
		return actiu;
	}

	public void setActiu(boolean actiu) {
		this.actiu = actiu;
	}

	public Date getDataApertura() {
		return dataApertura;
	}

	public void setDataApertura(Date dataApertura) {
		this.dataApertura = dataApertura;
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
	

}
