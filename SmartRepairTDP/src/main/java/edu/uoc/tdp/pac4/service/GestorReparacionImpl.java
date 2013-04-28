package edu.uoc.tdp.pac4.service;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Smart Repair 
 * ETIG - TDP PAC 4 Primavera 2013
 * Grup: FiveCoreDumped
 */
public class GestorReparacionImpl extends java.rmi.server.UnicastRemoteObject implements GestorReparacionInterface, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 425801725246033548L;

	public GestorReparacionImpl() throws RemoteException {
		super();
	}

	/**
	 *  Implementacion de los servicios RMI 
	 *  para el subsistema de Reparacion
	 */

}