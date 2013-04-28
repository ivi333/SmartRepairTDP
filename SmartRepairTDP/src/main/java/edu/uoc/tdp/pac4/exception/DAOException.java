/**
 * 
 */
package edu.uoc.tdp.pac4.exception;

import edu.uoc.tdp.pac4.common.TDSLanguageUtils;


/**
 * Smart Repair 
 * ETIG - TDP PAC 4 Primavera 2013
 * Grup: FiveCoreDumped
 */
public class DAOException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7668162501569876926L;

	public static final String ERR_GENERIC = TDSLanguageUtils.getMessage("ERR_GENERIC");
	public static final String ERR_BD_STOP = TDSLanguageUtils.getMessage("ERR_BD_STOP");
	public static final String ERR_NOT_FILE = TDSLanguageUtils.getMessage("ERR_NOT_FILE");
	public static final String ERR_PROPERTIES = TDSLanguageUtils.getMessage("ERR_PROPERTIES");
	public static final String ERR_CONECTION = TDSLanguageUtils.getMessage("ERR_CONECTION");
	public static final String ERR_SQL = TDSLanguageUtils.getMessage("ERR_SQL");
	public static final String ERR_RESOURCE_CLOSED = TDSLanguageUtils.getMessage("ERR_RESOURCE_CLOSED");
	public static final String ERR_DRIVER = TDSLanguageUtils.getMessage("ERR_DRIVER");

	/**
	 * 
	 */
	public DAOException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DAOException(String errorType, String message, Throwable cause) {
		super(errorType + ":" + message, cause);
	}



	/**
	 * @param cause
	 */
	public DAOException(Throwable cause) {
		super(cause);
	}
	
	

}
