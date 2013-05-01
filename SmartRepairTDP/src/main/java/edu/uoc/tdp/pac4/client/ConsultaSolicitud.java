package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import javax.swing.JSeparator;

public class ConsultaSolicitud extends JFrame {


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
	private JButton btModificar;
	private JButton btnCancelar;
	private JButton btnValidar;
	private JSeparator separator;
	private JLabel lblConsulta;
	private JLabel lblNSolicitud1;
	private JTextField txtNumero;
	private JButton btnConsulta;
	private JLabel lblEstado;
	private JLabel lblEstadoInfo;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaSolicitud frame = new ConsultaSolicitud();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
		setSize(new Dimension(398, 391));
	}
	private void seleccionIdioma() {
		Locale localLocale = new Locale("", "");
		TDSLanguageUtils.setDefaultLanguage("i18n/messages");
	}
	private void CargarControles()
	{
	setTitle(TDSLanguageUtils.getMessage("solicitud.new.titulo"));
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNSolicitud = new JLabel();
		lblNSolicitud.setText(TDSLanguageUtils.getMessage("solicitud.lbl.numero"));
		lblNSolicitud.setBounds(26, 91, 89, 14);
		contentPane.add(lblNSolicitud);
		
		lblInfoSolicitud = new JLabel();
		lblInfoSolicitud.setText("lblInfoSolicitud");
		lblInfoSolicitud.setBounds(125, 91, 46, 14);
		contentPane.add(lblInfoSolicitud);
		
		lblBastidor = new JLabel();
		lblBastidor.setText(TDSLanguageUtils.getMessage("solicitud.lbl.nbastidor"));
		lblBastidor.setBounds(26, 116, 89, 14);
		contentPane.add(lblBastidor);
		
		lblInfoBastidor = new JLabel();
		lblInfoBastidor.setText("lblInfoBastidor");
		lblInfoBastidor.setBounds(127, 113, 86, 20);
		contentPane.add(lblInfoBastidor);
		
		lblMatricula = new JLabel();
		lblMatricula.setText(TDSLanguageUtils.getMessage("solicitud.lbl.matricula"));
		lblMatricula.setBounds(26, 141, 89, 14);
		contentPane.add(lblMatricula);
		
		lblInfoMatricula= new JLabel();
		lblInfoMatricula.setText("lblInfoMatricula");
		lblInfoMatricula.setBounds(125, 144, 112, 20);
		contentPane.add(lblInfoMatricula);
		
		lblMarca = new JLabel();
		lblMarca.setText(TDSLanguageUtils.getMessage("solicitud.lbl.marca"));
		lblMarca.setBounds(26, 177, 89, 14);
		contentPane.add(lblMarca);
		
		lblInfoMarca = new JLabel();
		lblInfoMarca.setText("lblInfoMarca");
		lblInfoMarca.setBounds(125, 177, 112, 14);
		contentPane.add(lblInfoMarca);
		
		lblModelo = new JLabel();
		lblModelo.setText(TDSLanguageUtils.getMessage("solicitud.lbl.modelo"));
		lblModelo.setBounds(26, 211, 89, 14);
		contentPane.add(lblModelo);
		
		lblInfoModelo = new JLabel();
		lblInfoModelo.setText("lblInfoModelo");
		lblInfoModelo.setBounds(125, 211, 99, 14);
		contentPane.add(lblInfoModelo);
		
		lblComentario = new JLabel();
		lblComentario.setText(TDSLanguageUtils.getMessage("solicitud.lbl.comentarios"));
		lblComentario.setBounds(24, 236, 89, 14);
		contentPane.add(lblComentario);
		
		 textAreaComentario = new JTextArea();
		textAreaComentario.setBounds(123, 236, 212, 76);
		contentPane.add(textAreaComentario);
		
		btModificar = new JButton();
		btModificar.setText(TDSLanguageUtils.getMessage("solicitud.btn.modificar"));
		btModificar.setBounds(24, 323, 89, 23);
		contentPane.add(btModificar);
		
		btnCancelar = new JButton();
		btnCancelar.setText(TDSLanguageUtils.getMessage("solicitud.btn.cancelar"));
		btnCancelar.setBounds(275, 323, 89, 23);
		contentPane.add(btnCancelar);
		
		btnValidar = new JButton();
		btnValidar.setText(TDSLanguageUtils.getMessage("solicitud.btn.validar"));
		btnValidar.setBounds(143, 323, 89, 23);
		contentPane.add(btnValidar);
		
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
		
		txtNumero = new JTextField();
		txtNumero.setBounds(131, 29, 106, 20);
		contentPane.add(txtNumero);
		txtNumero.setColumns(10);
		
		btnConsulta = new JButton();
		btnConsulta.setText(TDSLanguageUtils.getMessage("solicitud.btn.consultar"));
		btnConsulta.setBounds(265, 28, 99, 23);
		contentPane.add(btnConsulta);
		
		lblEstado = new JLabel();
		lblEstado.setText(TDSLanguageUtils.getMessage("solicitud.lbl.estado"));
		lblEstado.setBounds(143, 60, 94, 14);
		contentPane.add(lblEstado);
		
		lblEstadoInfo = new JLabel();
		lblEstadoInfo.setText("lblEstadoInfo");
		lblEstadoInfo.setBounds(265, 62, 79, 14);
		contentPane.add(lblEstadoInfo);
	}

}
