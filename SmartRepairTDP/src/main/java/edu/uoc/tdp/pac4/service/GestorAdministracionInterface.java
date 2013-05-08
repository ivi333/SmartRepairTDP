package edu.uoc.tdp.pac4.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


import edu.uoc.tdp.pac4.beans.Client;
import edu.uoc.tdp.pac4.beans.Peca;


public interface GestorAdministracionInterface extends Remote{
	 
public ArrayList<Peca>getMarcas()throws RemoteException;

public boolean  getExistCliente(String strNIF) throws RemoteException;

public String aux()throws RemoteException;

public int getNewClient(Client altaCliente)throws RemoteException;
}



