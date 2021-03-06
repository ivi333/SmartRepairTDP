package edu.uoc.tdp.pac4.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

import edu.uoc.tdp.pac4.beans.Client;
import edu.uoc.tdp.pac4.beans.Solicitud;
import edu.uoc.tdp.pac4.beans.Taller;
import edu.uoc.tdp.pac4.common.ItemCombo;
import edu.uoc.tdp.pac4.common.Nif;
import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import edu.uoc.tdp.pac4.service.GestorAdministracionInterface;

public class AltaSolicitud extends JDialog {
	private static int port = 1099;
	 private final static String urlRMIAdmin = new String("rmi://localhost/GestorAdministracion");
	private static boolean B_FINALIZADA=false;
	private static boolean B_PENDIENTE=true;
	private JPanel contentPane;
	private JLabel lblNSolicitud;
	private JLabel lblInfoSolicitud;
	private JLabel lblBastidor;
	private JTextField txtBastidor;
	private JLabel lblMatricula;
	private JLabel lblInfoMatricula;
	private JLabel lblMarca;
	private JLabel lblInfoMarca;
	private JLabel lblModelo;
	private JLabel lblInfoModelo;
	private JLabel lblComentario;
	private JTextArea textAreaComentario; 
	private JButton btnAlta;
	private JButton btnCancelar;
	private static GestorAdministracionInterface conexionRemota;
	private JTextField txtNIF;
	private JButton  btnConsultar;
	private boolean isOkCLiente = false;
	private JLabel lblNewLabel_2;
	private JComboBox cmbTaller;
	private ArrayList<ItemCombo> cbTaller;
	/*private JTextField txtDia;
	private JTextField txtMes;
	private JTextField txtAnyo;*/
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaSolicitud frame = new AltaSolicitud();
					  frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AltaSolicitud() {
		try {
			seleccionIdioma();
			initialize();
			CargarControles();
			initCmbTaller ();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	public AltaSolicitud(GestorAdministracionInterface remoto) {
		try {
			seleccionIdioma();
		initialize();
		conexionRemota=remoto;
		CargarControles();
		initCmbTaller ();

	} catch (Exception ex) {
		ex.printStackTrace();
	}
	}

	private void initialize() {
		setSize(new Dimension(469, 481));
	}
	
	private void seleccionIdioma() {
		Locale localLocale = new Locale("", "");
		//TDSLanguageUtils.setDefaultLanguage("i18n/messages");
	}
	

	
	public void CargarControles() {
		setTitle(TDSLanguageUtils.getMessage("solicitud.new.titulo"));
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNSolicitud = new JLabel();
		lblNSolicitud.setText(TDSLanguageUtils.getMessage("solicitud.lbl.numero"));
		lblNSolicitud.setBounds(24, 56, 89, 14);
		contentPane.add(lblNSolicitud);
		
		lblInfoSolicitud = new JLabel();
		lblInfoSolicitud.setBounds(123, 56, 185, 14);
		contentPane.add(lblInfoSolicitud);
		
		lblBastidor = new JLabel();
		lblBastidor.setText(TDSLanguageUtils.getMessage("solicitud.lbl.nbastidor"));
		lblBastidor.setBounds(24, 81, 89, 14);
		contentPane.add(lblBastidor);
		
		txtBastidor = new JTextField();
		txtBastidor.setEnabled(false);
		txtBastidor.setBounds(125, 78, 208, 20);
		contentPane.add(txtBastidor);
		txtBastidor.setColumns(10);
		
		lblMatricula = new JLabel();
		lblMatricula.setText(TDSLanguageUtils.getMessage("solicitud.lbl.matricula"));
		lblMatricula.setBounds(24, 109, 89, 14);
		contentPane.add(lblMatricula);
		
		lblInfoMatricula= new JLabel();
		lblInfoMatricula.setBounds(123, 106, 150, 20);
		contentPane.add(lblInfoMatricula);
		
		lblMarca = new JLabel();
		lblMarca.setText(TDSLanguageUtils.getMessage("solicitud.lbl.marca"));
		lblMarca.setBounds(24, 137, 89, 14);
		contentPane.add(lblMarca);
		
		lblInfoMarca = new JLabel();
		lblInfoMarca.setBounds(123, 137, 150, 14);
		contentPane.add(lblInfoMarca);
		
		lblModelo = new JLabel();
		lblModelo.setText(TDSLanguageUtils.getMessage("solicitud.lbl.modelo"));
		lblModelo.setBounds(24, 171, 89, 14);
		contentPane.add(lblModelo);
		
		lblInfoModelo = new JLabel();
		lblInfoModelo.setBounds(123, 171, 150, 14);
		contentPane.add(lblInfoModelo);
		
		lblComentario = new JLabel();
		lblComentario.setText(TDSLanguageUtils.getMessage("solicitud.lbl.comentarios"));
		lblComentario.setBounds(24, 210, 89, 14);
		contentPane.add(lblComentario);
		
		 textAreaComentario = new JTextArea();
		textAreaComentario.setBounds(123, 210, 257, 76);
		contentPane.add(textAreaComentario);
		
	
		contentPane.add(getBtnAlta());
		
		contentPane.add(getBtnCancelaJ());
	
		
		JSeparator separator = new JSeparator();
		separator.setBounds(24, 45, 416, 2);
		contentPane.add(separator);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(24, 20, 145, 14);
		lblNewLabel.setText(TDSLanguageUtils.getMessage("cliente.lbl.nif.cliente"));
		contentPane.add(lblNewLabel);
		
		txtNIF = new JTextField();
		txtNIF.setBounds(172, 17, 120, 20);
		txtNIF.setColumns(10);
//		txtNIF.addKeyListener(new KeyAdapterNumbersOnly());
		txtNIF.setDocument(new JTextFieldLimit(9));
		contentPane.add(txtNIF);
		
		contentPane.add(getBtnConsultar());
		
		JLabel lblTaller = new JLabel();
		lblTaller.setText("Taller");
		lblTaller.setBounds(24, 337, 89, 14);
		contentPane.add(lblTaller);
		
		cmbTaller = new JComboBox();
		cmbTaller.setBounds(123, 333, 210, 22);
		contentPane.add(cmbTaller);
		
		/*JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setText("F.de finalizaci�n:");
		lblNewLabel_1.setBounds(24, 207, 106, 14);
		contentPane.add(lblNewLabel_1);
		
		txtDia = new JTextField();
		txtDia.setBounds(123, 204, 33, 20);
		txtDia.addKeyListener(new KeyAdapterNumbersOnly());
		contentPane.add(txtDia);
		txtDia.setColumns(10);
		txtDia.setDocument(new JTextFieldLimit(2));
		txtMes = new JTextField();
		txtMes.setBounds(187, 204, 33, 20);
		txtMes.addKeyListener(new KeyAdapterNumbersOnly());
		contentPane.add(txtMes);
		txtMes.setColumns(10);
		txtMes.setDocument(new JTextFieldLimit(2));
		JLabel label = new JLabel("/");
		label.setBounds(172, 207, 15, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("/");
		label_1.setBounds(230, 207, 15, 14);
		contentPane.add(label_1);
		
		txtAnyo = new JTextField();
		txtAnyo.setBounds(240, 204, 59, 20);
		txtAnyo.addKeyListener(new KeyAdapterNumbersOnly());
		contentPane.add(txtAnyo);
		txtAnyo.setColumns(10);
		txtAnyo.setDocument(new JTextFieldLimit(4));
		JLabel lblddmmyyy = new JLabel("(dd/mm/yyy)");
		lblddmmyyy.setBounds(322, 207, 82, 14);
		contentPane.add(lblddmmyyy);*/
		
	}
	
	private JButton getBtnAlta()
	{
		if (btnAlta == null) {
			btnAlta = new JButton();
			btnAlta.setForeground(new Color(0, 128, 0));
			btnAlta.setBounds(new Rectangle(24, 379, 89, 23));
			btnAlta.setText(TDSLanguageUtils.getMessage("solicitud.btn.alta"));
			btnAlta.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try{
						getNuevoPedido();
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}
			});
		}
		return btnAlta;
	}
	private void getNuevoPedido()
	{
		try{
			
			if(ImputValues().equals(""))
			{ 
				Solicitud sol= new Solicitud();
				sol.setNifCliente((txtNIF.getText().toString()));
				sol.setComentaris(textAreaComentario.getText().toString());
				java.util.Date dt = new java.util.Date();
				java.sql.Date dateAlta = new java.sql.Date(dt.getTime());
				sol.setDataalta(dateAlta);
				sol.setPendent(B_PENDIENTE);
				sol.setFinalitzada(B_FINALIZADA);
				sol.setTaller(Integer.valueOf(cbTaller.get(cmbTaller.getSelectedIndex()).getAux()));
				
			//	String anyo = txtAnyo.getText().toString();
				//	String dia = txtDia.getText().toString();
				//	String mes = txtMes.getText().toString();
				//	String strFecha = dia + "/" + mes + "/" + anyo;

				//	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				//java.util.Date dt1 = (java.util.Date) formatter.parse(strFecha);
				//java.sql.Date datafinalitzacio = new java.sql.Date(dt1.getTime());
				//	sol.setDatafinalitzacio(datafinalitzacio);
				
				if(conexionRemota.getNuevoSolicitud(sol)==1)
				{  String tittle =  TDSLanguageUtils.getMessage("solicitud.new.titulo");
					MuestraOk( TDSLanguageUtils.getMessage("cliente.msg.ok"), tittle);
					
				}
				dispose();
			}
			else
			{
				String strMsg=ImputValues();
				String tittle =  TDSLanguageUtils.getMessage("solicitud.new.titulo");
				LeerError(strMsg, tittle);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private String ImputValues() {
		String strResult = "";
		try {
			if (txtNIF.getText().toString().equals("")) {
				strResult = TDSLanguageUtils
						.getMessage("solicitud.msg.falta.nif");
				return strResult;

			}
			if(!Nif.validar(txtNIF.getText().toString()))
			{
				strResult = TDSLanguageUtils
						.getMessage("solicitud.msg.nif.error");
				return strResult;
			}
			if (textAreaComentario.getText().toString().equals("")) {
				strResult = TDSLanguageUtils
						.getMessage("solicitud.msg.falta.comentario");
				return strResult;
			}
			/*if (txtDia.getText().toString().equals("")) {
				strResult = TDSLanguageUtils
						.getMessage("solicitud.msg.falta.ffinalizacion");
				return strResult;

			}
			if (txtMes.getText().toString().equals("")) {
				strResult = TDSLanguageUtils
						.getMessage("solicitud.msg.falta.ffinalizacion");
				return strResult;

			}
			if (txtAnyo.getText().toString().equals("")) {
				strResult = TDSLanguageUtils
						.getMessage("solicitud.msg.falta.ffinalizacion");
				return strResult;

			}*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strResult;
	}
	
	private JButton getBtnCancelaJ() {
		if (btnCancelar == null) {
			btnCancelar = new JButton();
			btnCancelar.setForeground(Color.RED);
			btnCancelar.setBounds(new Rectangle(291, 379, 89, 23));
			btnCancelar.setText(TDSLanguageUtils.getMessage("solicitud.btn.cancelar"));
			btnCancelar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
					
				}
			});
		}
		return btnCancelar;
	}

	private JButton getBtnConsultar() {
	
			if (btnConsultar == null) {
				btnConsultar = new JButton();
				btnConsultar.setBounds(306, 16, 134, 23);
				btnConsultar.setText(TDSLanguageUtils
						.getMessage("solicitud.btn.consultar"));
				btnConsultar
						.addActionListener(new java.awt.event.ActionListener() {
							public void actionPerformed(
									java.awt.event.ActionEvent e) {
								try {
									String strMsg = "";
									String strNIF = txtNIF.getText().toString();
									if (!strNIF.equals("") && strNIF != null) {
										strMsg = getMsgExisteCliente((strNIF));
										MuestraOk(strMsg, TDSLanguageUtils.getMessage("solicitud.new.titulo"));
											if (isOkCLiente)
												getCargarClienteByNIF((strNIF));
										}
									
									// es necesario el nif

									else {
										String tittle =  TDSLanguageUtils.getMessage("solicitud.new.titulo");
										LeerError(TDSLanguageUtils
												.getMessage("solicitud.msg.falta.nif"), tittle);
									}
								} catch (Exception ex) {

								}
							}
						});
			}
		return btnConsultar;
	}

	private void getCargarClienteByNIF(String strNIF) {
		try {
			Client client=conexionRemota.getDadeClient(strNIF);
			if (client != null)
					{
				lblInfoModelo.setText(client.getModel().toString().trim());
				lblInfoMatricula.setText(client.getMatricula().toString().trim());
				lblInfoMarca.setText(client.getMarca().toString().trim());
				txtBastidor.setText(client.getNum_chasis().toString().trim());
					}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void initCmbTaller () {
		cbTaller = new ArrayList<ItemCombo>();
		List<Taller> talleres;
		try {
			talleres = conexionRemota.getAllTallers();
			//cbTaller.add(new ItemCombo(0, "", "0"));
			for (int i= 0; i < talleres.size(); i ++)
				cbTaller.add(new ItemCombo(i+1,talleres.get(i).getCif(),String.valueOf(talleres.get(i).getId())));
			for (int i=0; i < cbTaller.size(); i++) {
				cmbTaller.insertItemAt(cbTaller.get(i).getValue(), i);				
			}
			cmbTaller.setSelectedIndex(0);
		} catch (Exception e) {
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
			if(!Nif.validar(txtNIF.getText().toString()))
			{
				strResult = TDSLanguageUtils
						.getMessage("solicitud.msg.nif.error");
				return strResult;
			}
			else
			{
			isOkCLiente = false;
			boolean bMsg = conexionRemota.getExistCliente(strNIF);
			if (bMsg) {
				strResult = TDSLanguageUtils
						.getMessage("cliente.msg.cliente.existe");
				isOkCLiente = true;
			} else {
				strResult = TDSLanguageUtils
						.getMessage("cliente.msg.cliente.noexiste");
				isOkCLiente = false;
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return strResult;
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
		class JTextFieldLimit extends PlainDocument {
			  private int limit;
			  JTextFieldLimit(int limit) {
			    super();
			    this.limit = limit;
			  }

			  JTextFieldLimit(int limit, boolean upper) {
			    super();
			    this.limit = limit;
			  }

			  public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
			    if (str == null)
			      return;

			    if ((getLength() + str.length()) <= limit) {
			      super.insertString(offset, str, attr);
			    }
			  }
			}
}
