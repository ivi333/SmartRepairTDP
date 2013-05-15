package edu.uoc.tdp.pac4.client;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import javax.swing.JLabel;

import edu.uoc.tdp.pac4.beans.Asseguradora;
import edu.uoc.tdp.pac4.beans.Client;
import edu.uoc.tdp.pac4.beans.Peca;
import edu.uoc.tdp.pac4.common.ItemCombo;
import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import edu.uoc.tdp.pac4.service.GestorAdministracionInterface;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import java.awt.Color;

public class AltaCliente extends JDialog {
	private static int port = 1444;
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
	private JLabel lblPoblacion;
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
	private JComboBox cmbAseguradora;
	private JLabel lblMatricula;
	private JTextField txtMatricula;
	private JLabel lblBastidor;
	private JTextField txtBastidor;
	private JButton btnNewUpd;
	private JButton btnCancelar;
	private JTextField txtPoblacion;

	private ArrayList<ItemCombo> cbMarca;
	private ArrayList<ItemCombo> cbAseguradora;
	private static String NEW_UPD = "";

	public static String getNEW_UPD() {
		return NEW_UPD;
	}

	public void setNEW_UPD(String nEW_UPD) {
		NEW_UPD = nEW_UPD;
	}

	private static GestorAdministracionInterface conexionRemota;
	private JLabel lblTipo;
	private JTextField txtTipo;
	private JLabel lblSeleccionAseg;
	private JLabel lblColor;
	private JTextField txtColor;
	private JLabel lblAnyo;
	private JTextField txtAnyo;
	private JLabel lblAseguradora;

	private JTextField txtMes;
	private JTextField txtDia;
	private boolean isOkCLiente = false;

