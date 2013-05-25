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

	public void setPiezaComanda(boolean estado, int codigoPieza, int idTaller, int ordenReparacion, int cantidad, boolean tipoReparacion)
			throws RemoteException, GestorReparacionException {
		try {
			gestorReparacionDAO.setPiezaComanda(estado, codigoPieza, idTaller, ordenReparacion, cantidad, tipoReparacion);
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

	public List<Usuari> getUsuarios(String tipo, String nombre) throws RemoteException,
			GestorReparacionException {
		try {
			return gestorReparacionDAO.getUsuarios(tipo, nombre);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	public void asignarUsuarioNumeroReparacion(int idMecanico, int numeroReparaciones)
			throws RemoteException, GestorReparacionException {
		try {
			gestorReparacionDAO.asignarUsuarioNumeroReparacion(idMecanico, numeroReparaciones);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	public void asignarReparacionesMecanico(int idMecanico,
			int ordenReparacion1, int ordenReparacion2) throws RemoteException,
			GestorReparacionException {
		try {
			gestorReparacionDAO.asignarReparacionesMecanico(idMecanico, ordenReparacion1, ordenReparacion2);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	public void asignarMecanicoReparacion(int idMecanico, int ordenReparacion)
			throws RemoteException, GestorReparacionException {
		try {
			gestorReparacionDAO.asignarMecanicoReparacion(idMecanico, ordenReparacion);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	public List<DetallReparacio> getDetalleReparacionesMecanico(int idMecanico)
			throws RemoteException, GestorReparacionException {
		try {
			return gestorReparacionDAO.getDetalleReparacionesMecanico(idMecanico);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}
	
	public List<DetallReparacio> getDetalleReparacionesMecanicoFiltro(
			int idMecanico, int idFiltro, String valor) throws RemoteException,
			GestorReparacionException {
		try {
			return gestorReparacionDAO.getDetalleReparacionesMecanicoFiltro(idMecanico, idFiltro, valor);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	public int getStockMinimoPieza(int idPieza) throws RemoteException,
			GestorReparacionException {
		try {
			return gestorReparacionDAO.getStockMinimoPieza(idPieza);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	public List<DetallReparacio> getDetalleReparacionesFiltro(int idFiltro,
			String valor, String nombre, String apellido, String fechaDe, String fechaHasta)
			throws RemoteException, GestorReparacionException {
		try {
			return gestorReparacionDAO.getDetalleReparacionesFiltro(idFiltro, valor, nombre, apellido, fechaDe, fechaHasta);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	public String getMarcaPieza(int idPieza) throws RemoteException,
			GestorReparacionException {
		try {
			return gestorReparacionDAO.getMarcaPieza(idPieza);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	public String getModeloPieza(int idPieza) throws RemoteException,
			GestorReparacionException {
		try {
			return gestorReparacionDAO.getModeloPieza(idPieza);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	public List<DetallPeca> getDetallePiezasTallerFiltro(int idTaller,
			int filtro, String valor) throws RemoteException,
			GestorReparacionException {
		try {
			return gestorReparacionDAO.getDetallePiezasTallerFiltro(idTaller, filtro, valor);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	public void setHoraInicioReparacion(int ordenReparacion)
			throws RemoteException, GestorReparacionException {
		try {
			gestorReparacionDAO.setHoraInicioReparacion(ordenReparacion);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	public void setHoraFinReparacion(int ordenReparacion)
			throws RemoteException, GestorReparacionException {
		try {
			gestorReparacionDAO.setHoraFinReparacion(ordenReparacion);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	public Date getHoraInicioReparacion(int ordenReparacion)
			throws RemoteException, GestorReparacionException {
		try {
			return gestorReparacionDAO.getHoraInicioReparacion(ordenReparacion);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	public Date getHoraFinReparacion(int ordenReparacion)
			throws RemoteException, GestorReparacionException {
		try {
			return gestorReparacionDAO.getHoraFinReparacion(ordenReparacion);
		} catch (DAOException e) {
			throw new GestorReparacionException(GestorReparacionException.ERR_DAO +  e.getMessage());
		}
	}

	

	

	
}