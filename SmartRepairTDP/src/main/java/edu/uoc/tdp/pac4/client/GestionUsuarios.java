package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;


import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import javax.swing.text.JTextComponent;


import edu.uoc.tdp.pac4.beans.PerfilUsuari;
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
	private JComboBox cmbPerfil;
	
	private JTable tabla;
	private static final Object columnNames[] = {
		TDSLanguageUtils.getMessage("gestionusuarios.label.id"),
		TDSLanguageUtils.getMessage("gestionusuarios.label.nif"),
		TDSLanguageUtils.getMessage("gestionusuarios.label.nombre"),
		TDSLanguageUtils.getMessage("gestionusuarios.label.apellidos"),
		TDSLanguageUtils.getMessage("gestionusuarios.label.perfil")
	};
		

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
		setBounds(100, 100, 821, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnSalir = new JButton(TDSLanguageUtils.getMessage("gestionusuarios.boton.salir"));
		panel.add(btnSalir);
		
		JPanel panel_1 = new JPanel();

		contentPane.add(panel_1, BorderLayout.EAST);
		
		JButton btnFiltrar = new JButton(TDSLanguageUtils.getMessage("gestionusuarios.boton.filtrar"));
		btnFiltrar.setActionCommand("BTN_FILTRAR");
		btnFiltrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				actions (e);
				
			}
		});
		
		panel_1.add(btnFiltrar);
		
		Panel panel_2 = new Panel();
		contentPane.add(panel_2, BorderLayout.NORTH);
		
		JButton btnNuevo = new JButton(TDSLanguageUtils.getMessage("gestionusuarios.boton.alta"));
		btnNuevo.setActionCommand("BTN_NUEVO");
		btnNuevo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				actions (e);				
			}
		});
		
		JButton btnModificar = new JButton(TDSLanguageUtils.getMessage("gestionusuarios.boton.modif"));
		btnModificar.setActionCommand("BTN_MODIFICAR");
		btnModificar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				actions(e);
				
			}
		});
		
		JButton btnBaja = new JButton(TDSLanguageUtils.getMessage("gestionusuarios.boton.baja"));
		btnBaja.setActionCommand("BTN_BAJA");
		btnBaja.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				actions (e);
				
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNuevo)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(btnModificar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBaja)))
					.addContainerGap(186, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnNuevo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnModificar)
						.addComponent(btnBaja))
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.CENTER);
		
		JPanel panel_4 = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
					.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(1))
				.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
		);
		
		JLabel lblId = new JLabel(TDSLanguageUtils.getMessage("gestionusuarios.label.id"));
		JLabel lblNewLabel = new JLabel(TDSLanguageUtils.getMessage("gestionusuarios.label.nif"));		
		JLabel lblNewLabel_1 = new JLabel(TDSLanguageUtils.getMessage("gestionusuarios.label.nombre"));
		JLabel lblNewLabel_2 = new JLabel(TDSLanguageUtils.getMessage("gestionusuarios.label.apellidos"));	
		JLabel lblNewLabel_3 = new JLabel(TDSLanguageUtils.getMessage("gestionusuarios.label.perfil"));
		
		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.addKeyListener(new KeyAdapterNumbersOnly());
		
		txtNif = new JTextField();
		txtNif.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		
		cmbPerfil = new JComboBox();
		cmbPerfil.addItem("");
		for (PerfilUsuari t : PerfilUsuari.values()) {
			cmbPerfil.addItem(t.toString());
		}
		
		
		
		
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblId))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(txtNif, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(txtApellidos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_3)
						.addComponent(cmbPerfil, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(45, Short.MAX_VALUE))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel_4.createSequentialGroup()
							.addComponent(lblId)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_panel_4.createSequentialGroup()
							.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_3)
								.addComponent(lblNewLabel))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtNif, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtNombre, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtApellidos, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbPerfil, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_4.setLayout(gl_panel_4);
		
		
		scrollPane.setViewportView(makeTabla());
		panel_3.setLayout(gl_panel_3);
	}
			
	
	
	private JTable makeTabla()  
	{  
		String dataValues[][] =	{ };  		
		tabla = new JTable(dataValues, columnNames);
		tabla.setModel(new DefaultTableModel(dataValues, columnNames)
			{
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		return tabla;  
	}
	
	private void actions (ActionEvent actionEvent){
		if (actionEvent.getActionCommand().toString().equals("BTN_FILTRAR")) {
			if ((txtId.getText().length()> 0) || (txtNif.getText().length() > 0) ||					
					(txtNombre.getText().length()> 0) || (txtApellidos.getText().length() > 0) || 
					(cmbPerfil.getSelectedItem().toString().length()> 0))
				tabla.setModel(getUsuariByFilter());
			else
				tabla.setModel(getAllUsuaris());
		} else if (actionEvent.getActionCommand().toString().equals("BTN_NUEVO")) {
			winMantenimiento("x");
		}
	}
	
	private TableModel getUsuariByFilter () {
		List<Usuari> usuaris;
		TableModel model = null;
		try {
			usuaris = gestorConexion.getUsuarisByFilter( txtId.getText(), txtNif.getText(), 
						txtNombre.getText(), txtApellidos.getText(), cmbPerfil.getSelectedItem().toString());
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
			rowData[z][1] = String.valueOf(usuari.getNif());
			rowData[z][2] = String.valueOf(usuari.getNom());
			rowData[z][3] = String.valueOf(usuari.getCognoms());
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
