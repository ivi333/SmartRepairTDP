package edu.uoc.tdp.pac4.client;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import edu.uoc.tdp.pac4.service.GestorAdministracionInterface;
import java.awt.Color;
import java.awt.Font;

public class RecepcionPedidos extends JDialog {
	private static int port = 1099;
	 private final static String urlRMIAdmin = new String("rmi://localhost/GestorAdministracion");
	private JPanel contentPane;
	private JButton btnSalir;
	private JButton btnGestionar;
	JButton btnPedidoNuevo;
	private JTable jTableResultado;
	private JScrollPane scrollPaneResultado;
	private JButton btnRecepcionar;
	private int codigoPedido=0;
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
		//TDSLanguageUtils.setDefaultLanguage("i18n/messages");
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

	public RecepcionPedidos(GestorAdministracionInterface remoto) {
		try {
			initialize();
			seleccionIdioma();
			conexionRemota=remoto;
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
		jTableResultado.getColumnModel().getColumn(1).setPreferredWidth(50);
		jTableResultado.getColumnModel().getColumn(2).setPreferredWidth(50);
		jTableResultado.getColumnModel().getColumn(3).setPreferredWidth(80);
		jTableResultado.getColumnModel().getColumn(4).setPreferredWidth(80);
		jTableResultado.getColumnModel().getColumn(5).setPreferredWidth(80);
		jTableResultado.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	try{
	        	codigoPedido=Integer.valueOf(jTableResultado.getValueAt(jTableResultado.getSelectedRow(), 0).toString());
	        	}
	        	catch(Exception ex)
	        	{
	        		ex.printStackTrace();
	        		codigoPedido=0;
	        	}
	        	
	        }
	    });
	
		
		scrollPaneResultado.setViewportView(jTableResultado);

		getContentPane().add(this.scrollPaneResultado, null);
		
		
		contentPane.add(getbtnPedidoNuevo());
		
	
		contentPane.add(getbtnRecepcionar());
		
	}
	private JButton getbtnRecepcionar()
	{
		if(btnRecepcionar==null)
		{
			 btnRecepcionar = new JButton();
			 btnRecepcionar.setBounds(new Rectangle(391, 181, 170, 23));
			 btnRecepcionar.setText(TDSLanguageUtils.getMessage("recep.btn.recep.pedido"));
			 btnRecepcionar.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						try{
							
							GetRecepcionar();
							
							getCargarPedidos();
						}
						catch(Exception ex)
						{
							ex.printStackTrace();
						}
					}
				});
			}
		return btnRecepcionar;
	}
	
	private void GetRecepcionar()
	{
		String titulo=TDSLanguageUtils.getMessage("recep.titulo");
		String msg1=TDSLanguageUtils.getMessage("recep.msg.confirmar");
		try{
			if(codigoPedido>0)
			{
			 int reply = JOptionPane.showConfirmDialog(null,msg1, titulo,  JOptionPane.YES_NO_OPTION);
		        if (reply == JOptionPane.YES_OPTION) {

		        	conexionRemota.getRecepcionarPedido(codigoPedido,true);
		        }
		        else {
		          
		           System.exit(0);
		        }
			}
			else
			{
				String msg=TDSLanguageUtils.getMessage("recep.msg.recep");
				LeerError(msg,titulo);
			}
			
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	private void LeerError(String paramString1, String paramString2) {
		JOptionPane.showMessageDialog(this, paramString1, paramString2, 0);
	}
	private void MuestraOk(String paramString1, String paramString2) {
        JOptionPane.showMessageDialog(this, paramString1, paramString2, 1);
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

					NuevoPedido nuevoPedido = new NuevoPedido(conexionRemota);
					nuevoPedido.setSize(390, 388);
					final Toolkit toolkitpedidos = Toolkit.getDefaultToolkit();
					final Dimension screenSizetoolkitpedidos = toolkitpedidos
							.getScreenSize();
					int x = (screenSizetoolkitpedidos.width - nuevoPedido.getWidth()) / 2;
					int y = (screenSizetoolkitpedidos.height - nuevoPedido.getHeight()) / 2;
					nuevoPedido.setLocation(x, y);
					nuevoPedido.setVisible(true);

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
			btnGestionar.setBounds(new Rectangle(203, 181, 123, 23));
			btnGestionar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					getCargarPedidos();

				}
			});
		}

		return btnGestionar;
	}
	
	
	
	private void getCargarPedidos() {
		
		try {
			GruposTableModel gtm1 = (GruposTableModel) jTableResultado.getModel();
			if (gtm1.getRowCount() > 0) {
			    for (int i = gtm1.getRowCount() - 1; i > -1; i--) {
			    	gtm1.removeRow(i);
			    }
			}

			ArrayList<String> list = conexionRemota.getCargarPedidos();

			Iterator<String> it = list.iterator();
			GruposTableModel gtm = (GruposTableModel) jTableResultado.getModel();
			gtm.setRowCount(0);
			
			if (list.size() > 0) {
				while (it.hasNext()) {
					String codigo = it.next();
					String fecha = it.next();
					String cantidad = it.next();
					String pieza = it.next();
					String proveedor = it.next();
					String taller = it.next();
					String stock = it.next();

					gtm.addRow(new Object[] { codigo, fecha, cantidad, pieza,
							proveedor, taller });

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
					TDSLanguageUtils.getMessage("recep.lbl.fecha"), //
					TDSLanguageUtils.getMessage("recep.lbl.cantidad"),
					TDSLanguageUtils.getMessage("recep.lbl.pieza"),
					TDSLanguageUtils.getMessage("recep.lbl.proveedor"),
					TDSLanguageUtils.getMessage("recep.lbl.taller"),//
					//TDSLanguageUtils.getMessage("recep.lbl.stock.total"),
			},0);// numFilas); //
		}
		
		@Override
		public boolean isCellEditable(int row, int col) {
			return false;
		}
		public void setRowCount(int rowCount)
		{
			
		}
		
	}
}
