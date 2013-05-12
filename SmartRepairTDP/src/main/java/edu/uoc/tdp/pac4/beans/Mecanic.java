package edu.uoc.tdp.pac4.beans;

import java.util.Date;

public class Mecanic extends Usuari implements java.io.Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private int idmecanic;
	private boolean disponible;
	private int idrep1;
	private int idrep2;
	
	public Mecanic(int idmecanic, boolean disponible, Taller taller, Usuari usuari,
			String perfil, String contrasenya, boolean actiu, Date dataAlta,
			Date dataModificacio, Date dataBaixa, int reparacionsAssignades){
		
		/*super (taller, usuari, perfil, contrasenya, actiu, dataAlta, dataModificacio,
				dataBaixa, reparacionsAssignades);*/
		
		super();
		
		this.idmecanic = idmecanic;
		this.disponible = disponible;
	}

	public int getIdmecanic() {
		return idmecanic;
	}

	public void setIdmecanic(int idmecanic) {
		this.idmecanic = idmecanic;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public int getIdrep1() {
		return idrep1;
	}

	public void setIdrep1(int idrep1) {
		this.idrep1 = idrep1;
	}

	public int getIdrep2() {
		return idrep2;
	}

	public void setIdrep2(int idrep2) {
		this.idrep2 = idrep2;
	}
		
	
}
	