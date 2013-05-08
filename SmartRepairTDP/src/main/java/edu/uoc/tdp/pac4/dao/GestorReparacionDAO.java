package edu.uoc.tdp.pac4.dao;

import java.util.ArrayList;

import edu.uoc.tdp.pac4.beans.Peca;




/**
 * Clase abstracta que gestiona las operaciones basicas de conexion con postgres
 * Smart Repair 
 * ETIG - TDP PAC 4 Primavera 2013
 * Grup: FiveCoreDumped
 */
public interface GestorReparacionDAO {
	public abstract ArrayList<Peca> getMarcas();
}
