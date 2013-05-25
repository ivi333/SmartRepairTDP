package edu.uoc.tdp.pac4.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import edu.uoc.tdp.pac4.beans.DetallPeca;
import edu.uoc.tdp.pac4.beans.DetallReparacio;
import edu.uoc.tdp.pac4.beans.Reparacio;
import edu.uoc.tdp.pac4.beans.Mecanic;
import edu.uoc.tdp.pac4.beans.Stockpeca;
import edu.uoc.tdp.pac4.beans.Peca;
import edu.uoc.tdp.pac4.beans.Usuari;
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
	
	/**
	 * Obtiene un detalle de reparación dada una orden de reparación
	 * @param ordenReparacion
	 * @return
	 * @throws GestorReparacionException
	 */
	public DetallReparacio getDetalleReparacion(int ordenReparacion) throws RemoteException, GestorReparacionException;
	
	
	/**
	 * Marca como finalizada una reparación
	 * @param ordreReparacion
	 * @return
	 * @throws GestorReparacionException
	 */
	public void setReparacionFinalizada(int ordenReparacion) throws RemoteException, GestorReparacionException;
	
	/**
	 * Obtiene el detalle de las piezas de una reparación.
	 * @param ordenReparacion
	 * @return
	 * @throws GestorReparacionException
	 */
	public List<DetallPeca> getPiezasReparacion(int ordenReparacion) throws RemoteException, GestorReparacionException;
	
	/**
	 * Obtiene una lista con todos los detalles de las piezas. Se puede filtrar por nombre.
	 * @param nombrePieza
	 * @return
	 * @throws GestorReparacionException
	 */
	public List<DetallPeca> getDetallePiezas(String nombrePieza) throws RemoteException, GestorReparacionException;
	
	/**
	 * Obtiene un Usuario dado un id de Usuario
	 * @param idUsuario
	 * @return
	 * @throws GestorReparacionException
	 */
	public Usuari getUsuario(int idUsuario)throws RemoteException, GestorReparacionException;
	
	/**
	 * Inserta una pieza a la comanda
	 * @param estado, codigoPieza, idTaller, ordenReparacion, cantidad, tipoReparacion
	 * @return
	 * @throws GestorReparacionException
	 */
	public void setPiezaComanda(boolean estado, int codigoPieza, int idTaller, int ordenReparacion, int cantidad, boolean tipoReparacion)
			throws RemoteException, GestorReparacionException;
	
	/**
	 * Elimina una pieza a la comanda
	 * @param codigoPieza, ordenReparación
	 * @return
	 * @throws GestorReparacionException
	 */
	public void deletePiezaComanda(int codigoPieza, int ordenReparacion) 
			throws RemoteException, GestorReparacionException;


	/**
	 * Obtiene una lista con todos los detalles de las piezas de un taller.
	 * @param idTaller, nombrePieza
	 * @return
	 * @throws GestorReparacionException
	 */
	public List<DetallPeca> getDetallePiezasTaller(int idTaller, String nombrePieza)
			throws RemoteException, GestorReparacionException;
	
	/**
	 * Descuenta stock de una pieza
	 * @param codigoPieza, cantidad
	 * @return
	 * @throws GestorReparacionException
	 */
	public void setDescontarStock(int codigoPieza, int cantidad) throws RemoteException, GestorReparacionException;

	/**
	 * Marca una reparación como aceptada
	 * @param ordenReparacion
	 * @return
	 * @throws GestorReparacionException
	 */
	public void setReparacionAceptada(int ordenReparacion) throws RemoteException, GestorReparacionException;
	
	/**
	 * Marca una reparación como asignada
	 * @param ordenReparacion
	 * @return
	 * @throws GestorReparacionException
	 */
	public void setReparacionAsignada(int ordenReparacion) throws RemoteException, GestorReparacionException;
	
	/**
	 * Obtiene una lista de todos los usuarios de un determinado tipo.
	 * @param tipo, nombre
	 * @return
	 * @throws GestorReparacionException
	 */
	public List<Usuari> getUsuarios(String tipo, String nombre) throws RemoteException, GestorReparacionException;

	/**
	 * Asigna el número de reparaciones de un usuario.
	 * @param idMecanico, ordenReparacion
	 * @return
	 * @throws GestorReparacionException
	 */
	public void asignarUsuarioNumeroReparacion(int idMecanico, int numeroReparaciones) throws RemoteException, GestorReparacionException;

	/**
	 * Asigna reparaciones de un mecánico
	 * @param idMecanico, ordenReparacion1, ordenReparacion2
	 * @return
	 * @throws GestorReparacionException
	 */
	public void asignarReparacionesMecanico(int idMecanico, int ordenReparacion1, int ordenReparacion2) throws RemoteException, GestorReparacionException;
	
	/**
	 * Asigna un mecánico a la reparación
	 * @param idMecanico, ordenReparacion1
	 * @return
	 * @throws GestorReparacionException
	 */
	public void asignarMecanicoReparacion(int idMecanico, int ordenReparacion) throws RemoteException, GestorReparacionException;
	
	/**
	 * Obtiene todos los detalles de las reparaciones de un mecánico
	 * @param idMecanico
	 * @return
	 * @throws GestorReparacionException
	 */
	public List<DetallReparacio> getDetalleReparacionesMecanico(int idMecanico) throws RemoteException, GestorReparacionException;

	/**
	 * Obtiene todos los detalles de las reparaciones de un mecánico dependiendo de los filtros
	 * @param idMecanico, idFiltro, valor
	 * @return
	 * @throws GestorReparacionException
	 */
	public List<DetallReparacio> getDetalleReparacionesMecanicoFiltro(int idMecanico, int idFiltro, String valor) throws RemoteException, GestorReparacionException;
	
	/**
	 * Obtiene el stock mínimo dada una pieza
	 * @param idPieza
	 * @return
	 * @throws GestorReparacionException
	 */
	public int getStockMinimoPieza(int idPieza) throws RemoteException, GestorReparacionException;
	
	/**
	 * Obtiene todos los detalles de la reparación aplicando unos filtros
	 * @param idFiltro, valor, nombre, apellido
	 * @return
	 * @throws GestorReparacionException
	 */
	public List<DetallReparacio> getDetalleReparacionesFiltro(
			int idFiltro, String valor, String nombre, String apellido) throws RemoteException, GestorReparacionException;

	
	/**
	 * Obtiene la marca del coche de una pieza
	 * @param idPieza
	 * @return
	 * @throws GestorReparacionException
	 */
	public String getMarcaPieza(int idPieza) throws RemoteException, GestorReparacionException;
	
	/**
	 * Obtiene el modelo del coche de una pieza
	 * @param idPieza
	 * @return
	 * @throws GestorReparacionException
	 */
	public String getModeloPieza(int idPieza) throws RemoteException, GestorReparacionException;
	
	/**
	 * Obtiene una lista con todos los detalles de las piezas de un taller dado un filtro
	 * @param idTaller, filtro, valor
	 * @return
	 * @throws GestorReparacionException
	 */
	public List<DetallPeca> getDetallePiezasTallerFiltro(int idTaller, int filtro, String valor) 
			throws RemoteException, GestorReparacionException;
	
	/**
	 * Actualiza la hora de unicio de una reparación
	 * @param ordenReparacion
	 * @return
	 * @throws GestorReparacionException
	 */
	public void setHoraInicioReparacion(int ordenReparacion) throws RemoteException, GestorReparacionException;
	
	/**
	 * Actualiza la hora de finalización de una reparación
	 * @param ordenReparacion
	 * @return
	 * @throws GestorReparacionException
	 */
	public void setHoraFinReparacion(int ordenReparacion) throws RemoteException, GestorReparacionException;

	/**
	 * Recupera la hora de unicio de una reparación
	 * @param ordenReparacion
	 * @return
	 * @throws GestorReparacionException
	 */
	public Date getHoraInicioReparacion(int ordenReparacion) throws RemoteException, GestorReparacionException;
	
	/**
	 * Recupera la hora de finalización de una reparación
	 * @param ordenReparacion
	 * @return
	 * @throws GestorReparacionException
	 */
	public Date getHoraFinReparacion(int ordenReparacion) throws RemoteException, GestorReparacionException;
}





