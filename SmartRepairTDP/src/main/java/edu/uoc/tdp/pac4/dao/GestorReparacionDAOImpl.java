package edu.uoc.tdp.pac4.dao;

import java.util.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import edu.uoc.tdp.pac4.beans.DetallReparacio;
import edu.uoc.tdp.pac4.beans.Mecanic;
import edu.uoc.tdp.pac4.beans.Peca;
import edu.uoc.tdp.pac4.beans.Reparacio;
import edu.uoc.tdp.pac4.beans.Stockpeca;
import edu.uoc.tdp.pac4.beans.Taller;
import edu.uoc.tdp.pac4.beans.TipusReparacio;
import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.exception.DAOException;


/**
 * Smart Repair 
 * ETIG - TDP PAC 4 Primavera 2013
 * Grup: FiveCoreDumped
 */
public class GestorReparacionDAOImpl extends ConnectionPostgressDB implements GestorReparacionDAO {

	/**
	 * Implementar las operaciones de base de datos
	 */
	
	public static final String QUERY_GET_REPARACIONES = "SELECT * FROM REPARACIO";
	public static final String QUERY_GET_REPARACION = "SELECT * FROM REPARACIO WHERE ORDREREPARACIO = ?";
	public static final String QUERY_GET_MECANICOS = "SELECT * FROM MECANIC";
	public static final String QUERY_GET_MECANICO = "SELECT * FROM MECANIC WHERE IDMECANIC = ?";
	public static final String QUERY_GET_STOCKPIEZAS = "SELECT * FROM STOCKPECA";
	public static final String QUERY_GET_PIEZAS = "SELECT * FROM PECA";
	public static final String QUERY_GET_PIEZA = "SELECT * FROM PECA WHERE CODIPECA = ?";
	public static final String QUERY_GET_DETALLE_REPARACIONES = "select rep.ordrereparacio, sol.dataalta, rep.comptador, cli.matricula, " +
															    "cli.marca, cli.model, rep.observacions, rep.acceptada, rep.assignada " +
															    "from solicitud sol " +
															    "inner join reparacio rep on sol.numreparacio = rep.ordrereparacio " +
															    "inner join client cli on sol.client = cli.nif " +
															    "where sol.finalitzada = false";
	
	
	public GestorReparacionDAOImpl() {
			super();
	}


