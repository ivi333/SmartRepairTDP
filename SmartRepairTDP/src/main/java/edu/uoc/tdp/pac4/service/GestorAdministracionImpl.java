package edu.uoc.tdp.pac4.service;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import edu.uoc.tdp.pac4.beans.Asseguradora;
import edu.uoc.tdp.pac4.beans.Client;
import edu.uoc.tdp.pac4.beans.Peca;
import edu.uoc.tdp.pac4.dao.ConnectionPostgressDB;
import edu.uoc.tdp.pac4.dao.GestorAdministracionDAOImpl;
import edu.uoc.tdp.pac4.exception.DAOException;
import edu.uoc.tdp.pac4.exception.GestorAdministracionException;

/**
 * Smart Repair 
 * ETIG - TDP PAC 4 Primavera 2013
 * Grup: FiveCoreDumped
 */
public class GestorAdministracionImpl extends java.rmi.server.UnicastRemoteObject implements GestorAdministracionInterface, Serializable {

	private static final long serialVersionUID = -8030410035047028962L;
	private ConnectionPostgressDB cPostgressDB;
	
	public GestorAdministracionImpl() throws RemoteException{
		super();
		cPostgressDB=new ConnectionPostgressDB();
		try {
			cPostgressDB.getConnectionDB();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Peca> getMarcas() throws RemoteException {
		GestorAdministracionDAOImpl gestorAdministracionDAO = null;
		try {
			gestorAdministracionDAO = new GestorAdministracionDAOImpl(cPostgressDB);
		} catch (GestorAdministracionException e) {
			e.printStackTrace();
		}
		return  gestorAdministracionDAO.getMarcas();
	}
	public boolean getExistCliente(String strNIF) throws RemoteException{
		GestorAdministracionDAOImpl gestorAdministracionDAO = null;
		boolean bResult=false;
		try{
			gestorAdministracionDAO = new GestorAdministracionDAOImpl(cPostgressDB);
			bResult=gestorAdministracionDAO.getExistCliente(strNIF);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return bResult;
	}

	
	/**
	 * version prueba
	 */
	public String aux() throws RemoteException {
		GestorAdministracionDAOImpl gestorAdministracionDAO = null;
		try {
			gestorAdministracionDAO = new GestorAdministracionDAOImpl(
					cPostgressDB);
		} catch (GestorAdministracionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gestorAdministracionDAO.aux();
	}
	
	
	public int getNewClient(Client altaCliente) throws RemoteException {
		GestorAdministracionDAOImpl gestorAdministracionDAO = null;
		try {
			gestorAdministracionDAO = new GestorAdministracionDAOImpl(
					cPostgressDB);
		} catch (GestorAdministracionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gestorAdministracionDAO.getNewClient(altaCliente);
	}

	public  ArrayList<Asseguradora> getAseguradoras() throws RemoteException {
		GestorAdministracionDAOImpl gestorAdministracionDAO = null;
		try {
			gestorAdministracionDAO = new GestorAdministracionDAOImpl(cPostgressDB);
		} catch (GestorAdministracionException e) {
			e.printStackTrace();
		}
		return  gestorAdministracionDAO.getAseguradoras();
	}
}
	

	
	

	
