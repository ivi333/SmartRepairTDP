package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import edu.uoc.tdp.pac4.beans.Client;
import edu.uoc.tdp.pac4.beans.Reparacio;
import edu.uoc.tdp.pac4.beans.Solicitud;
import edu.uoc.tdp.pac4.beans.TipusReparacio;
import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import edu.uoc.tdp.pac4.service.GestorAdministracionInterface;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class BajaSolicitud extends JDialog {

	private JPanel contentPane;
	private JLabel lblNSolicitud;
	private JLabel lblInfoSolicitud;
	
	private JLabel lblBastidor;
	private JLabel lblInfoBastidor;
	private JLabel lblMatricula;
	private JLabel lblInfoMatricula;
	private JLabel lblMarca;
	private JLabel lblInfoMarca;
	private JLabel lblModelo;
	private JLabel lblInfoModelo;
	private JLabel lblComentario;
	private JTextArea textAreaComentario; 
	private JButton btnAceptar;
	private JButton btnFacturar;
	private JButton btnCerrar;
	private JSeparator separator;
	private JLabel lblConsulta;
	private JLabel lblNSolicitud1;
	private JTextField txtNumSol;
	private JButton btnConsulta;
	private JLabel lblEstado;
	private JLabel lblEstadoInfo;
	private JLabel lblInfofechafin ;
	private JLabel lblComentarioF;
	private JTextArea txtAreaComentarioF;
	private boolean bEstadoPendiente=false;
	private static GestorAdministracionInterface conexionRemota;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BajaSolicitud frame = new BajaSolicitud();
					  frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	private void initialize() {
		setSize(new Dimension(398, 526));
	}

	private void seleccionIdioma() {
		Locale localLocale = new Locale("", "");
		TDSLanguageUtils.setDefaultLanguage("i18n/messages");
	}
	
	public BajaSolicitud() {
		
		try{
			initialize();
		
			seleccionIdioma();
			CargarControles();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	
	public BajaSolicitud(GestorAdministracionInterface remoto) {
		try{
			initialize();
			conexionRemota=remoto;
			seleccionIdioma();
			CargarControles();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	private void CargarControles()
  {
    setTitle(TDSLanguageUtils.getMessage("solicitud.baja.titulo"));
	
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	lblNSolicitud = new JLabel();
	lblNSolicitud.setText(TDSLanguageUtils.getMessage("solicitud.lbl.numero"));
	lblNSolicitud.setBounds(26, 91, 89, 14);
	contentPane.add(lblNSolicitud);
	
	lblInfoSolicitud = new JLabel();
	lblInfoSolicitud.setBounds(125, 91, 112, 14);
	contentPane.add(lblInfoSolicitud);
	
	lblBastidor = new JLabel();
	lblBastidor.setText(TDSLanguageUtils.getMessage("solicitud.lbl.nbastidor"));
	lblBastidor.setBounds(26, 116, 89, 14);
	contentPane.add(lblBastidor);
	
	lblInfoBastidor = new JLabel();
	lblInfoBastidor.setBounds(127, 113, 110, 17);
	contentPane.add(lblInfoBastidor);
	
	lblMatricula = new JLabel();
	lblMatricula.setText(TDSLanguageUtils.getMessage("solicitud.lbl.matricula"));
	lblMatricula.setBounds(26, 144, 89, 14);
	contentPane.add(lblMatricula);
	
	lblInfoMatricula= new JLabel();
	lblInfoMatricula.setBounds(125, 144, 112, 14);
	contentPane.add(lblInfoMatricula);
	
	lblMarca = new JLabel();
	lblMarca.setText(TDSLanguageUtils.getMessage("solicitud.lbl.marca"));
	lblMarca.setBounds(26, 169, 89, 14);
	contentPane.add(lblMarca);
	
	lblInfoMarca = new JLabel();
	lblInfoMarca.setBounds(125, 169, 112, 14);
	contentPane.add(lblInfoMarca);
	
	lblModelo = new JLabel();
	lblModelo.setText(TDSLanguageUtils.getMessage("solicitud.lbl.modelo"));
	lblModelo.setBounds(26, 194, 89, 14);
	contentPane.add(lblModelo);
	
	lblInfoModelo = new JLabel();
	lblInfoModelo.setBounds(125, 194, 99, 14);
	contentPane.add(lblInfoModelo);
	
	lblComentario = new JLabel();
	lblComentario.setText(TDSLanguageUtils.getMessage("solicitud.lbl.comentarios"));
	lblComentario.setBounds(26, 252, 89, 14);
	contentPane.add(lblComentario);
	
	 textAreaComentario = new JTextArea();
	textAreaComentario.setBounds(132, 252, 212, 61);
	contentPane.add(textAreaComentario);
	
	contentPane.add(getBtnAceptar());
	
	contentPane.add(getBtnFacturar());
	
	
	contentPane.add(getBtnCerrar());
	
	separator = new JSeparator();
	separator.setBounds(26, 83, 340, 2);
	contentPane.add(separator);
	
	lblConsulta = new JLabel();
	lblConsulta.setText(TDSLanguageUtils.getMessage("solicitud.lbl.consulta"));
	lblConsulta.setBounds(26, 11, 134, 14);
	contentPane.add(lblConsulta);
	
	lblNSolicitud1 = new JLabel();
	lblNSolicitud1.setText(TDSLanguageUtils.getMessage("solicitud.lbl.numero"));
	lblNSolicitud1.setBounds(26, 32, 89, 14);
	contentPane.add(lblNSolicitud1);
	
	txtNumSol = new JTextField();
	txtNumSol.setBounds(131, 29, 106, 20);
	contentPane.add(txtNumSol);
	txtNumSol.addKeyListener(new KeyAdapterNumbersOnly());	
	txtNumSol.setColumns(10);
	
	
	contentPane.add(getBtnConsultar());
	
	lblEstado = new JLabel();
	lblEstado.setText(TDSLanguageUtils.getMessage("solicitud.lbl.estado"));
	lblEstado.setBounds(143, 60, 94, 14);
	contentPane.add(lblEstado);
	
	lblEstadoInfo = new JLabel();
	lblEstadoInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblEstadoInfo.setForeground(new Color(0, 128, 0));
	lblEstadoInfo.setBounds(265, 62, 101, 14);
	contentPane.add(lblEstadoInfo);
	
	JLabel label = new JLabel("F.de finalización:");
	label.setBounds(26, 227, 99, 14);
	contentPane.add(label);
	
	lblInfofechafin = new JLabel();
	lblInfofechafin.setBounds(135, 227, 134, 14);
	contentPane.add(lblInfofechafin);
	
	 lblComentarioF = new JLabel("New label");
	lblComentarioF.setBounds(26, 334, 211, 14);
	lblComentarioF.setText(TDSLanguageUtils.getMessage("solicitud.lbl.comentario.fac"));
	
	contentPane.add(lblComentarioF);
	
	txtAreaComentarioF = new JTextArea();
	txtAreaComentarioF.setBounds(127, 359, 217, 71);
	contentPane.add(txtAreaComentarioF);
}
	private JButton getBtnFacturar()
	{
		if (btnFacturar == null) {
			btnFacturar = new JButton();
			btnFacturar.setForeground(new Color(0, 128, 0));
			btnFacturar.setBounds(new Rectangle(178, 458, 89, 23));
			btnFacturar.setText(TDSLanguageUtils.getMessage("solicitud.btn.facturar"));
			btnFacturar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (ImputValues().equals("")) {	
						if(!txtAreaComentarioF.getText().toString().trim().equals(""))
						{
							getFacturar();
							String title = TDSLanguageUtils
									.getMessage("solicitud.baja.titulo");
							String strMsg = TDSLanguageUtils
									.getMessage("solicitud.msg.facturacion");
							MuestraOk(strMsg, title);
							dispose();
						}
						else
						{
							String title = TDSLanguageUtils
									.getMessage("solicitud.baja.titulo");
							String strMsg = TDSLanguageUtils
									.getMessage("solicitud.msg.falta.fac");
							LeerError(strMsg, title);
						}
						
					}else
					{
						String title = TDSLanguageUtils
								.getMessage("solicitud.baja.titulo");
						String strMsg = TDSLanguageUtils
								.getMessage("solicitud.msg.falta.num");
						LeerError(strMsg, title);
					}
					
				}
			});
		}
			return btnFacturar;
	}
	
	private void getFacturar()
	{ Solicitud sol = new Solicitud();
	
		try{
			
			sol.setComentaris(textAreaComentario.getText());
			sol.setFinalitzada(true);
			sol.setNumsol(Integer.valueOf(txtNumSol.getText()));
			String txtFactura=txtAreaComentarioF.getText().toString();
			int value=conexionRemota.getFacturarSolicitud(sol,txtFactura);
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	private JButton getBtnAceptar()
	{
		if (btnAceptar == null) {
			btnAceptar = new JButton();
			btnAceptar.setForeground(new Color(0, 0, 255));
			btnAceptar.setBounds(new Rectangle(33, 458, 127, 23));
			btnAceptar.setText(TDSLanguageUtils.getMessage("solicitud.btn.baja"));
			btnAceptar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
				try{
						if (ImputValues().equals("")) {
							if (bEstadoPendiente) {

							} else {
								String title = TDSLanguageUtils
										.getMessage("solicitud.baja.titulo");
								String strMsg = TDSLanguageUtils
										.getMessage("solicitud.msg.baja.solicitud.estadoP");
								LeerError(strMsg, title);
							}
						} else {
							String title = TDSLanguageUtils
									.getMessage("solicitud.baja.titulo");
							String strMsg = TDSLanguageUtils
									.getMessage("solicitud.msg.falta.num");
							LeerError(strMsg, title);
						}
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}
					
				}
			});
		}
			
		return btnAceptar;
	}
	
	private String ImputValues() {
		String strResult = "";
		try {
			if(txtNumSol.getText().toString().equals(""))
			{
				strResult=TDSLanguageUtils
						.getMessage("solicitud.msg.falta.num");
				return strResult;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return strResult;
	}
	
	private  JButton getBtnCerrar()	
	{if (btnCerrar == null) {
		btnCerrar = new JButton();
		btnCerrar.setForeground(Color.RED);
		btnCerrar.setBounds(new Rectangle(277, 458, 89, 23));
		btnCerrar.setText(TDSLanguageUtils.getMessage("solicitud.btn.cerrar"));
		btnCerrar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
			dispose();
				
			}
		});
	}
		return btnCerrar;
		
	}
	
	private JButton getBtnConsultar()
	{
		if (btnConsulta == null) {
			btnConsulta = new JButton();
			btnConsulta.setBounds(new Rectangle(265, 28, 99, 23));
			btnConsulta.setText(TDSLanguageUtils.getMessage("solicitud.btn.consultar"));
			btnConsulta.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					try{
						if (!txtNumSol.getText().toString().equals("")) {
							getConsultarSolicitud();
						} else {
							String title = TDSLanguageUtils
									.getMessage("solicitud.baja.titulo");
							String strMsg = TDSLanguageUtils
									.getMessage("solicitud.msg.falta.num");
							LeerError(strMsg, title);
						}

					}catch(Exception ex)
					{
						ex.printStackTrace();
					}
					
				}
			});
		}
			return btnConsulta;
	}
	
	private void getConsultarSolicitud()
	{
		try{
			int numsol=Integer.valueOf(txtNumSol.getText().toString());
			Solicitud sol=conexionRemota.getConsultarSolicitud(numsol);
		
			if(sol!=null)
			{

				txtNumSol.setText(String.valueOf(sol.getNumsol()));
				
				textAreaComentario.setText(sol.getComentaris());
				String NIF = String.valueOf(sol.getClient());
				Client client = conexionRemota.getDadeClient(NIF);
				
				if (client != null) {
				
					lblInfoSolicitud.setText(String.valueOf(sol.getNumsol()));
					lblInfoModelo.setText(client.getModel().toString().trim());
					lblInfoMatricula.setText(client.getMatricula().toString()
							.trim());
					lblInfoMarca.setText(client.getMarca().toString().trim());
					lblInfoBastidor.setText(client.getNum_chasis().toString()
							.trim());
				}
				else
				{   lblInfoSolicitud.setText("");
					lblInfoModelo.setText("");
					lblInfoMatricula.setText("");
					lblInfoMarca.setText("");
					lblInfoBastidor.setText("");
				}
				String strAnyo = String.valueOf(sol.getDatafinalitzacio());
				String[] arrayAnyo = strAnyo.split("-");
				if (arrayAnyo != null) {
					String anyo=(arrayAnyo[0]);
					String mes=(arrayAnyo[1]);
					String dia=(arrayAnyo[2]);
					
					lblInfofechafin.setText(dia+"/"+mes+"/" +anyo);
				}
				int numreparacio=sol.getNumreparacio();
				
				Reparacio r= conexionRemota.getReparacionByCodeReparacion(numreparacio);
				String strEstado="";
				/*
				 * En Espera Solicitud.pendent=true 
				 * Solicitud.finalitzada=false
				 * Reparacio.acceptada=false
				 *  Reparacio.assignada=false 
				 * En Curso
				 * Solicitud.pendent=false
				 *  Solicitud.finalitzada=false
				 * Reparacio.acceptada=true 
				 * Reparacio.assignada=true
				 *  Recibidas
				 * Solicitud.pendent=false 
				 * Solicitud.finalitzada=false
				 * Reparacio.acceptada=true 
				 * Reparacio.assignada=false
				 * Finalizadas 
				 * Solicitud.pendent=false
				 * Solicitud.finalitzada=true
				 * Reparacio.acceptada=true 
				 * Reparacio.assignada=true
				 
				 * Rechazadas 
				 * Reparacio.acceptada=false
				 * Reparacio.assignada=false
				 * Solicitud.pendent=false 
				 * Solicitud.finalitzada=true
				 */
				btnAceptar.setEnabled(true);
				if (r != null) {
					
					//EN ESPERA
					if (!r.getAssignada() && !sol.isFinalitzada()
							&& sol.isPendent() && !r.getAcceptada()) {
						strEstado = TipusReparacio.EnEspera.toString();
						lblEstadoInfo.setText(strEstado);
						btnAceptar.setEnabled(true);
					}
					//EN CURSO
					if (r.getAcceptada() && r.getAssignada()
							&& !sol.isPendent() && !sol.isFinalitzada()) {
						strEstado = TipusReparacio.EnCurs.toString();
						lblEstadoInfo.setText(strEstado);
						btnAceptar.setEnabled(true);
					}
					//RECIBIDAS
					if (!r.getAssignada() && !sol.isFinalitzada()
							&& !sol.isPendent() && r.getAcceptada()) {
						strEstado = "Recibidas";//TipusReparacio..toString();
						lblEstadoInfo.setText(strEstado);
						btnAceptar.setEnabled(true);
					}
					
					//FINALIZADAS
					if (r.getAssignada() && sol.isFinalitzada()
							&& !sol.isPendent() && r.getAcceptada()) {
						strEstado = TipusReparacio.Finalitzades.toString();
						lblEstadoInfo.setText(strEstado);
						btnAceptar.setEnabled(false);
					}
					//RECHAZADAS
					if (!r.getAssignada() && sol.isFinalitzada()
							&& !sol.isPendent() && !r.getAcceptada()) {
						strEstado = TipusReparacio.Rebutjades.toString();
						lblEstadoInfo.setText(strEstado);
						btnAceptar.setEnabled(false);
					}

				}
				else
				{
					if (!sol.isFinalitzada() && sol.isPendent()) {
						strEstado = TipusReparacio.EnEspera.toString();
						lblEstadoInfo.setText(strEstado);
						btnAceptar.setEnabled(true);
					}
					
					if (sol.isFinalitzada() && !sol.isPendent()) {
						strEstado = TipusReparacio.Rebutjades.toString();
						lblEstadoInfo.setText(strEstado);
						btnAceptar.setEnabled(false);
					}
				}
				
				
			}
			else
			{
				clearControles();
				String title = TDSLanguageUtils
						.getMessage("solicitud.baja.titulo");
				String strMsg = TDSLanguageUtils
						.getMessage("solicitud.msg.cliente.noexiste");
				LeerError(strMsg, title);
				
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	private void clearControles()
	{
		txtAreaComentarioF.setText("");
		textAreaComentario.setText("");
		
	}
	
	private void LeerError(String paramString1, String paramString2) {
		JOptionPane.showMessageDialog(this, paramString1, paramString2, 0);
	}

	private void MuestraOk(String paramString1, String paramString2) {
		JOptionPane.showMessageDialog(this, paramString1, paramString2, 1);
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
