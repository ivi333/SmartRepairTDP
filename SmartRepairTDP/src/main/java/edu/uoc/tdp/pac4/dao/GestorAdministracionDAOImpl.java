package edu.uoc.tdp.pac4.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import edu.uoc.tdp.pac4.beans.Asseguradora;
import edu.uoc.tdp.pac4.beans.Client;
import edu.uoc.tdp.pac4.beans.Peca;
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

	public ArrayList<Peca> getMarcas() {
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

		} catch (Exception e) {
			try {
				throw new GestorAdministracionException("Server.error.ConBD");
			} catch (GestorAdministracionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return modelos;
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
			ex.printStackTrace();
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
			prep.setString(4, altaCliente.getNif().toString().trim());
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
			ex.printStackTrace();
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
			prep.execute();
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
				client.setNif(rs.getString("nif"));
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
}
