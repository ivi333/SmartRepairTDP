package edu.uoc.tdp.pac4.beans;

import java.io.Serializable;

public class Stockpeca  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int idstockpeca;
	private int codipeca;
	private int stock;
	private int idtaller;
	private int stockminim;
	
	public Stockpeca()
	{
		
	}
	public int getIdstockpeca() {
		return idstockpeca;
	}
	public void setIdstockpeca(int idstockpeca) {
		this.idstockpeca = idstockpeca;
	}
	public int getCodipeca() {
		return codipeca;
	}
	public void setCodipeca(int codipeca) {
		this.codipeca = codipeca;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getIdtaller() {
		return idtaller;
	}
	public void setIdtaller(int idtaller) {
		this.idtaller = idtaller;
	}
	public int getStockminim() {
		return stockminim;
	}
	public void setStockminim(int stockminim) {
		this.stockminim = stockminim;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
