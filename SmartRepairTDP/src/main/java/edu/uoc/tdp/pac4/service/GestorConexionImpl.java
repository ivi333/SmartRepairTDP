package edu.uoc.tdp.pac4.service;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;


import edu.uoc.tdp.pac4.beans.Taller;
import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import edu.uoc.tdp.pac4.dao.GestorConexionDAO;
import edu.uoc.tdp.pac4.dao.GestorConexionDAOImpl;
import edu.uoc.tdp.pac4.exception.DAOException;
import edu.uoc.tdp.pac4.exception.GestorConexionException;

/**
 * Smart Repair 
 * ETIG - TDP PAC 4 Primavera 2013
 * Grup: FiveCoreDumped
 */
public class GestorConexionImpl extends java.rmi.server.UnicastRemoteObject implements GestorConexionInterface, Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3438374026271490739L;

	private GestorConexionDAO gestorConexionDAO;
	
	public GestorConexionImpl() throws RemoteException {
		super();
		gestorConexionDAO = new GestorConexionDAOImpl();		
		TDSLanguageUtils.setDefaultLanguage("i18n/messages");
	}

	/**
	 *  Implementacion de los servicios RMI 
	 *  para el subsistema de Conexion y Mantenimiento
	 */
	
	public Usuari doLogin(String username, String contrasenya)
			throws RemoteException, GestorConexionException {		
		Usuari usuari = null;
		try{
			usuari = gestorConexionDAO.getUsuariByUsuari(username);
			if (!(usuari == null)) {
				if (usuari.isActiu()) {
					if (!(usuari.getContrasenya().equals(contrasenya))) {		
						usuari = null;
						throw new GestorConexionException(GestorConexionException.ERR_USER_INVALID);
					}
				} else {
					throw new GestorConexionException(GestorConexionException.ERR_USER_DISABLED);
					
				}
				return usuari;
			} else {
				throw new GestorConexionException(GestorConexionException.ERR_USER_INVALID);
			}			
		}catch (DAOException e) {			
			throw new GestorConexionException(GestorConexionException.ERR_DAO +  e.getMessage());
		}		
		
	}
	
	public Usuari getUsuariById (int id) throws RemoteException, GestorConexionException {
		Usuari usuari = null;
		try {
			usuari = gestorConexionDAO.getUsuariById(id);
			if (usuari == null)
				throw new GestorConexionException(GestorConexionException.ERR_USER_NOTFOUND);
		} catch (DAOException e) {
			throw new GestorConexionException(GestorConexionException.ERR_DAO + e.getMessage());
		}
		return usuari;
	
	}

	public Usuari getUsuariByUsuari(String username) throws RemoteException,
			GestorConexionException {
		Usuari usuari = null;
		try {
			usuari = gestorConexionDAO.getUsuariByUsuari(username);
			if (usuari == null)
				throw new GestorConexionException(GestorConexionException.ERR_USER_NOTFOUND);
		} catch (DAOException e) {
			throw new GestorConexionException(GestorConexionException.ERR_DAO + e.getMessage());
		}
		return usuari;
	}
	
	public Usuari getUsuariByNif (String nif)
			throws RemoteException, GestorConexionException {
		Usuari usuari = null;
		try {
			usuari = gestorConexionDAO.getUsuariByNif(nif);
			if (usuari == null)
				throw new GestorConexionException(GestorConexionException.ERR_USER_NOTFOUND);
		} catch (DAOException e) {
			throw new GestorConexionException(GestorConexionException.ERR_DAO + e.getMessage());
		}
		return usuari;
	}

	public List<Usuari> getAllUsuaris() throws RemoteException,
			GestorConexionException {
		try {
			return gestorConexionDAO.getAllUsuaris();
		}catch (DAOException e){
			throw new GestorConexionException(GestorConexionException.ERR_DAO + e.getMessage());
		}
	}

	public List<Usuari> getUsuarisByFilter (String id, String nif, String nombre, String apellidos, String perfil)
		throws GestorConexionException {
		try {
			return gestorConexionDAO.getUsuarisByFilter(id, nif, nombre, apellidos, perfil);
		} catch (DAOException e) {
			throw new GestorConexionException(GestorConexionException.ERR_DAO + e.getMessage());
		}
	}
	
	public List<Usuari> getUsuarisCapTaller () throws RemoteException, GestorConexionException {
		try {
			return gestorConexionDAO.getUsuarisCapTaller();
		} catch (DAOException e) {
			throw new GestorConexionException(GestorConexionException.ERR_DAO + e.getMessage());
		}
	}
	
	public void disableUser(int idUsuari)
			throws RemoteException, GestorConexionException {
		try {
			Usuari usuari = gestorConexionDAO.getUsuariById(idUsuari);
			if (usuari.getReparacionsAssignades() == 0) {
				gestorConexionDAO.disableUser(idUsuari);
			} else {
				throw new GestorConexionException(GestorConexionException.ERR_USER_REPARACIONES);
			}
		} catch (DAOException e) {
			throw new GestorConexionException(GestorConexionException.ERR_DAO + e.getMessage());
		}
	}
	
	public void altaUsuari(Usuari usuari)
			throws RemoteException, GestorConexionException {

		try {
			if (!gestorConexionDAO.usuariExist(usuari.getNif(),usuari.getUsuari())) {
				gestorConexionDAO.altaUsuari(usuari);
			} else {
				throw new GestorConexionException(GestorConexionException.ERR_USER_EXIST);
			}
		} catch (DAOException e) {
			throw new GestorConexionException(GestorConexionException.ERR_DAO + e.getMessage());
		} 
	}
	
	public void modificarUsuari(Usuari usuari)
			throws RemoteException, GestorConexionException {
		try {
			Usuari usuariOld = gestorConexionDAO.getUsuariById(usuari.getId());
			if (usuariOld.getReparacionsAssignades() > 0 && usuariOld.getTaller() != usuari.getTaller())
				throw new GestorConexionException(GestorConexionException.ERR_USER_REP_TALLER);
			if (!gestorConexionDAO.usuariExist(usuari.getNif(), usuari.getUsuari(), usuari.getId())){
				gestorConexionDAO.modificarUsuari(usuari);
			} else {
				throw new GestorConexionException(GestorConexionException.ERR_USER_EXIST);
			}
		} catch (DAOException e) {
			throw new GestorConexionException(GestorConexionException.ERR_DAO + e.getMessage());
		}
	}
	
	public List<Taller> getAllTallers () throws RemoteException, GestorConexionException {
		try {
			return gestorConexionDAO.getAllTallers();
		} catch (DAOException e){
			throw new GestorConexionException(GestorConexionException.ERR_DAO + e.getMessage());
		}
	}
	
	public Taller getTallerById (int id) throws RemoteException, GestorConexionException {
		try {
			return gestorConexionDAO.getTallerById(id);
		} catch (DAOException e) {
			throw new GestorConexionException(GestorConexionException.ERR_DAO + e.getMessage());
		}
	}

	public List<Taller> getTallersByFilter (String id, String cif, String adreca, String capacitat, String idCapTaller)
			throws RemoteException, GestorConexionException {
		try {
			return gestorConexionDAO.getTallersByFilter(id, cif, adreca, capacitat, idCapTaller);
		} catch (DAOException e) {
			throw new GestorConexionException(GestorConexionException.ERR_DAO + e.getMessage());
		}	
	}
	
	public Taller altaTaller (Taller taller) 
		throws RemoteException, GestorConexionException {
		try {
			//gestorConexionDAO.insertTaller(taller);		
			return gestorConexionDAO.getTallerByCif(taller.getCif());
		} catch (DAOException e) {
			throw new GestorConexionException(GestorConexionException.ERR_DAO + e.getMessage());
		}
	}
	
	public Taller modificarTaller (Taller taller) 
		throws RemoteException, GestorConexionException {
		try {
			gestorConexionDAO.updateTaller(taller);
			return gestorConexionDAO.getTallerById(taller.getId());
		} catch (DAOException e) {
			throw new GestorConexionException(GestorConexionException.ERR_DAO + e.getMessage());
		}
	}
	

}