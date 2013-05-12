package edu.uoc.tdp.pac4.beans;

public class Peca implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int CodiPeca;
	private String Descripcio;
	private int PVP;
	private int PVPD;
	private String Marca;
	private String Model;
	private int IdProveidor;

	
	
	public Peca() {

	}

	public Peca(int CodiPeca, String Descripcio, int PVP, int PVPD,
			String Marca, String Model, int IdProveidor) {
		this.CodiPeca = CodiPeca;
		this.Descripcio = Descripcio;
		this.PVP = PVP;
		this.PVPD = PVPD;
		this.Marca = Marca;
		this.Model = Model;
		this.IdProveidor = IdProveidor;
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
		return PVPD;
	}

	public void setPVPD(int pVPD) {
		PVPD = pVPD;
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

	public int getIdProveidor() {
		return IdProveidor;
	}

	public void setIdProveidor(int idProveidor) {
		IdProveidor = idProveidor;
	}

}
