package edu.uoc.tdp.pac4.dao;

import java.util.ArrayList;

import edu.uoc.tdp.pac4.beans.Reparacio;
import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.exception.DAOException;


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
	
	public ArrayList <Reparacio> obtenirReparacions(String strOrdreReparacio, String strNomClient, String strCognomClient, String strNomMecanic, String strCognomMecanic , String strEstado, String dataInici, String dataFi) throws DAOException;
	
	
	//Informe de Clients
	
	public ArrayList <Reparacio> obtenirClients(String strOrdreReparacio, String strNomClient, String strCognom, String strMarca, String strNomAsseguradora) throws DAOException;
	
	
	//Informe d'empleats
		
	public ArrayList <Usuari> obtenirEmpleats(String strIdUsuari, String strNomUsuari, String strCognomUsuari) throws DAOException;
		
	public ArrayList<Reparacio> calcularNumRepRessoltes (String strIdMecanic, String strNomMecanic, String strCognomMecanic) throws DAOException;
		
	public ArrayList<Reparacio> calcularNumHoresTreballades (String strIdMecanic, String strNomMecanic, String strCognomMecanic)throws DAOException;
				
}
