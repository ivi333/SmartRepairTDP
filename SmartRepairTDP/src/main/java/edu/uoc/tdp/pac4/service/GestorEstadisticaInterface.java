package edu.uoc.tdp.pac4.service;

import edu.uoc.tdp.pac4.beans.*;
import edu.uoc.tdp.pac4.exception.DAOException;
import edu.uoc.tdp.pac4.exception.GestorEstadisticaException;

import java.rmi.Remote;
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

	ArrayList <Reparacio> obtenirReparacions(int intOrdreReparacio, String strNomClient, String strCognomClient, String strNomMecanic, String strCognomMecanic , boolean bPendent, boolean bAssignada, boolean bAcceptada, boolean bFinalitzada, String dataInici, String dataFi)
			throws GestorEstadisticaException;
	
	
	float calcularTempsMigEspera (ArrayList <Reparacio> reparacions) throws GestorEstadisticaException;
	
	float calcularTempsMigReparacio (ArrayList <Reparacio> reparacions) throws GestorEstadisticaException;
	
	float calcularTempsMigFinalitzacio (ArrayList <Reparacio> reparacions) throws GestorEstadisticaException;
	
	
	//Informe de Clients
	
	ArrayList <Client> obtenirClients(String strNomClient, String strCognom, String strMarca, String strNomAsseguradora)throws GestorEstadisticaException;
		
		
	//Informe d'empleats
		
	public ArrayList <Usuari> obtenirEmpleats(int intIdUsuari, String strNomUsuari, String strCognom) throws GestorEstadisticaException;
	
	
	public int calcularNumHoresTreballades () throws GestorEstadisticaException;

	public int calcularNumRepRessoltes (int intIdMecanic) throws GestorEstadisticaException;
	
	public int calcularNumFaltesAssistencia () throws GestorEstadisticaException;

}



