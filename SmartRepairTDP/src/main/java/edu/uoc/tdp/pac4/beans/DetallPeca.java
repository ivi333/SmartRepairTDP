package edu.uoc.tdp.pac4.beans;

public class DetallPeca implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int codiPeca;
	private String descripcio;
	private int stock;
	private int cantidad;
	private double pvp;
	private int stockMinim;
	private String marca;
	private String model;
	
	public DetallPeca(int codiPeca, String descripcio, int stock, int cantidad, double pvp, 
			int stockMinim, String marca, String model) {
		this.codiPeca = codiPeca;
		this.descripcio = descripcio;
		this.stock = stock;
		this.cantidad = cantidad;
		this.pvp = pvp;
		this.stockMinim = stockMinim;
		this.marca = marca;
		this.model = model;
	}
	
	public DetallPeca() {
		
	}
	
	public int getCodiPeca() {
		return codiPeca;
	}

	public void setCodiPeca(int codiPeca) {
		this.codiPeca = codiPeca;
	}
	
	public String getDescipcio() {
		return descripcio;
	}
	
	public void setDescipcio(String descripcio) {
		this.descripcio = descripcio;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public double getPvp() {
		return pvp;
	}
	
	public void setPvp(double pvp) {
		this.pvp = pvp;
	}
	
	public int getStockMinim() {
		return stockMinim;
	}
	
	public void setStockMinim(int stockMinim) {
		this.stockMinim = stockMinim;
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
}
