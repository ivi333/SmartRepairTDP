package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListDataListener;

import edu.uoc.tdp.pac4.beans.PerfilUsuari;
import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.common.ItemCombo;
import edu.uoc.tdp.pac4.exception.GestorConexionException;
import edu.uoc.tdp.pac4.service.GestorConexionInterface;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MntoUsuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GestorConexionInterface gestorConexion;
	
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtNif;
	private JTextField txtNom;
	private JTextField txtCognoms;
	private JTextField txtUsuari;
	private JTextField txtDAlta;
	private JTextField txtDModificacio;
	private JTextField txtDBaixa;
	private JPasswordField txtPassword;
	private JPasswordField txtRepeatPassword;
	private JComboBox cmbPerfil;
	private JCheckBox chkActiu;

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
		initialize ();
		initCmbPerfil();
	}
	
	public MntoUsuario (GestorConexionInterface gestorConexion, String accion, int idUsuari){
		this.gestorConexion = gestorConexion;
		initialize ();
		initCmbPerfil ();
		carregarUsuari (idUsuari);
	}
	
	private void initialize (){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 814, 521);
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
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(277)
					.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(276))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGap(344)
					.addComponent(panel_5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(331))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JButton btnSalir = new JButton("New button");
		btnSalir.setActionCommand("BTN_SALIR");
		btnSalir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				actions (e);
			}
		});
		panel_5.add(btnSalir);
		
		JButton btnAceptar = new JButton("New button");
		btnAceptar.setActionCommand("BTN_ACEPTAR");
		btnAceptar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				actions (e);
				
			}
		});
		panel_4.add(btnAceptar);
		
		JButton btnCancelar = new JButton("New button");
		btnCancelar.setActionCommand("BTN_CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				actions (e);
				
			}
		});		
		panel_4.add(btnCancelar);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel lblId = new JLabel("ID");
		panel_1.add(lblId, "2, 4, right, default");
		
		txtId = new JTextField();
		txtId.setText("txtId");
		panel_1.add(txtId, "4, 4");
		txtId.setColumns(10);
		txtId.setEditable(false);
		
		JLabel lblNif = new JLabel("NIF");
		panel_1.add(lblNif, "6, 4, right, default");
		
		txtNif = new JTextField();
		txtNif.setText("NIF");
		panel_1.add(txtNif, "8, 4");
		txtNif.setColumns(10);
		
		JLabel lblNom = new JLabel("NOMBRE");
		panel_1.add(lblNom, "6, 6, right, default");
		
		txtNom = new JTextField();
		txtNom.setText("NOMBRE");
		panel_1.add(txtNom, "8, 6");
		txtNom.setColumns(10);
		
		JLabel lblCognoms = new JLabel("APELLIDOS");
		panel_1.add(lblCognoms, "6, 8, right, default");
		
		txtCognoms = new JTextField();
		txtCognoms.setText("txtCogoms");
		panel_1.add(txtCognoms, "8, 8");
		txtCognoms.setColumns(10);
		
		JLabel lblUsuari = new JLabel("lblUsuario");
		panel_1.add(lblUsuari, "2, 12, right, default");
		
		txtUsuari = new JTextField();
		txtUsuari.setText("txtUsuario");
		panel_1.add(txtUsuari, "4, 12, fill, default");
		txtUsuari.setColumns(10);
		
		JLabel lblPassword = new JLabel("New label");
		panel_1.add(lblPassword, "6, 12, right, default");
		
		txtPassword = new JPasswordField();
		txtPassword.setText("txtPassword");
		panel_1.add(txtPassword, "8, 12, fill, default");
		
		JLabel lblPerfil = new JLabel("lblPerfil");
		panel_1.add(lblPerfil, "2, 14, right, default");
		
		cmbPerfil = new JComboBox();
		panel_1.add(cmbPerfil, "4, 14, fill, default");
		
		JLabel lblLblrepeatpassword = new JLabel("lblRepeatPassword");
		panel_1.add(lblLblrepeatpassword, "6, 14, right, default");
		
		txtRepeatPassword = new JPasswordField();
		txtRepeatPassword.setText("txtRepeatPassword");
		panel_1.add(txtRepeatPassword, "8, 14, fill, default");
		
		chkActiu = new JCheckBox("chkActivo");
		panel_1.add(chkActiu, "4, 16");
		
		JLabel lblFAlta = new JLabel("lblAlta");
		panel_1.add(lblFAlta, "2, 22, right, default");
		
		txtDAlta = new JTextField();
		txtDAlta.setText("txtAlta");
		panel_1.add(txtDAlta, "4, 22, fill, default");
		txtDAlta.setColumns(10);
		
		JLabel lblFModificaico = new JLabel("lblModificacion");
		panel_1.add(lblFModificaico, "2, 24, right, default");
		
		txtDModificacio = new JTextField();
		txtDModificacio.setText("txtModificacion");
		panel_1.add(txtDModificacio, "4, 24, fill, default");
		txtDModificacio.setColumns(10);
		
		JLabel lblDBaixa = new JLabel("lblBaja");
		panel_1.add(lblDBaixa, "2, 26, right, default");
		
		txtDBaixa = new JTextField();
		txtDBaixa.setText("txtBaja");
		panel_1.add(txtDBaixa, "4, 26, fill, default");
		txtDBaixa.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("New label");
		panel_2.add(lblTitle);
	}
	
	private void initCmbPerfil () {
		for (PerfilUsuari t : PerfilUsuari.values()) {
			cmbPerfil.addItem(t.toString());
		}
	}
	private void carregarUsuari (int idUsuari){
		try {
			Usuari usuari = gestorConexion.getUsuariById(idUsuari);
			txtId.setText(String.valueOf(usuari.getId()));
			txtNif.setText(usuari.getNif());
			txtNom.setText(usuari.getNom());
			txtCognoms.setText(usuari.getCognoms());
			chkActiu.setSelected(usuari.isActiu());
			cmbPerfil.setSelectedItem(usuari.getPerfil());
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GestorConexionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void actions (ActionEvent action){
		
	}
}
