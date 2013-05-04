package edu.uoc.tdp.pac4.client;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import edu.uoc.tdp.pac4.beans.Peca;
import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import edu.uoc.tdp.pac4.service.GestorAdministracionInterface;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JComboBox;

public class AltaCliente extends JFrame {

	private JPanel contentPanel;
	private JLabel lblCliente;
	private JLabel lblNif;
	private JLabel lblDatosCliente;
	private JLabel lblId;

	private JTextField txtNIF;
	private JTextField txtID;
	private JButton btnComprobar;

	private JSeparator separator1;
	private JLabel lblNIFNEW;
	private JTextField txtNifNew;
	private JLabel lblNombre;
	private JTextField txtNombre;

	private JLabel lblApellidos;
	private JTextField txtApellido;
	private JLabel lblDireccion;
	private JTextField txtDireccion;
	private JLabel lblCP;
	private JTextField txtCP;
	private JSeparator separator;
	private JLabel lblDatosVehiculo;
	private JLabel lblMarca;
	private JTextField txtModelo;
	private JLabel lblModelo;
	private JComboBox cmbMarca;
	private JLabel lblMatricula;
	private JTextField txtMatricula;
	private JLabel lblBastidor;
	private JTextField txtBastidor;
	private JButton btnNewUpd;
	private JButton btnCancelar;

	private static String NEW_UPD = "";

	public static String getNEW_UPD() {
		return NEW_UPD;
	}

	public void setNEW_UPD(String nEW_UPD) {
		NEW_UPD = nEW_UPD;
	}

	private static GestorAdministracionInterface conexionRemota;

