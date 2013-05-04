package edu.uoc.tdp.pac4.dao;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.uoc.tdp.pac4.beans.Peca;
import edu.uoc.tdp.pac4.exception.GestorAdministracionException;


public interface  GestorAdministracionDAO {
	
	public abstract ArrayList<Peca> getMarcas();
}
