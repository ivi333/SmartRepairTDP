package edu.uoc.tdp.pac4.dao;



import java.rmi.RemoteException;
import java.util.ArrayList;

import edu.uoc.tdp.pac4.beans.Asseguradora;
import edu.uoc.tdp.pac4.beans.Client;
import edu.uoc.tdp.pac4.beans.Comanda;
import edu.uoc.tdp.pac4.beans.Peca;
import edu.uoc.tdp.pac4.beans.Proveidor;
import edu.uoc.tdp.pac4.beans.Reparacio;
import edu.uoc.tdp.pac4.beans.Solicitud;
import edu.uoc.tdp.pac4.exception.DAOException;


public interface  GestorAdministracionDAO {
	
	public  ArrayList<Peca> getMarcas()throws DAOException;;

	public boolean getExistCliente(String strNIF)throws DAOException;;
	
	public  String aux()throws DAOException;;
	
	public int getNewClient(Client altaCliente)throws DAOException;;
	
	public ArrayList<Asseguradora> getAseguradoras()throws DAOException;;
	
	public Client getDadeClient(String strNIF)throws DAOException;;
	
	public int getUpdClient(Client updCliente)throws DAOException;;
	
	public ArrayList<Proveidor> getProveedores()throws DAOException;;
	
	public  ArrayList<Reparacio> getReparaciones()throws DAOException;
	
	public ArrayList<String> getPedidosPeca()throws DAOException;;
	
	public Solicitud getSolicitudByCodeReparacion(int codigoReparacion)throws DAOException;;
	

	public ArrayList<Peca> getPiezaByCodeProveedor(int codigoProv)throws DAOException;
	
	public Peca getPiezaByCode(int codigoPieza)throws DAOException;
	
	public int getNuevoPedido(Comanda comanda)throws DAOException;
	
	public ArrayList<String> getCargarPedidos()throws DAOException;
	
	public int getNuevoSolicitud(Solicitud solicitud)throws DAOException;
}
