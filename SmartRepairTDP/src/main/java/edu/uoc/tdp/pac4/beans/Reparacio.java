package edu.uoc.tdp.pac4.beans;

import java.util.ArrayList;
import java.util.Calendar;
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
	private Solicitud solicitud ;
	private Calendar calendar ;
	private float fImporte;
	//private ArrayList<Comanda> comandas;
	private Comanda comanda;
	
	private Mecanic mecanic;
	
	public Reparacio(int ordreReparacio, int capTaller, boolean acceptada, int idMecanic,
					 boolean assignada, double comptador, String observacions, int numCom,
					 Date dataAssignacio, Date dataInici, Date dataFi){
		this();
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
	
	public Reparacio() {
		
		this.calendar = Calendar.getInstance();
	}
	
	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
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
	
	/*public ArrayList<Comanda> getComandas()
	{
		return ( this.comandas);
	}
	
	public void setComandas(ArrayList<Comanda> comandas)
	{
		this.comandas=comandas;
	}*/

	
	public Mecanic getMecanic() {
		return mecanic;
	}

	public void setMecanic(Mecanic mecanic) {
		this.mecanic = mecanic;
	}
	
	public String getEstatReparacio ()
	{
		String strEstado = "" ;
		
		if (!this.assignada && this.acceptada && !this.solicitud.isPendent() && !this.solicitud.isFinalitzada()) strEstado = "Rebuda" ;
		else if (this.assignada && this.acceptada && !this.solicitud.isPendent() && !this.solicitud.isFinalitzada()) strEstado = "En Curs" ;
		else if (!this.assignada && !this.acceptada && this.solicitud.isPendent() && !this.solicitud.isFinalitzada()) strEstado = "En Espera" ;
		else if (!this.assignada && !this.acceptada && !this.solicitud.isPendent() && this.solicitud.isFinalitzada()) strEstado = "Rebutjada" ;
		else if (this.assignada && this.acceptada && !this.solicitud.isPendent() && this.solicitud.isFinalitzada()) strEstado = "Finalitzada" ;
		
		return ( strEstado );
	}
	

	public int getHoursDataInici () {
		if (dataInici!=null) {
			calendar.setTime(dataInici);
			return calendar.get(Calendar.HOUR_OF_DAY);
		} else {
			return -1;
		}
	}

	
	public int getMinuteDataInici () {
		if (dataInici!=null) {
			calendar.setTime(dataInici);
			return calendar.get(Calendar.MINUTE);
		} else {
			return -1;
		}
	}


	public int getSecondDataInici () {
		if (dataInici!=null) {
			calendar.setTime(dataInici);
			return calendar.get(Calendar.SECOND);
		} else {
			return -1;
		}
	}
	

	public int getHoursDataFi () {
		if (dataFi!=null) {
			calendar.setTime(dataFi);
			return calendar.get(Calendar.HOUR_OF_DAY);
		} else {
			return -1;
		}
	}


	public int getMinuteDataFi () {
		if (dataFi!=null) {
			calendar.setTime(dataFi);
			return calendar.get(Calendar.MINUTE);
		} else {
			return -1;
		}
	}

	
	public int getSecondDataFi () {
		if (dataFi!=null) {
			calendar.setTime(dataFi);
			return calendar.get(Calendar.SECOND);
		} else {
			return -1;
		}
	}
	
	public String prettyHoraInici()
	{
		return (
				a2Digitos(this.getHoursDataInici()) + ":" +
				a2Digitos(this.getMinuteDataInici()) + ":" +
				a2Digitos(this.getSecondDataInici())
			);
	}
	
	public String prettyHoraFi()
	{
		return (
				a2Digitos(this.getHoursDataFi()) + ":" +
				a2Digitos(this.getMinuteDataFi()) + ":" +
				a2Digitos(this.getSecondDataFi())
			);
	}
	
	private String a2Digitos (int iEntrada)
	{
		String strResult = "";
		
		if ( iEntrada <= 9 ) strResult = "0";
		
		return ( strResult + String.valueOf(iEntrada) );
	}
	public float getImporte()
	{
		return ( this.fImporte);
	}
	
	public void setImporte(float fImporte)
	{
		this.fImporte = fImporte;
	}

	public Comanda getComanda() {
		return comanda;
	}

	public void setComanda(Comanda comanda) {
		this.comanda = comanda;
	}

	

}
