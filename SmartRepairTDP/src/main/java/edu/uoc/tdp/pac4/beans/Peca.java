package edu.uoc.tdp.pac4.beans;

public class Peca implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int CodiPeca;
	private String Descripcio;
	private int PVP;
	private int PVD;
	private String Marca;
	private String Model;
	
	public Peca() {

	}

	public Peca(int CodiPeca, String Descripcio, int PVP, int PVD,
			String Marca, String Model) {
		this.CodiPeca = CodiPeca;
		this.Descripcio = Descripcio;
		this.PVP = PVP;
		this.PVD = PVD;
		this.Marca = Marca;
		this.Model = Model;
		
	}

	public int getCodiPeca() {
		return CodiPeca;
	}

	public void setCodiPeca(int codiPeca) {
		CodiPeca = codiPeca;
	}

	public String getDescripcio() {
		return Descripcio;
	}

	public void setDescripcio(String descripcio) {
		Descripcio = descripcio;
	}

	public int getPVP() {
		return PVP;
	}

	public void setPVP(int pVP) {
		PVP = pVP;
	}

	public int getPVPD() {
		return PVD;
	}

	public void setPVPD(int pVPD) {
		PVD = pVPD;
	}

	public String getMarca() {
		return Marca;
	}

	public void setMarca(String marca) {
		Marca = marca;
	}

	public String getModel() {
		return Model;
	}

	public void setModel(String model) {
		Model = model;
	}



}
