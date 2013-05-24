package edu.uoc.tdp.pac4.dao;

import java.util.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import edu.uoc.tdp.pac4.beans.DetallPeca;
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
															    "cli.marca, cli.model, rep.observacions, rep.acceptada, rep.assignada, rep.idmecanic, rep.dataassignacio, rep.datainici " +
															    "from solicitud sol " +
															    "inner join reparacio rep on sol.numreparacio = rep.ordrereparacio " +
															    "inner join client cli on sol.client = cli.nif " +
															    "where sol.finalitzada = false ";
	public static final String QUERY_GET_DETALLE_REPARACIONES_MECANICO = "select rep.ordrereparacio, sol.dataalta, rep.comptador, cli.matricula, " +
		    															 "cli.marca, cli.model, rep.observacions, rep.acceptada, rep.assignada, rep.idmecanic, rep.dataassignacio, rep.datainici " +
		    															 "from solicitud sol " +
		    															 "inner join reparacio rep on sol.numreparacio = rep.ordrereparacio " +
		    															 "inner join client cli on sol.client = cli.nif " +
		    															 "where sol.finalitzada = false ";
	public static final String QUERY_GET_DETALLE_REPARACION = "select rep.ordrereparacio, sol.dataalta, rep.comptador, cli.matricula, " +
		    												  "cli.marca, cli.model, rep.observacions, rep.acceptada, rep.assignada, rep.idmecanic, rep.dataassignacio, rep.datainici " +
		    												  "from solicitud sol " +
		    												  "inner join reparacio rep on sol.numreparacio = rep.ordrereparacio " +
		    												  "inner join client cli on sol.client = cli.nif " +
		    												  "where sol.numreparacio = ?";
	public static final String QUERY_SET_REPARACION_FINALIZADA = "update solicitud set finalitzada = true where numreparacio = ?";
	public static final String QUERY_GET_PIEZAS_REPARACION = "select com.codipeca, pec.descripcio, sto.stock, com.cantidad, pec.pvp, sto.stockminim, pec.marca, pec.model " +
															 "from comanda com " +
															 "inner join peca pec on com.codipeca = pec.codipeca " +
															 "inner join stockpeca sto on com.codipeca = sto.codipeca " +
															 "where com.ordrereparacio = ";
	public static final String QUERY_GET_DETALLE_PIEZAS = "select com.codipeca, pec.descripcio, sto.stock, com.cantidad, pec.pvp, sto.stockminim, pec.marca, pec.model " +
			 											  "from comanda com " +
			 											  "inner join peca pec on com.codipeca = pec.codipeca " +
			 											  "inner join stockpeca sto on com.codipeca = sto.codipeca " +
			 											  "where pec.descripcio like ";
	public static final String QUERY_GET_USUARIO = "select id, taller, usuari, perfil, nif, nom, cognoms, contrasenya, actiu, dataAlta, dataModificacio, dataBaixa, reparacionsAssignades from usuari where id = ?";
	public static final String QUERY_SET_PIEZA_COMANDA = "insert into comanda (estat,dataalta,codipeca,idcaptaller,idproveidor,ordrereparacio,tipusreparacio,cantidad) " +
														 "(select ? as estat, CURRENT_DATE as dataalta, ? as codipeca, " +
														 "(select id from usuari where perfil like '%JefeTaller%' and taller = ? limit 1) as idcaptaller, " +
														 "(select idproveidor from proveidorpeca where idpeca = ? limit 1) as idproveidor, " +
														 "? as ordrereparacio, ? as tipusreparacio, ? as cantidad)";
	public static final String QUERY_DELETE_PIEZA_COMANDA = "delete from comanda where codipeca = ? and ordrereparacio = ?";
	public static final String QUERY_GET_DETALLE_PIEZA_REPARACION = "select pec.codipeca, pec.descripcio, sto.stock, 0, pec.pvp, sto.stockminim, pec.marca, pec.model " +
																    "from peca pec " + 
																    "inner join stockpeca sto on pec.codipeca = sto.codipeca ";
	public static final String QUERY_SET_DESCONTAR_STOCK = "update stockpeca set stock = stock-? where codipeca = ?";
	public static final String QUERY_SET_REPARACION_ASIGNADA = "update reparacio set assignada = true, dataassignacio = CURRENT_DATE where ordrereparacio = ?";
	public static final String QUERY_SET_REPARACION_ACEPTADA = "update reparacio set acceptada = true where ordrereparacio = ?";
	public static final String QUERY_GET_USUARIOS = "select id, taller, usuari, perfil, nif, nom, cognoms, contrasenya, actiu, dataAlta, dataModificacio, dataBaixa, reparacionsAssignades from usuari ";
	public static final String QUERY_ASIGNAR_USUARIO_NUMERO_REPARACIONES = "update usuari set reparacionsassignades = ? where id = ?";
	public static final String QUERY_ASIGNAR_REPARACIONES_MECANICO = "update mecanic set idrep1 = ?, idrep2 = ? where idmecanic = ?";
	public static final String QUERY_ASIGNAR_MECANICO_REPARACION = "update reparacio set idmecanic = ? where ordrereparacio = ?";
	public static final String QUERY_GET_STOCK_MINIMO_PIEZA = "select stockminim from stockpeca where codipeca = ?";
	public static final String QUERY_GET_MARCA_PIEZA = "select marca from peca where codipeca = ?";
	public static final String QUERY_GET_MODELO_PIEZA = "select model from peca where codipeca = ?";
	
	
	
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
			String QUERY = QUERY_GET_DETALLE_REPARACIONES + " order by rep.ordrereparacio ";
			rs = stm.executeQuery(QUERY);
			while (rs.next()){
				result.add(new DetallReparacio(rs.getInt(1), rs.getDate(2), rs.getDouble(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getBoolean(9), rs.getInt(10), rs.getDate(11), rs.getDate(12)));
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

	public DetallReparacio getDetalleReparacion(int ordenReparacion)
			throws DAOException {
		getConnectionDB();
		DetallReparacio result = null;
		PreparedStatement ps = createPrepareStatment(QUERY_GET_DETALLE_REPARACION, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		try {
			ps.setLong(1, ordenReparacion);
			rs = ps.executeQuery();
			if (rs.next()){
				result = new DetallReparacio(rs.getInt(1), rs.getDate(2), rs.getDouble(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getBoolean(9), rs.getInt(10), rs.getDate(11), rs.getDate(12));
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
	
	public void setReparacionFinalizada(int ordenReparacion) throws DAOException {
		getConnectionDB();
		PreparedStatement ps = createPrepareStatment(QUERY_SET_REPARACION_FINALIZADA, ResultSet.CONCUR_UPDATABLE);
		try {
			ps.setLong(1, ordenReparacion);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(DAOException.ERR_SQL, e.getMessage(),  e);
		} finally {
			if (ps!=null) {
				try {ps.close();
				} catch (SQLException e) {
					throw new DAOException(DAOException.ERR_RESOURCE_CLOSED, e.getMessage(), e);
				}
			}
		}
	}


	public List<DetallPeca>  getPiezasReparacion(int ordenReparacion) throws DAOException {
		List<DetallPeca> result = new LinkedList<DetallPeca>();
		getConnectionDB();
		
		Statement stm = createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		try {
			String Query = QUERY_GET_PIEZAS_REPARACION + String.valueOf(ordenReparacion);
			rs = stm.executeQuery(Query);
			while (rs.next()){
				result.add(new DetallPeca(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getDouble(5), rs.getInt(6), rs.getString(7), rs.getString(8)));
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
	
	public List<DetallPeca> getDetallePiezas(String nombrePieza)
			throws DAOException {
		List<DetallPeca> result = new LinkedList<DetallPeca>();
		getConnectionDB();
		
		Statement stm = createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		try {
			String Query = QUERY_GET_DETALLE_PIEZAS + "'%" + nombrePieza + "%'";
			rs = stm.executeQuery(Query);
			while (rs.next()){
				result.add(new DetallPeca(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getDouble(5), rs.getInt(6), rs.getString(7), rs.getString(8)));
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



	public Usuari getUsuario(int idUsuario) throws DAOException {
		getConnectionDB();
		Usuari result = null;
		PreparedStatement ps = createPrepareStatment(QUERY_GET_USUARIO, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		try {
			ps.setLong(1, idUsuario);
			rs = ps.executeQuery();
			if (rs.next()){
				result = new Usuari(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(9), rs.getDate(10), rs.getDate(11), rs.getDate(12), rs.getInt(13));
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


	public void setPiezaComanda(boolean estado, int codigoPieza, int idTaller, int ordenReparacion, int cantidad, boolean tipoReparacion)
			throws DAOException {
		getConnectionDB();
		PreparedStatement ps = createPrepareStatment(QUERY_SET_PIEZA_COMANDA, ResultSet.CONCUR_UPDATABLE);
		try {
			ps.setBoolean(1, estado);
			ps.setInt(2, codigoPieza);
			ps.setInt(3, idTaller);
			ps.setInt(4, codigoPieza);
			ps.setInt(5, ordenReparacion);
			ps.setBoolean(6, tipoReparacion);
			ps.setInt(7, cantidad);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(DAOException.ERR_SQL, e.getMessage(),  e);
		} finally {
			if (ps!=null) {
				try {ps.close();
				} catch (SQLException e) {
					throw new DAOException(DAOException.ERR_RESOURCE_CLOSED, e.getMessage(), e);
				}
			}
		}
	}


	public void deletePiezaComanda(int codigoPieza, int ordenReparacion)
			throws DAOException {
		getConnectionDB();
		PreparedStatement ps = createPrepareStatment(QUERY_DELETE_PIEZA_COMANDA, ResultSet.CONCUR_UPDATABLE);
		try {
			ps.setInt(1, codigoPieza);
			ps.setInt(2, ordenReparacion);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(DAOException.ERR_SQL, e.getMessage(),  e);
		} finally {
			if (ps!=null) {
				try {ps.close();
				} catch (SQLException e) {
					throw new DAOException(DAOException.ERR_RESOURCE_CLOSED, e.getMessage(), e);
				}
			}
		}
	}


	public List<DetallPeca> getDetallePiezasTaller(int idTaller, String nombrePieza)
			throws DAOException {
		List<DetallPeca> result = new LinkedList<DetallPeca>();
		getConnectionDB();
		
		Statement stm = createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		try {
			String Query = QUERY_GET_DETALLE_PIEZA_REPARACION + "where sto.idtaller = " + idTaller + " and pec.descripcio like '%" + nombrePieza + "%'";
			rs = stm.executeQuery(Query);
			while (rs.next()){
				result.add(new DetallPeca(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getDouble(5), rs.getInt(6), rs.getString(7), rs.getString(8)));
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


	public void setDescontarStock(int codigoPieza, int cantidad) throws DAOException {
		getConnectionDB();
		PreparedStatement ps = createPrepareStatment(QUERY_SET_DESCONTAR_STOCK, ResultSet.CONCUR_UPDATABLE);
		try {
			ps.setInt(1, cantidad);
			ps.setInt(2, codigoPieza);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(DAOException.ERR_SQL, e.getMessage(),  e);
		} finally {
			if (ps!=null) {
				try {ps.close();
				} catch (SQLException e) {
					throw new DAOException(DAOException.ERR_RESOURCE_CLOSED, e.getMessage(), e);
				}
			}
		}
	}

	public void setReparacionAceptada(int ordenReparacion) throws DAOException {
		getConnectionDB();
		PreparedStatement ps = createPrepareStatment(QUERY_SET_REPARACION_ACEPTADA, ResultSet.CONCUR_UPDATABLE);
		try {
			ps.setInt(1, ordenReparacion);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(DAOException.ERR_SQL, e.getMessage(),  e);
		} finally {
			if (ps!=null) {
				try {ps.close();
				} catch (SQLException e) {
					throw new DAOException(DAOException.ERR_RESOURCE_CLOSED, e.getMessage(), e);
				}
			}
		}
	}

	public void setReparacionAsignada(int ordenReparacion) throws DAOException {
		getConnectionDB();
		PreparedStatement ps = createPrepareStatment(QUERY_SET_REPARACION_ASIGNADA, ResultSet.CONCUR_UPDATABLE);
		try {
			ps.setInt(1, ordenReparacion);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(DAOException.ERR_SQL, e.getMessage(),  e);
		} finally {
			if (ps!=null) {
				try {ps.close();
				} catch (SQLException e) {
					throw new DAOException(DAOException.ERR_RESOURCE_CLOSED, e.getMessage(), e);
				}
			}
		}
	}


	public List<Usuari> getUsuarios(String tipo, String nombre) throws DAOException {
		List<Usuari> result = new LinkedList<Usuari>();
		getConnectionDB();
		
		Statement stm = createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		try {
			String Query = QUERY_GET_USUARIOS + "where perfil like '%" + tipo + "%' and (nom like '%" + nombre + "%' or cognoms like '%" + nombre + "%')";
			rs = stm.executeQuery(Query);
			while (rs.next()){
				result.add(new Usuari(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(9), rs.getDate(10), rs.getDate(11), rs.getDate(12), rs.getInt(13)));
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
	
	public void asignarUsuarioNumeroReparacion(int idMecanico, int numeroReparaciones)
			throws DAOException {
		getConnectionDB();
		PreparedStatement ps = createPrepareStatment(QUERY_ASIGNAR_USUARIO_NUMERO_REPARACIONES, ResultSet.CONCUR_UPDATABLE);
		try {
			ps.setInt(1, numeroReparaciones);
			ps.setInt(2, idMecanico);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(DAOException.ERR_SQL, e.getMessage(),  e);
		} finally {
			if (ps!=null) {
				try {ps.close();
				} catch (SQLException e) {
					throw new DAOException(DAOException.ERR_RESOURCE_CLOSED, e.getMessage(), e);
				}
			}
		}
	}


	public void asignarReparacionesMecanico(int idMecanico,
			int ordenReparacion1, int ordenReparacion2) throws DAOException {
		getConnectionDB();
		PreparedStatement ps = createPrepareStatment(QUERY_ASIGNAR_REPARACIONES_MECANICO, ResultSet.CONCUR_UPDATABLE);
		try {
			if (ordenReparacion1 == 0) {
				ps.setObject(1, null);
			} else {
				ps.setInt(1, ordenReparacion1);
			}
			
			if (ordenReparacion2 == 0) {
				ps.setObject(2, null);
			} else {
				ps.setInt(2, ordenReparacion2);
				
			}
			
			ps.setInt(3, idMecanico);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(DAOException.ERR_SQL, e.getMessage(),  e);
		} finally {
			if (ps!=null) {
				try {ps.close();
				} catch (SQLException e) {
					throw new DAOException(DAOException.ERR_RESOURCE_CLOSED, e.getMessage(), e);
				}
			}
		}
	}


	public void asignarMecanicoReparacion(int idMecanico, int ordenReparacion)
			throws DAOException {
		getConnectionDB();
		PreparedStatement ps = createPrepareStatment(QUERY_ASIGNAR_MECANICO_REPARACION, ResultSet.CONCUR_UPDATABLE);
		try {
			if (idMecanico == 0) {
				ps.setObject(1, null);
			} else {
				ps.setInt(1, idMecanico);
			}
			ps.setInt(2, ordenReparacion);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(DAOException.ERR_SQL, e.getMessage(),  e);
		} finally {
			if (ps!=null) {
				try {ps.close();
				} catch (SQLException e) {
					throw new DAOException(DAOException.ERR_RESOURCE_CLOSED, e.getMessage(), e);
				}
			}
		}
	}


	public List<DetallReparacio> getDetalleReparacionesMecanico(int idMecanico)
			throws DAOException {
		List<DetallReparacio> result = new LinkedList<DetallReparacio>();
		getConnectionDB();
		
		Statement stm = createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		try {
			String QUERY = QUERY_GET_DETALLE_REPARACIONES_MECANICO + " and rep.idmecanic = " + idMecanico + " order by rep.ordrereparacio";
			rs = stm.executeQuery(QUERY);
			while (rs.next()){
				result.add(new DetallReparacio(rs.getInt(1), rs.getDate(2), rs.getDouble(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getBoolean(9), rs.getInt(10), rs.getDate(11), rs.getDate(12)));
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

	public List<DetallReparacio> getDetalleReparacionesMecanicoFiltro(
			int idMecanico, int idFiltro, String valor) throws DAOException {
		List<DetallReparacio> result = new LinkedList<DetallReparacio>();
		getConnectionDB();
		
		Statement stm = createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		try {
			String condiciones = "";
			if (valor != "") {
				switch (idFiltro) {
				case 0:
					break;
				case 1:
					condiciones = " and rep.datainici like '%" + valor + "%' ";
					break;
				case 2:
					condiciones = " and rep.datafi like '%" + valor + "%' ";
					break;
				case 3:
					condiciones = " and rep.dataassignacio like '%" + valor + "%' ";
					break;
				case 4:
					condiciones = " and cli.marca like '%" + valor + "%' ";
					break;
				case 5:
					condiciones = " and cli.model like '%" + valor + "%' ";
					break;
				case 6:
					condiciones = " and rep.ordrereparacio like " + valor + " ";
				default:
					break;
				}
			}
			
			String QUERY = QUERY_GET_DETALLE_REPARACIONES_MECANICO + " and rep.idmecanic = " + idMecanico + condiciones + " order by rep.ordrereparacio";
			rs = stm.executeQuery(QUERY);
			while (rs.next()){
				result.add(new DetallReparacio(rs.getInt(1), rs.getDate(2), rs.getDouble(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getBoolean(9), rs.getInt(10), rs.getDate(11), rs.getDate(12)));
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

	public int getStockMinimoPieza(int idPieza) throws DAOException {
		getConnectionDB();
		int result = 0;
		PreparedStatement ps = createPrepareStatment(QUERY_GET_STOCK_MINIMO_PIEZA, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		try {
			ps.setInt(1, idPieza);
			rs = ps.executeQuery();
			if (rs.next()){
				result = rs.getInt(1);
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


	public List<DetallReparacio> getDetalleReparacionesFiltro(int idFiltro,
			String valor, String nombre, String apellido) throws DAOException {
		List<DetallReparacio> result = new LinkedList<DetallReparacio>();
		getConnectionDB();
		
		Statement stm = createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		try {
			String condiciones = "";
			if (valor != "") {
				switch (idFiltro) {
				case 0:
					break;
				case 1:
					condiciones = " and rep.ordrereparacio like '%" + valor + "%' ";
					break;
				case 2:
					condiciones = " and cli.matricula like '%" + valor + "%' ";
					break;
				case 3:
					condiciones = " and cli.marca like '%" + valor + "%' ";
					break;
				case 4:
					condiciones = " and cli.model like '%" + valor + "%' ";
					break;
				case 5:
					condiciones = " and rep.acceptada = true ";
					break;
				case 6:
					condiciones = " and rep.acceptada = false ";
				case 7:
					condiciones = " and rep.assignada = true ";
					break;
				case 8:
					condiciones = " and rep.assignada = false ";
					break;
				default:
					break;
				}
			}
			
			String QUERY = QUERY_GET_DETALLE_REPARACIONES;
			if (condiciones != "") {
				QUERY = QUERY + condiciones;
			}
			QUERY = QUERY + " order by rep.ordrereparacio ";

			rs = stm.executeQuery(QUERY);
			while (rs.next()){
				result.add(new DetallReparacio(rs.getInt(1), rs.getDate(2), rs.getDouble(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getBoolean(9), rs.getInt(10), rs.getDate(11), rs.getDate(12)));
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


	public String getMarcaPieza(int idPieza) throws DAOException {
		getConnectionDB();
		String result = "";
		PreparedStatement ps = createPrepareStatment(QUERY_GET_MARCA_PIEZA, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		try {
			ps.setInt(1, idPieza);
			rs = ps.executeQuery();
			if (rs.next()){
				result = rs.getString(1);
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


	public String getModeloPieza(int idPieza) throws DAOException {
		getConnectionDB();
		String result = "";
		PreparedStatement ps = createPrepareStatment(QUERY_GET_MODELO_PIEZA, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		try {
			ps.setInt(1, idPieza);
			rs = ps.executeQuery();
			if (rs.next()){
				result = rs.getString(1);
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


	public List<DetallPeca> getDetallePiezasTallerFiltro(int idTaller,
			int filtro, String valor) throws DAOException {
		List<DetallPeca> result = new LinkedList<DetallPeca>();
		getConnectionDB();
		
		Statement stm = createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		try {
			String condiciones = "";
			if (valor != "") {
				switch (filtro) {
				case 0:
					break;
				case 1:
					condiciones = " and pec.codipeca like '%" + valor + "%' ";
					break;
				case 2:
					condiciones = " and pec.marca like '%" + valor + "%' ";
					break;
				case 3:
					condiciones = "  ";
					break;
				case 4:
					condiciones = " and pec.pvp like '%" + valor + "%' ";
					break;
				default:
					break;
				}
			}
			
			String Query = QUERY_GET_DETALLE_PIEZA_REPARACION + "where sto.idtaller = " + idTaller + condiciones;
			rs = stm.executeQuery(Query);
			while (rs.next()){
				result.add(new DetallPeca(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getDouble(5), rs.getInt(6), rs.getString(7), rs.getString(8)));
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
