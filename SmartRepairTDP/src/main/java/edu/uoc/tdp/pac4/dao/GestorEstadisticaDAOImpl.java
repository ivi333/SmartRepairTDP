package edu.uoc.tdp.pac4.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import edu.uoc.tdp.pac4.beans.Asseguradora;
import edu.uoc.tdp.pac4.beans.Client;
import edu.uoc.tdp.pac4.beans.Comanda;
import edu.uoc.tdp.pac4.beans.Mecanic;
import edu.uoc.tdp.pac4.beans.Peca;
import edu.uoc.tdp.pac4.beans.PerfilUsuari;
import edu.uoc.tdp.pac4.beans.Reparacio;
import edu.uoc.tdp.pac4.beans.Solicitud;
import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.exception.DAOException;
import edu.uoc.tdp.pac4.exception.GestorEstadisticaException;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import edu.uoc.tdp.pac4.dao.ConnectionPostgressDB;


/**
 * Smart Repair 
 * ETIG - TDP PAC 4 Primavera 2013
 * Grup: FiveCoreDumped
 */
public class GestorEstadisticaDAOImpl extends ConnectionPostgressDB implements GestorEstadisticaDAO{

	/**
	 * Implementar las operaciones de base de datos
	 * para el subsistema de Estadistica
	 */
	public GestorEstadisticaDAOImpl()
	{
		super();
	}
	
	
	//Informe de Reparacions
	
	
	public ArrayList<Reparacio> obtenirReparacions(int intOrdreReparacio, String strNomClient, String strCognomClient, String strNomMecanic, String strCognomMecanic , String strEstado, String dataInici, String dataFi) throws DAOException {
	
		ArrayList <Reparacio> taulaReparacions = new ArrayList <Reparacio>();
		getConnectionDB();
		
		Statement st = createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		try {			
			String query =
					//Columna situacio = estado (mostrar strEstado)
					" SELECT r.ordreReparacio, c.nomClient, c.cognomClient, m.nomMecanic, m.cognomMecanic, r.dataInici , r.dataFi, s.pendent, s.finalitzada, r.assignada, r.acceptada" +
					" FROM Reparacio r, Solicitud s, Client c, Mecanic m " +
					" WHERE r.ordreReparacio = s.numReparacio " +
					" AND to_char(r.ordreReparacio,'999999999999') LIKE '%" + intOrdreReparacio + "%' " +
					" AND s.client = c.nif " +
					" AND r.ordreReparacio = m.idMecanic " +
					" AND c.nomClient LIKE '%" + strNomClient + "%' " +
					" AND m.nomMecanic LIKE '%" + strNomMecanic + "%' " +
					" AND c.cognomClient LIKE '%" + strCognomClient + "%' " +
					" AND m.cognomMecanic LIKE '%" + strCognomMecanic + "%' " ;
		
			if ( strEstado == "Finalitzades" )
				query += " AND s.dataAlta IS NOT NULL AND r.dataInici IS NOT NULL AND s.datafinalitzacio IS NOT NULL " ;
			else if ( strEstado == "En espera" )
				query += " AND s.dataAlta IS NOT NULL AND r.dataAssignacio IS NULL " ;
			else if ( strEstado == "En curs" )
				query += " AND r.dataInici IS NOT NULL AND r.DataFi IS NULL " ;
			else if ( strEstado == "Rebujtades" )
				query += " AND s.pendent=false AND s.finalitzada=true AND r.acceptada=false AND s.assignada=false " ;
						
			if ( dataInici.trim() != "" )
				query += " AND r.datainici = '" + dataInici + "' ";
				//TODO: Controlar error al introducir mal la fecha
			
			if ( dataFi.trim() != "" )
				query += " AND r.datafi = '" + dataFi + "' ";
				//TODO: Controlar error al introducir mal la fecha
			 
			rs = st.executeQuery(query);
			
			while (rs.next()) {				
				Reparacio repAux = new Reparacio();
				Solicitud solAux = new Solicitud();
				Client cliAux = new Client();
				Mecanic mecAux = new Mecanic();
				
				
				repAux.setOrdreReparacio(rs.getInt(1));
				cliAux.setNom(rs.getString(2));
				cliAux.setCognoms(rs.getString(3));
				mecAux.setNom(rs.getString(4));
				mecAux.setCognoms(rs.getString(5));
				 //poner las variables del estado
				repAux.setAcceptada(rs.getBoolean(11));
				repAux.setAssignada(rs.getBoolean(10));
				solAux.setPendent(rs.getBoolean(8));
				solAux.setFinalitzada(rs.getBoolean(9));
				//aquí relleno los estados
				
				repAux.setDataInici(rs.getDate(7));
				repAux.setDataFi(rs.getDate(8)); 
				
				repAux.setMecanic(mecAux);	//Encapsulo
				solAux.setClientOb(cliAux);	//Accedo desde solicitud a cliente
				repAux.setSolicitud(solAux);
			
				taulaReparacions.add(repAux);
				
			
			}
			
		} catch (SQLException e) {
			throw new DAOException(DAOException.ERR_SQL, e.getMessage(),  e);
		} finally {
			if (st!=null) {
				try {st.close();
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
	
		return taulaReparacions;
				
	}
	

	//Informe de Clients
	
	public ArrayList <Client> obtenirClients(String strNomClient, String strCognom, String strMarca, String strNomAsseguradora) throws DAOException {
		
		ArrayList <Client> taulaClients = new ArrayList <Client>();
		getConnectionDB();
		
		Statement st = createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		try {
			
			
			String query =
					" SELECT , c.nom, c.cognoms, c.marca, a.nom, (p.pvp*co.cantidad) AS importe " +
					" FROM Solicitud s, Client c, Reparacio r, Comanda co, Peca p, Asseguradora a " +
					" WHERE r.ordreReparacio = s.numReparacio " +
					" AND r.ordreReparacio = co.ordreReparacio " +
					" AND co.codiPeca = p.CodiPeca " +
					" AND c.idasseguradora = a.idasseguradora" +
					" AND s.client = c.nif " +
					" AND s.finalitzada=true" +//codiPeca y CodiPeca en BBDD está como codipeca
					" AND c.nom LIKE '%" + strNomClient + "%' " +
					" AND c.cognom LIKE '%" + strCognom + "%' " +
					" AND c.marca LIKE '%" + strMarca + "%' " +
					" AND a.nom LIKE '%" + strNomAsseguradora + "%' ";
			
			 
			rs = st.executeQuery(query);
			
			while (rs.next()) {
				
				Client cliAux = new Client();
				Asseguradora asseAux = new Asseguradora();
				
				cliAux.setNom(rs.getString(1));
				cliAux.setCognoms(rs.getString(2));
				cliAux.setMarca(rs.getString(3));
				asseAux.setNom(rs.getString(4));
				//Falta mostrar la columna "Import"
				
				cliAux.setAsseguradora(asseAux);
			
				taulaClients.add(cliAux);
			
			}
			
		} catch (SQLException e) {
			throw new DAOException(DAOException.ERR_SQL, e.getMessage(),  e);
		} finally {
			if (st!=null) {
				try {st.close();
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
	
		return taulaClients;
				
	}
	
	
	
	//Informe d'empleats
	public ArrayList <Usuari> obtenirEmpleats(int intIdUsuari, String strNomUsuari, String strCognom) throws DAOException {
		
		ArrayList <Usuari> taulaEmpleats = new ArrayList <Usuari>();
		getConnectionDB();
		
		Statement st = createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		try {
			
			String query =
					" SELECT , u.id, u.nom, u.cognoms, u.nif, u.dataAlta, u.actiu" +
					" FROM Usuari u " +
					" WHERE u.nom LIKE '%" + strNomUsuari + "%' " +
					" AND u.cognom LIKE '%" + strCognom + "%' ";
					//Falta like del id
			 
			rs = st.executeQuery(query);
			
			while (rs.next()) {
				
				Usuari usuAux = new Usuari();
				
				usuAux.setId(rs.getInt(1));
				usuAux.setNom(rs.getString(2));
				usuAux.setCognoms(rs.getString(3));
				//usuAux.setNif(rs.getInt(4));
				usuAux.setDataAlta(rs.getDate(5));
				usuAux.setActiu(rs.getBoolean(6));
				
				taulaEmpleats.add(usuAux);
			}
			
		} catch (SQLException e) {
			throw new DAOException(DAOException.ERR_SQL, e.getMessage(),  e);
		} finally {
			if (st!=null) {
				try {st.close();
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
	
		return taulaEmpleats;
				
	}

	
	
	//Calcular nº de horas que un Mecanic SI ha trabajado
	/*public int calcularNumHoresTreballades () throws DAOException {
		return 0;
	
	}
	
	//usuari.reparacionsAssignadades con r.dataFi 
	/*public int calcularNumRepRessoltes (int intIdMecanic) throws DAOException {
	
		int nRepRessoltes = 0;
		getConnectionDB();
		
		Statement st = createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		
		try {
					
			String query =
					" SELECT , r.comptador" +
					" FROM Reparacio r, Mecanic m, Solicitud s " +
					" WHERE r.idMecanic = m.idmecanic" +
					" AND r.ordreReparacio = s.numReparacio "
					" AND s.finalitzada = true";
					
					 
			rs = st.executeQuery(query);
		
	
	
	}*/
	
}




