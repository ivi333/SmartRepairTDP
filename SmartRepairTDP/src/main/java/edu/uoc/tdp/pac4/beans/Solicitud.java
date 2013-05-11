package edu.uoc.tdp.pac4.beans;

import java.util.Date;

public class Solicitud implements java.io.Serializable {


	private static final long serialVersionUID = 1L;

	private int numsol;
	private String  comentaris;
	private Date dataalta;
	private Date datafinalitzacio;
	private String client;
	private int numreparacio;
	private boolean pendent;
	private boolean finalitzada;
	private String numpoliza;
	
	public Solicitud()
	{
		
	}
	public int getNumsol() {
		return numsol;
	}
	public void setNumsol(int numsol) {
		this.numsol = numsol;
	}
	public String getComentaris() {
		return comentaris;
	}
	public void setComentaris(String comentaris) {
		this.comentaris = comentaris;
	}
	public Date getDataalta() {
		return dataalta;
	}
	public void setDataalta(Date dataalta) {
		this.dataalta = dataalta;
	}
	public Date getDatafinalitzacio() {
		return datafinalitzacio;
	}
	public void setDatafinalitzacio(Date datafinalitzacio) {
		this.datafinalitzacio = datafinalitzacio;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public int getNumreparacio() {
		return numreparacio;
	}
	public void setNumreparacio(int numreparacio) {
		this.numreparacio = numreparacio;
	}
	public boolean isPendent() {
		return pendent;
	}
	public void setPendent(boolean pendent) {
		this.pendent = pendent;
	}
	public boolean isFinalitzada() {
		return finalitzada;
	}
	public void setFinalitzada(boolean finalitzada) {
		this.finalitzada = finalitzada;
	}
	public String getNumpoliza() {
		return numpoliza;
	}
	public void setNumpoliza(String numpoliza) {
		this.numpoliza = numpoliza;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
