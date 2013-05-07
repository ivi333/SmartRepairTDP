package edu.uoc.tdp.pac4.client;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.util.Locale;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import edu.uoc.tdp.pac4.common.TDSLanguageUtils;

public class GestionAvisos extends JDialog {

	private JPanel contentPane;
	private JButton btnSalir; 
	private JButton btnGestionar;
	private JTable jTableResultado =new JTable();
	private JScrollPane scrollPaneResultado;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionAvisos frame = new GestionAvisos();
					  frame.setLocationRelativeTo(null);
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

			   GruposTableModel gtm = (GruposTableModel) jTableResultado.getModel();
               gtm.addRow(new Object[]{
                           "", "", "", "" });
                           
               
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
		
		
		contentPane.add(getbtnSalirJ());
		
		
	
		scrollPaneResultado = new JScrollPane();
		scrollPaneResultado.setViewportBorder(UIManager
				.getBorder("ComboBox.border"));
		scrollPaneResultado.setBounds(new Rectangle(25, 11, 414, 150));
		this.jTableResultado.setModel(new GruposTableModel());
		
		scrollPaneResultado.setViewportView(jTableResultado);
		getContentPane().add(this.scrollPaneResultado, null);
		

	}
	private JButton getbtnSalirJ() {
		if (btnSalir == null) {
			btnSalir = new JButton();
			btnSalir.setBounds(new Rectangle(350, 181, 89, 23));
			btnSalir.setText(TDSLanguageUtils.getMessage("aviso.btn.salir"));
			btnSalir.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
					
				}
			});
		}
		return btnSalir;
	}
	private static class GruposTableModel extends DefaultTableModel {

		private static final long serialVersionUID = 1L;

		public GruposTableModel() {
            super(new String[]{
                        TDSLanguageUtils.getMessage("aviso.lbl.solicitud"), //
                        TDSLanguageUtils.getMessage("aviso.lbl.estado"), //
                        TDSLanguageUtils.getMessage("aviso.lbl.cliente"), //
                        TDSLanguageUtils.getMessage("aviso.lbl.apellidos"), //
                       },0);//numFilas); //
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return false;
        }
    }
	
}
