package edu.uoc.tdp.pac4.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.uoc.tdp.pac4.beans.DetallReparacio;
import edu.uoc.tdp.pac4.beans.Reparacio;
import edu.uoc.tdp.pac4.beans.Mecanic;
import edu.uoc.tdp.pac4.beans.Stockpeca;
import edu.uoc.tdp.pac4.beans.Peca;
import edu.uoc.tdp.pac4.exception.DAOException;
import edu.uoc.tdp.pac4.exception.GestorReparacionException;

/**
 * Smart Repair 
 * ETIG - TDP PAC 4 Primavera 2013
 * Grup: FiveCoreDumped
 */
public interface GestorReparacionInterface extends Remote{
	
	/**
	 * Obtiene todos las Reparciones
	 * @return
	 * @throws GestorPreusServiceException
	 */
	public List<Reparacio> getReparaciones()throws RemoteException, GestorReparacionException;
	
	/**
	 * Obtiene una Reparación a dado un id de Orden de Reparación
	 * @param ordenReparacion
	 * @return
	 * @throws GestorReparacionException
	 */
	public Reparacio getReparacion(int ordenReparacion) throws RemoteException, GestorReparacionException;
	
	/**
	 * Obtiene todos los Mecánicos
	 * @return
	 * @throws GestorReparacionException
	 */
	public List<Mecanic> getMecanicos() throws RemoteException, GestorReparacionException;
	
	/**
	 * Obtiene un Mecánico dada un id de Mecánico
	 * @param idMecanico
	 * @return
	 * @throws GestorReparacionException
	 */
	public Mecanic getMecanico(int idMecanico)throws RemoteException, GestorReparacionException;
	
	/**
	 * Obtiene todo el Stock de Piezas
	 * @return
	 * @throws GestorReparacionException
	 */
	public List<Stockpeca> getStockPiezas() throws RemoteException, GestorReparacionException;
	
	/**
	 * Obtiene todos los tipos de Piezas
	 * @return
	 * @throws GestorReparacionException
	 */
	public List<Peca> getPiezas() throws RemoteException, GestorReparacionException;
	
	/**
	 * Obtiene una Pieza dado el id de la Pieza
	 * @param idPieza
	 * @return
	 * @throws GestorReparacionException
	 */
	public Peca getPieza(int idPieza) throws RemoteException, GestorReparacionException;
	
	/**
	 * Obtiene todos los detalles de reparaciones
	 * @return
	 * @throws GestorReparacionException
	 */
	public List<DetallReparacio> getDetalleReparaciones() throws RemoteException, GestorReparacionException;
	
	
}





