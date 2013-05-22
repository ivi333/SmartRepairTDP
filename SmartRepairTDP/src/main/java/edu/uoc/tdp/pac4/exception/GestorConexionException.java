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
	public static final String ERR_USER_INVALID = TDSLanguageUtils.getMessage("ERR_USER_INVALID");
	public static final String ERR_USER_DISABLED = TDSLanguageUtils.getMessage("ERR_USER_DISABLED");
	public static final String ERR_USER_NOTFOUND = TDSLanguageUtils.getMessage("ERR_USER_NOTFOUND");
	public static final String ERR_USER_REPARACIONES = TDSLanguageUtils.getMessage("ERR_USER_REPARACIONES");
	public static final String ERR_USER_EXIST = TDSLanguageUtils.getMessage("ERR_USER_EXIST");
	public static final String ERR_USER_REP_TALLER = TDSLanguageUtils.getMessage("ERR_USER_REP_TALLER");
	public static final String ERR_JEFETALLER_ASIGN = TDSLanguageUtils.getMessage("ERR_JEFETALLER_ASIGNADO");
	public static final String ERR_TALLER_INACTIU = TDSLanguageUtils.getMessage("ERR_TALLER_INACTIU");
	public static final String ERR_JEFETALLER_TALLER = TDSLanguageUtils.getMessage("ERR_JEFETALLER_TALLER");
	public static final String ERR_TALLER_REPARACIONES = TDSLanguageUtils.getMessage("ERR_TALLER_REPARACIONES");


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
