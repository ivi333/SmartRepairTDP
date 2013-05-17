package edu.uoc.tdp.pac4.beans;

import java.util.Date;

public class DetallReparacio implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private int ordreReparacio;
	private Date dataEntrada;
	private double comptador;
	private String matricula;
	private String marca;
	private String model;
	private String observacions;
	boolean acceptada;
	boolean assignada;
	
	public DetallReparacio(int ordreReparacio, Date dataEntrada, double comptador, String matricula,
					 String marca, String model, String observacions, boolean acceptada, boolean assignada) {
		this.ordreReparacio = ordreReparacio;
		this.dataEntrada = dataEntrada;
		this.comptador = comptador;
		this.matricula = matricula;
		this.marca = marca;
		this.model = model;
		this.observacions = observacions;
		this.acceptada = acceptada;
		this.assignada = assignada;
	}
	
	public int getOrdreReparacio() {
		return ordreReparacio;
	}

	public void setOrdreReparacio(int ordreReparacio) {
		this.ordreReparacio = ordreReparacio;
	}
	
	public Date getDataEntrada() {
		return dataEntrada;
	}
	
	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	
	public double getComptador() {
		return comptador;
	}
	
	public void setComptador(double comptador) {
		this.comptador = comptador;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getObservacions() {
		return observacions;
	}
	
	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}
	
	public boolean getAcceptada() {
		return acceptada;
	}
	
	public void setAcceptada(boolean acceptada) {
		this.acceptada = acceptada;
	}
	
	public boolean getAssignada() {
		return assignada;
	}
	
	public void setAssignada(boolean assignada) {
		this.assignada = assignada;
	}
	
}
