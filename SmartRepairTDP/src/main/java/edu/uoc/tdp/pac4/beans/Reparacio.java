package edu.uoc.tdp.pac4.beans;

import java.util.Date;

public class Reparacio implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private int ordreReparacio;
	private int capTaller;
	private boolean acceptada;
	private int idMecanic;
	private boolean assignada;
	private double comptador;
	private String observacions;
	private int numCom;
	private Date dataAssignacio;
	private Date dataInici;
	private Date dataFi;
	
	public Reparacio(int ordreReparacio, int capTaller, boolean acceptada, int idMecanic,
					 boolean assignada, double comptador, String observacions, int numCom,
					 Date dataAssignacio, Date dataInici, Date dataFi){
		this.ordreReparacio = ordreReparacio;
		this.capTaller = capTaller;
		this.acceptada = acceptada;
		this.idMecanic = idMecanic;
		this.assignada = assignada;
		this.comptador = comptador;
		this.observacions = observacions;
		this.numCom = numCom;
		this.dataAssignacio = dataAssignacio;
		this.dataInici = dataInici;
		this.dataFi = dataFi;
	}
	
	public int getOrdreReparacio() {
		return ordreReparacio;
	}

	public void setOrdreReparacio(int ordreReparacio) {
		this.ordreReparacio = ordreReparacio;
	}
	
	public int getCapTaller() {
		return capTaller;
	}
	
	public void setCapTaller(int capTaller) {
		this.capTaller = capTaller;
	}
	
	public boolean getAcceptada() {
		return acceptada;
	}
	
	public void setAcceptada(boolean acceptada) {
		this.acceptada = acceptada;
	}
	
	public int getIdMecanic() {
		return idMecanic;
	}
	
	public void setIdMecanic(int idMecanic) {
		this.idMecanic = idMecanic;
	}
	
	public boolean getAssignada() {
		return assignada;
	}
	
	public void setAssignada(boolean assignada) {
		this.assignada = assignada;
	}
	
	public double getComptador() {
		return comptador;
	}
	
	public void setComptador(double comptador) {
		this.comptador = comptador;
	}
	
	public String getObservacions() {
		return observacions;
	}
	
	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}
	
	public int getNumCom() {
		return numCom;
	}
	
	public void setNumCom(int numCom) {
		this.numCom = numCom;
	}
	
	public Date getDataAssignacio() {
		return dataAssignacio;
	}
	
	public void setDataAssignacio(Date dataAssignacio) {
		this.dataAssignacio = dataAssignacio;
	}
	
	public Date getDataInici() {
		return dataInici;
	}
	
	public void setDataInici(Date dataInici) {
		this.dataInici = dataInici;
	}
	
	public Date getDataFi() {
		return dataFi;
	}
	
	public void setDataFi(Date dataFi) {
		this.dataFi = dataFi;
	}
	
}
