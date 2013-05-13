
package edu.uoc.tdp.pac4.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import edu.uoc.tdp.pac4.exception.DAOException;

/**
 * Clase abstracta que gestiona las operaciones basicas de conexion con postgres
 * Smart Repair 
 * ETIG - TDP PAC 4 Primavera 2013
 * Grup: FiveCoreDumped
 */
public  class ConnectionPostgressDB {

	private static Connection cnn = null;

	public ConnectionPostgressDB () {

	}

	/**
	 * Crea una conexion postgres a partir del fichero de configuracion y reutilizables en las clases de persistencia
	 * @return
	 * @throws DAOException
	 */
	public Connection getConnectionDB () throws DAOException {
		String url = "";
		String usuario = "";
		String clave ="";
		if (cnn==null) {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				throw new DAOException(DAOException.ERR_DRIVER, e.getMessage(), e);
			}
			try {
				Properties prop = new Properties();
				prop.load(ConnectionPostgressDB.class.getResourceAsStream("/properties/configuration.properties"));
				url = prop.getProperty("url");
				usuario = prop.getProperty("username");
				clave = prop.getProperty("password");
			} catch (java.io.FileNotFoundException e) {
				throw new DAOException(DAOException.ERR_NOT_FILE, e.getMessage(), e);
			} catch (IOException ioe) {
				throw new DAOException(DAOException.ERR_PROPERTIES, ioe.getMessage(), ioe);
			}

			try {
				cnn = DriverManager.getConnection(url, usuario, clave);
			} catch (SQLException sqle) {
				throw new DAOException(DAOException.ERR_CONECTION, sqle.getMessage(), sqle);
			} catch (Exception e) {
				throw new DAOException(DAOException.ERR_CONECTION, e.getMessage(), e);
			}
		}
		return cnn;
	}

	/**
	 * Crea una sentencia de ejecucion de SQL  el cual nos permite recorrer los resultados de un Resulset
	 * @param sql ->   Sentencia sql
	 * @param type1 -> Tipo de concurrencia.
	 * @param type2 -> Tipo de concurrencia.
	 * @return -> Devuelve un objeto con la sentencia preparada y pre compilada en SQL
	 * @throws DAOException
	 */
	public Statement createStatement(int type1, int type2) throws DAOException {
		Statement sentencia = null;
		try {
			sentencia = cnn.createStatement(type1, type2);
		} catch (SQLException sqle) {
			throw new DAOException(DAOException.ERR_SQL, sqle.getMessage(), sqle);
		} catch (Exception e) {
			throw new DAOException(DAOException.ERR_GENERIC, e.getMessage(), e);
		}
		return sentencia;
	}

	/**
	 * Ampliacion de una sentencia de ejecucion de SQL de tipo Scroll Insenstitive, el cual nos permite
	 * recorrer los resultados pero sin realizar modificaciones de los datos.
	 * @param sql ->   Sentencia sql
	 * @param type -> Tipo de concurrencia.
	 * @return -> Devuelve un objeto con la sentencia preparada y pre compilada en SQL
	 * @throws DAOException
	 */
	public PreparedStatement createPrepareStatment(String sql, int type) throws DAOException{
		try {
			return cnn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, type);
		} catch (SQLException sqle) {
			throw new DAOException(DAOException.ERR_SQL, sqle.getMessage(), sqle);
		} catch (Exception e) {
			throw new DAOException(DAOException.ERR_GENERIC, e.getMessage(), e);
		}
	}
	public PreparedStatement createPrepareStatment(String sql) throws DAOException{
		try {
			return cnn.prepareStatement(sql);
		} catch (SQLException sqle) {
			throw new DAOException(DAOException.ERR_SQL, sqle.getMessage(), sqle);
		} catch (Exception e) {
			throw new DAOException(DAOException.ERR_GENERIC, e.getMessage(), e);
		}
	}

	/**
	 * Cierra la conexion con el servidor postgress
	 * @throws DAOException
	 */
	public void closeConnection() throws DAOException {
		try {
			cnn.close();
		} catch (SQLException sqle) {
			throw new DAOException(DAOException.ERR_BD_STOP, sqle.getMessage(), sqle);
		} catch (Exception e) {
			throw new DAOException(DAOException.ERR_GENERIC, e.getMessage(), e);
		}
	}

}
