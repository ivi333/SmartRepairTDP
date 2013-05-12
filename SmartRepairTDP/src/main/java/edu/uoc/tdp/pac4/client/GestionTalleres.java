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


import edu.uoc.tdp.pac4.beans.Taller;
import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import edu.uoc.tdp.pac4.exception.GestorConexionException;
import edu.uoc.tdp.pac4.service.GestorConexionInterface;
import javax.swing.ListSelectionModel;


public class GestionTalleres extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected GestorConexionInterface gestorConexion = null;
	
	private JPanel contentPane;
	
	private JTextField txtId;
	private JTextField txtCif;
	private JTextField txtDireccion;
	private JTextField txtCapacidad;
	private JTextField txtJefeTaller;
	
	private JTable tabla;
	private static final Object columnNames[] = { "ID", "CIF","DIRECCION","CAPACIDAD","JEFE TALLER"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TDSLanguageUtils.setDefaultLanguage("i18n/messages");
					GestorConexionInterface gestorConexion = (GestorConexionInterface) Naming.lookup("rmi://localhost/GestorConexion");
					GestionTalleres frame = new GestionTalleres(gestorConexion);
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
	public GestionTalleres() {
		initialize ();
		
	}
	
	public GestionTalleres (GestorConexionInterface gestorConexion) {
		this.gestorConexion = gestorConexion;
		initialize ();
		
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
		txtCif = new JTextField();
		txtDireccion = new JTextField();
		txtCapacidad = new JTextField();
		txtCapacidad.addKeyListener(new KeyAdapterNumbersOnly());
		txtJefeTaller = new JTextField();
		jpanel.add(new JLabel("ID"));
		jpanel.add(new JLabel("CIF"));
		jpanel.add(new JLabel("DIRECCION"));
		jpanel.add(new JLabel("CAPACIDAD"));
		jpanel.add(new JLabel("JEFE TALLEr"));
		jpanel.add(txtId);
		jpanel.add(txtCif);
		jpanel.add(txtDireccion);
		jpanel.add(txtCapacidad);
		jpanel.add(txtJefeTaller);
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
		
	}
	
	private TableModel getTallerByFilter () {
		List<Taller> talleres;
		TableModel model = null;
		
		return model;
		
		
	}
	private TableModel getAllTalleres () {
		TableModel model = null;
		return model;
	}
	
	private Object makeTabla(List<Taller> talleres){
		Object rowData [][] = new Object [talleres.size()][5];
		
		return rowData;
	}
	
	private void winMantenimiento (String accion) {

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
