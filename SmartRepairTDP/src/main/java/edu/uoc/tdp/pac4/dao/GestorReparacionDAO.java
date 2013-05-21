package edu.uoc.tdp.pac4.dao;

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
import edu.uoc.tdp.pac4.exception.DAOException;
import edu.uoc.tdp.pac4.exception.GestorReparacionException;




/**
 * Clase abstracta que gestiona las operaciones basicas de conexion con postgres
 * Smart Repair 
 * ETIG - TDP PAC 4 Primavera 2013
 * Grup: FiveCoreDumped
 */
public interface GestorReparacionDAO {
	
	/**
	 * Obtiene todos las Reparciones
	 * @return
	 * @throws GestorPreusServiceException
	 */
	public List<Reparacio> getReparaciones()throws DAOException;
	
	/**
	 * Obtiene una Reparación a dado un id de Orden de Reparación
	 * @param ordenReparacion
	 * @return
	 * @throws GestorReparacionException
	 */
	public Reparacio getReparacion(int ordenReparacion) throws DAOException;
	
	/**
	 * Obtiene todos los Mecánicos
	 * @return
	 * @throws GestorReparacionException
	 */
	public List<Mecanic> getMecanicos() throws DAOException;
	
	/**
	 * Obtiene un Mecánico dada un id de Mecánico
	 * @param idMecanico
	 * @return
	 * @throws GestorReparacionException
	 */
	public Mecanic getMecanico(int idMecanico)throws DAOException;
	
	/**
	 * Obtiene todo el Stock de Piezas
	 * @return
	 * @throws GestorReparacionException
	 */
	public List<Stockpeca> getStockPiezas() throws DAOException;
	
	/**
	 * Obtiene todos los tipos de Piezas
	 * @return
	 * @throws GestorReparacionException
	 */
	public List<Peca> getPiezas() throws DAOException;
	
	/**
	 * Obtiene una Pieza dado el id de la Pieza
	 * @return
	 * @throws GestorReparacionException
	 */
	public Peca getPieza(int idPieza) throws DAOException;
	
	/**
	 * Obtiene todos los detalles de reserva
	 * @return
	 * @throws GestorReparacionException
	 */
	public List<DetallReparacio> getDetalleReparaciones() throws DAOException;
	
	/**
	 * Obtiene un detalle de reparación dado una orden de reparación
	 * @return
	 * @throws GestorReparacionException
	 */
	public DetallReparacio getDetalleReparacion(int ordenReparacion) throws DAOException;
	
	/**
	 * Marca como finalizada una reparación
	 * @return
	 * @throws GestorReparacionException
	 */
	public void setReparacionFinalizada(int ordenReparacion) throws DAOException;
	
	/**
	 * Obtiene una lista con todas las piezas asignadas a una reparación.
	 * @return
	 * @throws GestorReparacionException
	 */
	public List<DetallPeca> getPiezasReparacion(int ordenReparacion) throws DAOException;
	
	/**
	 * Obtiene una lista con todos los detalles de las piezas. Se puede filtrar por nombre.
	 * @return
	 * @throws GestorReparacionException
	 */
	public List<DetallPeca> getDetallePiezas(String nombrePieza) throws DAOException;
	
	/**
	 * Obtiene un Usuario dado un id de Usuario
	 * @param idUsuario
	 * @return
	 * @throws GestorReparacionException
	 */
	public Usuari getUsuario(int idUsuario)throws DAOException;
	
	/**
	 * Inserta una pieza a la comanda
	 * @param estado, codigoPieza, idTaller, ordenReparacion, cantidad
	 * @return
	 * @throws GestorReparacionException
	 */
	public void setPiezaComanda(boolean estado, int codigoPieza, int idTaller, int ordenReparacion, int cantidad)
			throws DAOException;
	
	/**
	 * Elimina una pieza a la comanda
	 * @param codigoPieza, ordenReparación
	 * @return
	 * @throws GestorReparacionException
	 */
	public void deletePiezaComanda(int codigoPieza, int ordenReparacion)
			throws DAOException;
	
	/**
	 * Obtiene una lista con todos los detalles de las piezas de un taller.
	 * @param idTaller, nombrePieza
	 * @return
	 * @throws GestorReparacionException
	 */
	public List<DetallPeca> getDetallePiezasTaller(int idTaller, String nombrePieza) throws DAOException;
	
	/**
	 * Descuenta stock de una pieza
	 * @param codigoPieza, cantidad
	 * @return
	 * @throws GestorReparacionException
	 */
	public void setDescontarStock(int codigoPieza, int cantidad) throws DAOException;
	
	/**
	 * Marca una reparación como aceptada
	 * @param ordenReparacion
	 * @return
	 * @throws GestorReparacionException
	 */
	public void setReparacionAceptada(int ordenReparacion) throws DAOException;
	
	/**
	 * Marca una reparación como asignada
	 * @param ordenReparacion
	 * @return
	 * @throws GestorReparacionException
	 */
	public void setReparacionAsignada(int ordenReparacion) throws DAOException;
	

}
