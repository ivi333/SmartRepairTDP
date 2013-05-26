package edu.uoc.tdp.pac4.service;

import edu.uoc.tdp.pac4.beans.*;
import edu.uoc.tdp.pac4.exception.DAOException;
import edu.uoc.tdp.pac4.exception.GestorConexionException;
import edu.uoc.tdp.pac4.exception.GestorEstadisticaException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;


/**
 * Smart Repair 
 * ETIG - TDP PAC 4 Primavera 2013
 * Grup: FiveCoreDumped
 */
public interface GestorEstadisticaInterface extends Remote{
	/**
	 * Definir las interficies de servicios RMI
	 * para el subsistema de Estadistica
	 * @throws GestorEstadisticaException 
	 */
	
	
	// Informe de Reparaciones

	ArrayList <Reparacio> obtenirReparacions(String strOrdreReparacio, String strNomClient, String strCognomClient, String strNomMecanic, String strCognomMecanic ,String strEstado, String dataInici, String dataFi) throws RemoteException, GestorEstadisticaException;
	
	
	float calcularTempsMigEspera (ArrayList <Reparacio> reparacions) throws  RemoteException, GestorEstadisticaException;
	
	float calcularTempsMigReparacio (ArrayList <Reparacio> reparacions) throws  RemoteException, GestorEstadisticaException;
	
	float calcularTempsMigFinalitzacio (ArrayList <Reparacio> reparacions) throws  RemoteException, GestorEstadisticaException;
	
	
	//Informe de Clients
	
	ArrayList <Reparacio> obtenirClients(String strOrdreReparacio, String strNomClient, String strCognom, String strMarca, String strNomAsseguradora)throws  RemoteException, GestorEstadisticaException;
		
		
	//Informe d'empleats
		
	public ArrayList <Usuari> obtenirEmpleats(String strIdUsuari, String strNomUsuari, String strCognomUsuari) throws  RemoteException, GestorEstadisticaException;
	
	
	public int calcularNumRepRessoltes (int idMecanic) throws  RemoteException, GestorEstadisticaException;

	public float calcularNumHoresRep (int idMecanic) throws  RemoteException, GestorEstadisticaException;
	

}



