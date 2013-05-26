package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import edu.uoc.tdp.pac4.beans.Taller;
import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.common.ItemCombo;
import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import edu.uoc.tdp.pac4.exception.GestorConexionException;
import edu.uoc.tdp.pac4.service.GestorConexionInterface;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;


public class MntoTaller extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GestorConexionInterface gestorConexion;
	
	private JPanel contentPane;
	private JButton btnAceptar;
	private JButton btnCancelar;

	
	private String accion;
	private int idTaller;
	private Taller taller;
	private ArrayList<ItemCombo> cbJefeTaller;	
	
	private JTextField txtId;
	private JTextField txtCif;
	private JTextField txtFalta;
	private JTextField txtFmodificacion;
	private JTextField txtFbaja;
	private JTextField txtDireccion;
	private JTextField txtCapacidad;
	private JTextField txtTelefono;
	private JTextField txtWeb;
	private JCheckBox chkActivo;
	private JComboBox cmbJefeTaller;
	private JLabel lblTitle;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MntoTaller frame = new MntoTaller();
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
		this.accion = "NUEVO";
		initialize ();
		initCmbJefeTaller();
		cargarOperacion ();
		
	}
	
	public MntoTaller (GestorConexionInterface gestorConexion, String accion, int idTaller){
		this.gestorConexion = gestorConexion;
		this.accion = accion;
		this.idTaller = idTaller;
		initialize ();
		initCmbJefeTaller ();
		leerTallerById();
		cargarOperacion ();
		mostrarTaller();
		
	}
	
	private void initialize (){
		this.setTitle(TDSLanguageUtils.getMessage("mntotaller.titulo.ventana"));
		setBounds(100, 100, 958, 529);
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
		
		JButton btnSalir = new JButton(TDSLanguageUtils.getMessage("mntotaller.btn.salir"));
		btnSalir.setActionCommand("BTN_SALIR");
		btnSalir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				actions (e);
			}
		});
		panel_5.add(btnSalir);
		
		btnAceptar = new JButton(TDSLanguageUtils.getMessage("mntotaller.btn.aceptar"));
		btnAceptar.setActionCommand("BTN_ACEPTAR");
		btnAceptar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				actions (e);
				
			}
		});
		panel_4.add(btnAceptar);
		
		btnCancelar = new JButton(TDSLanguageUtils.getMessage("mntotaller.btn.cancelar"));
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
		
		lblTitle = new JLabel("Titulo");
		panel_2.add(lblTitle);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		JLabel lblId = new JLabel(TDSLanguageUtils.getMessage("mntotaller.label.id"));
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblCif = new JLabel(TDSLanguageUtils.getMessage("mntotaller.label.cif"));
		lblCif.setHorizontalAlignment(SwingConstants.RIGHT);
		
		txtId = new JTextField();
		txtId.setColumns(10);
		
		txtCif = new JTextField();
		txtCif.setColumns(10);
		
		JLabel lblDireccion = new JLabel(TDSLanguageUtils.getMessage("mntotaller.label.direccion"));
		lblDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblCapacidad = new JLabel(TDSLanguageUtils.getMessage("mntotaller.label.capacidad"));
		lblCapacidad.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblJefeTaller = new JLabel(TDSLanguageUtils.getMessage("mntotaller.label.jefetaller"));
		lblJefeTaller.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblTelefono = new JLabel(TDSLanguageUtils.getMessage("mntotaller.label.telefono"));
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblWeb = new JLabel(TDSLanguageUtils.getMessage("mntotaller.label.web"));
		lblWeb.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblFalta = new JLabel(TDSLanguageUtils.getMessage("mntotaller.label.falta"));
		lblFalta.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblFmodificacion = new JLabel(TDSLanguageUtils.getMessage("mntotaller.label.fmodificacion"));
		lblFmodificacion.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblFbaja = new JLabel(TDSLanguageUtils.getMessage("mntotaller.label.fbaja"));
		lblFbaja.setHorizontalAlignment(SwingConstants.RIGHT);
		
		txtFalta = new JTextField();
		txtFalta.setColumns(10);
		
		txtFmodificacion = new JTextField();
		txtFmodificacion.setColumns(10);
		
		txtFbaja = new JTextField();
		txtFbaja.setColumns(10);
		
		
		chkActivo = new JCheckBox(TDSLanguageUtils.getMessage("mntotaller.label.activo"));
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		
		txtCapacidad = new JTextField();
		txtCapacidad.setColumns(10);
		txtCapacidad.addKeyListener(new KeyAdapterNumbersOnly());
		
		cmbJefeTaller = new JComboBox();
		
		txtTelefono = new JTextField();
		txtTelefono.addKeyListener(new KeyAdapterNumbersOnly());
		
		txtWeb = new JTextField();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblFalta)
							.addComponent(lblFmodificacion)
							.addComponent(lblFbaja))
						.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
					.addGap(44)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDireccion, Alignment.TRAILING)
								.addComponent(lblCapacidad, Alignment.TRAILING)
								.addComponent(lblJefeTaller, Alignment.TRAILING)
								.addComponent(lblTelefono, Alignment.TRAILING)
								.addComponent(lblWeb, Alignment.TRAILING)
								.addComponent(lblCif, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(cmbJefeTaller, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(chkActivo)
								.addComponent(txtDireccion, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCif, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCapacidad, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(txtWeb, Alignment.LEADING)
									.addComponent(txtTelefono, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))))
						.addComponent(txtFbaja, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtFmodificacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtFalta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(112))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblCif)
							.addComponent(txtCif, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblId)
							.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDireccion)
						.addComponent(txtDireccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCapacidad)
						.addComponent(txtCapacidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblJefeTaller)
						.addComponent(cmbJefeTaller, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelefono)
						.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWeb)
						.addComponent(txtWeb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addComponent(chkActivo)
					.addGap(69)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFalta)
						.addComponent(txtFalta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFmodificacion)
						.addComponent(txtFmodificacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFbaja)
						.addComponent(txtFbaja, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(24))
		);
		gl_panel_1.linkSize(SwingConstants.HORIZONTAL, new Component[] {txtCif, txtCapacidad, txtTelefono, txtWeb});
		gl_panel_1.linkSize(SwingConstants.HORIZONTAL, new Component[] {lblId, lblFalta, lblFmodificacion, lblFbaja});
		gl_panel_1.linkSize(SwingConstants.HORIZONTAL, new Component[] {lblCif, lblDireccion, lblCapacidad, lblJefeTaller, lblTelefono, lblWeb});
		panel_1.setLayout(gl_panel_1);
		setResizable(false);
		pack();
	}
	
	private void initCmbJefeTaller () {
		cbJefeTaller = new ArrayList<ItemCombo>();
		List<Usuari> usuaris;
		try {
			usuaris = this.gestorConexion.getUsuarisCapTallerDisponibles(idTaller);
			cbJefeTaller.add(new ItemCombo(0, "", "0"));
			for (int i= 0; i < usuaris.size(); i ++)
				cbJefeTaller.add(new ItemCombo(i+1,usuaris.get(i).getNomCognoms(),String.valueOf(usuaris.get(i).getId())));
			for (int i=0; i < cbJefeTaller.size(); i++) {
				cmbJefeTaller.insertItemAt(cbJefeTaller.get(i).getValue(), i);				
			}
			cmbJefeTaller.setSelectedIndex(0);

		} catch (GestorConexionException e){
			showErrorKey(e.getMessage(), "");
		} catch (Exception e) {
			showError(e.getMessage(), "");
		}
		
	}
	
	private void cargarOperacion () {
		
		txtId.setEnabled(false);
		txtFalta.setEnabled(false);
		txtFmodificacion.setEnabled(false);
		txtFbaja.setEnabled(false);
		
		if (this.accion.equalsIgnoreCase("NUEVO")) {
			lblTitle.setText(TDSLanguageUtils.getMessage("mntotaller.titulo.alta"));
			txtCif.setEnabled(true);
			txtDireccion.setEnabled(true);
			txtCapacidad.setEnabled(true);
			txtTelefono.setEnabled(true);
			txtWeb.setEnabled(true);
			cmbJefeTaller.setEnabled(true);
			chkActivo.setEnabled(true);
		} else if (this.accion.equalsIgnoreCase("MODIFICAR")) {
			lblTitle.setText(TDSLanguageUtils.getMessage("mntotaller.titulo.modificacion"));
			txtCif.setEnabled(true);
			txtDireccion.setEnabled(true);
			txtCapacidad.setEnabled(true);
			txtTelefono.setEnabled(true);
			txtWeb.setEnabled(true);
			cmbJefeTaller.setEnabled(true);
			if (taller.isActiu())
				chkActivo.setEnabled(false);
		} else if (this.accion.equalsIgnoreCase("BAJA")){
			lblTitle.setText(TDSLanguageUtils.getMessage("mntotaller.titulo.baja"));
			txtCif.setEnabled(false);
			txtDireccion.setEnabled(false);
			txtCapacidad.setEnabled(false);
			txtTelefono.setEnabled(false);
			txtWeb.setEnabled(false);
			cmbJefeTaller.setEnabled(false);
			if (taller.isActiu()) {
				chkActivo.setEnabled(true);
			} else {
				chkActivo.setEnabled(false);
				btnAceptar.setEnabled(false);
				btnCancelar.setEnabled(false);				
			}
		}
	}
	private void leerTallerById () {
		try {
			taller = gestorConexion.getTallerById(idTaller);		
		} catch (GestorConexionException e) {
			showErrorKey(e.getMessage(), "");
		} catch (Exception e) {
			showError(e.getMessage(), "");
		}
	}
	
	private void leerTallerByCif () {
		try {
			taller = gestorConexion.getTallerByCif (txtCif.getText().toUpperCase());
			idTaller = taller.getId();
		} catch (GestorConexionException e) {
			showErrorKey(e.getMessage(), "");
		} catch (Exception e) {
			showError(e.getMessage(), "");
		}
	}

	private void mostrarTaller () {				
		
		txtId.setText(String.valueOf(taller.getId()));
		txtCif.setText(taller.getCif());
		txtDireccion.setText(taller.getAdreca());
		txtCapacidad.setText(String.valueOf(taller.getCapacitat()));
		chkActivo.setSelected(taller.isActiu());

		for (int i = 0; i < cbJefeTaller.size(); i ++) {
			System.out.println("cbJefeTaller.get(i).getAux() " + cbJefeTaller.get(i).getAux());
			System.out.println("taller.getCapTaller() " + taller.getCapTaller());
			if (Integer.valueOf(cbJefeTaller.get(i).getAux()) == taller.getCapTaller()) {
				cmbJefeTaller.setSelectedIndex(i);
				break;
			}
		}
		

		txtTelefono.setText(taller.getTelefon());
		txtWeb.setText(taller.getWeb());
		txtFalta.setText(taller.getDataApertura().toString());
		if (taller.getDataModificacio() != null)
			txtFmodificacion.setText(taller.getDataModificacio().toString());
		if (taller.getDataBaixa() != null)
			txtFbaja.setText(taller.getDataBaixa().toString());
		
		
	}

	
	private void actions (ActionEvent action){
		if (action.getActionCommand().toString().equalsIgnoreCase("BTN_ACEPTAR")){
			
			if (this.accion.equals("NUEVO")){
				Taller taller = new Taller ();
				taller.setCif(txtCif.getText().toUpperCase());
				taller.setAdreca(txtDireccion.getText());
				taller.setCapacitat(Integer.valueOf(txtCapacidad.getText()));
				taller.setActiu(chkActivo.isSelected());
				taller.setCapTaller(Integer.valueOf(cbJefeTaller.get(cmbJefeTaller.getSelectedIndex()).getAux()));
				taller.setWeb(txtWeb.getText());
				taller.setTelefon(txtTelefono.getText());
				String msg = validarCampos();
				if (msg.equals("")) {
				
					try {
						gestorConexion.altaTaller(taller);
						showInfo(TDSLanguageUtils.getMessage("mntotaller.alta.ok"), lblTitle.getText());
						this.accion = "MODIFICAR";
						leerTallerByCif();
						cargarOperacion();
						mostrarTaller();					
					} catch (GestorConexionException e){
						showErrorKey(e.getMessage(), lblTitle.getText());
					} catch (Exception e) {
						showError(e.getMessage(), lblTitle.getText());
					}
				} else {
					showWarning(TDSLanguageUtils.getMessage(msg), lblTitle.getText());
				}
				
				
			}else if (this.accion.equalsIgnoreCase("MODIFICAR")){	
				taller.setCif(txtCif.getText().toUpperCase());
				taller.setAdreca(txtDireccion.getText());
				taller.setCapacitat(Integer.valueOf(txtCapacidad.getText()));
				taller.setActiu(chkActivo.isSelected());
				taller.setCapTaller(Integer.valueOf(cbJefeTaller.get(cmbJefeTaller.getSelectedIndex()).getAux()));
				taller.setWeb(txtWeb.getText());
				taller.setTelefon(txtTelefono.getText());
				String msg = validarCampos();
				if (msg.equals("")) {
					try {
						gestorConexion.modificarTaller(taller);
						showInfo(TDSLanguageUtils.getMessage("mntotaller.modif.ok"), lblTitle.getText());
						leerTallerById();
						mostrarTaller();
					} catch (GestorConexionException e) {
						showErrorKey(e.getMessage(), lblTitle.getText());
					} catch (Exception e) {
						showError(e.getMessage(), lblTitle.getText());
					}
				} else {
					showWarning(TDSLanguageUtils.getMessage(msg), lblTitle.getText());
				}
			} else {
				if (JOptionPane.showConfirmDialog(this, 
						TDSLanguageUtils.getMessage("mntotaller.confimar.desactivar") + " " + taller.getCif(), 
						TDSLanguageUtils.getMessage("mntotaller.atencion"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {					
					try {					
						taller.setActiu(chkActivo.isSelected());
						gestorConexion.disableTaller(taller);
						showInfo(TDSLanguageUtils.getMessage("mntotaller.baja.ok"), lblTitle.getText());
					} catch (GestorConexionException e) {
						showError (e.getMessage(), lblTitle.getText());
					} catch (Exception e) {
						showError(e.getMessage(), lblTitle.getText());
					}
				}
				leerTallerById();
				cargarOperacion();
				mostrarTaller();					

			}
		} else if (action.getActionCommand().toString().equals("BTN_CANCELAR")) {
			if (taller != null) {
				leerTallerById();
				cargarOperacion();
				mostrarTaller();
			}
		} else {
			dispose();
		}
	}
	
	private String validarCampos () {
		String msg = "";
		if (txtCif.getText().length() == 0 )
			msg = "mntotaller.valida.cif";
		if (txtDireccion.getText().length() == 0 )
			msg = "mntotaller.valida.direccion";
		if (txtCapacidad.getText().length() == 0)
			msg = "mntotaller.valida.capacidad";
		if (Integer.valueOf(txtCapacidad.getText()) == 0)
			msg = "mntotaller.valida.capacidad0";
		if (txtTelefono.getText().length() == 0)
			msg = "mntotaller.valida.telefono";
		if (txtWeb.getText().length() == 0)
			msg = "mntotaller.valida.web";
		if (chkActivo.isSelected()) {
			if (cbJefeTaller.get(cmbJefeTaller.getSelectedIndex()).getId()==0){
				msg = "mntotaller.valida.jefetaller";
			}
		}
		return msg;
	}
	
	private void showErrorKey (String key, String title) {
		String msg = TDSLanguageUtils.getMessage(key);
		if (msg.equalsIgnoreCase(""))
			msg = key;
		showError (msg, title);
	}
	
	private void showError (String message, String title){
		String titulo = TDSLanguageUtils.getMessage("GESCON.showmessage.error") + " - " +title; 
		showMessage (message, titulo, JOptionPane.ERROR_MESSAGE);
	}
	
	private void showWarning (String message, String title) {
		String titulo = TDSLanguageUtils.getMessage("GESCON.showmessage.aviso") + " - " + title;
		showMessage(message, titulo, JOptionPane.WARNING_MESSAGE);
	}
	
	private void showInfo (String message, String title){		
		showMessage (message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void showMessage (String message, String title, int messageType) {
		JOptionPane.showMessageDialog(this, message, title, messageType);
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
