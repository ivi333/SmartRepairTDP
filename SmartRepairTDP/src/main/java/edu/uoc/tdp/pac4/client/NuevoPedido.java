package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import javax.swing.JComboBox;

public class NuevoPedido extends JFrame {

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
	
	private JTextField txtPVP;
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
		try{
			seleccionIdioma();
			initialize();
			CargarControles();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	private void initialize() {
		setSize(new Dimension(475, 422));
	}

	private void CargarControles()
	{
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblDescripcion= new JLabel();
		lblDescripcion.setBounds(22, 27, 88, 14);
		lblDescripcion
		.setText(TDSLanguageUtils.getMessage("nuevo.pedido.descripcion"));
		contentPane.add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(120, 24, 275, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		lblStock = new JLabel();
		lblStock.setBounds(22, 75, 88, 14);
		lblStock
		.setText(TDSLanguageUtils.getMessage("nuevo.pedido.stock"));
		contentPane.add(lblStock);
		
		txtStock = new JTextField();
		txtStock.setBounds(120, 72, 67, 20);
		contentPane.add(txtStock);
		txtStock.setColumns(10);
		
		lblPVP = new JLabel();
		lblPVP.setBounds(22, 118, 88, 14);
		lblPVP
		.setText(TDSLanguageUtils.getMessage("nuevo.pedido.PVP"));
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
		lblProveedor.setBounds(22, 159, 88, 14);
		lblProveedor.setText(TDSLanguageUtils.getMessage("nuevo.pedido.proveedor"));
		contentPane.add(lblProveedor);
		
		cmbProveedor = new JComboBox();
		cmbProveedor.setBounds(120, 156, 234, 20);
		contentPane.add(cmbProveedor);
		
		lblStockMin = new JLabel("New label");
		lblStockMin.setBounds(216, 75, 94, 14);
		lblStockMin
		.setText(TDSLanguageUtils.getMessage("nuevo.pedido.stock.minimo"));
		contentPane.add(lblStockMin);
		

		txtPVP = new JTextField();
		txtPVP.setBounds(120, 115, 67, 20);
		contentPane.add(txtPVP);
		txtPVP.setColumns(10);
		txtPVP.setColumns(10);
		
		
	}
}
