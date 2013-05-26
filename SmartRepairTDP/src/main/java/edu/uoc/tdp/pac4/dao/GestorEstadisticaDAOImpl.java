package edu.uoc.tdp.pac4.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import edu.uoc.tdp.pac4.beans.Asseguradora;
import edu.uoc.tdp.pac4.beans.Client;
import edu.uoc.tdp.pac4.beans.Comanda;
import edu.uoc.tdp.pac4.beans.Mecanic;
import edu.uoc.tdp.pac4.beans.Reparacio;
import edu.uoc.tdp.pac4.beans.Solicitud;
import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.exception.DAOException;


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
	
	
	public ArrayList<Reparacio> obtenirReparacions(String strOrdreReparacio, String strNomClient, String strCognomClient, String strNomMecanic, String strCognomMecanic , String strEstado, String dataInici, String dataFi) throws DAOException {
	
		ArrayList <Reparacio> taulaReparacions = new ArrayList <Reparacio>();
		getConnectionDB();
		
		Statement st = createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		try {			
			String query =
					//Columna situacio = estado (mostrar strEstado)
					" SELECT r.ordreReparacio, c.nom, c.cognoms, u.nom, u.cognoms, r.dataInici , r.dataFi, s.pendent, s.finalitzada, r.assignada, r.acceptada, s.dataalta" +
					" FROM Reparacio r, Solicitud s, Client c, Mecanic m , Usuari u " +
					" WHERE r.ordreReparacio = s.numReparacio " +
					" AND s.client = c.nif " +
					" AND r.idMecanic = m.idMecanic " +
					" AND m.IdMecanic = u.id " +
					" AND to_char(r.ordreReparacio,'999999999999') LIKE '%" + strOrdreReparacio + "%' " +
					" AND c.nom LIKE '%" + strNomClient + "%' " +
					" AND u.nom LIKE '%" + strNomMecanic + "%' " +
					" AND c.cognoms LIKE '%" + strCognomClient + "%' " +
					" AND u.cognoms LIKE '%" + strCognomMecanic + "%' " ;
		
			/*if ( strEstado == "Finalitzada" )
				query += " AND s.dataAlta IS NOT NULL AND r.dataInici IS NOT NULL AND s.datafinalitzacio IS NOT NULL " ;
			else if ( strEstado == "En espera" )
				query += " AND s.dataAlta IS NOT NULL AND r.dataAssignacio IS NULL " ;
			else if ( strEstado == "En curs" )
				query += " AND r.dataInici IS NOT NULL AND r.DataFi IS NULL " ;
			else if ( strEstado == "Rebujtada" )
				query += " AND s.pendent=false AND s.finalitzada=true AND r.acceptada=false AND r.assignada=false " ;
			else if ( strEstado == "Rebuda" )
				query += " AND s.pendent=false AND s.finalitzada=true AND r.acceptada=false AND s.assignada=false " ;
			*/
			
			if ( strEstado == "Finalitzada" )
				query += " AND r.assignada=true AND r.acceptada=true AND s.pendent=false AND s.finalitzada=true" ;
			else if ( strEstado == "En espera" )
				query += " AND r.assignada=false AND r.acceptada=false AND s.pendent=true AND s.finalitzada=false" ;
			else if ( strEstado == "En curs" )
				query += " AND r.assignada=true AND r.acceptada=true AND s.pendent=false AND s.finalitzada=false" ;
			else if ( strEstado == "Rebutjada" )
				query += " AND r.assignada=true AND r.acceptada=true AND s.pendent=true AND s.finalitzada=false" ;
			else if ( strEstado == "Acceptada" )
				query += " AND r.assignada=false AND r.acceptada=true AND s.pendent=false AND s.finalitzada=false" ;

			
			if ( !dataInici.trim().equals(""))
				query += " AND to_char(r.datainici,'dd/MM/yyyy') like '%" + dataInici + "%' ";
				//TODO: Lanzar error introducir bien fecha
			
			if ( !dataFi.trim().equals(""))
				query += " AND to_char(r.datafi,'dd/MM/yyyy') like '%" + dataFi + "%' ";
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
				//aquÃ­ relleno los estados
				
				repAux.setDataInici(new Date(rs.getTimestamp(6).getTime()));
				repAux.setDataFi(new Date(rs.getTimestamp(7).getTime())); 
				
				solAux.setDataalta(new Date(rs.getTimestamp(12).getTime()));
				
				repAux.setMecanic(mecAux);	//Encapsulo
				solAux.setObjClient(cliAux);	//Accedo desde solicitud a cliente
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
	
	public ArrayList <Reparacio> obtenirClients(String strOrdreReparacio, String strNomClient, String strCognom, String strMarca, String strNomAsseguradora) throws DAOException {
		
		ArrayList <Reparacio> taulaClients = new ArrayList <Reparacio>();
		getConnectionDB();
		
		Statement st = createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		try {
			
			
			String query =
					" SELECT c.nom, c.cognoms, c.marca, a.nom, r.ordreReparacio, sum ( co.cantidad *  p.pvp ) as Importe " +
					" FROM Solicitud s, Client c, Reparacio r, Comanda co, Peca p, Asseguradora a " +
					" WHERE r.ordreReparacio = s.numReparacio " +
					" AND r.ordreReparacio = co.ordreReparacio " +
					" AND co.codiPeca = p.CodiPeca " +
					" AND c.idasseguradora = a.idasseguradora" +
					" AND s.client = c.nif " +
					" AND s.finalitzada=true " +
					" AND to_char(r.ordreReparacio,'999999999999') LIKE '%" + strOrdreReparacio + "%' " +
					" AND lower(c.nom) LIKE '%" + strNomClient.toLowerCase() + "%' " +
					" AND c.cognoms LIKE '%" + strCognom + "%' " +
					" AND c.marca LIKE '%" + strMarca + "%' " +
					" AND a.nom LIKE '%" + strNomAsseguradora + "%' " +
					" GROUP BY c.nom, c.cognoms, c.marca, a.nom, r.ordreReparacio ";
			
			 
			rs = st.executeQuery(query);
			
			while (rs.next()) {
				
				Reparacio repAux = new Reparacio();
				Asseguradora asseAux = new Asseguradora();
				Client cliAux = new Client();
				Solicitud solAux = new Solicitud();
				Comanda comAux = new Comanda();
				
				cliAux.setNom(rs.getString(1));
				cliAux.setCognoms(rs.getString(2));
				cliAux.setMarca(rs.getString(3));
				asseAux.setNom(rs.getString(4));
				repAux.setOrdreReparacio(rs.getInt(5));
				repAux.setImporte(rs.getFloat(6));
		
				//La columna "Import" se muestra en el Informe
				
				cliAux.setAsseguradora(asseAux);
				solAux.setObjClient(cliAux);
				repAux.setSolicitud(solAux);
				taulaClients.add(repAux);			
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
	public ArrayList <Usuari> obtenirEmpleats(String strIdUsuari, String strNomUsuari, String strCognomUsuari) throws DAOException {
		
		ArrayList <Usuari> taulaEmpleats = new ArrayList <Usuari>();
		getConnectionDB();
		
		Statement st = createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		try {
			
			String query =
					" SELECT u.id, u.nom, u.cognoms, u.nif, u.actiu" +
					" FROM Usuari u " +
					" WHERE u.nom LIKE '%" + strNomUsuari + "%' " +
					" AND u.cognoms LIKE '%" + strCognomUsuari + "%' " +
					" AND to_char(u.id,'999999999999') LIKE '%" + strIdUsuari + "%' ";
					
			 
			rs = st.executeQuery(query);
			
			while (rs.next()) {
				
				Usuari usuAux = new Usuari();
				
				usuAux.setId(rs.getInt(1));
				usuAux.setNom(rs.getString(2));
				usuAux.setCognoms(rs.getString(3));
				usuAux.setNif(rs.getString(4));
				usuAux.setActiu(rs.getBoolean(5));
				
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

	//usuari.reparacionsAssignadas con r.dataFi 
	public int calcularNumRepRessoltes (int iIdMecanic) throws DAOException {
		
		int iResult = 0 ;
		getConnectionDB();
		
		Statement st = createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		
		try {	
			String query =
					" SELECT COUNT (*)" +
					" FROM Reparacio r, Mecanic m, Solicitud s" +
					" WHERE r.idMecanic = m.idmecanic " +
					" AND s.numreparacio = r.ordreReparacio " +
					" AND s.finalitzada=true " +
					" AND m.idmecanic = " + iIdMecanic;
			
			rs = st.executeQuery(query);
			
			if (rs.next()) {iResult = rs.getInt(1);}
			
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
	
		return (iResult);					
	}
	
			
		//Calcular nÂº de horas que un Mecanic ha trabajado
	public float calcularNumHoresRep (int iIdMecanic) throws DAOException {
		
		float fResult = 0 ;
		getConnectionDB();
		
		Statement st = createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null;
		
		try {	
			String query =
					" SELECT (SUM(r.comptador)/60) as horasTrabajadas" +
					" FROM Reparacio r, Mecanic m, Solicitud s" +
					" WHERE r.idMecanic = m.idmecanic " +
					" AND s.numreparacio = r.ordreReparacio " +
					" AND s.finalitzada=true " +
					" AND m.idmecanic = " + iIdMecanic;
			
			rs = st.executeQuery(query);
			
			if (rs.next()) {fResult = rs.getFloat(1);}
			
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
	
		return (fResult);					
	}


	
				
		/*public ArrayList<Comanda> obtenirComandasDeReparacio (String strOrdreReparacio) throws DAOException {
		
			ArrayList <Comanda> listaComandas = new ArrayList <Comanda>();
			getConnectionDB();
			
			Statement st = createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = null;
			try {	
				String query =
						" SELECT r.ordreReparacio, c.numcom" +
						" FROM Reparacio r, Comanda c" +
						" WHERE r.ordrereparacio = c.ordrereparacio " +
						" AND to_char(m.ordrereparacio,'999999999999') LIKE '%" + strOrdreReparacio + "%' ";
						
			
			
				rs = st.executeQuery(query);
					
					while (rs.next()) {
						
						Reparacio repAux = new Reparacio();
						Comanda comAux = new Comanda();
						
						repAux.setOrdreReparacio(rs.getInt(1));
						comAux.set
					
						listaComandas.add(repAux);
						
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
			
			return taulaRepMec;
						
		}*/
						
		
	
}




