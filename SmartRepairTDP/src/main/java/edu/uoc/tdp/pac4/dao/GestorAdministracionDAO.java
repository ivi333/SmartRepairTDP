package edu.uoc.tdp.pac4.dao;



import java.util.ArrayList;
import edu.uoc.tdp.pac4.beans.Peca;


public interface  GestorAdministracionDAO {
	
	public  ArrayList<Peca> getMarcas();

	public boolean getExistCliente(String strNIF);
	
	public  String aux();
}
