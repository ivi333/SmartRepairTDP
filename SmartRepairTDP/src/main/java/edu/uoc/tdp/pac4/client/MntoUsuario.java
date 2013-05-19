package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListDataListener;

import edu.uoc.tdp.pac4.beans.PerfilUsuari;
import edu.uoc.tdp.pac4.beans.Taller;
import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.common.ItemCombo;
import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import edu.uoc.tdp.pac4.exception.GestorConexionException;
import edu.uoc.tdp.pac4.service.GestorConexionInterface;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JInternalFrame;

public class MntoUsuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GestorConexionInterface gestorConexion;
	
	private JPanel contentPane;
	private JTextField txtNif;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JPasswordField txtPassword;
	private JPasswordField txtRepeatPassword;
	private JComboBox cmbTaller;
	private JCheckBox chkActivo;
	private JTextField txtFbaja;
	private JTextField txtFModificacion;
	private JTextField txtId;
	private JTextField txtUsuario;
	private JTextField txtFalta;
	private JComboBox cmbPerfil;
	private JButton btnAceptar;
	private JButton btnCancelar;

	
	private String accion;
	private int idUsuari;
	private Usuari usuari;
	private ArrayList<ItemCombo> cbTaller;
	
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
		
		btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setActionCommand("BTN_ACEPTAR");
		btnAceptar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				actions (e);
				
			}
		});
		panel_4.add(btnAceptar);
		
		btnCancelar = new JButton("CANCELAR");
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
		
		JLabel lblTitle = new JLabel("Titulo");
		panel_2.add(lblTitle);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		JLayeredPane panel_6 = new JLayeredPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_6, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JLabel lblNif = new JLabel("NIF");
		
		txtNif = new JTextField();
		txtNif.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		
		JLabel lblTaller = new JLabel("Taller");
		
		cmbTaller = new JComboBox();
				
		JLabel lblPassword = new JLabel("Contraseña");
		
		txtPassword = new JPasswordField();
		txtPassword.setColumns(10);
		
		JLabel lblRepeatPass = new JLabel("Repeat");
		
		txtRepeatPassword= new JPasswordField();
		txtRepeatPassword.setColumns(10);
		
		chkActivo = new JCheckBox("Activo");
		
		txtFalta = new JTextField("FALTA");
		txtFalta.setEnabled(false);
		
		JLabel label = new JLabel("FBaja");
		
		JLabel lblFModificacion = new JLabel("FModificacion");
		
		txtFModificacion = new JTextField();
		txtFModificacion.setEditable(false);
		
		JLabel lblId = new JLabel("ID");
		
		JLabel lblUsuario = new JLabel("Usuario");
		
		JLabel lblPerfil = new JLabel("Perfil");
		
		JLabel lblFalta = new JLabel("Falta");
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setEditable(false);
		txtId.setColumns(10);
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		
		cmbPerfil = new JComboBox();
		
		
		txtFbaja = new JTextField();
		txtFbaja.setColumns(10);
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_6.createSequentialGroup()
							.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_6.createSequentialGroup()
									.addGap(57)
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_6.createSequentialGroup()
											.addGap(12)
											.addComponent(txtFalta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_6.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(txtFbaja, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_panel_6.createSequentialGroup()
									.addGroup(gl_panel_6.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblId)
										.addComponent(lblFModificacion, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblUsuario)
										.addComponent(lblPerfil)
										.addComponent(lblFalta))
									.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_6.createSequentialGroup()
											.addGap(12)
											.addComponent(txtFModificacion, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_6.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
												.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(cmbPerfil, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
										.addGroup(gl_panel_6.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(txtFalta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
							.addGap(115)
							.addGroup(gl_panel_6.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblPassword)
								.addComponent(lblRepeatPass)))
						.addComponent(lblApellidos)
						.addComponent(lblNombre)
						.addComponent(lblTaller)
						.addComponent(lblNif))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtApellidos, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbTaller, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtRepeatPassword, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
						.addComponent(chkActivo)
						.addComponent(txtNif, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(101))
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_6.createSequentialGroup()
							.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtNif, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNif)
								.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNombre))
							.addGap(5)
							.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtApellidos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblApellidos))
							.addGap(5)
							.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
								.addComponent(cmbTaller, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTaller)))
						.addGroup(gl_panel_6.createSequentialGroup()
							.addComponent(lblId)
							.addGap(70)))
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_6.createSequentialGroup()
							.addGap(14)
							.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUsuario)
								.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(9)
							.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPerfil)
								.addComponent(cmbPerfil, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_6.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPassword))
							.addGap(5)
							.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtRepeatPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRepeatPass))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chkActivo)))
					.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFalta)
						.addComponent(txtFalta, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_6.createSequentialGroup()
							.addGap(2)
							.addComponent(lblFModificacion))
						.addComponent(txtFModificacion, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_6.createSequentialGroup()
							.addGap(2)
							.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
								.addComponent(label)
								.addComponent(txtFbaja, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)))
						.addComponent(txtFalta, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(22))
		);
		gl_panel_6.linkSize(SwingConstants.VERTICAL, new Component[] {lblNif, lblNombre, lblApellidos, lblTaller, lblPassword, lblRepeatPass});
		panel_6.setLayout(gl_panel_6);
		panel_1.setLayout(gl_panel_1);
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

		} catch (RemoteException e) {
			showError(e.getMessage(),"GESCON.showmessage.error");
		} catch (GestorConexionException e) {
			showError(e.getMessage(),"GESCON.showmessage.error");
		}
		
	}
	
	private void cargarOperacion () {
		if (this.accion.equalsIgnoreCase("NUEVO")) {
			txtNif.setEnabled(true);
			txtNombre.setEnabled(true);
			txtApellidos.setEnabled(true);
			txtUsuario.setEnabled(true);
			txtPassword.setEnabled(true);
			txtRepeatPassword.setEnabled(true);
			cmbTaller.setEnabled(true);
			cmbPerfil.setEnabled(true);
			chkActivo.setEnabled(true);
		} else if (this.accion.equalsIgnoreCase("MODIFICAR")) {
			txtNif.setEnabled(true);
			txtNombre.setEnabled(true);
			txtApellidos.setEnabled(true);
			txtUsuario.setEnabled(true);
			txtPassword.setEnabled(true);
			txtRepeatPassword.setEnabled(true);
			cmbTaller.setEnabled(true);
			cmbPerfil.setEnabled(false);
			if (usuari.isActiu())
				chkActivo.setEnabled(false);
		} else if (this.accion.equalsIgnoreCase("BAJA")){
			txtNif.setEnabled(false);
			txtNombre.setEnabled(false);
			txtApellidos.setEnabled(false);
			txtUsuario.setEnabled(false);
			txtPassword.setEnabled(false);
			txtRepeatPassword.setEnabled(false);
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
		} catch (RemoteException e) {
			showError(e.getMessage(), "ERROR");
		} catch (GestorConexionException e) {
			showError(e.getMessage(), "ERROR");
		}
	}
	
	private void leerUsuariByNif () {
		try {
			usuari = gestorConexion.getUsuariByNif(txtNif.getText().toUpperCase());
			idUsuari = usuari.getId();
		} catch (RemoteException e) {
			showError(e.getMessage(), "ERROR");
		} catch (GestorConexionException e) {
			showError(e.getMessage(), "ERROR");
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
		txtRepeatPassword.setText(usuari.getContrasenya());
		txtFalta.setText(usuari.getDataAlta().toString());
		if (usuari.getDataModificacio() != null)
			txtFModificacion.setText(usuari.getDataModificacio().toString());
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
				if (validarPassword()) {
					try {
						gestorConexion.altaUsuari(usuari);
						this.accion = "MODIFICAR";
						leerUsuariByNif();
						cargarOperacion();
						mostrarUsuari();
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (RemoteException e) {
						showError(e.getMessage(),"GESCON.showmessage.error");
					} catch (GestorConexionException e) {
						showError(e.getMessage(),"GESCON.showmessage.error");
					}
						
				} else {
					showInfo("ZXX", "SSS");
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
				if (validarPassword()) {
					try {
						gestorConexion.modificarUsuari(usuari);
						leerUsuariById();
						mostrarUsuari();
					} catch (RemoteException e) {
						showError(e.getMessage(), "Error");
					} catch (GestorConexionException e) {
						showError (e.getMessage(), "Error");
					}
				} else {
					showInfo("AAA", "BBB");
				}
			} else {
				try {
					gestorConexion.disableUser(usuari.getId());
					leerUsuariById();
					cargarOperacion();
					mostrarUsuari();					
				} catch (NumberFormatException e) {
					showError(e.getMessage(), "error");
				} catch (RemoteException e) {
					showError(e.getMessage(), "error");
				} catch (GestorConexionException e) {
					showError(e.getMessage(), "error");
				}
			}
		} else if (action.getActionCommand().toString().equals("BTN_CANCELAR")) {
			leerUsuariById();
			cargarOperacion();
			mostrarUsuari();
		} else {
			dispose();
		}
	}
	
	private boolean validarPassword () {
		return (new String(txtPassword.getPassword()).equals(new String(txtRepeatPassword.getPassword())));
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
