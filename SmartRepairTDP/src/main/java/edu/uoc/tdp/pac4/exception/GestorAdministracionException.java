package edu.uoc.tdp.pac4.exception;

import edu.uoc.tdp.pac4.common.TDSLanguageUtils;

/**
 * Smart Repair 
 * ETIG - TDP PAC 4 Primavera 2013
 * Grup: FiveCoreDumped
 */
public class GestorAdministracionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3177292791577685329L;
	/**
	 * Definicion de Errores
	 */
	public static final String ERR_DAO = TDSLanguageUtils.getMessage("ERR_DAO");


	public GestorAdministracionException() {
		super();
	}

	public GestorAdministracionException(String msg) {
		super(msg);
	}

	public GestorAdministracionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public GestorAdministracionException(Throwable cause) {
		super(cause);
	}


}
