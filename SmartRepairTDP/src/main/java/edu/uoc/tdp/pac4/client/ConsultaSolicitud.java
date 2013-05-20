package edu.uoc.tdp.pac4.client;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import edu.uoc.tdp.pac4.beans.Client;
import edu.uoc.tdp.pac4.beans.Solicitud;
import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import edu.uoc.tdp.pac4.service.GestorAdministracionInterface;

import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ConsultaSolicitud extends JDialog {

	private static int port = 1099;
	 private final static String urlRMIAdmin = new String("rmi://localhost/GestorAdministracion");
private JPanel contentPane;
	private JLabel lblNSolicitud;
	private JLabel lblInfoSolicitud;
	
	private JLabel lblBastidor;
	private JTextField lblInfoBastidor;
	private JLabel lblMatricula;
	private JTextField lblInfoMatricula;
	private JLabel lblMarca;
	private JTextField lblInfoMarca;
	private JLabel lblModelo;
	private JTextField lblInfoModelo;
	private JLabel lblComentario;
	private JTextArea textAreaComentario; 
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JSeparator separator;
	private JLabel lblConsulta;
	private JLabel lblNSolicitud1;
	private JTextField txtNumSol;
	private JButton btnConsulta;
	private JLabel lblEstado;
	private JLabel lblEstadoInfo;
	private static GestorAdministracionInterface conexionRemota;
	private JLabel lblNewLabel;
	private JTextField txtDia;
	private JTextField txtMes;
	private JTextField txtAnyo;
	private JLabel label;
	private JLabel label_1;
	private JLabel lblddMm;
	
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaSolicitud frame = new ConsultaSolicitud();
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

				Registry registry = LocateRegistry.getRegistry(urlRMIAdmin,
						port);
				conexionRemota = (GestorAdministracionInterface) registry
						.lookup("PAC4");

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return conexionRemota;
	}
	
	public ConsultaSolicitud() {
		try{
			initialize();
			seleccionIdioma();
			CargarControles();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
    private void initialize() {
		setSize(new Dimension(398, 441));
	}
	
    private void seleccionIdioma() {
		Locale localLocale = new Locale("", "");
		TDSLanguageUtils.setDefaultLanguage("i18n/messages");
	}
	
    private void CargarControles()
	{
	setTitle(TDSLanguageUtils.getMessage("solicitud.upd.titulo"));
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNSolicitud = new JLabel();
		lblNSolicitud.setText(TDSLanguageUtils.getMessage("solicitud.lbl.numero"));
		lblNSolicitud.setBounds(26, 91, 89, 14);
		contentPane.add(lblNSolicitud);
		
		lblInfoSolicitud = new JLabel();
		lblInfoSolicitud.setBounds(125, 91, 134, 14);
		contentPane.add(lblInfoSolicitud);
		
		lblBastidor = new JLabel();
		lblBastidor.setText(TDSLanguageUtils.getMessage("solicitud.lbl.nbastidor"));
		lblBastidor.setBounds(26, 116, 89, 14);
		contentPane.add(lblBastidor);
		
		lblInfoBastidor = new JTextField();
		lblInfoBastidor.setBounds(127, 116, 132, 20);
		contentPane.add(lblInfoBastidor);
		
		lblMatricula = new JLabel();
		lblMatricula.setText(TDSLanguageUtils.getMessage("solicitud.lbl.matricula"));
		lblMatricula.setBounds(26, 150, 89, 14);
		contentPane.add(lblMatricula);
		
		lblInfoMatricula= new JTextField();
		lblInfoMatricula.setBounds(125, 144, 134, 20);
		contentPane.add(lblInfoMatricula);
		
		lblMarca = new JLabel();
		lblMarca.setText(TDSLanguageUtils.getMessage("solicitud.lbl.marca"));
		lblMarca.setBounds(26, 177, 89, 14);
		contentPane.add(lblMarca);
		
		lblInfoMarca = new JTextField();
		lblInfoMarca.setBounds(125, 175, 134, 20);
		contentPane.add(lblInfoMarca);
		
		lblModelo = new JLabel();
		lblModelo.setText(TDSLanguageUtils.getMessage("solicitud.lbl.modelo"));
		lblModelo.setBounds(26, 211, 89, 14);
		contentPane.add(lblModelo);
		
		lblInfoModelo = new JTextField();
		lblInfoModelo.setBounds(125, 211, 134, 20);
		contentPane.add(lblInfoModelo);
		
		lblComentario = new JLabel();
		lblComentario.setText(TDSLanguageUtils.getMessage("solicitud.lbl.comentarios"));
		lblComentario.setBounds(33, 275, 89, 14);
		contentPane.add(lblComentario);
		
		 textAreaComentario = new JTextArea();
		textAreaComentario.setBounds(132, 275, 212, 76);
		contentPane.add(textAreaComentario);
		
		
		contentPane.add(getBtnGuardar());
		
		contentPane.add(getBtnCancelaJ());
		
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
		txtNumSol.setColumns(10);
		
		txtNumSol.addKeyListener(new KeyAdapterNumbersOnly());	
		contentPane.add(getBtnConsultar());
		
		lblEstado = new JLabel();
		lblEstado.setText(TDSLanguageUtils.getMessage("solicitud.lbl.estado"));
		lblEstado.setBounds(161, 60, 76, 14);
		contentPane.add(lblEstado);
		
		lblEstadoInfo = new JLabel();
		lblEstadoInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEstadoInfo.setForeground(new Color(0, 128, 0));
		lblEstadoInfo.setBounds(265, 60, 101, 16);
		contentPane.add(lblEstadoInfo);
		
		lblNewLabel = new JLabel("F.de finalización:");
		lblNewLabel.setBounds(30, 250, 85, 14);
		contentPane.add(lblNewLabel);
		
		txtDia = new JTextField();
		txtDia.setBounds(127, 244, 30, 20);
		contentPane.add(txtDia);
		txtDia.setColumns(10);
		
		txtMes = new JTextField();
		txtMes.setBounds(181, 244, 24, 20);
		contentPane.add(txtMes);
		txtMes.setColumns(10);
		
		txtAnyo = new JTextField();
		txtAnyo.setBounds(230, 244, 53, 20);
		contentPane.add(txtAnyo);
		txtAnyo.setColumns(10);
		
		label = new JLabel("/");
		label.setBounds(167, 244, 16, 20);
		contentPane.add(label);
		
		label_1 = new JLabel("/");
		label_1.setBounds(215, 244, 16, 20);
		contentPane.add(label_1);
		
		lblddMm = new JLabel("(dd / mm/ yyy)");
		lblddMm.setBounds(298, 244, 82, 20);
		contentPane.add(lblddMm);
		
		
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
									.getMessage("solicitud.upd.titulo");
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
			Solicitud sol=getRemoto().getConsultarSolicitud(numsol);
		
			if(sol!=null)
			{

				txtNumSol.setText(String.valueOf(sol.getNumsol()));
				
				textAreaComentario.setText(sol.getComentaris());
				String NIF = String.valueOf(sol.getClient());
				Client client = getRemoto().getDadeClient(NIF);
				
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
					txtAnyo.setText(arrayAnyo[0]);
					txtMes.setText(arrayAnyo[1]);
					txtDia.setText(arrayAnyo[2]);
				}
				if(sol.isFinalitzada()){
				lblEstadoInfo.setText("Finalizado");
				lblEstadoInfo.setForeground(Color.red);
				Imput(false);
				}
				if(sol.isPendent())
				{	lblEstadoInfo.setText("Pendiente");
				Imput(true);
				}
			}
			else
			{
				String title = TDSLanguageUtils
						.getMessage("solicitud.upd.titulo");
				String strMsg = TDSLanguageUtils
						.getMessage("mensaje.ErrorCombo");
				LeerError(strMsg, title);
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void Imput(boolean b) {
		lblInfoSolicitud.setEnabled(b);
		lblInfoModelo.setEnabled(b);
		lblInfoMatricula.setEnabled(b);
		lblInfoMarca.setEnabled(b);
		lblInfoBastidor.setEnabled(b);
		btnGuardar.setEnabled(b);
		textAreaComentario.setEnabled(b);
		txtAnyo.setEnabled(b);
		txtMes.setEnabled(b);
		txtDia.setEnabled(b);
	}
	
	private JButton getBtnGuardar()
	{	if (btnGuardar == null) {
		btnGuardar = new JButton();
		btnGuardar.setForeground(new Color(0, 128, 0));
		btnGuardar.setBounds(new Rectangle(26, 362, 89, 23));
		btnGuardar.setText(TDSLanguageUtils.getMessage("solicitud.btn.guardar"));
		btnGuardar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						if (ImputValues().equals(""))
						{
							Solicitud sol= new Solicitud();
							sol.setNumsol(Integer.parseInt(txtNumSol.getText().toString()));
							sol.setComentaris(textAreaComentario.getText().toString());
							
							String anyo = txtAnyo.getText().toString();
							String dia = txtDia.getText().toString();
							String mes = txtMes.getText().toString();
							String strFecha = dia + "/" + mes + "/" + anyo;

							DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
							java.util.Date dt1 = (java.util.Date) formatter.parse(strFecha);
							java.sql.Date datafinalitzacio = new java.sql.Date(dt1.getTime());
							sol.setDatafinalitzacio(datafinalitzacio);
							
							getRemoto().getActualizarSolicitud(sol);
							String tittle =  TDSLanguageUtils.getMessage("solicitud.upd.titulo");
							MuestraOk( TDSLanguageUtils.getMessage("cliente.msg.ok"), tittle);
							dispose();
						} else {
							String strMsg = ImputValues();
							String tittle = TDSLanguageUtils
									.getMessage("solicitud.upd.titulo");
							LeerError(strMsg, tittle);
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
			}
		});
	}
	return btnGuardar;
	
	}

	private String ImputValues() {
		String strResult = "";
		try {
			
			if (textAreaComentario.getText().toString().equals("")) {
				strResult = TDSLanguageUtils
						.getMessage("solicitud.msg.falta.comentario");
				return strResult;
			}
			if (txtDia.getText().toString().equals("")) {
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

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strResult;
	}

	private JButton getBtnCancelaJ() {
		if (btnCancelar == null) {
			btnCancelar = new JButton();
			btnCancelar.setForeground(Color.RED);
			btnCancelar.setBounds(new Rectangle(277, 362, 89, 23));
			btnCancelar.setText(TDSLanguageUtils.getMessage("solicitud.btn.cancelar"));
			btnCancelar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
					
				}
			});
		}
		return btnCancelar;
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
