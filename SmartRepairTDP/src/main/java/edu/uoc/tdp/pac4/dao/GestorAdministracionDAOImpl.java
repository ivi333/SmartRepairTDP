package edu.uoc.tdp.pac4.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import edu.uoc.tdp.pac4.beans.Peca;
import edu.uoc.tdp.pac4.exception.GestorAdministracionException;


/**
 * Smart Repair 
 * ETIG - TDP PAC 4 Primavera 2013
 * Grup: FiveCoreDumped
 */
public class GestorAdministracionDAOImpl extends ConnectionPostgressDB implements GestorAdministracionDAO {

private ConnectionPostgressDB cPostgressDB;
	
	public GestorAdministracionDAOImpl(ConnectionPostgressDB cPostgressDB)throws GestorAdministracionException
	{
		this.cPostgressDB=cPostgressDB;
		//this.cPostgressDB=cPostgressDB.getConnectionDB();
	}
	
	public ArrayList<Peca> getMarcas()
	{
		ArrayList<Peca> modelos = new ArrayList<Peca>();
		PreparedStatement pstmt =null;
		   ResultSet rs = null;
		String sql = "SELECT * FROM Client";
		try{
			pstmt= cPostgressDB.createPrepareStatment(sql,ResultSet.CONCUR_UPDATABLE);
			 rs =pstmt.executeQuery();
			while (rs.next()){
				modelos.add (new Peca (rs.getInt("CodiPeca"),rs.getString("Descripcio"),
		                   rs.getInt("PVP"),rs.getInt("PVD"),
		                   rs.getString("Marca"),rs.getString("Model"),rs.getInt("IdProveidor")));
			}
			
		}catch(Exception e){
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
	
	public boolean getExistCliente(String strNIF)
	{ 
		boolean bResult=false;
		try{
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
	}
		return bResult;
		
	}
	
}
