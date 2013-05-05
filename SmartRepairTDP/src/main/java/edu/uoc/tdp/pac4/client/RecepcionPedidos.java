package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import edu.uoc.tdp.pac4.common.TDSLanguageUtils;

public class RecepcionPedidos extends JDialog {

	private JPanel contentPane;
	private JButton btnSalir; 
	private JButton btnGestionar;
	private JTable jTableResultado;
	private JScrollPane scrollPaneResultado;
	
	public static void main(String[] args) {
		try {
			RecepcionPedidos dialog = new RecepcionPedidos();
		
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	private void initialize() {
		setSize(new Dimension(491, 250));
	}

	private void seleccionIdioma() {
		Locale localLocale = new Locale("", "");
		TDSLanguageUtils.setDefaultLanguage("i18n/messages");
	}
	
	public RecepcionPedidos() {
		try{
			initialize();
			seleccionIdioma();
			CargarControles();
			  GruposTableModel gtm = (GruposTableModel) jTableResultado.getModel();
              gtm.addRow(new Object[]{
                          "", "", "", "","" });
		}catch (Exception e) {
		e.printStackTrace();
		}
	}
	private void CargarControles()
	{ setTitle(TDSLanguageUtils.getMessage("recep.titulo"));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnGestionar = new JButton();
		btnGestionar.setText(TDSLanguageUtils.getMessage("recep.btn.actualizar"));
		btnGestionar.setBounds(25, 181, 89, 23);
		contentPane.add(btnGestionar);
		
		
		contentPane.add(getbtnSalirJ());
		
		
	
		scrollPaneResultado = new JScrollPane();
		scrollPaneResultado.setViewportBorder(UIManager
				.getBorder("ComboBox.border"));
		scrollPaneResultado.setBounds(new Rectangle(25, 11, 414, 150));
		this.jTableResultado = new JTable();
		this.jTableResultado.setModel(new GruposTableModel());
		scrollPaneResultado.setViewportView(jTableResultado);
		
		getContentPane().add(this.scrollPaneResultado, null);
		

	}
	private JButton getbtnSalirJ() {
		if (btnSalir == null) {
			btnSalir = new JButton();
			btnSalir.setBounds(new Rectangle(350, 181, 89, 23));
			btnSalir.setText(TDSLanguageUtils.getMessage("recep.btn.salir"));
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
                        TDSLanguageUtils.getMessage("recep.lbl.codigo"), //
                        TDSLanguageUtils.getMessage("recep.lbl.descripcion"), //
                        TDSLanguageUtils.getMessage("recep.lbl.unidades"), //
                        TDSLanguageUtils.getMessage("recep.lbl.pvp"),
                        TDSLanguageUtils.getMessage("recep.lbl.pvd"),//
                       },0);//numFilas); //
        }
		
        @Override
        public boolean isCellEditable(int row, int col) {
            return false;
        }
    }
}
