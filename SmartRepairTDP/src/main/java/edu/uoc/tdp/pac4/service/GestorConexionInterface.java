package edu.uoc.tdp.pac4.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.uoc.tdp.pac4.beans.Taller;
import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.exception.GestorConexionException;

/**
 * Smart Repair 
 * ETIG - TDP PAC 4 Primavera 2013
 * Grup: FiveCoreDumped
 */
public interface GestorConexionInterface extends Remote{
	/**
	 * Definir las interficies de servicios RMI
	 * para el subsistema de Conexion y Mantenimiento
	 */

	public Usuari doLogin (String username, String contrasenya) 
		throws RemoteException, GestorConexionException;
	
	public void changePassword (Usuari usuari, String password)
		throws RemoteException, GestorConexionException;
	
	public Usuari getUsuariById (int id)
		throws RemoteException, GestorConexionException;
	
	public Usuari getUsuariByUsuari (String username)
		throws RemoteException, GestorConexionException;
	
	public Usuari getUsuariByNif (String nif)
			throws RemoteException, GestorConexionException;
	
	public List<Usuari> getAllUsuaris ()
		throws RemoteException, GestorConexionException;
	
	public List<Usuari> getUsuarisByFilter (String id, String nif, String nombre, String apellidos, String perfil)
			throws RemoteException, GestorConexionException;

	public List<Usuari> getUsuarisCapTaller () 
		throws RemoteException, GestorConexionException;
	
	public void disableUser(int idUsuari)
		throws RemoteException, GestorConexionException;
	
	public void altaUsuari(Usuari usuari)
		throws RemoteException, GestorConexionException;
	
	public void modificarUsuari(Usuari usuari)
			throws RemoteException, GestorConexionException;
	
	public List<Taller> getAllTallers ()
		throws RemoteException, GestorConexionException;
	
	public Taller getTallerById (int id) 
		throws RemoteException, GestorConexionException;
	
	public List<Taller> getTallersByFilter (String id, String cif, String adreca, String capacitat, String idCapTaller)
		throws RemoteException, GestorConexionException;
	
	public Taller altaTaller (Taller taller)
		throws RemoteException, GestorConexionException;
	
	public Taller modificarTaller  (Taller taller)
		throws RemoteException, GestorConexionException;
}

