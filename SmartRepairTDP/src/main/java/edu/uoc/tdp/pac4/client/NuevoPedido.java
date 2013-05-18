package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import edu.uoc.tdp.pac4.beans.Comanda;
import edu.uoc.tdp.pac4.beans.Peca;
import edu.uoc.tdp.pac4.beans.Proveidor;
import edu.uoc.tdp.pac4.beans.Stockpeca;
import edu.uoc.tdp.pac4.common.ItemCombo;
import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import edu.uoc.tdp.pac4.service.GestorAdministracionInterface;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Font;

public class NuevoPedido extends JDialog {
	private static int port = 1099;
	 private final static String urlRMIAdmin = new String("rmi://localhost/GestorAdministracion");
	private static int IDTALLER = 1;
	private JPanel contentPane;
	private JLabel lblDescripcion;
	private JLabel lblStock;
	private JTextField txtStock;
	private JLabel lblPVP;
	private JLabel lblPVD;
	private JTextField txtPVD;
	private JLabel lblProveedor;
	private JComboBox cmbProveedor;
	private JComboBox cmbPieza;
	private static GestorAdministracionInterface conexionRemota;
	private JTextField txtPVP;
	private JTextField txtMarca;
	private JTextField txtModelo;
	private JButton btnRealizarPedido;
	private JButton btnSalir;
	private ArrayList<ItemCombo> cbProveedor;
	private ArrayList<ItemCombo> cbPieza;
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

	

	private void initialize() {
		setSize(new Dimension(391, 388));
	}

