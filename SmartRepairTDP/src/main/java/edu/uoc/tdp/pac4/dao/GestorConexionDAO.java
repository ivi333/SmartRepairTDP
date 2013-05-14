package edu.uoc.tdp.pac4.dao;

import java.util.List;

import edu.uoc.tdp.pac4.beans.Taller;
import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.exception.DAOException;


/**
 * Clase abstracta que gestiona las operaciones basicas de conexion con postgres
 * Smart Repair 
 * ETIG - TDP PAC 4 Primavera 2013
 * Grup: FiveCoreDumped
 */
public interface GestorConexionDAO {
	/**
	 * Definir las interficies de base de datos
	 * para el subsistema de Conexion y Mantenimiento 
	 */
	
	public Usuari getUsuariById (int id)
		throws DAOException;
	
	public Usuari getUsuariByUsuari (String usuari)
		throws DAOException;
	
	public List<Usuari> getAllUsuaris ()
		throws DAOException;
	
	public List<Usuari> getUsuarisByFilter (String id, String nif, String nombre, String apellidos, String perfil)
			throws DAOException;
	
	public List<Usuari> getUsuarisCapTaller ()
		throws DAOException;
	
	public void disableUser (int id)
		throws DAOException;
	
	public Taller getTallerById (int id)
		throws DAOException;
	
	public List<Taller> getAllTallers ()
		throws DAOException;
	
	public List<Taller>getTallersByFilter (String id, String cif, String adreca, String capacitat, String idCapTaller)
		throws DAOException;
}
