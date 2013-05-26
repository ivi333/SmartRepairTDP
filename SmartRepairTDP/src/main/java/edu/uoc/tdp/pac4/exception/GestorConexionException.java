package edu.uoc.tdp.pac4.exception;

import edu.uoc.tdp.pac4.common.TDSLanguageUtils;

/**
 * Smart Repair 
 * ETIG - TDP PAC 4 Primavera 2013
 * Grup: FiveCoreDumped
 */
public class GestorConexionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8904014188966498823L;
	/**
	 * Definicion de Errores
	 */
	public static final String ERR_DAO = TDSLanguageUtils.getMessage("ERR_DAO");
	public static final String ERR_USER_INVALID = "ERR_USER_INVALID";
	public static final String ERR_USER_DISABLED = "ERR_USER_DISABLED";
	public static final String ERR_USER_NOTFOUND = "ERR_USER_NOTFOUND";
	public static final String ERR_USER_REPARACIONES = "ERR_USER_REPARACIONES";
	public static final String ERR_USER_EXIST = "ERR_USER_EXIST";
	public static final String ERR_USER_REP_TALLER = "ERR_USER_REP_TALLER";
	public static final String ERR_JEFETALLER_ASIGN = "ERR_JEFETALLER_ASIGNADO";
	public static final String ERR_JEFETALLER_TALLER = "ERR_JEFETALLER_TALLER";
	public static final String ERR_TALLER_REPARACIONES = "ERR_TALLER_REPARACIONES";
	public static final String ERR_JEFETALLER_INACTIU ="ERR_JEFETALLER_INACTIU";
	
	public GestorConexionException() {
		super();
	}

	public GestorConexionException(String msg) {
		super(msg);
	}

	public GestorConexionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public GestorConexionException(Throwable cause) {
		super(cause);
	}


}
