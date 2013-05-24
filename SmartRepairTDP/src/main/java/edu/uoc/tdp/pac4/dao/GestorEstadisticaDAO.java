package edu.uoc.tdp.pac4.dao;

import java.sql.Date;

import java.util.ArrayList;

import org.postgresql.jdbc2.EscapedFunctions;

import edu.uoc.tdp.pac4.beans.Client;
import edu.uoc.tdp.pac4.beans.Reparacio;
import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.exception.DAOException;
import edu.uoc.tdp.pac4.exception.GestorEstadisticaException;


/**
 * Clase abstracta que gestiona las operaciones basicas de conexion con postgres
 * Smart Repair 
 * ETIG - TDP PAC 4 Primavera 2013
 * Grup: FiveCoreDumped
 */
public interface GestorEstadisticaDAO {
	/**
	 * Definir las interficies de base de datos
	 * para el subsistema de Estadistica
	 */

	//Informe de Reparacions
	
	public ArrayList<Reparacio> obtenirReparacions(int intOrdreReparacio, String strNomClient, String strCognomClient, String strNomMecanic, String strCognomMecanic , boolean bPendent, boolean bAssignada, boolean bAcceptada, boolean bFinalitzada, String dataInici, String dataFi) throws DAOException;
	
	
	//Informe de Clients
	
	public ArrayList <Client> obtenirClients(String strNomClient, String strCognom, String strMarca, String strNomAsseguradora) throws DAOException;
	
	
	//Informe d'empleats
		
	public ArrayList <Usuari> obtenirEmpleats(int intIdUsuari, String strNomUsuari, String strCognom) throws DAOException;
		
}
