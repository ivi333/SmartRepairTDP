package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class AltaSolicitud extends JFrame {

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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaSolicitud frame = new AltaSolicitud();
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
		setSize(new Dimension(337, 342));
	}
	private void seleccionIdioma() {
		Locale localLocale = new Locale("", "");
		TDSLanguageUtils.setDefaultLanguage("i18n/messages");
	}
	public void CargarControles() {
		setTitle(TDSLanguageUtils.getMessage("solicitud.new.titulo"));
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNSolicitud = new JLabel();
		lblNSolicitud.setText(TDSLanguageUtils.getMessage("solicitud.lbl.numero"));
		lblNSolicitud.setBounds(24, 11, 89, 14);
		contentPane.add(lblNSolicitud);
		
		lblInfoSolicitud = new JLabel();
		lblInfoSolicitud.setText("lblInfoSolicitud");
		lblInfoSolicitud.setBounds(123, 11, 46, 14);
		contentPane.add(lblInfoSolicitud);
		
		lblBastidor = new JLabel();
		lblBastidor.setText(TDSLanguageUtils.getMessage("solicitud.lbl.nbastidor"));
		lblBastidor.setBounds(24, 36, 89, 14);
		contentPane.add(lblBastidor);
		
		txtBastidor = new JTextField();
		txtBastidor.setBounds(125, 33, 86, 20);
		contentPane.add(txtBastidor);
		txtBastidor.setColumns(10);
		
		lblMatricula = new JLabel();
		lblMatricula.setText(TDSLanguageUtils.getMessage("solicitud.lbl.matricula"));
		lblMatricula.setBounds(24, 61, 89, 14);
		contentPane.add(lblMatricula);
		
		lblInfoMatricula= new JLabel();
		lblInfoMatricula.setText("lblInfoMatricula");
		lblInfoMatricula.setBounds(123, 64, 112, 20);
		contentPane.add(lblInfoMatricula);
		
		lblMarca = new JLabel();
		lblMarca.setText(TDSLanguageUtils.getMessage("solicitud.lbl.marca"));
		lblMarca.setBounds(24, 97, 89, 14);
		contentPane.add(lblMarca);
		
		lblInfoMarca = new JLabel();
		lblInfoMarca.setText("lblInfoMarca");
		lblInfoMarca.setBounds(123, 97, 112, 14);
		contentPane.add(lblInfoMarca);
		
		lblModelo = new JLabel();
		lblModelo.setText(TDSLanguageUtils.getMessage("solicitud.lbl.modelo"));
		lblModelo.setBounds(24, 131, 89, 14);
		contentPane.add(lblModelo);
		
		lblInfoModelo = new JLabel();
		lblInfoModelo.setText("lblInfoModelo");
		lblInfoModelo.setBounds(123, 131, 99, 14);
		contentPane.add(lblInfoModelo);
		
		lblComentario = new JLabel();
		lblComentario.setText(TDSLanguageUtils.getMessage("solicitud.lbl.comentarios"));
		lblComentario.setBounds(24, 167, 89, 14);
		contentPane.add(lblComentario);
		
		 textAreaComentario = new JTextArea();
		textAreaComentario.setBounds(123, 167, 185, 76);
		contentPane.add(textAreaComentario);
		
		btnAlta = new JButton();
		btnAlta.setText(TDSLanguageUtils.getMessage("solicitud.btn.alta"));
		btnAlta.setBounds(24, 260, 89, 23);
		contentPane.add(btnAlta);
		
		btnCancelar = new JButton();
		btnCancelar.setText(TDSLanguageUtils.getMessage("solicitud.btn.cancelar"));
		btnCancelar.setBounds(204, 260, 89, 23);
		contentPane.add(btnCancelar);
		
	}
}
