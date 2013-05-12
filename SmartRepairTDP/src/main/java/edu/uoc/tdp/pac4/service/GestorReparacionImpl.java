package edu.uoc.tdp.pac4.service;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

import edu.uoc.tdp.pac4.beans.Mecanic;
import edu.uoc.tdp.pac4.beans.Peca;
import edu.uoc.tdp.pac4.beans.Reparacio;
import edu.uoc.tdp.pac4.beans.Stockpeca;
import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import edu.uoc.tdp.pac4.dao.GestorReparacionDAO;
import edu.uoc.tdp.pac4.dao.GestorReparacionDAOImpl;
import edu.uoc.tdp.pac4.exception.DAOException;
import edu.uoc.tdp.pac4.exception.GestorReparacionException;


/**
 * Smart Repair 
 * ETIG - TDP PAC 4 Primavera 2013
 * Grup: FiveCoreDumped
 */
public class GestorReparacionImpl extends java.rmi.server.UnicastRemoteObject implements GestorReparacionInterface, Serializable {

	/**
	 * Objecto para operar con la base de datos (database access object)
	 */
	private GestorReparacionDAO gestorReparacionDAO;
	/**
	 * 
	 */

	private static final long serialVersionUID = 425801725246033548L;

	public GestorReparacionImpl() throws RemoteException {
		super();
		this.gestorReparacionDAO = new GestorReparacionDAOImpl();
		TDSLanguageUtils.setDefaultLanguage("i18n/messages");
	}

	/**
	 * Definir Implementaciones
	 */
	
	@Override
	public List<Reparacio> getReparaciones()
			throws GestorReparacionException {
		try {
			return gestorReparacionDAO.getReparaciones();
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}
	
	@Override
	public Reparacio getReparacio(int ordenReparacion) 
			throws GestorReparacionException {
		try {
			return gestorReparacionDAO.getReparacion(ordenReparacion);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}
	
	@Override
	public List<Mecanic> getMecanicos() 
			throws GestorReparcionException {
		try {
			return gestorReparacionDAO.getMecanicos();
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}
	
	@Override
	public Mecanic getMecanico(int idMecanico)
			throws GestorReparacionException {
		try {
			return gestorReparacionDAO.getMecanico(idMecanico);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}
	
	@Override
	public List<Stockpeca> getStockPiezas()
			throws GestorReparacionException {
		try {
			return gestorReparacionDAO.getStockPiezas();
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}
	
	@Override
	public List<Peca> getPiezas()
			throws GestorReparacionException {
		try{
			return gestorReparacionDAO.getPiezas();
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}
	
	@Override
	public Peca getPieza(int idPieza) 
			throws GestorReparacionException {
		try {
			return gestorReparacionDAO.getPieza(idPieza);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	

}