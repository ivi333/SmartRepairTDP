package edu.uoc.tdp.pac4.exception;

import edu.uoc.tdp.pac4.common.TDSLanguageUtils;

/**
 * Smart Repair 
 * ETIG - TDP PAC 4 Primavera 2013
 * Grup: FiveCoreDumped
 */
public class GestorEstadisticaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1099221619619779195L;
	/**
	 * Definicion de Errores
	 */
	public static final String ERR_DAO = TDSLanguageUtils.getMessage("ERR_DAO");


	public GestorEstadisticaException() {
		super();
	}

	public GestorEstadisticaException(String msg) {
		super(msg);
	}

	public GestorEstadisticaException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public GestorEstadisticaException(Throwable cause) {
		super(cause);
	}


}
