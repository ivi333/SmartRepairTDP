package edu.uoc.tdp.pac4.dao;



import java.util.ArrayList;

import edu.uoc.tdp.pac4.beans.Asseguradora;
import edu.uoc.tdp.pac4.beans.Client;
import edu.uoc.tdp.pac4.beans.Peca;


public interface  GestorAdministracionDAO {
	
	public  ArrayList<Peca> getMarcas();

	public boolean getExistCliente(String strNIF);
	
	public  String aux();
	
	public int getNewClient(Client altaCliente);
	
	public ArrayList<Asseguradora> getAseguradoras();
	
	public Client getDadeClient(String strNIF);
	
	public int getUpdClient(Client updCliente);
	
}
