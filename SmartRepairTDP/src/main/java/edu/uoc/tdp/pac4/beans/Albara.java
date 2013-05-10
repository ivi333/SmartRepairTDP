package edu.uoc.tdp.pac4.beans;

public class Albara implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numalbara;
	private String comantaris;
	private Comanda comanda;
	
	
	public Albara (int numalbara, String comantaris, Comanda comanda){
		
		this.numalbara = numalbara;
		this.comantaris = comantaris;
		this.comanda = comanda;
	}


	public int getNumalbara() {
		return numalbara;
	}


	public void setNumalbara(int numalbara) {
		this.numalbara = numalbara;
	}


	public String getComantaris() {
		return comantaris;
	}


	public void setComantaris(String comantaris) {
		this.comantaris = comantaris;
	}


	public Comanda getComanda() {
		return comanda;
	}


	public void setComanda(Comanda comanda) {
		this.comanda = comanda;
	}
	
}