	private void CargarControles() {
		setTitle(TDSLanguageUtils.getMessage("nuevo.pedido.titulo"));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDescripcion = new JLabel();
		lblDescripcion.setBounds(17, 63, 170, 14);
		lblDescripcion.setText(TDSLanguageUtils
				.getMessage("nuevo.pedido.Pieza"));
		contentPane.add(lblDescripcion);

		lblStock = new JLabel();
		lblStock.setBounds(17, 232, 88, 14);
		lblStock.setText(TDSLanguageUtils.getMessage("nuevo.pedido.cantidad"));
		contentPane.add(lblStock);

		txtStock = new JTextField();
		txtStock.setBounds(120, 229, 67, 20);
		txtStock.addKeyListener(new KeyAdapterNumbersOnly());
		contentPane.add(txtStock);
		txtStock.setColumns(10);

		lblPVP = new JLabel();
		lblPVP.setBounds(22, 101, 88, 14);
		lblPVP.setText(TDSLanguageUtils.getMessage("nuevo.pedido.PVP"));
		contentPane.add(lblPVP);

		lblPVD = new JLabel();
		lblPVD.setBounds(207, 101, 67, 14);
		lblPVD.setText(TDSLanguageUtils.getMessage("nuevo.pedido.PVD"));
		
		contentPane.add(lblPVD);

		txtPVD = new JTextField();
		txtPVD.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtPVD.setForeground(Color.BLACK);
		txtPVD.setEditable(false);
		txtPVD.setBounds(287, 98, 67, 20);
		txtPVD.addKeyListener(new KeyAdapterNumbersOnly());
		contentPane.add(txtPVD);
		txtPVD.setColumns(10);

		lblProveedor = new JLabel();
		lblProveedor.setBounds(17, 27, 159, 14);
		lblProveedor.setText(TDSLanguageUtils
				.getMessage("nuevo.pedido.proveedor"));
		contentPane.add(lblProveedor);


		contentPane.add(getcmbProveedor());

		txtPVP = new JTextField();
		txtPVP.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtPVP.setForeground(Color.BLACK);
		txtPVP.setEditable(false);
		txtPVP.setBounds(120, 98, 67, 20);
		txtPVP.setColumns(10);
		txtPVP.addKeyListener(new KeyAdapterNumbersOnly());
		contentPane.add(txtPVP);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(22, 135, 88, 14);
		lblNewLabel.setText(TDSLanguageUtils.getMessage("nuevo.pedido.marca"));
		contentPane.add(lblNewLabel);

		txtMarca = new JTextField();
		txtMarca.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtMarca.setForeground(Color.BLACK);
		txtMarca.setEditable(false);
		txtMarca.setBounds(120, 132, 175, 20);
		contentPane.add(txtMarca);
		txtMarca.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(22, 171, 88, 14);
		lblNewLabel_1.setText(TDSLanguageUtils
				.getMessage("nuevo.pedido.modelo"));
		contentPane.add(lblNewLabel_1);

		txtModelo = new JTextField();
		txtModelo.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtModelo.setForeground(Color.BLACK);
		txtModelo.setEditable(false);
		txtModelo.setBounds(120, 171, 175, 20);
		contentPane.add(txtModelo);
		txtModelo.setColumns(10);

		contentPane.add(btnRealizarPedido());
		contentPane.add(btnSalir());
		
		
		contentPane.add(getcmbPieza());
		
		JSeparator separator = new JSeparator();
		separator.setBounds(22, 207, 332, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(22, 88, 332, 2);
		contentPane.add(separator_1);

	}

	private JComboBox  getcmbProveedor()
	{
		if(cmbProveedor==null)
		{
			cmbProveedor = new JComboBox();
			cmbProveedor.setBounds(189, 24, 165, 20);
			cmbProveedor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
					try {
						cmbPieza.setEnabled(false);
							
							int item=cmbProveedor.getSelectedIndex();
							if(cbProveedor.size()>0)
							for (int i = 0; i < cbProveedor.size(); i++) {
								if (item == cbProveedor.get(i).getId()) 
								{
									cmbPieza.setEnabled(true);
									  CargarCmbPiezasByCodeProv(Integer.valueOf(cbProveedor.get(i).getAux()));
									  break;
								}
							}else
							{
								cmbPieza.setEnabled(false);
							}
						
						
						
					}catch(Exception ex)
					{
						ex.printStackTrace();
					}
					}
			});
		}
		return cmbProveedor;
	}
	
	private JComboBox  getcmbPieza()
	{
		if(cmbPieza==null)
		{
			cmbPieza = new JComboBox();
			cmbPieza.setBounds(189, 60, 165, 20);
			cmbPieza.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
					try {
						int item=cmbPieza.getSelectedIndex();
						if(cbPieza.size()>0){
						for(int i=0; i<cbPieza.size();i++)
						{
							if (item == cbPieza.get(i).getId()) 
							{
								CargarPiezaByCode(Integer.valueOf(cbPieza.get(i).getAux()));
								break;
							}
						}
						}
						else
						{
							ClearControles();
						}
						
						
					}catch(Exception ex)
					{
						ex.printStackTrace();
					}
					}
			});
		}
		return cmbPieza;
	}
	
	private void CargarPiezaByCode(int codigoPieza)
	{
		try{
			ClearControles();
			Peca p=getRemoto().getPiezaByCode(codigoPieza);
			
			if(p!=null)
			{
				txtMarca.setText(p.getMarca());
				txtModelo.setText(p.getModel());
				txtPVP.setText(String.valueOf(p.getPVP()));
				txtPVD.setText(String.valueOf(p.getPVPD()));
				
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	private void ClearControles()
	{
		txtMarca.setText("");
		txtModelo.setText("");
		txtPVP.setText("");
		txtPVD.setText("");
	}
	private void CargarCmbPiezasByCodeProv(int codigoProv)
	{ArrayList<Peca> ListPeca = null;
		try{
			cbPieza=null;
			cmbPieza.removeAllItems();
			cbPieza = new ArrayList<ItemCombo>();
			ListPeca=getRemoto().getPiezaByCodeProveedor(codigoProv);
			
			for (int i = 0; i < ListPeca.size(); i++) {
				cbPieza.add(new ItemCombo(i, ListPeca.get(i).getDescripcio(),
						String.valueOf(ListPeca.get(i).getCodiPeca())));
				
				cmbPieza.insertItemAt(ListPeca.get(i).getDescripcio(), i);

			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	private void CargarCmbProveedores() throws RemoteException {
		ArrayList<Proveidor> ListProveedor = null;
		try {
			cbProveedor = null;
			cbProveedor = new ArrayList<ItemCombo>();
			ListProveedor = getRemoto().getProveedores();
		

			for (int i = 0; i < ListProveedor.size(); i++) {
			cbProveedor.add(new ItemCombo(i, ListProveedor.get(i).getNom(),
						String.valueOf(ListProveedor.get(i).getIdproveidor())));
		     	
				cmbProveedor.insertItemAt(ListProveedor.get(i).getNom(), i);
				
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
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
		 int codigoProv=0;
		 int codigoPeca=0;
		try{
			
			Comanda comanda= new Comanda();
			
			int icodPeca= cmbPieza.getSelectedIndex();
			int icodProv= cmbProveedor.getSelectedIndex();
			for (int i = 0; i < cbPieza.size(); i++) {
				if (icodPeca == cbPieza.get(i).getId()) {
					codigoPeca = Integer.valueOf(cbPieza.get(i).getAux());
					break;
				}
			}

			for (int i = 0; i < cbProveedor.size(); i++) {
				if (icodProv == cbProveedor.get(i).getId()) {
					codigoProv = Integer.valueOf(cbProveedor.get(i).getAux());
					break;
				}
			}

			comanda.setCodipeca(codigoPeca);
			comanda.setCodigoProveedor(codigoProv);
			java.util.Date dt = new java.util.Date();
			java.sql.Date dateAlta = new java.sql.Date(dt.getTime());

			comanda.setDataalta(dateAlta);
			comanda.setEstat(true);
			comanda.setIdcaptaller(IDTALLER);

			//comanda.setOrdrereparacio(0);
			comanda.setTipusreparacio(false);
			comanda.setCantidad(Integer.valueOf(txtStock.getText()));
			
			getRemoto().getNuevoPedido(comanda);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	private String ImputValues() {
		String srtResult = "";
		try {
			
			if (cmbProveedor.getSelectedItem().equals("")) {
				srtResult = TDSLanguageUtils
						.getMessage("nuevo.msg.proveedor");
				return srtResult;
			}
			if (cmbProveedor.getSelectedItem().equals("")) {
				srtResult = TDSLanguageUtils
						.getMessage("nuevo.msg.pieza");
				return srtResult;
			}
			
			if(txtStock.getText().equals(""))
			{
				srtResult = TDSLanguageUtils
						.getMessage("nuevo.msg.cantidad");
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
			btnSalir.setBounds(new Rectangle(234, 299, 107, 23));
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