	public List<Reparacio> getReparaciones() throws DAOException {
		List<Reparacio> result = new LinkedList<Reparacio>();
		getConnectionDB();
		
		Statement stm = createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		try {
			rs = stm.executeQuery(QUERY_GET_REPARACIONES);
			while (rs.next()){
				result.add(new Reparacio(rs.getInt(1), rs.getInt(2), rs.getBoolean(3), rs.getInt(4), rs.getBoolean(5), rs.getDouble(6), rs.getString(7), rs.getInt(8), rs.getDate(9), rs.getDate(10), rs.getDate(11)));
			}
			
			return result;
		} catch (SQLException e) {
			throw new DAOException(DAOException.ERR_SQL, e.getMessage(),  e);
		} finally {
			if (stm!=null) {
				try {stm.close();
				} catch (SQLException e) {
					throw new DAOException(DAOException.ERR_RESOURCE_CLOSED, e.getMessage(), e);
				}
			}
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new DAOException(DAOException.ERR_RESOURCE_CLOSED, e.getMessage(), e);
				}
			}
		}
	}


	public Reparacio getReparacion(int ordenReparacion) throws DAOException {
		getConnectionDB();
		Reparacio result = null;
		PreparedStatement ps = createPrepareStatment(QUERY_GET_REPARACION, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		try {
			ps.setLong(1, ordenReparacion);
			rs = ps.executeQuery();
			if (rs.next()){
				result = new Reparacio(rs.getInt(1), rs.getInt(2), rs.getBoolean(3), rs.getInt(4), rs.getBoolean(5), rs.getDouble(6), rs.getString(7), rs.getInt(8), rs.getDate(9), rs.getDate(10), rs.getDate(11));
			}
			return result;
		} catch (SQLException e) {
			throw new DAOException(DAOException.ERR_SQL, e.getMessage(),  e);
		} finally {
			if (ps!=null) {
				try {ps.close();
				} catch (SQLException e) {
					throw new DAOException(DAOException.ERR_RESOURCE_CLOSED, e.getMessage(), e);
				}
			}
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new DAOException(DAOException.ERR_RESOURCE_CLOSED, e.getMessage(), e);
				}
			}
		}		
	}


	public List<Mecanic> getMecanicos() throws DAOException {
		List<Mecanic> result = new LinkedList<Mecanic>();
		getConnectionDB();
		
		Statement stm = createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		try {
			rs = stm.executeQuery(QUERY_GET_MECANICOS);
			while (rs.next()){
				result.add(new Mecanic(rs.getInt(1), rs.getBoolean(2), rs.getInt(3), rs.getInt(4)));
			}
			return result;
		} catch (SQLException e) {
			throw new DAOException(DAOException.ERR_SQL, e.getMessage(),  e);
		} finally {
			if (stm!=null) {
				try {stm.close();
				} catch (SQLException e) {
					throw new DAOException(DAOException.ERR_RESOURCE_CLOSED, e.getMessage(), e);
				}
			}
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new DAOException(DAOException.ERR_RESOURCE_CLOSED, e.getMessage(), e);
				}
			}
		}
	}


	public Mecanic getMecanico(int idMecanico) throws DAOException {
		getConnectionDB();
		Mecanic result = null;
		PreparedStatement ps = createPrepareStatment(QUERY_GET_MECANICO, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		try {
			ps.setLong(1, idMecanico);
			rs = ps.executeQuery();
			if (rs.next()){
				result = new Mecanic(rs.getInt(1), rs.getBoolean(2), rs.getInt(3), rs.getInt(4));
			}
			return result;
		} catch (SQLException e) {
			throw new DAOException(DAOException.ERR_SQL, e.getMessage(),  e);
		} finally {
			if (ps!=null) {
				try {ps.close();
				} catch (SQLException e) {
					throw new DAOException(DAOException.ERR_RESOURCE_CLOSED, e.getMessage(), e);
				}
			}
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new DAOException(DAOException.ERR_RESOURCE_CLOSED, e.getMessage(), e);
				}
			}
		}		
	}


	public List<Stockpeca> getStockPiezas() throws DAOException {
		List<Stockpeca> result = new LinkedList<Stockpeca>();
		getConnectionDB();
		
		Statement stm = createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		try {
			rs = stm.executeQuery(QUERY_GET_STOCKPIEZAS);
			while (rs.next()){
				result.add(new Stockpeca(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)));
			}
			return result;
		} catch (SQLException e) {
			throw new DAOException(DAOException.ERR_SQL, e.getMessage(),  e);
		} finally {
			if (stm!=null) {
				try {stm.close();
				} catch (SQLException e) {
					throw new DAOException(DAOException.ERR_RESOURCE_CLOSED, e.getMessage(), e);
				}
			}
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new DAOException(DAOException.ERR_RESOURCE_CLOSED, e.getMessage(), e);
				}
			}
		}
	}


	public List<Peca> getPiezas() throws DAOException {
		List<Peca> result = new LinkedList<Peca>();
		getConnectionDB();
		
		Statement stm = createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		try {
			rs = stm.executeQuery(QUERY_GET_PIEZAS);
			while (rs.next()){
				result.add(new Peca(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6)));
			}
			return result;
		} catch (SQLException e) {
			throw new DAOException(DAOException.ERR_SQL, e.getMessage(),  e);
		} finally {
			if (stm!=null) {
				try {stm.close();
				} catch (SQLException e) {
					throw new DAOException(DAOException.ERR_RESOURCE_CLOSED, e.getMessage(), e);
				}
			}
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new DAOException(DAOException.ERR_RESOURCE_CLOSED, e.getMessage(), e);
				}
			}
		}
	}


	public Peca getPieza(int idPieza) throws DAOException {
		getConnectionDB();
		Peca result = null;
		PreparedStatement ps = createPrepareStatment(QUERY_GET_PIEZA, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		try {
			ps.setLong(1, idPieza);
			rs = ps.executeQuery();
			if (rs.next()){
				result = new Peca(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6));
			}
			return result;
		} catch (SQLException e) {
			throw new DAOException(DAOException.ERR_SQL, e.getMessage(),  e);
		} finally {
			if (ps!=null) {
				try {ps.close();
				} catch (SQLException e) {
					throw new DAOException(DAOException.ERR_RESOURCE_CLOSED, e.getMessage(), e);
				}
			}
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new DAOException(DAOException.ERR_RESOURCE_CLOSED, e.getMessage(), e);
				}
			}
		}		
	}


	public List<DetallReparacio> getDetalleReparaciones() throws DAOException {
		List<DetallReparacio> result = new LinkedList<DetallReparacio>();
		getConnectionDB();
		
		Statement stm = createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		try {
			rs = stm.executeQuery(QUERY_GET_DETALLE_REPARACIONES);
			while (rs.next()){
				result.add(new DetallReparacio(rs.getInt(1), rs.getDate(2), rs.getDouble(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getBoolean(9)));
			}
			return result;
		} catch (SQLException e) {
			throw new DAOException(DAOException.ERR_SQL, e.getMessage(),  e);
		} finally {
			if (stm!=null) {
				try {stm.close();
				} catch (SQLException e) {
					throw new DAOException(DAOException.ERR_RESOURCE_CLOSED, e.getMessage(), e);
				}
			}
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new DAOException(DAOException.ERR_RESOURCE_CLOSED, e.getMessage(), e);
				}
			}
		}
	}

}
