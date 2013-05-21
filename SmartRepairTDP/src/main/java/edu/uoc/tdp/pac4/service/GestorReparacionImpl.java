package edu.uoc.tdp.pac4.service;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import edu.uoc.tdp.pac4.beans.DetallPeca;
import edu.uoc.tdp.pac4.beans.DetallReparacio;
import edu.uoc.tdp.pac4.beans.Mecanic;
import edu.uoc.tdp.pac4.beans.Peca;
import edu.uoc.tdp.pac4.beans.Reparacio;
import edu.uoc.tdp.pac4.beans.Stockpeca;
import edu.uoc.tdp.pac4.beans.Usuari;
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

	public List<Reparacio> getReparaciones() throws RemoteException,
			GestorReparacionException {
		try {
			return gestorReparacionDAO.getReparaciones();
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	public Reparacio getReparacion(int ordenReparacion) throws RemoteException,
			GestorReparacionException {
		try {
			return gestorReparacionDAO.getReparacion(ordenReparacion);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	public List<Mecanic> getMecanicos() throws RemoteException,
			GestorReparacionException {
		try {
			return gestorReparacionDAO.getMecanicos();
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	public Mecanic getMecanico(int idMecanico) throws RemoteException,
			GestorReparacionException {
		try {
			return gestorReparacionDAO.getMecanico(idMecanico);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	public List<Stockpeca> getStockPiezas() throws RemoteException,
			GestorReparacionException {
		try {
			return gestorReparacionDAO.getStockPiezas();
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	public List<Peca> getPiezas() throws RemoteException,
			GestorReparacionException {
		try{
			return gestorReparacionDAO.getPiezas();
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	public Peca getPieza(int idPieza) throws RemoteException,
			GestorReparacionException {
		try {
			return gestorReparacionDAO.getPieza(idPieza);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	public List<DetallReparacio> getDetalleReparaciones()
			throws RemoteException, GestorReparacionException {
		try {
			return gestorReparacionDAO.getDetalleReparaciones();
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}
	
	public DetallReparacio getDetalleReparacion(int ordenReparacion)
			throws RemoteException, GestorReparacionException {
		try {
			return gestorReparacionDAO.getDetalleReparacion(ordenReparacion);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	public void setReparacionFinalizada(int ordenReparacion)
			throws RemoteException, GestorReparacionException {
		try {
			gestorReparacionDAO.setReparacionFinalizada(ordenReparacion);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	public List<DetallPeca> getPiezasReparacion(int ordenReparacion)
			throws RemoteException, GestorReparacionException {
		try {
			return gestorReparacionDAO.getPiezasReparacion(ordenReparacion);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}
	
	public List<DetallPeca> getDetallePiezas(String nombrePieza)
			throws RemoteException, GestorReparacionException {
		try {
			return gestorReparacionDAO.getDetallePiezas(nombrePieza);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	public Usuari getUsuario(int idUsuario) throws RemoteException,
			GestorReparacionException {
		try {
			return gestorReparacionDAO.getUsuario(idUsuario);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	public void setPiezaComanda(boolean estado, int codigoPieza, int idTaller, int ordenReparacion, int cantidad)
			throws RemoteException, GestorReparacionException {
		try {
			gestorReparacionDAO.setPiezaComanda(estado, codigoPieza, idTaller, ordenReparacion, cantidad);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
		
	}

	public void deletePiezaComanda(int codigoPieza, int ordenReparacion)
			throws RemoteException, GestorReparacionException {
		try {
			gestorReparacionDAO.deletePiezaComanda(codigoPieza, ordenReparacion);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	public List<DetallPeca> getDetallePiezasTaller(int idTaller, String nombrePieza)
			throws RemoteException, GestorReparacionException {
		try {
			return gestorReparacionDAO.getDetallePiezasTaller(idTaller, nombrePieza);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	public void setDescontarStock(int codigoPieza, int cantidad) throws RemoteException,
			GestorReparacionException {
		try {
			gestorReparacionDAO.setDescontarStock(codigoPieza, cantidad);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}
	
	public void setReparacionAceptada(int ordenReparacion)
			throws RemoteException, GestorReparacionException {
		try {
			gestorReparacionDAO.setReparacionAceptada(ordenReparacion);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	public void setReparacionAsignada(int ordenReparacion)
			throws RemoteException, GestorReparacionException {
		try {
			gestorReparacionDAO.setReparacionAsignada(ordenReparacion);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	

	
}