	public static void main(String args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaCliente frame = new AltaCliente();
					String s = getNEW_UPD().toString();
					String ss = s;
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static GestorAdministracionInterface getRemoto()
			throws RemoteException, NotBoundException {
		try {

			if (conexionRemota == null) {

				Registry registry = LocateRegistry.getRegistry("localhost",
						1099);
				conexionRemota = (GestorAdministracionInterface) registry
						.lookup("PAC4");

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return conexionRemota;
	}

	public AltaCliente() {
		try {

			seleccionIdioma();
			initialize();
			CargarControles();
			CargarCmbMarca();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void initialize() {
		setSize(new Dimension(422, 555));
	}

	private void seleccionIdioma() {
		Locale localLocale = new Locale("", "");
		TDSLanguageUtils.setDefaultLanguage("i18n/messages");
	}

	private void CargarControles()
		{
		 if(NEW_UPD.equals("NEW"))
		setTitle(TDSLanguageUtils.getMessage("cliente.new.titulo"));
		 if(NEW_UPD.equals("UPD"))
			 setTitle(TDSLanguageUtils.getMessage("cliente.upd.titulo"));
	    contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		
		contentPanel.setLayout(null);
		
		lblCliente = new JLabel();
		lblCliente.setText(TDSLanguageUtils.getMessage("cliente.lbl.comprobar"));
		lblCliente.setBounds(10, 11, 260, 14);
		contentPanel.add(lblCliente);
		
		 btnComprobar = new JButton();
		 if(NEW_UPD.equals("NEW"))
		     btnComprobar.setText(TDSLanguageUtils.getMessage("cliente.btn.comprobar"));
		 if(NEW_UPD.equals("UPD"))
			 btnComprobar.setText(TDSLanguageUtils.getMessage("cliente.btn.consulta"));
		 
		 btnComprobar.setBounds(284, 38, 117, 23);
		contentPanel.add(btnComprobar);
		this.btnComprobar.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
		String strMsg="";
				try{
					String strNIF=txtNIF.getText().toString();
					
					if(!strNIF.equals("") && strNIF!=null)
					{
						strMsg= getMsgExisteCliente(strNIF);
						LeerError(strMsg,TDSLanguageUtils.getMessage("cliente.msg.titulo"));
					}
					else
					{String tittle="";
						 if(NEW_UPD.equals("NEW"))
						 tittle=TDSLanguageUtils.getMessage("cliente.new.titulo");
						
						 if(NEW_UPD.equals("UPD"))
						 tittle=TDSLanguageUtils.getMessage("cliente.upd.titulo");
								 
						LeerError(TDSLanguageUtils.getMessage("cliente.msg.nif"),tittle);
					}
					
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
			}
		});
		
		
		lblNif = new JLabel();
		lblNif.setText(TDSLanguageUtils.getMessage("cliente.lbl.NIF"));
		lblNif.setBounds(10, 42, 62, 14);
		contentPanel.add(lblNif);
		
		txtNIF = new JTextField();
		txtNIF.setBounds(82, 39, 86, 20);
		contentPanel.add(txtNIF);
		txtNIF.setColumns(10);
		
		separator1 = new JSeparator();
		separator1.setBounds(10, 67, 403, 2);
		contentPanel.add(separator1);
		
	    lblDatosCliente = new JLabel();
	    lblDatosCliente.setText(TDSLanguageUtils.getMessage("cliente.lbl.datos.cliente"));
		lblDatosCliente.setBounds(10, 80, 231, 14);
		contentPanel.add(lblDatosCliente);
		
		lblId = new JLabel();
		lblId.setText(TDSLanguageUtils.getMessage("cliente.lbl.id"));
		lblId.setBounds(10, 105, 96, 14);
		contentPanel.add(lblId);
		
		txtID = new JTextField();
		txtID.setBounds(115, 105, 146, 20);
		contentPanel.add(txtID);
		txtID.setColumns(10);
		
		lblNIFNEW = new JLabel();
		lblNIFNEW.setText(TDSLanguageUtils.getMessage("cliente.lbl.NIF"));
		lblNIFNEW.setBounds(10, 136, 96, 14);
		contentPanel.add(lblNIFNEW);
		
		txtNifNew = new JTextField();
		txtNifNew.setBounds(115, 133, 146, 20);
		contentPanel.add(txtNifNew);
		txtNifNew.setColumns(10);
		
		lblNombre = new JLabel();
		lblNombre.setText(TDSLanguageUtils.getMessage("cliente.lbl.nombre"));
		lblNombre.setBounds(10, 167, 96, 14);
		contentPanel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(115, 164, 205, 20);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		lblApellidos = new JLabel();
		lblApellidos.setText(TDSLanguageUtils.getMessage("cliente.lbl.apellido"));
		lblApellidos.setBounds(10, 198, 96, 14);
		contentPanel.add(lblApellidos);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(115, 195, 205, 20);
		contentPanel.add(txtApellido);
		txtApellido.setColumns(10);
		
		lblDireccion = new JLabel();
		lblDireccion.setText(TDSLanguageUtils.getMessage("cliente.lbl.direccion"));
		lblDireccion.setBounds(10, 231, 96, 14);
		contentPanel.add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(115, 228, 205, 20);
		contentPanel.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		lblCP = new JLabel();
		lblCP.setText(TDSLanguageUtils.getMessage("cliente.lbl.CP"));
		lblCP.setBounds(10, 256, 96, 14);
		contentPanel.add(lblCP);
		
		txtCP = new JTextField();
		txtCP.setBounds(115, 253, 86, 20);
		contentPanel.add(txtCP);
		txtCP.setColumns(10);
		
		separator = new JSeparator();
		separator.setBounds(10, 288, 403, 2);
		contentPanel.add(separator);
		
		lblDatosVehiculo = new JLabel();
		lblDatosVehiculo.setText(TDSLanguageUtils.getMessage("cliente.lbl.datos.vehiculo"));
		lblDatosVehiculo.setBounds(10, 301, 200, 14);
		contentPanel.add(lblDatosVehiculo);
		
		lblMarca = new JLabel();
		lblMarca.setText(TDSLanguageUtils.getMessage("cliente.lbl.vehiculo.marca"));
		lblMarca.setBounds(10, 326, 96, 14);
		contentPanel.add(lblMarca);
		
		cmbMarca = new JComboBox();
		cmbMarca.setBounds(104, 323, 168, 20);
		contentPanel.add(cmbMarca);
		
		lblModelo = new JLabel();
		lblModelo.setText(TDSLanguageUtils.getMessage("cliente.lbl.vehiculo.modelo"));
		lblModelo.setBounds(10, 354, 96, 14);
		contentPanel.add(lblModelo);
		
		txtModelo = new JTextField();
		txtModelo.setBounds(104, 351, 137, 20);
		contentPanel.add(txtModelo);
		txtModelo.setColumns(10);
		
		lblMatricula = new JLabel();
		lblMatricula.setText(TDSLanguageUtils.getMessage("cliente.lbl.vehiculo.matricula"));
		lblMatricula.setBounds(10, 385, 96, 14);
		contentPanel.add(lblMatricula);
		
		txtMatricula = new JTextField();
		txtMatricula.setBounds(104, 382, 137, 20);
		contentPanel.add(txtMatricula);
		txtMatricula.setColumns(10);
		
		lblBastidor = new JLabel();
		lblBastidor.setText(TDSLanguageUtils.getMessage("cliente.lbl.vehiculo.bastidor"));
		lblBastidor.setBounds(10, 413, 96, 14);
		contentPanel.add(lblBastidor);
		
		txtBastidor = new JTextField();
		txtBastidor.setBounds(104, 413, 137, 20);
		contentPanel.add(txtBastidor);
		txtBastidor.setColumns(10);
		
		btnNewUpd = new JButton();
		
		 if(NEW_UPD.equals("NEW"))
			 btnNewUpd.setText(TDSLanguageUtils.getMessage("cliente.btn.alta.cliente"));
		 if(NEW_UPD.equals("UPD"))
			 btnNewUpd.setText(TDSLanguageUtils.getMessage("cliente.btn.modificar"));
			
		btnNewUpd.setBounds(10, 478, 117, 23);
		contentPanel.add(btnNewUpd);
		this.btnNewUpd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
			String strMsg="";
					try{
					 if(NEW_UPD.equals("NEW")){
						  strMsg=getInsertNewCliente();
						if (!strMsg.equals("")) {
							String tittle = TDSLanguageUtils.getMessage("cliente.new.titulo");
							
							LeerError(strMsg, tittle);
						}

					 }
					 
					   if(NEW_UPD.equals("UPD"))
					   {
						   
					   }
					   
					}catch(Exception ex)
					{
						ex.printStackTrace();
					}
					
				}
			});
		
		
		btnCancelar = new JButton();
		btnCancelar.setText(TDSLanguageUtils.getMessage("cliente.btn.cancelar"));
		btnCancelar.setBounds(251, 478, 107, 23);
		contentPanel.add(btnCancelar);
		
	}

