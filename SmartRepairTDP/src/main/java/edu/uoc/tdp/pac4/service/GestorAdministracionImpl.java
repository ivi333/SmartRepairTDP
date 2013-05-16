package edu.uoc.tdp.pac4.service;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import edu.uoc.tdp.pac4.beans.Asseguradora;
import edu.uoc.tdp.pac4.beans.Client;
import edu.uoc.tdp.pac4.beans.Comanda;
import edu.uoc.tdp.pac4.beans.Peca;
import edu.uoc.tdp.pac4.beans.Proveidor;
import edu.uoc.tdp.pac4.beans.Reparacio;
import edu.uoc.tdp.pac4.beans.Solicitud;
import edu.uoc.tdp.pac4.dao.ConnectionPostgressDB;
import edu.uoc.tdp.pac4.dao.GestorAdministracionDAOImpl;
import edu.uoc.tdp.pac4.exception.DAOException;
import edu.uoc.tdp.pac4.exception.GestorAdministracionException;

/**
 * Smart Repair ETIG - TDP PAC 4 Primavera 2013 Grup: FiveCoreDumped
 */
public class GestorAdministracionImpl extends
		java.rmi.server.UnicastRemoteObject implements
		GestorAdministracionInterface, Serializable {

	private static final long serialVersionUID = -8030410035047028962L;
	private ConnectionPostgressDB cPostgressDB;

	public GestorAdministracionImpl() throws RemoteException {
		super();
		cPostgressDB = new ConnectionPostgressDB();
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
			gestorAdministracionDAO = new GestorAdministracionDAOImpl(
					cPostgressDB);
		} catch (GestorAdministracionException e) {
			e.printStackTrace();
		}
		try {
			return gestorAdministracionDAO.getMarcas();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean getExistCliente(String strNIF) throws RemoteException {
		GestorAdministracionDAOImpl gestorAdministracionDAO = null;
		boolean bResult = false;
		try {
			gestorAdministracionDAO = new GestorAdministracionDAOImpl(
					cPostgressDB);
			bResult = gestorAdministracionDAO.getExistCliente(strNIF);
		} catch (Exception ex) {
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
		try {
			return gestorAdministracionDAO.getNewClient(altaCliente);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -2;
	}

	public int getUpdClient(Client updCliente) throws RemoteException {
		GestorAdministracionDAOImpl gestorAdministracionDAO = null;
		try {
			gestorAdministracionDAO = new GestorAdministracionDAOImpl(
					cPostgressDB);
		} catch (GestorAdministracionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return gestorAdministracionDAO.getUpdClient(updCliente);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -2;
	}

	public Client getDadeClient(String strNIF) throws RemoteException {
		GestorAdministracionDAOImpl gestorAdministracionDAO = null;
		try {
			gestorAdministracionDAO = new GestorAdministracionDAOImpl(
					cPostgressDB);
		} catch (GestorAdministracionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return gestorAdministracionDAO.getDadeClient(strNIF);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Asseguradora> getAseguradoras() throws RemoteException {
		GestorAdministracionDAOImpl gestorAdministracionDAO = null;
		try {
			gestorAdministracionDAO = new GestorAdministracionDAOImpl(
					cPostgressDB);
		} catch (GestorAdministracionException e) {
			e.printStackTrace();
		}
		try {
			return gestorAdministracionDAO.getAseguradoras();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<Proveidor> getProveedores() throws RemoteException {
		GestorAdministracionDAOImpl gestorAdministracionDAO = null;
		try {
			gestorAdministracionDAO = new GestorAdministracionDAOImpl(
					cPostgressDB);
		} catch (GestorAdministracionException e) {
			e.printStackTrace();
		}
		try {
			return gestorAdministracionDAO.getProveedores();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Reparacio> getReparaciones() throws RemoteException {
		GestorAdministracionDAOImpl gestorAdministracionDAO = null;
		try {
			gestorAdministracionDAO = new GestorAdministracionDAOImpl(
					cPostgressDB);
		} catch (GestorAdministracionException e) {
			e.printStackTrace();
		}
		try {
			return gestorAdministracionDAO.getReparaciones();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Solicitud getSolicitudByCodeReparacion(int codigoReparacion) throws RemoteException {
		GestorAdministracionDAOImpl gestorAdministracionDAO = null;
		try {
			gestorAdministracionDAO = new GestorAdministracionDAOImpl(
					cPostgressDB);
		} catch (GestorAdministracionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gestorAdministracionDAO.getSolicitudByCodeReparacion(codigoReparacion);
	}
	
	public ArrayList<Peca> getPiezaByCodeProveedor(int codigoProv)
	{GestorAdministracionDAOImpl gestorAdministracionDAO = null;
	try {
		gestorAdministracionDAO = new GestorAdministracionDAOImpl(
				cPostgressDB);
	} catch (GestorAdministracionException e) {
		e.printStackTrace();
	}
	try {
		return gestorAdministracionDAO.getPiezaByCodeProveedor(codigoProv);
	} catch (DAOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	}

	public Peca getPiezaByCode(int codigoPieza) throws RemoteException {
		GestorAdministracionDAOImpl gestorAdministracionDAO = null;
		try {
			gestorAdministracionDAO = new GestorAdministracionDAOImpl(
					cPostgressDB);
		} catch (GestorAdministracionException e) {
			e.printStackTrace();
		}
		try {
			return gestorAdministracionDAO.getPiezaByCode(codigoPieza);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		}

	public int getNuevoPedido(Comanda comanda) throws RemoteException {
		GestorAdministracionDAOImpl gestorAdministracionDAO = null;
		try {
			gestorAdministracionDAO = new GestorAdministracionDAOImpl(
					cPostgressDB);
		} catch (GestorAdministracionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return gestorAdministracionDAO.getNuevoPedido(comanda);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public ArrayList<String> getCargarPedidos() {
		GestorAdministracionDAOImpl gestorAdministracionDAO = null;
		try {
			gestorAdministracionDAO = new GestorAdministracionDAOImpl(
					cPostgressDB);
		} catch (GestorAdministracionException e) {
			e.printStackTrace();
		}
		try {
			return gestorAdministracionDAO.getCargarPedidos();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int  getNuevoSolicitud(Solicitud solicitud) throws RemoteException {
		GestorAdministracionDAOImpl gestorAdministracionDAO = null;
		try {
			gestorAdministracionDAO = new GestorAdministracionDAOImpl(
					cPostgressDB);
		} catch (GestorAdministracionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return gestorAdministracionDAO.getNuevoSolicitud(solicitud);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}
