package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.uoc.tdp.pac4.beans.PerfilUsuari;
import edu.uoc.tdp.pac4.beans.Taller;
import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.common.ItemCombo;
import edu.uoc.tdp.pac4.common.Nif;
import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import edu.uoc.tdp.pac4.service.GestorConexionInterface;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MntoUsuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GestorConexionInterface gestorConexion;
	
	private JPanel contentPane;
	private JButton btnAceptar;
	private JButton btnCancelar;

	
	private String accion;
	private int idUsuari;
	private Usuari usuari;
	private ArrayList<ItemCombo> cbTaller;
	private JTextField txtId;
	private JTextField txtNif;
	private JTextField txtFalta;
	private JTextField txtFmodificacion;
	private JTextField txtFbaja;
	private JTextField txtUsuario;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JPasswordField txtPassword;
	private JPasswordField txtRepeatPass;
	private JComboBox cmbPerfil;
	private JCheckBox chkActivo;
	private JComboBox cmbTaller;
	private JLabel lblTitle;
	
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
	public MntoUsuario() {
		initialize ();
	}
	
	public MntoUsuario (GestorConexionInterface gestorConexion) {
		this.gestorConexion = gestorConexion;
		this.accion = "NUEVO";
		initialize ();
		initCmbPerfil();		
		initCmbTaller();
		cargarOperacion ();
	}
	
	public MntoUsuario (GestorConexionInterface gestorConexion, String accion, int idUsuari){
		this.gestorConexion = gestorConexion;
		this.accion = accion;
		this.idUsuari = idUsuari;
		initialize ();
		initCmbPerfil ();
		initCmbTaller ();
		leerUsuariById();
		cargarOperacion ();
		mostrarUsuari();
	}
	
	private void initialize (){
		this.setTitle(TDSLanguageUtils.getMessage("mntousuario.titulo.ventana"));
		setBounds(100, 100, 800, 500);
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
		
		JButton btnSalir = new JButton(TDSLanguageUtils.getMessage("mntousuario.btn.salir"));
		btnSalir.setActionCommand("BTN_SALIR");
		btnSalir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				actions (e);
			}
		});
		panel_5.add(btnSalir);
		
		btnAceptar = new JButton(TDSLanguageUtils.getMessage("mntousuario.btn.aceptar"));
		btnAceptar.setActionCommand("BTN_ACEPTAR");
		btnAceptar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				actions (e);
				
			}
		});
		panel_4.add(btnAceptar);
		
		btnCancelar = new JButton(TDSLanguageUtils.getMessage("mntousuario.btn.cancelar"));
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
		
		JLabel lblId = new JLabel(TDSLanguageUtils.getMessage("mntousuario.label.id"));
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNif = new JLabel(TDSLanguageUtils.getMessage("mntousuario.label.nif"));
		lblNif.setHorizontalAlignment(SwingConstants.RIGHT);
		
		txtId = new JTextField();
		txtId.setColumns(10);
		
		txtNif = new JTextField();
		txtNif.setColumns(10);
		
		JLabel lblNombre = new JLabel(TDSLanguageUtils.getMessage("mntousuario.label.nombre"));
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblApellidos = new JLabel(TDSLanguageUtils.getMessage("mntousuario.label.apellidos"));
		lblApellidos.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblTaller = new JLabel(TDSLanguageUtils.getMessage("mntousuario.label.taller"));
		lblTaller.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblPassword = new JLabel(TDSLanguageUtils.getMessage("mntousuario.label.password"));
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblRepeatpass = new JLabel(TDSLanguageUtils.getMessage("mntousuario.label.repeatpass"));
		lblRepeatpass.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblUsuario = new JLabel(TDSLanguageUtils.getMessage("mntousuario.label.usuario"));
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblPerfil = new JLabel(TDSLanguageUtils.getMessage("mntousuario.label.perfil"));
		lblPerfil.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblFalta = new JLabel(TDSLanguageUtils.getMessage("mntousuario.label.falta"));
		lblFalta.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblFmodificacion = new JLabel(TDSLanguageUtils.getMessage("mntousuario.label.fmodificacion"));
		lblFmodificacion.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblFbaja = new JLabel(TDSLanguageUtils.getMessage("mntousuario.label.fbaja"));
		lblFbaja.setHorizontalAlignment(SwingConstants.RIGHT);
		
		txtFalta = new JTextField();
		txtFalta.setColumns(10);
		
		txtFmodificacion = new JTextField();
		txtFmodificacion.setColumns(10);
		
		txtFbaja = new JTextField();
		txtFbaja.setColumns(10);
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		
		cmbPerfil = new JComboBox();
		
		chkActivo = new JCheckBox(TDSLanguageUtils.getMessage("mntousuario.label.activo"));
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		
		cmbTaller = new JComboBox();
		
		txtPassword = new JPasswordField();
		
		txtRepeatPass = new JPasswordField();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblPerfil)
							.addComponent(lblUsuario)
							.addComponent(lblFalta)
							.addComponent(lblFmodificacion)
							.addComponent(lblFbaja))
						.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblTaller)
								.addComponent(lblApellidos)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(cmbPerfil, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(chkActivo))
									.addGap(84)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNif, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPassword)
										.addComponent(lblRepeatpass)))
								.addComponent(lblNombre))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtRepeatPass)
								.addComponent(txtNombre)
								.addComponent(txtApellidos)
								.addComponent(cmbTaller, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPassword)
								.addComponent(txtNif, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)))
						.addComponent(txtFalta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtFmodificacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtFbaja, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(49, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap(149, Short.MAX_VALUE)
							.addComponent(cmbPerfil, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(14))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(23)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtNif, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblNif))
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblId)
									.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNombre))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtApellidos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblApellidos))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(cmbTaller, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTaller))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblUsuario)
										.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPassword))
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(14)))
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPerfil)
								.addComponent(txtRepeatPass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRepeatpass))
							.addGap(15)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chkActivo)
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFalta)
						.addComponent(txtFalta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFmodificacion)
						.addComponent(txtFmodificacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFbaja)
						.addComponent(txtFbaja, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(63, Short.MAX_VALUE))
		);
		gl_panel_1.linkSize(SwingConstants.HORIZONTAL, new Component[] {lblNif, lblNombre, lblApellidos, lblTaller, lblPassword, lblRepeatpass});
		gl_panel_1.linkSize(SwingConstants.HORIZONTAL, new Component[] {lblId, lblUsuario, lblPerfil, lblFalta, lblFmodificacion, lblFbaja});
		panel_1.setLayout(gl_panel_1);
		setResizable(false);
		pack();
	}
	
	private void initCmbPerfil () {
		for (PerfilUsuari t : PerfilUsuari.values()) {
			cmbPerfil.addItem(t.toString());
		}
	}
	
	private void initCmbTaller () {
		cbTaller = new ArrayList<ItemCombo>();
		List<Taller> talleres;
		try {
			talleres = this.gestorConexion.getAllTallers();
			cbTaller.add(new ItemCombo(0, "", "0"));
			for (int i= 0; i < talleres.size(); i ++)
				cbTaller.add(new ItemCombo(i+1,talleres.get(i).getCif(),String.valueOf(talleres.get(i).getId())));
			for (int i=0; i < cbTaller.size(); i++) {
				cmbTaller.insertItemAt(cbTaller.get(i).getValue(), i);				
			}
			cmbTaller.setSelectedIndex(0);

		} catch (Exception e) {
			showError(e.getMessage(),"GESCON.showmessage.error");
		} 
		
	}
	
	private void cargarOperacion () {
		
		txtId.setEnabled(false);
		txtFalta.setEnabled(false);
		txtFmodificacion.setEnabled(false);
		txtFbaja.setEnabled(false);
		
		if (this.accion.equalsIgnoreCase("NUEVO")) {
			lblTitle.setText(TDSLanguageUtils.getMessage("mntousuario.titulo.alta"));
			txtNif.setEnabled(true);
			txtNombre.setEnabled(true);
			txtApellidos.setEnabled(true);
			txtUsuario.setEnabled(true);
			txtPassword.setEnabled(true);
			txtRepeatPass.setEnabled(true);
			cmbTaller.setEnabled(true);
			cmbPerfil.setEnabled(true);
			chkActivo.setEnabled(true);
		} else if (this.accion.equalsIgnoreCase("MODIFICAR")) {
			lblTitle.setText(TDSLanguageUtils.getMessage("mntousuario.titulo.modificacion"));
			txtNif.setEnabled(true);
			txtNombre.setEnabled(true);
			txtApellidos.setEnabled(true);
			txtUsuario.setEnabled(true);
			txtPassword.setEnabled(true);
			txtRepeatPass.setEnabled(true);
			cmbTaller.setEnabled(true);
			cmbPerfil.setEnabled(false);
			if (usuari.isActiu())
				chkActivo.setEnabled(false);
		} else if (this.accion.equalsIgnoreCase("BAJA")){
			lblTitle.setText(TDSLanguageUtils.getMessage("mntousuario.titulo.baja"));
			txtNif.setEnabled(false);
			txtNombre.setEnabled(false);
			txtApellidos.setEnabled(false);
			txtUsuario.setEnabled(false);
			txtPassword.setEnabled(false);
			txtRepeatPass.setEnabled(false);
			cmbTaller.setEnabled(false);
			cmbPerfil.setEnabled(false);
			if (usuari.isActiu()) {
				chkActivo.setEnabled(true);
			} else {
				chkActivo.setEnabled(false);
				btnAceptar.setEnabled(false);
				btnCancelar.setEnabled(false);				
			}
		}
	}
	private void leerUsuariById () {
		try {
			usuari = gestorConexion.getUsuariById(idUsuari);
		} catch (Exception e) {
			showError(e.getMessage(), "GESCON.showmessage.error");
		} 
	}
	
	private void leerUsuariByNif () {
		try {
			usuari = gestorConexion.getUsuariByNif(txtNif.getText().toUpperCase());
			idUsuari = usuari.getId();
		} catch (Exception e) {
			showError(e.getMessage(), "GESCON.showmessage.error");
		} 
	}

	private void mostrarUsuari () {
		txtId.setText(String.valueOf(usuari.getId()));
		txtNif.setText(usuari.getNif());
		txtNombre.setText(usuari.getNom());
		txtApellidos.setText(usuari.getCognoms());
		chkActivo.setSelected(usuari.isActiu());
		cmbPerfil.setSelectedItem(usuari.getPerfil());
		System.out.println("Usuari.getTaller = " + usuari.getTaller());
		for (int i = 0; i < cbTaller.size(); i ++) {
			if (Integer.valueOf(cbTaller.get(i).getAux()) == usuari.getTaller()) {
				cmbTaller.setSelectedIndex(Integer.valueOf(cbTaller.get(i).getAux()));
				break;
			}
		}
		
		cmbTaller.setSelectedItem(usuari.getTaller());
		txtUsuario.setText(usuari.getUsuari());
		txtPassword.setText(usuari.getContrasenya());
		txtRepeatPass.setText(usuari.getContrasenya());
		txtFalta.setText(usuari.getDataAlta().toString());
		if (usuari.getDataModificacio() != null)
			txtFmodificacion.setText(usuari.getDataModificacio().toString());
		if (usuari.getDataBaixa() != null)
			txtFbaja.setText(usuari.getDataBaixa().toString());
		
		
	}

	
	private void actions (ActionEvent action){
		if (action.getActionCommand().toString().equalsIgnoreCase("BTN_ACEPTAR")){
			if (this.accion.equals("NUEVO")){
				Usuari usuari = new Usuari ();
				usuari.setNif(txtNif.getText().toUpperCase());
				usuari.setNom(txtNombre.getText());
				usuari.setCognoms(txtApellidos.getText());
				usuari.setUsuari(txtUsuario.getText().toUpperCase());
				usuari.setPerfil(cmbPerfil.getSelectedItem().toString());
				usuari.setContrasenya(new String(txtPassword.getPassword()));
				usuari.setActiu(chkActivo.isSelected());
				usuari.setTaller(Integer.valueOf(cbTaller.get(cmbTaller.getSelectedIndex()).getAux()));
				String msg = validarCampos();
				if (msg.equals("")) {
				
					try {
						gestorConexion.altaUsuari(usuari);
						this.accion = "MODIFICAR";
						leerUsuariByNif();
						cargarOperacion();
						mostrarUsuari();					
					} catch (Exception e) {
						showError(e.getMessage(),this.lblTitle.getText());
					}
				} else {
					showInfo(msg, "GESCON.showmessage.valida");
				}
				
				
			}else if (this.accion.equalsIgnoreCase("MODIFICAR")){	
				usuari.setNif(txtNif.getText().toUpperCase());
				usuari.setNom(txtNombre.getText());
				usuari.setCognoms(txtApellidos.getText());
				usuari.setUsuari(txtUsuario.getText().toUpperCase());
				usuari.setPerfil(cmbPerfil.getSelectedItem().toString());
				usuari.setContrasenya(new String(txtPassword.getPassword()));
				usuari.setActiu(chkActivo.isSelected());
				usuari.setTaller(Integer.valueOf(cbTaller.get(cmbTaller.getSelectedIndex()).getAux()));
				String msg = validarCampos();
				if (msg.equals("")) {
					try {
						gestorConexion.modificarUsuari(usuari);
						leerUsuariById();
						mostrarUsuari();
					} catch (Exception e) {
						showError(e.getMessage(), this.lblTitle.getText());
					}
				} else {
					showInfo(msg, "GESCON.showmessage.valida");
				}
			} else {
				try {
					gestorConexion.disableUser(usuari.getId());
					leerUsuariById();
					cargarOperacion();
					mostrarUsuari();					
				} catch (Exception e ) {
					showError(e.getMessage(), this.lblTitle.getText());
				}
			}
		} else if (action.getActionCommand().toString().equals("BTN_CANCELAR")) {
			if (usuari != null) {
				leerUsuariById();
				cargarOperacion();
				mostrarUsuari();
			}
		} else {
			dispose();
		}
	}
	
	private String validarCampos () {
		String msg = "";
		if (txtNif.getText().length() == 0)			
			return "mntousuario.valida.nif";
		try {
			if (!(Nif.validar(txtNif.getText())))			
				return "mntousuario.valida.nif";
		} catch (Exception e){
				return "mntousuario.valida.nif";
		}
		if (txtNombre.getText().length() == 0)
			return "mntousuario.valida.nombre";
		if (txtApellidos.getText().length() == 0)
			return "mntousuario.valida.apellidos";
		if (txtUsuario.getText().length()==0)
			return "mntousuario.valida.usuario";
		if (new String(txtPassword.getPassword()).length() == 0)
			return "mntousuario.valida.password";
		if (!(new String(txtPassword.getPassword()).equals(new String(txtRepeatPass.getPassword()))))
			return "mntousuario.valida.repeatpass";
		if (!(cmbPerfil.getSelectedItem().equals(PerfilUsuari.Administrador.toString()))) {
			if ((chkActivo.isSelected()) && (cmbTaller.getSelectedIndex()==0))
				return "mntousuario.valida.taller";
		} else if (cmbTaller.getSelectedIndex() != 0) {
			return "mntousuario.valida.talleradmin";
		}
		return msg;
	}
	
	private void showError (String message, String title){		
		String txtTitle;		
		txtTitle = TDSLanguageUtils.getMessage(title);
		showMessage (message, txtTitle,JOptionPane.ERROR_MESSAGE);
	}
	
	private void showInfo (String message, String title){
		String txtMessage;
		String txtTitle;		
		txtMessage = TDSLanguageUtils.getMessage(message);
		txtTitle = TDSLanguageUtils.getMessage(title);
		showMessage (txtMessage, txtTitle,JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void showMessage (String message, String title, int messageType) {
		JOptionPane.showMessageDialog(this, message, title, messageType);
	}
}
