package edu.uoc.tdp.pac4.client;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import edu.uoc.tdp.pac4.common.TDSLanguageUtils;

public class GestionAvisos extends JFrame {

	private JPanel contentPane;
	private JButton btnSalir; 
	private JButton btnGestionar;
	private JTable jTableResultado;
	private JScrollPane scrollPaneResultado;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionAvisos frame = new GestionAvisos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestionAvisos() {
		getContentPane().setLayout(null);

		try {
			seleccionIdioma();
			initialize();
			CargarControles();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void initialize() {
		setSize(new Dimension(491, 250));
	}

	private void seleccionIdioma() {
		Locale localLocale = new Locale("", "");
		TDSLanguageUtils.setDefaultLanguage("i18n/messages");
	}
	private void CargarControles()
	{ setTitle(TDSLanguageUtils.getMessage("aviso.titulo"));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnGestionar = new JButton();
		btnGestionar.setText(TDSLanguageUtils.getMessage("aviso.btn.gestionar"));
		btnGestionar.setBounds(25, 181, 89, 23);
		contentPane.add(btnGestionar);
		
		btnSalir = new JButton();
		btnSalir.setText(TDSLanguageUtils.getMessage("aviso.btn.salir"));
		btnSalir.setBounds(350, 181, 89, 23);
		contentPane.add(btnSalir);
		
		
	
		scrollPaneResultado = new JScrollPane();
		scrollPaneResultado.setViewportBorder(UIManager
				.getBorder("ComboBox.border"));
		scrollPaneResultado.setBounds(new Rectangle(25, 11, 414, 150));
		this.jTableResultado = new JTable();
		scrollPaneResultado.setViewportView(jTableResultado);
		getContentPane().add(this.scrollPaneResultado, null);
		

	}
}
