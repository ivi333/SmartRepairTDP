package edu.uoc.tdp.pac4.dao;

import java.util.List;

import edu.uoc.tdp.pac4.beans.Mecanic;
import edu.uoc.tdp.pac4.beans.Taller;
import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.exception.DAOException;

/**
 * Clase abstracta que gestiona las operaciones basicas de conexion con postgres
 * Smart Repair ETIG - TDP PAC 4 Primavera 2013 Grup: FiveCoreDumped
 */

public interface GestorConexionDAO {
	/**
	 * Definir las interficies de base de datos para el subsistema de Conexion y
	 * Mantenimiento
	 */

	/**
	 * Obtiene los datos de un usuario a partir de su identificador
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	public Usuari getUsuariById(int id) throws DAOException;

	/**
	 * Obtiene los datos de un usuario a partir de su código de usuario
	 * @param usuari
	 * @return
	 * @throws DAOException
	 */
	public Usuari getUsuariByUsuari(String usuari) throws DAOException;
	
	/**
	 * Obtiene los datos de un usuario a partir de su Nif
	 * @param nif
	 * @return
	 * @throws DAOException
	 */
	public Usuari getUsuariByNif(String nif) throws DAOException;

	/**
	 * Obtiene una lista de todos los usuarios.
	 * @return
	 * @throws DAOException
	 */
	public List<Usuari> getAllUsuaris() throws DAOException;

	/**
	 * Deshabilita un usuario
	 * @param id
	 * @throws DAOException
	 */
	public void disableUser(int id) throws DAOException;

	/**
	 * Obtiene una lista de usuarios segun los parámetros informados
	 * @param id
	 * @param nif
	 * @param nombre
	 * @param apellidos
	 * @param perfil
	 * @return
	 * @throws DAOException
	 */
	public List<Usuari> getUsuarisByFilter(String id, String nif,
			String nombre, String apellidos, String perfil) throws DAOException;
	
	/**
	 * Obtiene una lista con los usuarios que tiene el perfil de Jefe de Taller
	 * @return
	 * @throws DAOException
	 */
	public List<Usuari> getUsuarisCapTaller() throws DAOException;

	
	/**
	 * Obtiene una lista con los usuarios que tiene el perfil de Jefe de Taller
	 * que estan disponibles, no estan asignados a ningún taller
	 * @return
	 * @throws DAOException
	 */
	public List<Usuari> getUsuarisCapTallerDisponbiles(int idTaller) throws DAOException;
	
	/**
	 * Comprueba si el usuario ya existe al realizar una alta de usuario
	 * @param nif
	 * @param usuari
	 * @return
	 * @throws DAOException
	 */
	public boolean usuariExist (String nif, String usuari)
		throws DAOException;
	
	/**
	 * Comprueba si el usuario ya existe al realizar un modificacón
	 * @param nif
	 * @param usuari
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	public boolean usuariExist (String nif, String usuari, int id)
			throws DAOException;	
	
	/**
	 * Da de alta un nuevo usuario en el sistema
	 * @param usuari
	 * @throws DAOException
	 */
	public void altaUsuari (Usuari usuari)
		throws DAOException;
	
	/**
	 * Modifica los datos de un usuario
	 * @param usuari
	 * @throws DAOException
	 */
	public void modificarUsuari (Usuari usuari)
		throws DAOException;

	/**
	 * Modifica la password de un usuario
	 * @param usuari
	 * @param password
	 * @throws DAOException
	 */
	public void changePassword (Usuari usuari, String password)
		throws DAOException;
	
	
	/**
	 * Obtiene los datos de un mecanico
	 * @param idMecanic
	 * @return
	 * @throws DAOException
	 */
	/*public Mecanic getMecanicById (int idMecanic)
		throws DAOException;*/
	
	/**
	 * Da de alta un mecánico
	 * @param mecanic
	 * @throws DAOException
	 */
	public void altaMecanic (Mecanic mecanic)
		throws DAOException;
	
	
	/**
	 * Habilita/deshabilita un mecanico
	 * @param id
	 * @param disponible
	 * @throws DAOException
	 */
	public void estadoMecanic (int id, boolean disponible) throws DAOException;
		
	
	/**
	 * Obtiene los datos de un taller a partir de su identificador
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	public Taller getTallerById(int id) throws DAOException;
	
	/**
	 * Obtiene los datod de un taller a partir de su CIF
	 * @param cif
	 * @return
	 * @throws DAOException
	 */
	public Taller getTallerByCif(String cif) throws DAOException;

	/**
	 * Obtiene una lista de todos los talleres
	 * @return
	 * @throws DAOException
	 */
	public List<Taller> getAllTallers() throws DAOException;

	/**
	 * Obtiene una lista de todos los talleres segun los parametros informados
	 * @param id
	 * @param cif
	 * @param adreca
	 * @param capacitat
	 * @param idCapTaller
	 * @return
	 * @throws DAOException
	 */
	public List<Taller> getTallersByFilter(String id, String cif,
			String adreca, String capacitat, String idCapTaller)
			throws DAOException;
	
	/**
	 * Obtine el taller del cual el Usario es Jefe de Taller
	 * @param idUsuari
	 * @return
	 * @throws DAOException
	 */
	public Taller getTallersByCapTaller (int idUsuari)
		throws DAOException;
	
	/**
	 * Obtiene el taller del cual un Usuario es Jefe de Taller sin
	 * tener el cuenta el taller informado
	 * 
	 * @param idUsuari
	 * @param idTaller
	 * @return
	 * @throws DAOException
	 */
	public Taller getTallersByCapTaller (int idUsuari, int idTaller)
			throws DAOException;
	
	/**
	 * Da de alta un nuevo taller
	 * @param taller
	 * @throws DAOException
	 */
	public void altaTaller (Taller taller) throws DAOException;
	
	/**
	 * Modificar los datos de un taller
	 * @param taller
	 * @throws DAOException
	 */
	public void updateTaller (Taller taller) throws DAOException;

	public int getNumRepPendTaller (int idTaller) throws DAOException;
	
	public void disableTaller(int id) throws DAOException;
}
