package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import edu.uoc.tdp.pac4.beans.Proveidor;
import edu.uoc.tdp.pac4.common.ItemCombo;
import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import edu.uoc.tdp.pac4.service.GestorAdministracionInterface;

import javax.swing.JComboBox;
import javax.swing.JButton;

public class NuevoPedido extends JFrame {
	private static int port = 1454;
	private JPanel contentPane;
	private JTextField txtDescripcion;
	private JLabel lblDescripcion;
	private JLabel lblStock;
	private JTextField txtStock;
	private JLabel lblPVP;
	private JTextField txtStockMin;
	private JLabel lblPVD;
	private JTextField txtPVD;
	private JLabel lblProveedor;
	private JComboBox cmbProveedor;
	private JLabel lblStockMin;
	private static GestorAdministracionInterface conexionRemota;
	private JTextField txtPVP;
	private JTextField txtMarca;
	private JTextField txtModelo;
	private JButton btnRealizarPedido;
	private JButton btnSalir;
	private ArrayList<ItemCombo> cbProveedor;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevoPedido frame = new NuevoPedido();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void seleccionIdioma() {
		Locale localLocale = new Locale("", "");
		TDSLanguageUtils.setDefaultLanguage("i18n/messages");
	}

	public NuevoPedido() {
		try {
			seleccionIdioma();
			initialize();
			CargarControles();
			CargarCmbProveedores();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
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

	private void CargarCmbProveedores() throws RemoteException {
		ArrayList<Proveidor> ListProveedor = null;
		try {
			cbProveedor = null;
			cbProveedor = new ArrayList<ItemCombo>();
			ListProveedor = getRemoto().getProveedores();
			cmbProveedor.insertItemAt("", 0);

			for (int i = 0; i < ListProveedor.size(); i++) {
				cbProveedor.add(new ItemCombo(i, ListProveedor.get(i).getNom(),
						String.valueOf(ListProveedor.get(i).getIdproveidor())));
				
				cmbProveedor.insertItemAt(ListProveedor.get(i).getNom(), i);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void initialize() {
		setSize(new Dimension(475, 388));
	}

	private void CargarControles() {
		setTitle(TDSLanguageUtils.getMessage("nuevo.pedido.titulo"));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDescripcion = new JLabel();
		lblDescripcion.setBounds(22, 47, 88, 14);
		lblDescripcion.setText(TDSLanguageUtils
				.getMessage("nuevo.pedido.descripcion"));
		contentPane.add(lblDescripcion);

		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(120, 44, 275, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);

		lblStock = new JLabel();
		lblStock.setBounds(22, 75, 88, 14);
		lblStock.setText(TDSLanguageUtils.getMessage("nuevo.pedido.stock"));
		contentPane.add(lblStock);

		txtStock = new JTextField();
		txtStock.setBounds(120, 72, 67, 20);
		contentPane.add(txtStock);
		txtStock.setColumns(10);

		lblPVP = new JLabel();
		lblPVP.setBounds(22, 118, 88, 14);
		lblPVP.setText(TDSLanguageUtils.getMessage("nuevo.pedido.PVP"));
		contentPane.add(lblPVP);

		txtStockMin = new JTextField();
		txtStockMin.setBounds(320, 72, 67, 20);
		contentPane.add(txtStockMin);
		txtStockMin.setColumns(10);

		lblPVD = new JLabel();
		lblPVD.setBounds(216, 118, 67, 14);
		lblPVD.setText(TDSLanguageUtils.getMessage("nuevo.pedido.PVD"));
		contentPane.add(lblPVD);

		txtPVD = new JTextField();
		txtPVD.setBounds(320, 115, 67, 20);

		contentPane.add(txtPVD);
		txtPVD.setColumns(10);

		lblProveedor = new JLabel();
		lblProveedor.setBounds(22, 226, 88, 14);
		lblProveedor.setText(TDSLanguageUtils
				.getMessage("nuevo.pedido.proveedor"));
		contentPane.add(lblProveedor);

		cmbProveedor = new JComboBox();
		cmbProveedor.setBounds(120, 223, 234, 20);
		contentPane.add(cmbProveedor);

		lblStockMin = new JLabel("New label");
		lblStockMin.setBounds(216, 75, 94, 14);
		lblStockMin.setText(TDSLanguageUtils
				.getMessage("nuevo.pedido.stock.minimo"));
		contentPane.add(lblStockMin);

		txtPVP = new JTextField();
		txtPVP.setBounds(120, 115, 67, 20);
		contentPane.add(txtPVP);
		txtPVP.setColumns(10);
		txtPVP.setColumns(10);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(22, 158, 88, 14);
		lblNewLabel.setText(TDSLanguageUtils.getMessage("nuevo.pedido.marca"));
		contentPane.add(lblNewLabel);

		txtMarca = new JTextField();
		txtMarca.setBounds(120, 155, 175, 20);
		contentPane.add(txtMarca);
		txtMarca.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(22, 195, 46, 14);
		lblNewLabel_1.setText(TDSLanguageUtils
				.getMessage("nuevo.pedido.modelo"));
		contentPane.add(lblNewLabel_1);

		txtModelo = new JTextField();
		txtModelo.setBounds(120, 192, 175, 20);
		contentPane.add(txtModelo);
		txtModelo.setColumns(10);

		contentPane.add(btnRealizarPedido());
		;

		contentPane.add(btnSalir());

	}

	private JButton btnRealizarPedido() {
		if (btnRealizarPedido == null) {
			btnRealizarPedido = new JButton();
			btnRealizarPedido.setText(TDSLanguageUtils
					.getMessage("nuevo.pedido.btn.realizar"));
			btnRealizarPedido.setBounds(new Rectangle(22, 299, 134, 23));
			btnRealizarPedido
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {

							if(ImputValues().equals(""))
							{
								getNuevoPedido();
								String strAux=TDSLanguageUtils
										.getMessage("nuevo.msg.ok");
								MuestraOk(strAux, TDSLanguageUtils
										.getMessage("nuevo.pedido.titulo"));
								dispose();
							}
							else
							{
								String strMsg=ImputValues();
								LeerError(strMsg, TDSLanguageUtils
										.getMessage("nuevo.pedido.titulo"));
							}
						}
					});

		}
		return btnRealizarPedido;
	}

	private void getNuevoPedido()
	{ 
		try{
			
		}catch(Exception ex)
		{
			
		}
	}
	
	private String ImputValues() {
		String srtResult = "";
		try {
			if (!txtDescripcion.getText().toString().equals("")) {
				srtResult = TDSLanguageUtils.getMessage("nuevo.msg.descripcion");
				return srtResult;
			}
			if (!txtPVD.getText().toString().equals("")) {
				srtResult = TDSLanguageUtils.getMessage("nuevo.msg.PVD");
				return srtResult;
			}
			if (!txtPVP.getText().toString().equals("")) {
				srtResult = TDSLanguageUtils.getMessage("nuevo.msg.PVP");
				return srtResult;
			}
			if (!txtStock.getText().toString().equals("")) {
				srtResult = TDSLanguageUtils.getMessage("nuevo.msg.stock");
				return srtResult;
			}
			if (!txtStockMin.getText().toString().equals("")) {
				srtResult = TDSLanguageUtils.getMessage("nuevo.msg.stock.minimo");
				return srtResult;
			}
			if (!txtMarca.getText().toString().equals("")) {
				srtResult = TDSLanguageUtils.getMessage("nuevo.msg.marca");
				return srtResult;
			}
			if (!txtModelo.getText().toString().equals("")) {
				srtResult = TDSLanguageUtils.getMessage("nuevo.msg.modelo");
				return srtResult;
			}
			if (cmbProveedor.getSelectedIndex() == 0) {
				srtResult = TDSLanguageUtils
						.getMessage("nuevo.msg.descripcion");
				return srtResult;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return srtResult;
	}
	
    private JButton btnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton();
			btnSalir.setText(TDSLanguageUtils
					.getMessage("nuevo.pedido.btn.salir"));
			btnSalir.setBounds(new Rectangle(320, 299, 107, 23));
			btnSalir.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					dispose();

				}
			});

		}
		return btnSalir;
	}

    private void LeerError(String paramString1, String paramString2) {
		JOptionPane.showMessageDialog(this, paramString1, paramString2, 0);
	}
	 private void MuestraOk(String paramString1, String paramString2) {
	        JOptionPane.showMessageDialog(this, paramString1, paramString2, 1);
	    }
}
