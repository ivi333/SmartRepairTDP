package edu.uoc.tdp.pac4.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import edu.uoc.tdp.pac4.beans.Asseguradora;
import edu.uoc.tdp.pac4.beans.Client;
import edu.uoc.tdp.pac4.beans.Peca;
import edu.uoc.tdp.pac4.beans.Proveidor;
import edu.uoc.tdp.pac4.beans.Reparacio;
import edu.uoc.tdp.pac4.beans.Solicitud;
import edu.uoc.tdp.pac4.beans.Stockpeca;
import edu.uoc.tdp.pac4.exception.DAOException;
import edu.uoc.tdp.pac4.exception.GestorAdministracionException;


/**
 * Smart Repair ETIG - TDP PAC 4 Primavera 2013 Grup: FiveCoreDumped
 */
public class GestorAdministracionDAOImpl extends ConnectionPostgressDB
		implements GestorAdministracionDAO {

	private ConnectionPostgressDB cPostgressDB;

	public GestorAdministracionDAOImpl(ConnectionPostgressDB cPostgressDB)
			throws GestorAdministracionException {
		this.cPostgressDB = cPostgressDB;
		try {
			cPostgressDB.getConnectionDB();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Peca> getMarcas() throws DAOException{
		ArrayList<Peca> modelos = new ArrayList<Peca>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM peca";
		try {
			pstmt = cPostgressDB.createPrepareStatment(sql,
					ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				modelos.add(new Peca(rs.getInt("CodiPeca"), rs
						.getString("Descripcio"), rs.getInt("PVP"), rs
						.getInt("PVD"), rs.getString("Marca"), rs
						.getString("Model"), rs.getInt("IdProveidor")));
			}
			return modelos;
		} catch (SQLException e) {
			throw new DAOException(DAOException.ERR_SQL, e.getMessage(),  e);
		} finally {
			if (pstmt!=null) {
				try {pstmt.close();
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
	
	public ArrayList<Peca> getPiezaByCodeProveedor(int codigoProv)throws DAOException
	{
		ArrayList<Peca> piezas = new ArrayList<Peca>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " select codipeca,descripcio,pvp,pvd,marca,model from peca "
		+ " inner join proveidorpeca " 
		+ " on peca.codipeca = proveidorpeca.idpeca" 
		+ " where idproveidor= ? ";
		try {
	    pstmt = cPostgressDB.createPrepareStatment(sql,ResultSet.CONCUR_READ_ONLY);
		pstmt.setInt(1, codigoProv);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			Peca p=new Peca();
			p.setCodiPeca(rs.getInt("codipeca"));
			p.setDescripcio(rs.getString("descripcio"));
			p.setMarca(rs.getString("marca"));
			p.setModel( rs.getString("model"));
			p.setPVP(rs.getInt("pvp"));
			p.setPVPD(rs.getInt("pvd"));
			
			piezas.add(p);
		}
		return piezas;
		} catch (SQLException e) {
			throw new DAOException(DAOException.ERR_SQL, e.getMessage(),  e);
		} finally {
			if (pstmt!=null) {
				try {pstmt.close();
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

	public String aux() {
		// TODO Auto-generated method stub
		return "111AUX";
	}

	public boolean getExistCliente(String strNIF) {
		boolean bResult = false;
		try {
			String sql = " SELECT * FROM client WHERE nif = ?";
			PreparedStatement pstmt = cPostgressDB.createPrepareStatment(sql,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, strNIF);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getString(("nif").toString().trim()).equals(strNIF))
					bResult = true;
				else
					bResult = true;
			}
			
		} catch (Exception ex) {
			try {
				throw new GestorAdministracionException("Server.error.ConBD");
			} catch (GestorAdministracionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return bResult;

	}

	public int getNewClient(Client altaCliente) {
		int iResult = -1;
		try {
			String sql = " insert into  client "
					+ "( nom, cognoms, adreca, nif, poblacio, "
					+ " codiPostal,  dataAlta, IdAsseguradora,"
					+ " marca, tipus, num_chasis, model, matricula, color, anyo )"
					+ " VALUES (?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?)";

			PreparedStatement prep = cPostgressDB.createPrepareStatment(sql,
					ResultSet.CONCUR_UPDATABLE);

			prep.setString(1, altaCliente.getNom().toString().trim());
			prep.setString(2, altaCliente.getCognoms().toString().trim());
			prep.setString(3, altaCliente.getAdreca().toString().trim());
			prep.setString(4, altaCliente.getNif());
			prep.setString(5, altaCliente.getPoblacio().toString().trim());
			prep.setInt(6, altaCliente.getCodiPostal());

			java.util.Date dt = new java.util.Date();
			java.sql.Date dateAlta = new java.sql.Date(dt.getTime());
			prep.setDate(7, (java.sql.Date) dateAlta);

			prep.setInt(8, altaCliente.getIdasseguradora());
			prep.setString(9, altaCliente.getMarca().toString().trim());
			prep.setString(10, altaCliente.getTipus().toString().trim());
			prep.setString(11, altaCliente.getNum_chasis().toString().trim());
			prep.setString(12, altaCliente.getModel().toString().trim());
			prep.setString(13, altaCliente.getMatricula().toString().trim());
			prep.setString(14, altaCliente.getColor().toString().trim());

			prep.setDate(15, (java.sql.Date) altaCliente.getAnyo());
			
			prep.executeQuery();
			iResult = 1;
		} catch (Exception ex) {
			iResult = -2;
			try {
				throw new GestorAdministracionException("Server.error.ConBD");
			} catch (GestorAdministracionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return iResult;
	}

	public int getUpdClient(Client updCliente) {
		int iResult = -1;
		try{
			 
			String sql = " update   client SET "
					+ " nom=?, cognoms=?, adreca=?, poblacio=?, "
					+ " codiPostal=?,  dataAlta=?, IdAsseguradora=?,"
					+ " marca=?, tipus=?, num_chasis=?, model=?, matricula=?, color=?, anyo=? "
					+" WHERE nif=?";

			PreparedStatement prep = cPostgressDB.createPrepareStatment(sql,
					ResultSet.CONCUR_UPDATABLE);

			prep.setString(1, updCliente.getNom().toString().trim());
			prep.setString(2, updCliente.getCognoms().toString().trim());
			prep.setString(3, updCliente.getAdreca().toString().trim());
			
			prep.setString(4, updCliente.getPoblacio().toString().trim());
			prep.setInt(5, updCliente.getCodiPostal());

			java.util.Date dt = new java.util.Date();
			java.sql.Date dateAlta = new java.sql.Date(dt.getTime());
			prep.setDate(6, (java.sql.Date) dateAlta);

			prep.setInt(7, updCliente.getIdasseguradora());
			
			prep.setString(8, updCliente.getMarca().toString().trim());
			prep.setString(9, updCliente.getTipus().toString().trim());
			prep.setString(10, updCliente.getNum_chasis().toString().trim());
			prep.setString(11, updCliente.getModel().toString().trim());
			prep.setString(12, updCliente.getMatricula().toString().trim());
			prep.setString(13, updCliente.getColor().toString().trim());
			prep.setDate(14, (java.sql.Date) updCliente.getAnyo());
			
			prep.setString(15, updCliente.getNif());
			int ii=prep.executeUpdate();
			
			iResult = 1;
		
	}catch(Exception ex)
	{  ex.printStackTrace();
	   iResult=-2;
	}
	return iResult;
	}

	public Client getDadeClient(String strNIF) {
		Client client = null;
		try {
			String sql = "SELECT * FROM client  WHERE nif=?";
			PreparedStatement pstmt = cPostgressDB.createPrepareStatment(sql,
					ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, strNIF);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				client = new Client();
				client.setNumClient(rs.getInt("numclient"));
				client.setNom(rs.getString("nom"));
				client.setCognoms(rs.getString("cognoms"));
				client.setAdreca(rs.getString("adreca"));
				client.setNif((rs.getString("nif")));
				client.setPoblacio(rs.getString("poblacio"));
				client.setCodiPostal(rs.getInt("codipostal"));
				client.setDataAlta(rs.getDate("dataalta"));
				client.setIdasseguradora(rs.getInt("idasseguradora"));
				client.setMarca(rs.getString("marca"));
				client.setTipus(rs.getString("tipus"));
				client.setNum_chasis(rs.getString("num_chasis"));
				client.setModel(rs.getString("model"));
				client.setMatricula(rs.getString("matricula"));
				client.setColor(rs.getString("color"));
				client.setAnyo(rs.getDate("anyo"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return client;
	}

	public ArrayList<Asseguradora> getAseguradoras() {
		ArrayList<Asseguradora> Aseguradoras = new ArrayList<Asseguradora>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM asseguradora ";
		try {
			pstmt = cPostgressDB.createPrepareStatment(sql,
					ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Asseguradora aseg = new Asseguradora();
				aseg.setIdasseguradora(rs.getInt("idasseguradora"));
				aseg.setCif(rs.getString("cif"));
				aseg.setNom(rs.getString("nom"));
				aseg.setAdreca(rs.getString("adreca"));
				aseg.setTelefon(rs.getInt("telefon"));

				Aseguradoras.add(aseg);
			}

		} catch (Exception e) {
			try {
				throw new GestorAdministracionException("Server.error.ConBD");
			} catch (GestorAdministracionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return Aseguradoras;
	}
	
	public ArrayList<Reparacio> getReparaciones()
	{
		ArrayList<Reparacio> Reparaciones = new ArrayList<Reparacio>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM reparacio ";
			pstmt = cPostgressDB.createPrepareStatment(sql,
					ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Reparacio r= new Reparacio();
				r.setOrdreReparacio(rs.getInt("ordrereparacio"));
				r.setCapTaller(rs.getInt("captaller"));
				r.setAcceptada(rs.getBoolean("acceptada"));
				r.setIdMecanic(rs.getInt("idmecanic"));
				r.setAssignada(rs.getBoolean("assignada"));
				r.setComptador(rs.getDouble("comptador"));
				r.setObservacions(rs.getString("observacions"));
				r.setNumCom(rs.getInt("numcom"));
				r.setDataAssignacio(rs.getDate("dataassignacio"));
				r.setDataInici(rs.getDate("datainici"));
				r.setDataFi(rs.getDate("datafi"));
				Reparaciones.add(r);
			}
		} catch (Exception e) {
		}
		return Reparaciones;
	}
	
	public ArrayList<String> getPedidosPeca()
	{    ArrayList<String> list=new ArrayList<String>();
		
		try{
		
			String sql = " select "
					+ " peca.codipeca ,"
					+ " peca.descripcio ,"
					+ " peca.pvp ,"
					+ " peca.pvd ,"
					+ " peca.marca,"
					+ " peca.model,"
					+ " stockpeca.stock,"
					+ " stockpeca.stockminim,"
					+ " proveidor.nom  from "
					+ " peca inner join stockpeca"
					+ " on peca.codipeca=stockpeca.codipeca"
					+ " inner join proveidor on peca.idproveidor=proveidor.idproveidor";
				
			PreparedStatement pstmt = cPostgressDB.createPrepareStatment(sql,
					ResultSet.CONCUR_UPDATABLE);
		
			ResultSet rs = pstmt.executeQuery();
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(String.valueOf(rs.getInt(("codipeca"))));
				list.add(rs.getString("descripcio"));
				list.add(String.valueOf(rs.getInt(("pvp"))));
				list.add(String.valueOf(rs.getInt(("pvd"))));
				list.add(rs.getString("marca"));
				list.add(rs.getString("model"));
				list.add(String.valueOf(rs.getInt(("stock"))));
				list.add(String.valueOf(rs.getInt(("stockminim"))));
				list.add(rs.getString("nom"));
				
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return list;
	}
	
	public Solicitud getSolicitudByCodeReparacion(int codigoReparacion)
	{ 
		Solicitud sol =null;
		try{
			String sql = "SELECT * FROM solicitud  WHERE numreparacio=?";
			PreparedStatement pstmt = cPostgressDB.createPrepareStatment(sql,
					ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, codigoReparacion);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				sol =new Solicitud();
				sol.setNumsol(rs.getInt("numsol"));
				sol.setComentaris(rs.getString("comentaris"));
				sol.setDataalta(rs.getDate("dataalta"));
				sol.setDatafinalitzacio(rs.getDate("datafinalitzacio"));
				sol.setClient(rs.getInt("client"));
				sol.setNumreparacio(rs.getInt("numreparacio"));
				sol.setPendent(rs.getBoolean("pendent"));
				sol.setFinalitzada(rs.getBoolean("finalitzada"));
				sol.setNumpoliza(rs.getString("numpoliza"));
				
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return sol;
	}

	public ArrayList<Proveidor> getProveedores() throws DAOException{
		ArrayList<Proveidor> Proveedores = new ArrayList<Proveidor>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM proveidor ";
			pstmt = cPostgressDB.createPrepareStatment(sql,
					ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Proveidor p= new Proveidor();
				p.setIdproveidor(rs.getInt("idproveidor"));
				p.setNom(rs.getString("nom"));
				Proveedores.add(p);
			}
			return Proveedores;
		} catch (SQLException e) {
			throw new DAOException(DAOException.ERR_SQL, e.getMessage(),  e);
		} finally {
			if (pstmt!=null) {
				try {pstmt.close();
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

	public int getNuevoPedido(Peca peca) {
		int idResult=-1;
		try{
			String sql = " insert into peca "
					+ "(descripcio, pvp, pvd,  marca, model, idproveidor )"
					+" VALUES(?,?,?,?,?,?)";
			
			PreparedStatement prep = cPostgressDB.createPrepareStatment(sql,Statement.RETURN_GENERATED_KEYS);  
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
			idResult=-2;
		}
		return idResult;
	}
	
	public int getNuevoStockPedido(Stockpeca stockPeca)
	{  int idResult=-1;
		try{
			String sql = " insert into   stockpeca "
					+ "(codipeca, stock, idtaller,  stockminim )"
					+ " VALUES (?,?, ?,?)";

			PreparedStatement prep = cPostgressDB.createPrepareStatment(sql,
					ResultSet.CONCUR_UPDATABLE);
			
			
			prep.setInt(1, stockPeca.getCodipeca());
			prep.setInt(2, stockPeca.getStock());
			prep.setInt(3, stockPeca.getIdtaller());
			prep.setInt(4, stockPeca.getStockminim());
			
			prep.executeQuery();
			idResult = 1;
			
		}catch(Exception ex)
		{    idResult=-2;
			ex.printStackTrace();
		}
		return idResult;
	}

	public Peca getPiezaByCode(int codigoPieza) throws DAOException{
		Peca p= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM peca  where codipeca =?";
			pstmt = cPostgressDB.createPrepareStatment(sql,
					ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, codigoPieza);
			 rs = pstmt.executeQuery();
			while (rs.next()) {
				 p= new Peca();
				 p.setCodiPeca(rs.getInt("codipeca"));
					p.setDescripcio(rs.getString("descripcio"));
					p.setMarca(rs.getString("marca"));
					p.setModel( rs.getString("model"));
					p.setPVP(rs.getInt("pvp"));
					p.setPVPD(rs.getInt("pvd"));
			}
			return p;
		} catch (SQLException e) {
			throw new DAOException(DAOException.ERR_SQL, e.getMessage(),  e);
		} finally {
			if (pstmt!=null) {
				try {pstmt.close();
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

