package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import javax.swing.text.JTextComponent;


import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import edu.uoc.tdp.pac4.exception.GestorConexionException;
import edu.uoc.tdp.pac4.service.GestorConexionInterface;
import javax.swing.ListSelectionModel;


public class GestionUsuarios extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected GestorConexionInterface gestorConexion = null;
	
	private JPanel contentPane;
	
	private JTextField txtId;
	private JTextField txtNif;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtPerfil;
	
	private JTable tabla;
	private static final Object columnNames[] = { "ID", "NIF","NOM","APELLIDOS","PERFIL"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TDSLanguageUtils.setDefaultLanguage("i18n/messages");
					GestorConexionInterface gestorConexion = (GestorConexionInterface) Naming.lookup("rmi://localhost/GestorConexion");
					GestionUsuarios frame = new GestionUsuarios(gestorConexion);
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
	public GestionUsuarios() {
		initialize ();
		
	}
	
	public GestionUsuarios (GestorConexionInterface gestorConexion) {
		this.gestorConexion = gestorConexion;
		initialize ();
		tabla.setModel(getAllUsuaris());
	}
	
	private void initialize (){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(panelTop(), BorderLayout.NORTH);
		contentPane.add(panelBottom(), BorderLayout.SOUTH);
		contentPane.add(panelLeft(), BorderLayout.WEST);
		contentPane.add(panelRight(), BorderLayout.EAST);
		contentPane.add(panelCentral(), BorderLayout.CENTER);
		pack();
		
	}
	private JPanel panelTop () {
		JPanel panel = new JPanel();
		JButton btnAlta = new JButton("ALTA");
		btnAlta.setActionCommand("BTN_ALTA");
		btnAlta.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				actions(e);
				
			}
		});
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setActionCommand("BTN_MODIF");
		btnModificar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				actions (e);
				
			}
		});
		JButton btnBaja = new JButton("BAJA");
		btnBaja.setActionCommand("BTN_BAJA");
		btnBaja.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				actions (e);				
			}
		});
		panel.add(btnAlta);
		panel.add(btnModificar);
		panel.add(btnBaja);
		return panel;
	}
	
	private JPanel panelBottom (){
		JPanel panel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(makeTabla());
		panel.add(scrollPane);      
		return panel;
	}
	private JPanel panelLeft () {
		JPanel panel = new JPanel();
		return panel;
	}
	
	private JPanel panelRight () {
		JPanel panel = new JPanel();
		JButton btnFiltrar = new JButton("FILTRAR");
		btnFiltrar.setActionCommand("BTN_FILTRAR");
		btnFiltrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				actions(e);
				
			}
		});
		JButton btnSalir = new JButton("SALIR");
		panel.add(btnFiltrar);
		panel.add(btnSalir);

		return panel;
	}
	
	private JPanel panelCentral() {
		JPanel jpanel = new JPanel(new GridLayout(2, 1));		
		txtId = new JTextField();
		txtId.addKeyListener(new KeyAdapterNumbersOnly());
		txtNif = new JTextField();
		txtNombre = new JTextField();
		txtApellidos = new JTextField();
		txtPerfil = new JTextField();
		jpanel.add(new JLabel("ID"));
		jpanel.add(new JLabel("NIF"));
		jpanel.add(new JLabel("NOMBRE"));
		jpanel.add(new JLabel("APELLIDOS"));
		jpanel.add(new JLabel("PERFIL"));
		jpanel.add(txtId);
		jpanel.add(txtNif);
		jpanel.add(txtNombre);
		jpanel.add(txtApellidos);
		jpanel.add(txtPerfil);
		return jpanel;
	}
	
	private JTable makeTabla()  
	{  
		String dataValues[][] =	{ };  
		tabla = new JTable(dataValues, columnNames);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		return tabla;  
	}
	
	private void actions (ActionEvent actionEvent){
		if (actionEvent.getActionCommand().toString().equals("BTN_FILTRAR")) {
			if ((txtId.getText().length()> 0) || (txtNif.getText().length() > 0) ||					
					(txtNombre.getText().length()> 0) || (txtApellidos.getText().length() > 0) || 
					(txtPerfil.getText().length()> 0))
				tabla.setModel(getUsuariByFilter());
			else
				tabla.setModel(getAllUsuaris());
		} else if (actionEvent.getActionCommand().toString().equals("BTN_ALTA")) {
			winMantenimiento("x");
		}
	}
	
	private TableModel getUsuariByFilter () {
		List<Usuari> usuaris;
		TableModel model = null;
		try {
			usuaris = gestorConexion.getUsuarisByFilter( txtId.getText(), txtNif.getText(), 
						txtNombre.getText(), txtApellidos.getText(), txtPerfil.getText());
			model = new DefaultTableModel((Object[][]) makeTabla(usuaris), columnNames);
		}  catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GestorConexionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		return model;
		
		
	}
	private TableModel getAllUsuaris () {
		try {
		List<Usuari> usuaris = gestorConexion.getAllUsuaris();
		
		TableModel model = new DefaultTableModel((Object[][]) makeTabla(usuaris), columnNames);
		return model;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Object makeTabla(List<Usuari> usuaris){
		Object rowData [][] = new Object [usuaris.size()][5];
		int z=0;
		for (Usuari usuari : usuaris) {
			rowData[z][0] = String.valueOf(usuari.getId());
			rowData[z][1] = String.valueOf(usuari.getUsuari());
			rowData[z][2] = String.valueOf(usuari.getContrasenya());
			rowData[z][3] = String.valueOf(usuari.getPerfil());
			rowData[z][4] = String.valueOf(usuari.getPerfil());
			z++;
		}
		return rowData;
	}
	
	private void winMantenimiento (String accion) {
		MntoUsuario mnto = new MntoUsuario();		
		mnto.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mnto.setVisible(true);
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
