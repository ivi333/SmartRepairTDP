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
				if (!(usuari.getContrasenya().equals(contrasenya))) {
					usuari = null;
					throw new GestorConexionException(GestorConexionException.ERR_USER_INVALID);
				} else {
					if (!usuari.isActiu()) {
						usuari = null;
						throw new GestorConexionException(GestorConexionException.ERR_USER_DISABLED);
					}					
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
	
	public List<Usuari> getUsuarisCapTallerDisponibles (int idTaller) throws RemoteException, GestorConexionException {
		try {
			return gestorConexionDAO.getUsuarisCapTallerDisponbiles(idTaller);
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

		boolean isJefeTaller = false, isMecanico = false;
		Usuari newUsuari = null;

		String perfiles [] = usuari.getPerfil().split(";");
		try {
			if (!gestorConexionDAO.usuariExist(usuari.getNif(),usuari.getUsuari())) {		
				if (usuari.isActiu()) {
					Taller taller = gestorConexionDAO.getTallerById(usuari.getTaller());
					
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
						isMecanico = true;
					} else if (perfil.equals(PerfilUsuari.JefeTaller.toString())) {
						isJefeTaller = true;
					}
				}
				
				if (isMecanico || isJefeTaller) {
					newUsuari = getUsuariByUsuari(usuari.getUsuari());
				}
				if (isMecanico) {
										
					gestorConexionDAO.altaMecanic(new Mecanic(newUsuari.getId(), newUsuari.isActiu(), 0, 0));
				}
				
				if ((isJefeTaller) && (usuari.getTaller()!=0)) {
					Taller taller = gestorConexionDAO.getTallerById(usuari.getTaller());
					if (taller.getCapTaller() == 0){
						taller.setCapTaller(newUsuari.getId());
						gestorConexionDAO.modificarTaller(taller);
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
			boolean mecanic = false;
			boolean jefeTaller = false;
			boolean updTaller = false;
			Taller taller = null;
			
			Usuari usuariOld = gestorConexionDAO.getUsuariById(usuari.getId());
			String perfiles [] = usuariOld.getPerfil().split(";");
			
			for (String perfil : perfiles) {
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

			if (jefeTaller)  {
				taller = gestorConexionDAO.getTallerById(usuari.getTaller());	
				if (taller != null) {
					if (usuari.isActiu()){								
						if (usuari.getId() != taller.getCapTaller()) {
							throw new GestorConexionException(GestorConexionException.ERR_JEFETALLER_TALLER);
						}
					} else {
						if (taller.getCapTaller() == 0) {
							taller.setCapTaller(usuari.getId());
							updTaller = true;
						}
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
				
				if (updTaller) {
					gestorConexionDAO.modificarTaller(taller);
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
	
	public void altaTaller(Taller taller) throws RemoteException,
			GestorConexionException {
		try {

			if (((taller.isActiu()) && (gestorConexionDAO
					.getTallersByCapTaller(taller.getCapTaller()) == null))
					|| !(taller.isActiu())) {
				gestorConexionDAO.altaTaller(taller);
				if (taller.isActiu()){
					Usuari usuari = gestorConexionDAO.getUsuariById(taller.getCapTaller());
					usuari.setTaller(taller.getId());
					gestorConexionDAO.modificarUsuari(usuari);
				}
			} else {
				throw new GestorConexionException(
						GestorConexionException.ERR_JEFETALLER_ASIGN);
			}

		} catch (DAOException e) {
			throw new GestorConexionException(GestorConexionException.ERR_DAO
					+ e.getMessage());
		}
	}
	
	public void modificarTaller(Taller taller) throws RemoteException,
			GestorConexionException {
		try {			
			Usuari usuari = null;
			Taller oldTaller = gestorConexionDAO.getTallerById(taller.getId());
			if (!oldTaller.isActiu() && taller.isActiu()) {
				usuari = gestorConexionDAO.getUsuariById(taller.getCapTaller());
				
				if (usuari.getTaller() != taller.getId() && usuari.getTaller()!=0) {
					throw new GestorConexionException(GestorConexionException.ERR_JEFETALLER_TALLER);
				} else if (!usuari.isActiu()) {
					throw new GestorConexionException(GestorConexionException.ERR_JEFETALLER_INACTIU);
				}
//				usuari.setTaller(taller.getId());				
//				gestorConexionDAO.modificarUsuari(usuari);
//				String perfiles[] = usuari.getPerfil().split(";");
//				for (String perfil : perfiles){
//					if (perfil.equals(PerfilUsuari.Mecanico.toString())){
//						gestorConexionDAO.estadoMecanic(usuari.getId(), true);
//					}
//				}
				gestorConexionDAO.modificarTaller(taller);

			} else {
				gestorConexionDAO.modificarTaller(taller);
			}
			
		} catch (DAOException e) {
			throw new GestorConexionException(GestorConexionException.ERR_DAO
					+ e.getMessage());
		}
	}
	
	public void disableTaller (Taller taller) throws RemoteException, GestorConexionException {
		try {
			
			int reparaciones = gestorConexionDAO.getNumRepPendTaller(taller.getId());
						
			if (reparaciones == 0) {
//				Taller oldTaller = gestorConexionDAO.getTallerById(taller.getId());
//				Usuari usuari = gestorConexionDAO.getUsuariById(oldTaller.getCapTaller());
//				String perfiles [] = usuari.getPerfil().split(";");
//				usuari.setTaller(0);				
//				gestorConexionDAO.modificarUsuari(usuari);
//				for (String perfil : perfiles){
//					if (perfil.equals(PerfilUsuari.Mecanico.toString())) {
//						gestorConexionDAO.estadoMecanic (usuari.getId(), false);
//						break;
//					}
//				}
				gestorConexionDAO.disableTaller(taller.getId());				
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