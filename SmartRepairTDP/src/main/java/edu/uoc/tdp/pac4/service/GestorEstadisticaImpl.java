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
	

	public ArrayList <Reparacio> obtenirReparacions(String strOrdreReparacio, String strNomClient, String strCognomClient, String strNomMecanic, String strCognomMecanic , String strEstado, String dataInici, String dataFi)
			throws RemoteException, GestorEstadisticaException {
		try {
			return gestorEstadisticaDAO.obtenirReparacions(strOrdreReparacio, strNomClient, strCognomClient, strNomMecanic, strCognomMecanic , strEstado , dataInici, dataFi);
			
		} catch (DAOException e) {
			throw new GestorEstadisticaException(GestorEstadisticaException.ERR_DAO +  e.getMessage());
		}
		
	}
	
	
	
	/*Càlcul del temps mig que passa entre el moment en el queda enregistrada la sol.licitut de reparació
	i l'hora en la que es comença*/
	
	
	public float calcularTempsMigEspera (ArrayList<Reparacio> reparacions) throws  RemoteException, GestorEstadisticaException {
		long lMilliseconds = 0 ;
		float fHoras = 0 ;
		
		if ( !reparacions.isEmpty()){
			Iterator<Reparacio> it = reparacions.iterator();
			
			while ( it.hasNext())
			{
				Reparacio rep = it.next();
				lMilliseconds += rep.getDataInici().getTime() - rep.getSolicitud().getDataalta().getTime();			
			}
			
			fHoras = lMilliseconds / 1000 ;
			fHoras /= (reparacions.size()*60*60);
		}
		
		return(fHoras);
	}

	
	public float calcularTempsMigReparacio (ArrayList<Reparacio> reparacions) throws RemoteException, GestorEstadisticaException {
		long lMilliseconds = 0 ;
		float fHoras = 0 ;
		
		if ( !reparacions.isEmpty()){
			Iterator<Reparacio> it = reparacions.iterator();
			
			while ( it.hasNext())
			{
				Reparacio rep = it.next();
				lMilliseconds += rep.getDataInici().getTime() - rep.getDataFi().getTime();			
			}
			
			fHoras = lMilliseconds / 1000 ;
			fHoras /= (reparacions.size()*60*60);
		}
		
		return(fHoras);	
	}
	
	
	public float calcularTempsMigFinalitzacio (ArrayList<Reparacio> reparacions) throws RemoteException, GestorEstadisticaException {
		long lMilliseconds = 0 ;
		float fHoras = 0 ;
		ArrayList<Reparacio> vectorAux = new ArrayList<Reparacio>();
		
		for ( Reparacio reparacio : reparacions )
		{
			if ( reparacio.getSolicitud().isFinalitzada())
				vectorAux.add(reparacio);
		}
		
		if ( !vectorAux.isEmpty()){
			Iterator<Reparacio> it = vectorAux.iterator();
			
			while ( it.hasNext())
			{
				Reparacio rep = it.next();
				lMilliseconds += rep.getDataInici().getTime() - rep.getDataFi().getTime();			
			}
			
			fHoras = lMilliseconds / 1000 ;
			fHoras /= (reparacions.size()*60*60);
		}
		
		return(fHoras);
	}
	
	//Informe de Clients
	
	public ArrayList <Reparacio> obtenirClients(String strOrdreReparacio, String strNomClient, String strCognom, String strMarca, String strNomAsseguradora) throws RemoteException, GestorEstadisticaException {
		
		try {
			//ArrayList<Reparacio> reparacions = gestorEstadisticaDAO.obtenirClients(strOrdreReparacio, strNomClient, strCognom, strMarca, strNomAsseguradora);
			return gestorEstadisticaDAO.obtenirClients(strOrdreReparacio, strNomClient, strCognom, strMarca, strNomAsseguradora);
			//return ( reparacions ) ;
		} catch (DAOException e) {
			throw new GestorEstadisticaException(GestorEstadisticaException.ERR_DAO +  e.getMessage());
		}
	}
	
	
	//Informe d'empleats

	public ArrayList <Usuari> obtenirEmpleats(String strIdUsuari, String strNomUsuari, String strCognomUsuari) throws RemoteException, GestorEstadisticaException {
		
		try {
			return gestorEstadisticaDAO.obtenirEmpleats(strIdUsuari, strNomUsuari, strCognomUsuari);
		} catch (DAOException e) {
			throw new GestorEstadisticaException(GestorEstadisticaException.ERR_DAO +  e.getMessage());
		}
	}
	
	
	public int calcularNumRepRessoltes (int idMecanic) throws RemoteException, GestorEstadisticaException {
		
		try {
			return gestorEstadisticaDAO.calcularNumRepRessoltes(idMecanic);
		} catch (DAOException e) {
			throw new GestorEstadisticaException(GestorEstadisticaException.ERR_DAO +  e.getMessage());
		}
	}

	public float calcularNumHoresRep (int idMecanic) throws RemoteException, GestorEstadisticaException {
		
		try {
			return gestorEstadisticaDAO.calcularNumHoresRep(idMecanic);
		} catch (DAOException e) {
			throw new GestorEstadisticaException(GestorEstadisticaException.ERR_DAO +  e.getMessage());
		}
	}
}