	private void LeerError(String paramString1, String paramString2) {
		JOptionPane.showMessageDialog(this, paramString1, paramString2, 0);
	}

	private String getMsgExisteCliente(String strNIF) {
		String strResult = "";
		try {

			boolean bMsg = getRemoto().getExistCliente(strNIF);
			if (bMsg)
				strResult = TDSLanguageUtils
						.getMessage("cliente.msg.cliente.existe");
			else
				strResult = TDSLanguageUtils
						.getMessage("cliente.msg.cliente.noexiste");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return strResult;
	}

	private void CargarCmbMarca() throws RemoteException {
		ArrayList<Peca> ListMarca = null;
		try {
			/*
			 * ListMarca = getRemoto().getMarcas(); cmbMarca.insertItemAt("",
			 * 0);
			 * 
			 * for (int i = 0; i < ListMarca.size(); i++) {
			 * cmbMarca.insertItemAt
			 * (ListMarca.get(i).getMarca(),ListMarca.get(i).getCodiPeca());
			 * 
			 * }
			 */

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private String getInsertNewCliente() {
		String strResult = "";
		try {

			if (!ImputValues().equals("") && ImputValues() != null) {

			} else {
				strResult = ImputValues();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return strResult;
	}

	private String ImputValues() {
		String strResult = "";
		try {
			if (!txtNifNew.getText().toString().equals("")) {
				strResult = TDSLanguageUtils.getMessage("cliente.btn.nif");
				return strResult;
			}
			if (!txtNombre.getText().toString().equals("")) {
				strResult = TDSLanguageUtils.getMessage("cliente.btn.nombre");
				return strResult;
			}
			if (!txtApellido.getText().toString().equals("")) {
				strResult = TDSLanguageUtils.getMessage("cliente.btn.apellido");
				return strResult;
			}
			if (!txtDireccion.getText().toString().equals("")) {
				strResult = TDSLanguageUtils
						.getMessage("cliente.btn.direccion");
				return strResult;
			}
			if (!txtCP.getText().toString().equals("")) {
				strResult = TDSLanguageUtils.getMessage("cliente.msg.falta.cp");
				return strResult;
			}
			if (!txtModelo.getText().toString().equals("")) {
				strResult = TDSLanguageUtils
						.getMessage("cliente.msg.falta.modelo");
				return strResult;
			}
			if (cmbMarca.getSelectedIndex() > 0) {
				strResult = TDSLanguageUtils
						.getMessage("cliente.msg.falta.marca");
				return strResult;
			}
			if (!txtMatricula.getText().toString().equals("")) {
				strResult = TDSLanguageUtils
						.getMessage("cliente.msg.falta.matricula");
				return strResult;
			}
			if (!txtBastidor.getText().toString().equals("")) {
				strResult = TDSLanguageUtils
						.getMessage("cliente.msg.falta.bastidor");
				return strResult;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return strResult;
	}
}
