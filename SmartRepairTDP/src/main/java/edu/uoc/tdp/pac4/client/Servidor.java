package edu.uoc.tdp.pac4.client;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import edu.uoc.tdp.pac4.service.GestorAdministracionImpl;
import edu.uoc.tdp.pac4.service.GestorAdministracionInterface;

public class Servidor {

	
	public static void main(String args[]) throws IOException{
		new Servidor();
		}
		public Servidor(){
		try {
		System.out.println("Iniciando servidor RMI...");
		Registry registry = LocateRegistry.createRegistry(1099);
		GestorAdministracionInterface objetoRemoto = new GestorAdministracionImpl();
		registry.rebind("PAC4", objetoRemoto);
		
		System.out.println("Servidor iniciado!");
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
		}
}
