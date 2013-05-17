package edu.uoc.tdp.pac4.beans;

import java.util.Date;

public class Comanda implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numcom;
	private boolean estat;
	private Date dataalta;
	private int codipeca;
	private int idcaptaller;
	private int codigoProveedor;
	private Proveidor idproveidor;
	private int ordrereparacio;
	private boolean tipusreparacio;
	private int cantidad;
	
	public Comanda (int numcom, boolean estat, Date dataalta, int codipeca, int idcaptaller,
			Proveidor idproveidor){
		
		this.numcom = numcom;
		this.estat = estat;
		this.dataalta = dataalta;
		this.codipeca = codipeca;
		this.idcaptaller = idcaptaller;
		this.idproveidor = idproveidor;
	}


	public Comanda() {
		// TODO Auto-generated constructor stub
	}


	public int getNumcom() {
		return numcom;
	}


	public void setNumcom(int numcom) {
		this.numcom = numcom;
	}


	public boolean isEstat() {
		return estat;
	}


	public void setEstat(boolean estat) {
		this.estat = estat;
	}


	public Date getDataalta() {
		return dataalta;
	}


	public void setDataalta(Date dataalta) {
		this.dataalta = dataalta;
	}


	public int getCodipeca() {
		return codipeca;
	}


	public void setCodipeca(int codipeca) {
		this.codipeca = codipeca;
	}


	public int getIdcaptaller() {
		return idcaptaller;
	}


	public void setIdcaptaller(int idcaptaller) {
		this.idcaptaller = idcaptaller;
	}


	public Proveidor getIdproveidor() {
		return idproveidor;
	}


	public void setIdproveidor(Proveidor idproveidor) {
		this.idproveidor = idproveidor;
	}


	public int getOrdrereparacio() {
		return ordrereparacio;
	}


	public void setOrdrereparacio(int ordrereparacio) {
		this.ordrereparacio = ordrereparacio;
	}


	public boolean isTipusreparacio() {
		return tipusreparacio;
	}


	public void setTipusreparacio(boolean tipusreparacio) {
		this.tipusreparacio = tipusreparacio;
	}


	public int getCodigoProveedor() {
		return codigoProveedor;
	}


	public void setCodigoProveedor(int codigoProveedor) {
		this.codigoProveedor = codigoProveedor;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
