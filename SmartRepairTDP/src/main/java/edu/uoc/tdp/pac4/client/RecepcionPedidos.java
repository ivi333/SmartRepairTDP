package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Iterator;
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
import edu.uoc.tdp.pac4.service.GestorAdministracionInterface;
import java.awt.Color;
import java.awt.Font;

public class RecepcionPedidos extends JDialog {
	private static int port = 1212;
	private JPanel contentPane;
	private JButton btnSalir;
	private JButton btnGestionar;
	JButton btnPedidoNuevo;
	private JTable jTableResultado;
	private JScrollPane scrollPaneResultado;
	private static GestorAdministracionInterface conexionRemota;
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
		setSize(new Dimension(762, 250));
	}

	private void seleccionIdioma() {
		Locale localLocale = new Locale("", "");
		TDSLanguageUtils.setDefaultLanguage("i18n/messages");
	}

	public RecepcionPedidos() {
		try {
			initialize();
			seleccionIdioma();
			CargarControles();
			GruposTableModel gtm = (GruposTableModel) jTableResultado
					.getModel();
			gtm.addRow(new Object[] { "", "", "", "", "" });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void CargarControles() {
		setTitle(TDSLanguageUtils.getMessage("recep.titulo"));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		contentPane.add(getbtnActualizar());

		contentPane.add(getbtnSalir());

		scrollPaneResultado = new JScrollPane();
		scrollPaneResultado.setViewportBorder(UIManager
				.getBorder("ComboBox.border"));
		scrollPaneResultado.setBounds(new Rectangle(25, 11, 667, 150));
		this.jTableResultado = new JTable();
		this.jTableResultado.setModel(new GruposTableModel());
		jTableResultado.getColumnModel().getColumn(0).setPreferredWidth(50);
		jTableResultado.getColumnModel().getColumn(1).setPreferredWidth(130);
		jTableResultado.getColumnModel().getColumn(2).setPreferredWidth(80);
		jTableResultado.getColumnModel().getColumn(3).setPreferredWidth(80);
		jTableResultado.getColumnModel().getColumn(4).setPreferredWidth(50);
		jTableResultado.getColumnModel().getColumn(5).setPreferredWidth(50);
		jTableResultado.getColumnModel().getColumn(6).setPreferredWidth(50);
		jTableResultado.getColumnModel().getColumn(7).setPreferredWidth(80);
		jTableResultado.getColumnModel().getColumn(8).setPreferredWidth(140);
		
		
		scrollPaneResultado.setViewportView(jTableResultado);

		getContentPane().add(this.scrollPaneResultado, null);
		
		
		contentPane.add(getbtnPedidoNuevo());
		
	}
	
	private JButton getbtnPedidoNuevo() {
		if(btnPedidoNuevo==null)
		{
			
			btnPedidoNuevo= new JButton();
			btnPedidoNuevo.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnPedidoNuevo.setForeground(new Color(0, 128, 0));
			btnPedidoNuevo.setBounds(new Rectangle(25, 181, 138, 23));
			btnPedidoNuevo.setText(TDSLanguageUtils.getMessage("recep.btn.nuevo.pedido"));
			btnPedidoNuevo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

				

				}
			});
		}
		return btnPedidoNuevo;
	}
	private JButton getbtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton();
			btnSalir.setForeground(Color.RED);
			btnSalir.setBounds(new Rectangle(603, 181, 89, 23));
			btnSalir.setText(TDSLanguageUtils.getMessage("recep.btn.salir"));
			btnSalir.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					dispose();

				}
			});
		}
		return btnSalir;
	}

	private JButton getbtnActualizar() {

		if (btnGestionar == null) {
			btnGestionar = new JButton();
			btnGestionar.setText(TDSLanguageUtils
					.getMessage("recep.btn.actualizar"));
			btnGestionar.setBounds(new Rectangle(323, 181, 123, 23));
			btnGestionar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					getCargarPedidos();

				}
			});
		}

		return btnGestionar;
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
	private void getCargarPedidos() {
		
		try {

			ArrayList<String> list=getRemoto().getPedidosPeca();
			
			Iterator<String> it=list.iterator();
			
			
			
			GruposTableModel gtm = (GruposTableModel) jTableResultado.getModel();
			
			if(list.size()>0)
			{while (it.hasNext())
			{
				
				String codigo=it.next();
				String descripcion= it.next();
			
				String pvp= it.next();
				String pvd= it.next();
				String marca= it.next();
				String modelo= it.next();
				String stock= it.next();
				String stockminimo= it.next();
				String proveedor= it.next();
				
				gtm.addRow(new Object[] {codigo,descripcion,stock,stockminimo,pvp,pvd,marca,modelo,proveedor});
			
			}
				
				
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static class GruposTableModel extends DefaultTableModel {

		private static final long serialVersionUID = 1L;

		public GruposTableModel() {
			super(new String[] {
					TDSLanguageUtils.getMessage("recep.lbl.codigo"), //
					TDSLanguageUtils.getMessage("recep.lbl.descripcion"), //
					TDSLanguageUtils.getMessage("recep.lbl.stock"),
					TDSLanguageUtils.getMessage("recep.lbl.stock.minimo"),
					TDSLanguageUtils.getMessage("recep.lbl.pvp"),
					TDSLanguageUtils.getMessage("recep.lbl.pvd"),//
					TDSLanguageUtils.getMessage("recep.lbl.marca"),
					TDSLanguageUtils.getMessage("recep.lbl.modelo"),					
					TDSLanguageUtils.getMessage("recep.lbl.proveedor"),//
			}, 0);// numFilas); //
		}

		@Override
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	}
}
