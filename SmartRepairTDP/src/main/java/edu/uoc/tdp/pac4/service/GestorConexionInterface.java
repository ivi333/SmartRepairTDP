package edu.uoc.tdp.pac4.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

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
	
	public Usuari getUsuariByUsuari (String username)
		throws RemoteException, GestorConexionException;
	
	public List<Usuari> getAllUsuaris ()
		throws RemoteException, GestorConexionException;
	
	public List<Usuari> getUsuarisByFilter (String id, String nif, String nombre, String apellidos, String perfil)
			throws RemoteException, GestorConexionException;
				
}

