package edu.uoc.tdp.pac4.common;

public class ItemCombo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String value;
	private String aux;
	public ItemCombo()
	{
		
	}
	public ItemCombo(Integer id,String value,String aux)
	{  this.id=id;
		this.value=value;
		this.setAux(aux);
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAux() {
		return aux;
	}
	public void setAux(String aux) {
		this.aux = aux;
	}
}