	public static void main(String args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaCliente frame = new AltaCliente();
					frame.setLocationRelativeTo(null);
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
						port);
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
			CargarCmbAseg();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public AltaCliente(String tipo) {
		try {
			NEW_UPD = tipo;
			seleccionIdioma();
			initialize();
			CargarControles();
			CargarCmbMarca();
			CargarCmbAseg();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void initialize() {
		setSize(new Dimension(422, 781));
	}

	private void seleccionIdioma() {
		Locale localLocale = new Locale("", "");
		TDSLanguageUtils.setDefaultLanguage("i18n/messages");
	}

	private void CargarControles() {
		if (NEW_UPD.equals("NEW"))
			setTitle(TDSLanguageUtils.getMessage("cliente.new.titulo"));
		if (NEW_UPD.equals("UPD"))
			setTitle(TDSLanguageUtils.getMessage("cliente.upd.titulo"));
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);

		contentPanel.setLayout(null);

		lblCliente = new JLabel();
		lblCliente
				.setText(TDSLanguageUtils.getMessage("cliente.lbl.comprobar"));
		lblCliente.setBounds(10, 11, 260, 14);
		contentPanel.add(lblCliente);

		contentPanel.add(getBtnComprobar());

		lblNif = new JLabel();
		lblNif.setText(TDSLanguageUtils.getMessage("cliente.lbl.NIF"));
		lblNif.setBounds(10, 42, 62, 14);
		
		contentPanel.add(lblNif);

		txtNIF = new JTextField();
		txtNIF.setBounds(82, 39, 86, 20);
		contentPanel.add(txtNIF);
		txtNIF.addKeyListener(new KeyAdapterNumbersOnly());
		txtNIF.setColumns(10);

		separator1 = new JSeparator();
		separator1.setBounds(10, 67, 403, 2);
		contentPanel.add(separator1);

		lblDatosCliente = new JLabel();
		lblDatosCliente.setText(TDSLanguageUtils
				.getMessage("cliente.lbl.datos.cliente"));
		lblDatosCliente.setBounds(10, 80, 231, 14);
		contentPanel.add(lblDatosCliente);

		lblId = new JLabel();
		lblId.setText(TDSLanguageUtils.getMessage("cliente.lbl.id"));
		lblId.setBounds(10, 105, 96, 14);
		contentPanel.add(lblId);

		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setEnabled(false);
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
		txtNifNew.addKeyListener(new KeyAdapterNumbersOnly());
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
		lblApellidos.setText(TDSLanguageUtils
				.getMessage("cliente.lbl.apellido"));
		lblApellidos.setBounds(10, 198, 96, 14);
		contentPanel.add(lblApellidos);

		txtApellido = new JTextField();
		txtApellido.setBounds(115, 195, 205, 20);
		contentPanel.add(txtApellido);
		txtApellido.setColumns(10);

		lblDireccion = new JLabel();
		lblDireccion.setText(TDSLanguageUtils
				.getMessage("cliente.lbl.direccion"));
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
		txtCP.addKeyListener(new KeyAdapterNumbersOnly());

		separator = new JSeparator();
		separator.setBounds(10, 336, 403, 2);
		contentPanel.add(separator);

		lblDatosVehiculo = new JLabel();
		lblDatosVehiculo.setText(TDSLanguageUtils
				.getMessage("cliente.lbl.datos.vehiculo"));
		lblDatosVehiculo.setBounds(20, 431, 200, 14);
		contentPanel.add(lblDatosVehiculo);

		lblMarca = new JLabel();
		lblMarca.setText(TDSLanguageUtils
				.getMessage("cliente.lbl.vehiculo.marca"));
		lblMarca.setBounds(20, 473, 96, 14);
		contentPanel.add(lblMarca);

		cmbMarca = new JComboBox();
		cmbMarca.setBounds(114, 470, 168, 20);
		contentPanel.add(cmbMarca);

		lblModelo = new JLabel();
		lblModelo.setText(TDSLanguageUtils
				.getMessage("cliente.lbl.vehiculo.modelo"));
		lblModelo.setBounds(20, 501, 96, 14);
		contentPanel.add(lblModelo);

		txtModelo = new JTextField();
		txtModelo.setBounds(114, 498, 137, 20);
		contentPanel.add(txtModelo);
		txtModelo.setColumns(10);

		lblMatricula = new JLabel();
		lblMatricula.setText(TDSLanguageUtils
				.getMessage("cliente.lbl.vehiculo.matricula"));
		lblMatricula.setBounds(20, 532, 96, 14);
		contentPanel.add(lblMatricula);

		txtMatricula = new JTextField();
		txtMatricula.setBounds(114, 529, 137, 20);
		contentPanel.add(txtMatricula);
		txtMatricula.setColumns(10);

		lblBastidor = new JLabel();
		lblBastidor.setText(TDSLanguageUtils
				.getMessage("cliente.lbl.vehiculo.bastidor"));
		lblBastidor.setBounds(20, 560, 96, 14);
		contentPanel.add(lblBastidor);

		txtBastidor = new JTextField();
		txtBastidor.setBounds(114, 560, 137, 20);
		contentPanel.add(txtBastidor);
		txtBastidor.setColumns(10);

		btnNewUpd = new JButton();
		btnNewUpd.setForeground(new Color(0, 128, 0));

		if (NEW_UPD.equals("NEW"))
			btnNewUpd.setText(TDSLanguageUtils
					.getMessage("cliente.btn.alta.cliente"));
		if (NEW_UPD.equals("UPD"))
			btnNewUpd.setText(TDSLanguageUtils
					.getMessage("cliente.btn.modificar"));

		btnNewUpd.setBounds(10, 700, 117, 23);
		contentPanel.add(btnNewUpd);
		this.btnNewUpd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
				String strMsg = "";
				try {
					if (NEW_UPD.equals("NEW")) {
						strMsg = getNewOrUpdCliente();
						if (!strMsg.equals("")) {
							String tittle = TDSLanguageUtils
									.getMessage("cliente.new.titulo");
							LeerError(strMsg, tittle);
						}else
						{
							MuestraOk( TDSLanguageUtils
									.getMessage("cliente.msg.ok") ,TDSLanguageUtils
									.getMessage("cliente.new.titulo"));
							dispose();
						}
						
					}

					if (NEW_UPD.equals("UPD")) {
						strMsg = getNewOrUpdCliente();
						if (!strMsg.equals("")) {
							String tittle = TDSLanguageUtils
									.getMessage("cliente.upd.titulo");
							LeerError(strMsg, tittle);
						}
						else
							MuestraOk( TDSLanguageUtils
									.getMessage("cliente.msg.ok") ,TDSLanguageUtils
									.getMessage("cliente.upd.titulo"));
						dispose();
					}

				} catch (Exception ex) {
					ex.printStackTrace();
					String titleError = TDSLanguageUtils
							.getMessage("mensaje.Error");
					String msgCombo = TDSLanguageUtils
							.getMessage("mensaje.ErrorCombo");
					LeerError(msgCombo, titleError);
				}

			}
		});

		contentPanel.add(getBtnCancelaJ());

		lblPoblacion = new JLabel();
		lblPoblacion.setBounds(10, 288, 96, 14);
		lblPoblacion.setText(TDSLanguageUtils
				.getMessage("cliente.lbl.vehiculo.poblacion"));
		contentPanel.add(lblPoblacion);

		txtPoblacion = new JTextField();
		txtPoblacion.setBounds(115, 285, 86, 20);
		contentPanel.add(txtPoblacion);
		txtPoblacion.setColumns(10);

		lblTipo = new JLabel();
		lblTipo.setBounds(20, 585, 62, 14);
		lblTipo.setText(TDSLanguageUtils
				.getMessage("cliente.lbl.vehiculo.tipo"));
		contentPanel.add(lblTipo);

		txtTipo = new JTextField();
		txtTipo.setBounds(114, 585, 137, 20);
		contentPanel.add(txtTipo);
		txtTipo.setColumns(10);

		lblColor = new JLabel();
		lblColor.setBounds(20, 619, 62, 14);
		lblColor.setText(TDSLanguageUtils
				.getMessage("cliente.lbl.vehiculo.color"));
		contentPanel.add(lblColor);

		txtColor = new JTextField();
		txtColor.setBounds(114, 616, 137, 20);
		contentPanel.add(txtColor);
		txtColor.setColumns(10);

		lblAnyo = new JLabel();
		lblAnyo.setBounds(20, 650, 62, 14);
		lblAnyo.setText(TDSLanguageUtils
				.getMessage("cliente.lbl.vehiculo.anyo"));
		contentPanel.add(lblAnyo);

		lblAseguradora = new JLabel();
		lblAseguradora.setBounds(10, 376, 146, 14);
		lblAseguradora.setText(TDSLanguageUtils
				.getMessage("cliente.lbl.vehiculo.aseguradora"));
		contentPanel.add(lblAseguradora);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 418, 403, 2);
		contentPanel.add(separator_1);

		cmbAseguradora = new JComboBox();
		cmbAseguradora.setBounds(166, 373, 214, 20);
		contentPanel.add(cmbAseguradora);

		lblSeleccionAseg = new JLabel();
		lblSeleccionAseg.setBounds(10, 351, 292, 14);
		lblSeleccionAseg.setText(TDSLanguageUtils
				.getMessage("cliente.lbl.vehiculo.selec.aseguradora"));
		contentPanel.add(lblSeleccionAseg);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setText("( dd / MM / yyyy )");
		lblNewLabel.setBounds(274, 650, 106, 14);

		contentPanel.add(lblNewLabel);

		txtDia = new JTextField();
		txtDia.setBounds(115, 647, 33, 20);
		contentPanel.add(txtDia);
		txtDia.setColumns(10);
		txtDia.addKeyListener(new KeyAdapterNumbersOnly());

		txtMes = new JTextField();
		txtMes.setBounds(166, 647, 33, 20);
		contentPanel.add(txtMes);
		txtMes.setColumns(10);
		txtMes.addKeyListener(new KeyAdapterNumbersOnly());

		txtAnyo = new JTextField();
		txtAnyo.setBounds(209, 647, 41, 20);
		contentPanel.add(txtAnyo);
		txtAnyo.setColumns(10);
		txtAnyo.addKeyListener(new KeyAdapterNumbersOnly());

		JLabel label = new JLabel("/");
		label.setBounds(156, 650, 12, 14);
		contentPanel.add(label);

		JLabel label_1 = new JLabel("/");
		label_1.setBounds(201, 650, 19, 14);
		contentPanel.add(label_1);

	}

	private JButton getBtnCancelaJ() {
		if (btnCancelar == null) {
			btnCancelar = new JButton();
			btnCancelar.setForeground(Color.RED);
			btnCancelar.setBounds(new Rectangle(236, 700, 107, 23));
			btnCancelar.setText(TDSLanguageUtils
					.getMessage("cliente.btn.cancelar"));
			btnCancelar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();

				}
			});
		}
		return btnCancelar;
	}

	private JButton getBtnComprobar() {

		if (btnComprobar == null) {
			btnComprobar = new JButton();
			btnComprobar.setForeground(new Color(0, 100, 0));
			if (NEW_UPD.equals("NEW"))
				btnComprobar.setText(TDSLanguageUtils
						.getMessage("cliente.btn.comprobar"));
			if (NEW_UPD.equals("UPD"))
				btnComprobar.setText(TDSLanguageUtils
						.getMessage("cliente.btn.consulta"));
			btnComprobar.setBounds(284, 38, 117, 23);
			btnComprobar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					try {
						String strMsg = "";
						String strNIF = (txtNIF.getText());

						if (String.valueOf(strNIF)!=null&& !String.valueOf(strNIF).equals("")) {
							strMsg = getMsgExisteCliente(strNIF);
							if(isOkCLiente)//existe cliente
								MuestraOk(strMsg, TDSLanguageUtils
										.getMessage("cliente.msg.titulo"));
							else
								LeerError(strMsg, TDSLanguageUtils
									.getMessage("cliente.msg.titulo"));
							
							
							if (NEW_UPD.equals("UPD")) {
								if (isOkCLiente)
									getCargarClienteByNIF(strNIF);
							}
						}

						// es necesario el nif

						else {
							String tittle = "";
							if (NEW_UPD.equals("NEW"))
								tittle = TDSLanguageUtils
										.getMessage("cliente.new.titulo");

							if (NEW_UPD.equals("UPD"))
								tittle = TDSLanguageUtils
										.getMessage("cliente.upd.titulo");

							LeerError(TDSLanguageUtils
									.getMessage("cliente.msg.nif"), tittle);
						}

					} catch (Exception ex) {
						ex.printStackTrace();

					}
				}

			});
		}
		return btnComprobar;

	}

	private void getCargarClienteByNIF(String strNIF) {
		try {
			Client client = getRemoto().getDadeClient(strNIF);
			if (client != null) {
				txtID.setText(String.valueOf(client.getNumClient()));
				txtID.setEnabled(false);
				txtNifNew.setText(String.valueOf(client.getNif()));
				txtNifNew.setEnabled(false);
				txtNombre.setText(String.valueOf(client.getNom().toString().trim()));
				txtApellido.setText(String.valueOf(client.getCognoms().toString().trim()));
				txtDireccion.setText(String.valueOf(client.getAdreca().toString().trim()));
				txtPoblacion.setText(String.valueOf(client.getPoblacio().toString().trim()));
				txtCP.setText(String.valueOf(client.getCodiPostal()));
				txtModelo.setText(String.valueOf(client.getModel().toString().trim()));
				txtColor.setText(String.valueOf(client.getColor().toString().trim()));
				txtMatricula.setText(String.valueOf(client.getMatricula().toString().trim()));
				txtTipo.setText(String.valueOf(client.getTipus().toString().trim()));
				txtBastidor.setText(String.valueOf(client.getNum_chasis().toString().trim()));

				String strAnyo = String.valueOf(client.getAnyo());
				String[] arrayAnyo = strAnyo.split("-");
				if (arrayAnyo != null) {
					txtAnyo.setText(arrayAnyo[0]);
					txtMes.setText(arrayAnyo[1]);
					txtDia.setText(arrayAnyo[2]);
				}
				String strMarca = client.getMarca();
				if (!strMarca.equals("")) {
					for (int i = 0; i < cbMarca.size(); i++) {
						String strM=cbMarca.get(i).getValue();
						if (strMarca.equals(strM)) {
							cmbMarca.setSelectedIndex((cbMarca.get(i).getId()));
						
							//JComboBox d =(JComboBox) cmbMarca.getSelectedItem();
							
						}
					}
				}
				
			//	for (int i = 0; i < cbAseguradora.size(); i++) {
				//	if (client.getIdasseguradora() == (cbAseguradora.get(i).getId())) {
						cmbAseguradora.setSelectedIndex(client.getIdasseguradora()-1);
						//cmbAseguradora.getItemAt(Integer.parseInt(cbAseguradora.get(i).getAux()));
						//break;
					//}
				//}
				
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void LeerError(String paramString1, String paramString2) {
		JOptionPane.showMessageDialog(this, paramString1, paramString2, 0);
	}

	private void MuestraOk(String paramString1, String paramString2) {
	        JOptionPane.showMessageDialog(this, paramString1, paramString2, 1);
	    }

	private String getMsgExisteCliente(String strNIF) {
		String strResult = "";
		try {
			isOkCLiente = false;
			boolean bMsg = getRemoto().getExistCliente(strNIF);
			if (bMsg) {
				strResult = TDSLanguageUtils
						.getMessage("cliente.msg.cliente.existe");
				isOkCLiente = true;
			} else {
				strResult = TDSLanguageUtils
						.getMessage("cliente.msg.cliente.noexiste");
				isOkCLiente = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return strResult;
	}

	private void CargarCmbMarca() throws RemoteException {
		ArrayList<Peca> ListMarca = null;
		try {
			cbMarca = null;
			cbMarca = new ArrayList<ItemCombo>();
			ListMarca = getRemoto().getMarcas();
		//	cmbMarca.insertItemAt("", 0);

			for (int i = 0; i < ListMarca.size(); i++) {
				cbMarca.add(new ItemCombo(i, ListMarca.get(i).getMarca(),
						String.valueOf(ListMarca.get(i).getCodiPeca())));
				cmbMarca.insertItemAt(ListMarca.get(i).getMarca(), i);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			String titleError = TDSLanguageUtils
					.getMessage("mensaje.Error");
			String msgCombo = TDSLanguageUtils
					.getMessage("mensaje.ErrorCombo");
			LeerError(msgCombo, titleError);
		}
	}

	private void CargarCmbAseg() throws RemoteException {
		ArrayList<Asseguradora> ListAseg = null;
		try {
			cbAseguradora = null;
			cbAseguradora = new ArrayList<ItemCombo>();
			ListAseg = getRemoto().getAseguradoras();
			//cmbAseguradora.insertItemAt("", 0);

			for (int i = 0; i < ListAseg.size(); i++) {
				cbAseguradora.add(new ItemCombo(i, ListAseg.get(i).getNom(),
						String.valueOf(ListAseg.get(i).getIdasseguradora())));
				cmbAseguradora.insertItemAt(ListAseg.get(i).getNom(), i);

			}

		} catch (Exception ex) {
			
			ex.printStackTrace();
			String titleError = TDSLanguageUtils
					.getMessage("mensaje.Error");
			String msgCombo = TDSLanguageUtils
					.getMessage("mensaje.ErrorCombo");
			LeerError(msgCombo, titleError);
		}
	}

	private String getNewOrUpdCliente() {
		String strResult = "";
		try {

			if (ImputValues().equals("")) {
				if (NEW_UPD.equals("NEW"))
				{
				getRemoto().getNewClient(getObjectClient());
				
				}
				if (NEW_UPD.equals("UPD"))
					getRemoto().getUpdClient(getObjectClient());
				
			} else {
				strResult = ImputValues();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			String titleError = TDSLanguageUtils
					.getMessage("mensaje.Error");
			String msgCombo = TDSLanguageUtils
					.getMessage("mensaje.ErrorCombo");
			LeerError(msgCombo, titleError);
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
			if (cmbMarca.getSelectedIndex() == 0) {
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
			if (cmbAseguradora.getSelectedIndex() == 0) {
				strResult = TDSLanguageUtils
						.getMessage("cliente.msg.falta.aseguradora");
				return strResult;
			}
			if (txtColor.getText().toString().equals("")) {
				strResult = TDSLanguageUtils
						.getMessage("cliente.msg.falta.color");
				return strResult;
			}
			if (txtAnyo.getText().toString().equals("")) {
				strResult = TDSLanguageUtils
						.getMessage("cliente.msg.falta.anyo");
				return strResult;
			}
			if (txtTipo.getText().toString().equals("")) {
				strResult = TDSLanguageUtils
						.getMessage("cliente.msg.falta.tipo");
				return strResult;
			}
			if (txtPoblacion.getText().toString().equals("")) {

				strResult = TDSLanguageUtils
						.getMessage("cliente.msg.falta.poblacion");
				return strResult;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return strResult;
	}

	private Client getObjectClient() {
		Client altaCliente = new Client();
		try {

			altaCliente.setAdreca(txtDireccion.getText().toString());
			altaCliente.setCodiPostal(Integer.parseInt(txtCP.getText()));
			altaCliente.setCognoms(txtApellido.getText().toString());
			altaCliente.setNom(txtNombre.getText().toString());
			altaCliente.setPoblacio(txtPoblacion.getText().toString());
			altaCliente.setNif(txtNifNew.getText());

			altaCliente.setIdasseguradora(1);
			for (int i = 0; i < cbMarca.size(); i++) {
				if (cmbMarca.getSelectedIndex() == cbMarca.get(i).getId()) {
					altaCliente.setMarca(cbMarca.get(i).getValue());
					break;
				}
			}
			for (int i = 0; i < cbAseguradora.size(); i++) {
				if (cmbAseguradora.getSelectedIndex() == cbAseguradora.get(i)
						.getId()) {
					altaCliente.setIdasseguradora(Integer.parseInt(cbAseguradora.get(i).getAux()));
					break;
				}
			}

			altaCliente.setTipus(txtTipo.getText().toString());

			altaCliente.setNum_chasis(txtBastidor.getText().toString());
			altaCliente.setModel(txtModelo.getText().toString());
			altaCliente.setMatricula(txtMatricula.getText().toString());
			altaCliente.setColor(txtColor.getText().toString());
			altaCliente.setPoblacio(txtPoblacion.getText().toString());

			String anyo = txtAnyo.getText().toString();
			String dia = txtDia.getText().toString();
			String mes = txtMes.getText().toString();
			String strFecha = dia + "/" + mes + "/" + anyo;

			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date dt1 = (java.util.Date) formatter.parse(strFecha);
			java.sql.Date DateAnyo = new java.sql.Date(dt1.getTime());

			altaCliente.setAnyo(DateAnyo);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return altaCliente;

	}

	public class KeyAdapterNumbersOnly extends KeyAdapter {

		/**
		 * Regular expression which defines the allowed characters.
		 */
		private String allowedRegex = "[^0-9]";

		/**
		 * Key released on field.
		 */
		public void keyReleased(KeyEvent e) {
			String curText = ((JTextComponent) e.getSource()).getText();
			curText = curText.replaceAll(allowedRegex, "");

			((JTextComponent) e.getSource()).setText(curText);
		}
	}
}
