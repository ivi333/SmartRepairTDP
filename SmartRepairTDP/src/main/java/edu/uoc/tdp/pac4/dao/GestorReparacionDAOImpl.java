package edu.uoc.tdp.pac4.dao;

import java.util.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import edu.uoc.tdp.pac4.beans.Mecanic;
import edu.uoc.tdp.pac4.beans.Peca;
import edu.uoc.tdp.pac4.beans.Reparacio;
import edu.uoc.tdp.pac4.beans.Stockpeca;
import edu.uoc.tdp.pac4.beans.TipusReparacio;
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
	
	public static final String QUERY_GET_REPARACIONES = "SELECT * FROM REPARACION";

	
	public GestorReparacionDAOImpl() {
			super();
	}


	public List<Reparacio> getReparaciones() throws DAOException {
		/*List<Reparacio> result = new LinkedList<Reparacio>();
		getConnectionDB();
		
		Statement stm = createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		try {
			rs = stm.executeQuery(QUERY_GET_REPARACIONES);
			while (rs.next()){
				result.add(new Reparacio(rs.getInt(1), rs.getInt(2), rs.getBoolean(3), rs.getInt(4), rs.getBoolean(5), rs.getDouble(6), rs.getString(7), rs.getInt(8), rs.getDate(9), rs.getDate(10), rs.getDate(11), rs.getTipusReparacio(12)));
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
		}*/return null;
	}


	public Reparacio getReparacion(int ordenReparacion) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Mecanic> getMecanicos() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}


	public Mecanic getMecanico(int idMecanico) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Stockpeca> getStockPiezas() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Peca> getPiezas() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}


	public Peca getPieza(int idPieza) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
