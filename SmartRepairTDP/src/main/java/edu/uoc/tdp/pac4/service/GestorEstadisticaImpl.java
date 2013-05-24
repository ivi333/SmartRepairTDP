package edu.uoc.tdp.pac4.service;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

import edu.uoc.tdp.pac4.exception.DAOException;
import edu.uoc.tdp.pac4.exception.GestorEstadisticaException;
import edu.uoc.tdp.pac4.beans.Client;
import edu.uoc.tdp.pac4.beans.Reparacio;
import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.dao.GestorEstadisticaDAO;
import edu.uoc.tdp.pac4.dao.GestorEstadisticaDAOImpl;

/**
 * Smart Repair 
 * ETIG - TDP PAC 4 Primavera 2013
 * Grup: FiveCoreDumped
 * 
 * /**
	 *  Implementacion de los servicios RMI 
	 *  para el subsistema de Estadistica
	 */

public class GestorEstadisticaImpl extends java.rmi.server.UnicastRemoteObject implements GestorEstadisticaInterface, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2260455636917603178L;
	private GestorEstadisticaDAO gestorEstadisticaDAO;

	public GestorEstadisticaImpl() throws RemoteException {
		super();
		this.gestorEstadisticaDAO = new GestorEstadisticaDAOImpl();
	}
	

	public ArrayList <Reparacio> obtenirReparacions(int intOrdreReparacio, String strNomClient, String strCognomClient, String strNomMecanic, String strCognomMecanic , boolean bPendent, boolean bAssignada, boolean bAcceptada, boolean bFinalitzada, String dataInici, String dataFi)
			throws GestorEstadisticaException {
		
		try {
			return gestorEstadisticaDAO.obtenirReparacions(intOrdreReparacio, strNomClient, strCognomClient, strNomMecanic, strCognomMecanic , bPendent, bAssignada,  bAcceptada, bFinalitzada, dataInici, dataFi);
			
		} catch (DAOException e) {
			throw new GestorEstadisticaException(GestorEstadisticaException.ERR_DAO +  e.getMessage());
		}
		
	}
	
	
	
	/*Càlcul del temps mig que passa entre el moment en el queda enregistrada la sol.licitut de reparació
	i l'hora en la que es comença*/
	
	
	public float calcularTempsMigEspera (ArrayList<Reparacio> reparacions) throws GestorEstadisticaException {
		float fHoras = 0 ;
		float fMedia = 0 ;
		
		if ( !reparacions.isEmpty()){
			Iterator<Reparacio> it = reparacions.iterator();
			
			while ( it.hasNext())
			{
				Reparacio rep = it.next();
				fHoras += rep.getDataInici().getHours() - rep.getSolicitud().getDataalta().getHours() ;			
			}
			fMedia = fHoras / reparacions.size();
		}
		
		return(fMedia);		
	}

	
	public float calcularTempsMigReparacio (ArrayList<Reparacio> reparacions) throws GestorEstadisticaException{
		float fHoras = 0 ;
		float fMedia = 0 ;
		
		if ( !reparacions.isEmpty()){
			Iterator<Reparacio> it = reparacions.iterator();
			
			while ( it.hasNext())
			{
				Reparacio rep = it.next();
				fHoras += rep.getDataFi().getHours() - rep.getDataInici().getHours();			
			}
			fMedia = fHoras / reparacions.size();
		}
		
		return(fMedia);		
	}
	
	
	public float calcularTempsMigFinalitzacio (ArrayList<Reparacio> reparacions) throws GestorEstadisticaException{
		float fHoras = 0 ;
		float fMedia = 0 ;
		
		if ( !reparacions.isEmpty()){
			Iterator<Reparacio> it = reparacions.iterator();
			
			while ( it.hasNext())
			{
				Reparacio rep = it.next();
				fHoras += rep.getDataFi().getHours() - rep.getSolicitud().getDataalta().getHours() ;			
			}
			fMedia = fHoras / reparacions.size();
		}
		
		return(fMedia);		
	}
	
	//Informe de Clients
	
	public ArrayList <Client> obtenirClients(String strNomClient, String strCognom, String strMarca, String strNomAsseguradora)throws GestorEstadisticaException{
		
		try {
			return gestorEstadisticaDAO.obtenirClients(strNomClient, strCognom, strMarca, strNomAsseguradora);
		} catch (DAOException e) {
			throw new GestorEstadisticaException(GestorEstadisticaException.ERR_DAO +  e.getMessage());
		}
	}
	
	
	//Informe d'empleats

	public ArrayList <Usuari> obtenirEmpleats(int intIdUsuari, String strNomUsuari, String strCognom) throws GestorEstadisticaException{
		
		try {
			return gestorEstadisticaDAO.obtenirEmpleats(intIdUsuari, strNomUsuari, strCognom);
		} catch (DAOException e) {
			throw new GestorEstadisticaException(GestorEstadisticaException.ERR_DAO +  e.getMessage());
		}
	}
	
	//Calcular nº de horas que un Mecanic SI ha trabajado
	public int calcularNumHoresTreballades () throws GestorEstadisticaException{
		return 0;
	
	}
	
	//usuari.reparacionsAssignadades con r.dataFi 
	public int calcularNumRepRessoltes (int intIdMecanic) throws GestorEstadisticaException{
		return intIdMecanic;
	
	
	}
	//Calcular nº de horas que un Mecanic NO ha trabajado
	public int calcularNumFaltesAssistencia () throws GestorEstadisticaException{
		return 0;
	
	}



				
}