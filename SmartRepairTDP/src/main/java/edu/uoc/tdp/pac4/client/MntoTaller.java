package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import edu.uoc.tdp.pac4.beans.Taller;
import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.common.ItemCombo;
import edu.uoc.tdp.pac4.exception.GestorConexionException;
import edu.uoc.tdp.pac4.service.GestorConexionInterface;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;


import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JLayeredPane;


public class MntoTaller extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GestorConexionInterface gestorConexion;
	
	private JPanel contentPane;
	private JTextField txtCif;
	private JTextField txtId;
	private JTextField txtDireccion;
	private JTextField txtCapacidad;
	private JTextField txtTelefono;
	private JTextField txtWeb;
	private JComboBox cmbJefeTaller;
	private JCheckBox chkActivo;
	private JTextField txtFalta;
	private JTextField txtFmodificacion;
	private JTextField txtFbaja;
	

	private ArrayList<ItemCombo> cbJefeTaller;
	private int idTaller;
	private String accion;

	private Taller taller;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MntoUsuario frame = new MntoUsuario();
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
	public MntoTaller() {
		initialize ();
	}
	
	public MntoTaller (GestorConexionInterface gestorConexion) {
		this.gestorConexion = gestorConexion;
		initialize ();
		cargarCmbJefeTaller();
		this.accion = "NUEVO";

	}
	
	public MntoTaller (GestorConexionInterface gestorConexion, String accion, int idTaller){
		this.gestorConexion = gestorConexion;
		this.idTaller = idTaller;
		this.accion = accion;
		initialize ();
		cargarCmbJefeTaller();
		cargarTaller();
		cargarAccion ();
	}
	
	private void initialize (){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 839, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JPanel panel_4 = new JPanel();
		
		JPanel panel_5 = new JPanel();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
				.addComponent(panel_5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JButton btnSalir = new JButton("SALIR");
		btnSalir.setActionCommand("BTN_SALIR");
		btnSalir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				actions (e);
			}
		});
		panel_5.add(btnSalir);
		
		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setActionCommand("BTN_ACEPTAR");
		btnAceptar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				actions (e);
				
			}
		});
		panel_4.add(btnAceptar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setActionCommand("BTN_CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				actions (e);
				
			}
		});		
		panel_4.add(btnCancelar);
		panel.setLayout(gl_panel);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("New label");
		panel_2.add(lblTitle);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		
		JLayeredPane panel_6 = new JLayeredPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		JLabel lblCif = new JLabel("Cif");
		
		txtCif = new JTextField();
		txtCif.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direccion");
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		
		JLabel lblCapacidad = new JLabel("Capacidad");
		
		txtCapacidad = new JTextField();
		txtCapacidad.setColumns(10);
		
		JLabel lblJefetaller = new JLabel("JefeTaller");
		
		cmbJefeTaller = new JComboBox();
				
		JLabel lblTelefono = new JLabel("Telefono");
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		
		JLabel lblWeb = new JLabel("Web");
		
		txtWeb = new JTextField();
		txtWeb.setColumns(10);
		
		chkActivo = new JCheckBox("Activo");
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addComponent(lblWeb, Alignment.TRAILING)
						.addComponent(lblTelefono, Alignment.TRAILING)
						.addComponent(lblJefetaller, Alignment.TRAILING)
						.addComponent(lblCapacidad, Alignment.TRAILING)
						.addComponent(lblDireccion, Alignment.TRAILING)
						.addComponent(lblCif, Alignment.TRAILING))
					.addGap(5)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addComponent(txtCif, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtDireccion, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCapacidad, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbJefeTaller, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtWeb, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
						.addComponent(chkActivo)))
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_6.createSequentialGroup()
							.addGap(2)
							.addComponent(lblCif))
						.addComponent(txtCif, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDireccion)
						.addComponent(txtDireccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCapacidad)
						.addComponent(txtCapacidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addComponent(lblJefetaller)
						.addComponent(cmbJefeTaller, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTelefono)
						.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addComponent(lblWeb)
						.addComponent(txtWeb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addComponent(chkActivo))
		);
		gl_panel_6.linkSize(SwingConstants.VERTICAL, new Component[] {lblCif, lblDireccion, lblCapacidad, lblJefetaller, lblTelefono, lblWeb});
		panel_6.setLayout(gl_panel_6);
		
		JLabel lblId = new JLabel("New label");
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setColumns(10);
		
		JLabel lblFalta = new JLabel("FAlta");
		
		 txtFalta = new JTextField();
		txtFalta.setEditable(false);
	
		JLabel lblFmodificacion = new JLabel("FModificacion");
		
		txtFmodificacion = new JTextField();
		txtFmodificacion.setEditable(false);
		
		JLabel lblFbaja = new JLabel("FBaja");
		
		txtFbaja = new JTextField();
		txtFbaja.setEditable(false);
		
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(56)
							.addComponent(lblId))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(90)
							.addComponent(lblFalta))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(30)
							.addComponent(lblFmodificacion))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(87)
							.addComponent(lblFbaja)))
					.addGap(5)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtFalta, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtFmodificacion, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtFbaja, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(2)
							.addComponent(lblId))
						.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(160)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(2)
							.addComponent(lblFalta))
						.addComponent(txtFalta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(2)
							.addComponent(lblFmodificacion))
						.addComponent(txtFmodificacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(2)
							.addComponent(lblFbaja))
						.addComponent(txtFbaja, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		gl_panel_3.linkSize(SwingConstants.VERTICAL, new Component[] {txtId, txtFalta, txtFmodificacion, txtFbaja});
		gl_panel_3.linkSize(SwingConstants.VERTICAL, new Component[] {lblId, lblFalta, lblFmodificacion, lblFbaja});
		panel_3.setLayout(gl_panel_3);
		panel_1.setLayout(gl_panel_1);
	}
	

	private void cargarCmbJefeTaller (){
		cbJefeTaller = new ArrayList<ItemCombo>();
		DefaultComboBoxModel defaultCombo = new DefaultComboBoxModel();
		try {
			List<Usuari> jefesTaller = gestorConexion.getUsuarisCapTaller();
			cbJefeTaller.add(new ItemCombo(-1,"","0"));
			for (int i = 0; i < jefesTaller.size(); i++) {				
				Usuari jefeTaller = jefesTaller.get(i);
				cbJefeTaller.add(new ItemCombo(i+1, jefeTaller.getNomCognoms(), 
						String.valueOf(jefeTaller.getId())));
				cmbJefeTaller.insertItemAt(jefeTaller.getNomCognoms(), i);
			}
			for (int i = 0; i < cbJefeTaller.size(); i++)
				defaultCombo.insertElementAt(cbJefeTaller.get(i).getValue(), i);
			cmbJefeTaller.setModel(defaultCombo);
			cmbJefeTaller.setSelectedItem(-1);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GestorConexionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void cargarTaller (){
		if (Integer.valueOf(idTaller) == null)
			taller = new Taller ();
		else {
			try {
				taller = gestorConexion.getTallerById(idTaller);
				txtId.setText(String.valueOf(taller.getId()));
				txtCif.setText(taller.getCif());
				txtDireccion.setText(taller.getAdreca());
				txtCapacidad.setText(String.valueOf(taller.getCapacitat()));
				txtTelefono.setText(taller.getTelefon());
				txtWeb.setText(taller.getWeb());
				chkActivo.setSelected(taller.isActiu());			
				for (int i = 0; i < cbJefeTaller.size(); i ++){
					if (Integer.valueOf(cbJefeTaller.get(i).getAux()) == taller.getCapTaller()) {
						cmbJefeTaller.setSelectedItem(cbJefeTaller.get(i).getValue());
						break;
					}
				}
				
				txtFalta.setText(taller.getDataApertura().toString());
				if (taller.getDataModificacio()!= null)
					txtFmodificacion.setText(taller.getDataModificacio().toString());
				if (taller.getDataBaixa()!= null)
					txtFbaja.setText(taller.getDataBaixa().toString());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (GestorConexionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	private void cargarAccion () {
		if (accion.toString().equalsIgnoreCase("MODIFICAR")) {
			txtCif.setEnabled(true);
			txtDireccion.setEnabled(true);
			txtCapacidad.setEnabled(true);
			cmbJefeTaller.setEnabled(true);
			txtTelefono.setEnabled(true);
			txtWeb.setEnabled(true);
			if (chkActivo.isSelected())
				chkActivo.setEnabled(false);
		} else if (accion.toString().equalsIgnoreCase("BAJA")){
			txtCif.setEnabled(false);
			txtDireccion.setEnabled(false);
			txtCapacidad.setEnabled(false);
			cmbJefeTaller.setEnabled(false);
			txtTelefono.setEnabled(false);
			txtWeb.setEnabled(false);
			if (!(chkActivo.isSelected()))
				chkActivo.setEnabled(false);
		} else {
			txtCif.setEnabled(true);
			txtDireccion.setEnabled(true);
			txtCapacidad.setEnabled(true);
			cmbJefeTaller.setEnabled(true);
			txtTelefono.setEnabled(true);
			txtWeb.setEnabled(true);
			chkActivo.setEnabled(true);
		}
		
	}
	private void actions (ActionEvent action){
		if (action.getActionCommand().toString().equals("BTN_SALIR")) {
			dispose ();
		} else if (action.getActionCommand().toString().equals("BTN_ACEPTAR")) {
			
		} else if (action.getActionCommand().toString().equals("BTN_CANCELAR")) {
			cargarTaller();
			cargarAccion();
		}
	}
}
