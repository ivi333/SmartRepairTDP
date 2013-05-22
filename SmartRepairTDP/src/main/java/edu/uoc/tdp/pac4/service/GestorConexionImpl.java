package edu.uoc.tdp.pac4.service;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;


import edu.uoc.tdp.pac4.beans.Mecanic;
import edu.uoc.tdp.pac4.beans.PerfilUsuari;
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
	
	public void changePassword (Usuari usuari, String password)
			throws RemoteException, GestorConexionException {
		try {
			gestorConexionDAO.changePassword(usuari, password);
		} catch (DAOException e) {
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
	
	public void disableUser(int idUsuari) throws RemoteException,
			GestorConexionException {
		try {
			Usuari usuari = gestorConexionDAO.getUsuariById(idUsuari);
			boolean mecanic = false;
			String perfiles[] = usuari.getPerfil().split(";");
			for (String m : perfiles) {
				if (m.equals(PerfilUsuari.Mecanico.toString())) {
					mecanic = true;
					break;
				}
			}

			if (mecanic) {
				if (usuari.getReparacionsAssignades() == 0) {
					gestorConexionDAO.disableUser(idUsuari);
					gestorConexionDAO.estadoMecanic(idUsuari, false);
				} else {
					throw new GestorConexionException(
							GestorConexionException.ERR_USER_REPARACIONES);
				}
			} else {
				gestorConexionDAO.disableUser(idUsuari);
			}
		} catch (DAOException e) {
			throw new GestorConexionException(GestorConexionException.ERR_DAO
					+ e.getMessage());
		}
	}
	
	public void altaUsuari(Usuari usuari)
			throws RemoteException, GestorConexionException {
		String perfiles [] = usuari.getPerfil().split(";");
		try {
			if (!gestorConexionDAO.usuariExist(usuari.getNif(),usuari.getUsuari())) {		
				if (usuari.isActiu()) {
					Taller taller = gestorConexionDAO.getTallerById(usuari.getTaller());
					if (!taller.isActiu()) {
						throw new GestorConexionException(GestorConexionException.ERR_TALLER_INACTIU);
					}
					
					for (String perfil : perfiles){
						if (perfil.equals(PerfilUsuari.JefeTaller.toString())){
							if (usuari.getId() != taller.getCapTaller()) {
								throw new GestorConexionException(GestorConexionException.ERR_JEFETALLER_TALLER);
							}
							break;
						}
					}
				}
				gestorConexionDAO.altaUsuari(usuari);

				for (String perfil : perfiles) {
					if (perfil.equals(PerfilUsuari.Mecanico.toString())) {
						Usuari mecanic = getUsuariByUsuari(usuari.getUsuari());
						gestorConexionDAO.altaMecanic(new Mecanic(mecanic.getId(), mecanic.isActiu(), 0, 0));
						break;
					}
				}
					
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
			boolean administrador = false;
			boolean mecanic = false;
			boolean jefeTaller = false;
			String perfiles [] = usuariOld.getPerfil().split(";");
			
			for (String perfil : perfiles) {
				if (perfil.equals(PerfilUsuari.Administrador.toString())){
					administrador = true;
				}
				if (perfil.equals(PerfilUsuari.Mecanico.toString())){
					mecanic = true;
				}
				if (perfil.equals(PerfilUsuari.JefeTaller.toString())){
					jefeTaller = true;
				}

			}

			if (usuariOld.getReparacionsAssignades() > 0 && usuariOld.getTaller() != usuari.getTaller()){
				throw new GestorConexionException(GestorConexionException.ERR_USER_REP_TALLER);
			}
			if (usuari.isActiu()){
				if (!administrador) {
					Taller taller = gestorConexionDAO.getTallerById(usuari.getTaller());

					if (!taller.isActiu()){
						throw new GestorConexionException(GestorConexionException.ERR_TALLER_INACTIU);
					}
					
					if (jefeTaller && usuari.getId() != taller.getCapTaller()) {
						throw new GestorConexionException(GestorConexionException.ERR_JEFETALLER_TALLER);
					}
				}
			}
			
			if (!gestorConexionDAO.usuariExist(usuari.getNif(), usuari.getUsuari(), usuari.getId())){
				gestorConexionDAO.modificarUsuari(usuari);
				if (mecanic) {
					if (usuariOld.isActiu() != usuari.isActiu()) {
						gestorConexionDAO.estadoMecanic (usuari.getId(), usuari.isActiu());
					}
				}
					
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
	
	public Taller getTallerByCif (String cif) throws RemoteException, GestorConexionException {
		try {
			return gestorConexionDAO.getTallerByCif(cif);
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
	
	public Taller altaTaller(Taller taller) throws RemoteException,
			GestorConexionException {
		try {

			if (((taller.isActiu()) && (gestorConexionDAO
					.getTallersByCapTaller(taller.getCapTaller()) == null))
					|| !(taller.isActiu())) {

				return gestorConexionDAO.getTallerByCif(taller.getCif());
			} else {
				throw new GestorConexionException(
						GestorConexionException.ERR_JEFETALLER_ASIGN);
			}

		} catch (DAOException e) {
			throw new GestorConexionException(GestorConexionException.ERR_DAO
					+ e.getMessage());
		}
	}
	
	public Taller modificarTaller(Taller taller) throws RemoteException,
			GestorConexionException {
		try {
			if (((taller.isActiu()) && (gestorConexionDAO
					.getTallersByCapTaller(taller.getCapTaller(),
							taller.getId()) == null))
					|| !(taller.isActiu())) {
				gestorConexionDAO.updateTaller(taller);
				return gestorConexionDAO.getTallerById(taller.getId());
			} else {
				throw new GestorConexionException(
						GestorConexionException.ERR_JEFETALLER_ASIGN);
			}
		} catch (DAOException e) {
			throw new GestorConexionException(GestorConexionException.ERR_DAO
					+ e.getMessage());
		}
	}
	
	public void disableTaller (int idTaller) throws RemoteException, GestorConexionException {
		try {
			int reparaciones = gestorConexionDAO.getNumRepPendTaller(idTaller);
			
			if (reparaciones == 0) {
				//gestorConexionDAO.disableTaller(idTaller);				
			} else {
				throw new GestorConexionException(
						GestorConexionException.ERR_TALLER_REPARACIONES);
			}
		} catch (DAOException e) {
			throw new GestorConexionException(GestorConexionException.ERR_DAO
					+ e.getMessage());
		}
		
	}	

}