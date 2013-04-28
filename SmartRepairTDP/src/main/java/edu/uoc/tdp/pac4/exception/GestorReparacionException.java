package edu.uoc.tdp.pac4.exception;

import edu.uoc.tdp.pac4.common.TDSLanguageUtils;

/**
 * Smart Repair 
 * ETIG - TDP PAC 4 Primavera 2013
 * Grup: FiveCoreDumped
 */
public class GestorReparacionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 174707001842535005L;
	/**
	 * Definicion de Errores
	 */
	public static final String ERR_DAO = TDSLanguageUtils.getMessage("ERR_DAO");


	public GestorReparacionException() {
		super();
	}

	public GestorReparacionException(String msg) {
		super(msg);
	}

	public GestorReparacionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public GestorReparacionException(Throwable cause) {
		super(cause);
	}


}
