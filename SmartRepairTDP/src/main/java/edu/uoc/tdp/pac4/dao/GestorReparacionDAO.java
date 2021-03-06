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
	 * Obtiene todos los detalles de la reparación
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
	 * @param estado, codigoPieza, idTaller, ordenReparacion, cantidad, tipoReparacion
	 * @return
	 * @throws GestorReparacionException
	 */
	public void setPiezaComanda(boolean estado, int codigoPieza, int idTaller, int ordenReparacion, int cantidad, boolean tipoReparacion)
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
	
	/**
	 * Obtiene una lista de todos los usuarios de un determinado tipo.
	 * @param tipo, nombre
	 * @return
	 * @throws GestorReparacionException
	 */
	public List<Usuari> getUsuarios(String tipo, String nombre) throws DAOException;
	
	/**
	 * Asigna el número de reparaciones de un usuario.
	 * @param idMecanico, ordenReparacion
	 * @return
	 * @throws GestorReparacionException
	 */
	public void asignarUsuarioNumeroReparacion(int idMecanico, int numeroReparaciones) throws DAOException;
	
	/**
	 * Asigna reparaciones de un mecánico
	 * @param idMecanico, ordenReparacion1, ordenReparacion2
	 * @return
	 * @throws GestorReparacionException
	 */
	public void asignarReparacionesMecanico(int idMecanico, int ordenReparacion1, int ordenReparacion2) throws DAOException;
	
	/**
	 * Asigna un mecánico a la reparación
	 * @param idMecanico, ordenReparacion1
	 * @return
	 * @throws GestorReparacionException
	 */
	public void asignarMecanicoReparacion(int idMecanico, int ordenReparacion) throws DAOException;
	
	/**
	 * Obtiene todos los detalles de las reparaciones de un mecánico
	 * @param idMecanico
	 * @return
	 * @throws GestorReparacionException
	 */
	public List<DetallReparacio> getDetalleReparacionesMecanico(int idMecanico) throws DAOException;
	
	/**
	 * Obtiene todos los detalles de las reparaciones de un mecánico dependiendo de los filtros
	 * @param idMecanico, idFiltro, valor
	 * @return
	 * @throws GestorReparacionException
	 */
	public List<DetallReparacio> getDetalleReparacionesMecanicoFiltro(int idMecanico, int idFiltro, String valor) throws DAOException;
	
	/**
	 * Obtiene el stock mínimo dada una pieza
	 * @param idPieza
	 * @return
	 * @throws GestorReparacionException
	 */
	public int getStockMinimoPieza(int idPieza) throws DAOException;
	
	/**
	 * Obtiene todos los detalles de la reparación aplicando unos filtros
	 * @param idFiltro, valor, nombre, apellido, fechaDe, fechaHasta
	 * @return
	 * @throws GestorReparacionException
	 */
	public List<DetallReparacio> getDetalleReparacionesFiltro(
			int idFiltro, String valor, String nombre, String apellido, 
			String fechaDe, String fechaHasta) throws DAOException;
	
	/**
	 * Obtiene la marca del coche de una pieza
	 * @param idPieza
	 * @return
	 * @throws GestorReparacionException
	 */
	public String getMarcaPieza(int idPieza) throws DAOException;
	
	/**
	 * Obtiene el modelo del coche de una pieza
	 * @param idPieza
	 * @return
	 * @throws GestorReparacionException
	 */
	public String getModeloPieza(int idPieza) throws DAOException;
	
	/**
	 * Obtiene una lista con todos los detalles de las piezas de un taller dado un filtro
	 * @param idTaller, filtro, valor
	 * @return
	 * @throws GestorReparacionException
	 */
	public List<DetallPeca> getDetallePiezasTallerFiltro(int idTaller, int filtro, String valor) throws DAOException;
	
	/**
	 * Actualiza la hora de unicio de una reparación
	 * @param ordenReparacion
	 * @return
	 * @throws GestorReparacionException
	 */
	public void setHoraInicioReparacion(int ordenReparacion) throws DAOException;
	
	/**
	 * Actualiza la hora de finalización de una reparación
	 * @param ordenReparacion
	 * @return
	 * @throws GestorReparacionException
	 */
	public void setHoraFinReparacion(int ordenReparacion) throws DAOException;
	
	/**
	 * Recupera la hora de unicio de una reparación
	 * @param ordenReparacion
	 * @return
	 * @throws GestorReparacionException
	 */
	public Date getHoraInicioReparacion(int ordenReparacion) throws DAOException;
	
	/**
	 * Recupera la hora de finalización de una reparación
	 * @param ordenReparacion
	 * @return
	 * @throws GestorReparacionException
	 */
	public Date getHoraFinReparacion(int ordenReparacion) throws DAOException;
	
	/**
	 * Recupera el número de comandas pendiente de recepcionar dada una reparación
	 * @param ordenReparacion
	 * @return
	 * @throws GestorReparacionException
	 */
	public int getNumComandasPendientes(int ordenReparacion) throws DAOException;
	
	/**
	 * Inserta el valor del contador de la reparación (en minutos)
	 * @param ordenReparacion, contador
	 * @return
	 * @throws GestorReparacionException
	 */
	public void setContadorReparacion(int ordenReparacion, double contador) throws DAOException;
	
}
