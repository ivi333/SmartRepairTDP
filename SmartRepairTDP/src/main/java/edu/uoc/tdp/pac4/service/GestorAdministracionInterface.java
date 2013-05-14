package edu.uoc.tdp.pac4.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


import edu.uoc.tdp.pac4.beans.Asseguradora;
import edu.uoc.tdp.pac4.beans.Client;
import edu.uoc.tdp.pac4.beans.Comanda;
import edu.uoc.tdp.pac4.beans.Peca;
import edu.uoc.tdp.pac4.beans.Proveidor;
import edu.uoc.tdp.pac4.beans.Reparacio;
import edu.uoc.tdp.pac4.beans.Solicitud;


public interface GestorAdministracionInterface extends Remote{
	 
public ArrayList<Peca>getMarcas()throws RemoteException;

public boolean  getExistCliente(String strNIF) throws RemoteException;

public String aux()throws RemoteException;

public int getNewClient(Client altaCliente)throws RemoteException;

public ArrayList<Asseguradora> getAseguradoras()throws RemoteException;

public Client getDadeClient(String strNIF)throws RemoteException;

public int getUpdClient(Client updCliente)throws RemoteException;

public  ArrayList<Reparacio> getReparaciones()throws RemoteException;

public Solicitud getSolicitudByCodeReparacion(int codigoReparacion)throws RemoteException;

public ArrayList<Proveidor> getProveedores()throws RemoteException;

public int getNuevoPedido(Comanda comanda)throws RemoteException;

public ArrayList<Peca> getPiezaByCodeProveedor(int codigoProv)throws RemoteException;

public Peca getPiezaByCode(int codigoPieza)throws RemoteException;

public ArrayList<String> getCargarPedidos()throws RemoteException;
}



