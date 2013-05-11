package edu.uoc.tdp.pac4.client;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import edu.uoc.tdp.pac4.beans.Client;
import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import edu.uoc.tdp.pac4.service.GestorAdministracionInterface;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class AltaSolicitud extends JDialog {
	private static int port = 1454;
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
		try {seleccionIdioma();
			initialize();
			CargarControles();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	private void initialize() {
		setSize(new Dimension(424, 391));
	}
	
	private void seleccionIdioma() {
		Locale localLocale = new Locale("", "");
		TDSLanguageUtils.setDefaultLanguage("i18n/messages");
	}
	
	public static GestorAdministracionInterface getRemoto() throws RemoteException, NotBoundException {
	       try{
	    	   
	    	   
		   if(conexionRemota == null) {
	           
			   Registry registry = LocateRegistry.getRegistry("localhost",
						port);
				conexionRemota = (GestorAdministracionInterface) registry
						.lookup("PAC4");
	           
		   }
	       }catch(Exception ex)
	       {
	    	   ex.printStackTrace();
	       }
	        return conexionRemota;
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
		lblInfoSolicitud.setText("lblInfoSolicitud");
		lblInfoSolicitud.setBounds(123, 56, 185, 14);
		contentPane.add(lblInfoSolicitud);
		
		lblBastidor = new JLabel();
		lblBastidor.setText(TDSLanguageUtils.getMessage("solicitud.lbl.nbastidor"));
		lblBastidor.setBounds(24, 81, 89, 14);
		contentPane.add(lblBastidor);
		
		txtBastidor = new JTextField();
		txtBastidor.setBounds(125, 78, 208, 20);
		contentPane.add(txtBastidor);
		txtBastidor.setColumns(10);
		
		lblMatricula = new JLabel();
		lblMatricula.setText(TDSLanguageUtils.getMessage("solicitud.lbl.matricula"));
		lblMatricula.setBounds(24, 109, 89, 14);
		contentPane.add(lblMatricula);
		
		lblInfoMatricula= new JLabel();
		lblInfoMatricula.setText("lblInfoMatricula");
		lblInfoMatricula.setBounds(123, 106, 150, 20);
		contentPane.add(lblInfoMatricula);
		
		lblMarca = new JLabel();
		lblMarca.setText(TDSLanguageUtils.getMessage("solicitud.lbl.marca"));
		lblMarca.setBounds(24, 137, 89, 14);
		contentPane.add(lblMarca);
		
		lblInfoMarca = new JLabel();
		lblInfoMarca.setText("lblInfoMarca");
		lblInfoMarca.setBounds(123, 137, 150, 14);
		contentPane.add(lblInfoMarca);
		
		lblModelo = new JLabel();
		lblModelo.setText(TDSLanguageUtils.getMessage("solicitud.lbl.modelo"));
		lblModelo.setBounds(24, 171, 89, 14);
		contentPane.add(lblModelo);
		
		lblInfoModelo = new JLabel();
		lblInfoModelo.setText("lblInfoModelo");
		lblInfoModelo.setBounds(123, 171, 150, 14);
		contentPane.add(lblInfoModelo);
		
		lblComentario = new JLabel();
		lblComentario.setText(TDSLanguageUtils.getMessage("solicitud.lbl.comentarios"));
		lblComentario.setBounds(24, 196, 89, 14);
		contentPane.add(lblComentario);
		
		 textAreaComentario = new JTextArea();
		textAreaComentario.setBounds(123, 196, 257, 76);
		contentPane.add(textAreaComentario);
		
		btnAlta = new JButton();
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAlta.setText(TDSLanguageUtils.getMessage("solicitud.btn.alta"));
		btnAlta.setBounds(24, 283, 89, 23);
		contentPane.add(btnAlta);
		
		contentPane.add(getBtnCancelaJ());
		
		JSeparator separator = new JSeparator();
		separator.setBounds(24, 45, 368, 2);
		contentPane.add(separator);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(24, 20, 145, 14);
		lblNewLabel.setText(TDSLanguageUtils.getMessage("cliente.lbl.nif.cliente"));
		contentPane.add(lblNewLabel);
		
		txtNIF = new JTextField();
		txtNIF.setBounds(172, 17, 120, 20);
		txtNIF.setColumns(10);
		contentPane.add(txtNIF);
		
		
		contentPane.add(getBtnConsultar());
		
	}
	private JButton getBtnCancelaJ() {
		if (btnCancelar == null) {
			btnCancelar = new JButton();
			btnCancelar.setBounds(new Rectangle(291, 283, 89, 23));
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
		try {
			if (btnConsultar == null) {
				btnConsultar = new JButton();
				btnConsultar.setBounds(302, 11, 104, 23);
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
										strMsg = getMsgExisteCliente(strNIF);
										MuestraOk(strMsg, TDSLanguageUtils.getMessage("solicitud.new.titulo"));
											if (isOkCLiente)
												getCargarClienteByNIF(strNIF);
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
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return btnConsultar;
	}

	private void getCargarClienteByNIF(String strNIF) {
		try {
			Client client=getRemoto().getDadeClient(strNIF);
			if (client != null)
					{
				lblModelo.setText(client.getModel().toString().trim());
				lblMatricula.setText(client.getMatricula().toString().trim());
				lblMarca.setText(client.getMarca().toString().trim());
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
}
