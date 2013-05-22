package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;


import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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


import edu.uoc.tdp.pac4.beans.Asseguradora;
import edu.uoc.tdp.pac4.beans.Taller;
import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.common.ItemCombo;
import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import edu.uoc.tdp.pac4.exception.GestorConexionException;
import edu.uoc.tdp.pac4.service.GestorConexionInterface;
import javax.swing.ListSelectionModel;
import javax.swing.JSeparator;


public class GestionTalleres extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected GestorConexionInterface gestorConexion = null;
	
	private JPanel contentPane;
	
	private JTextField txtId;
	private JTextField txtCif;
	private JTextField txtAdreca;
	private JTextField txtCapacitat;
	private JComboBox cmbCapTaller;
	
	private JButton btnModificar;
	private JButton btnBaja;
	
	private JTable tabla;
	
	private ArrayList<ItemCombo> cbCapTaller;
	
	
	private static final Object columnNames[] = {
		TDSLanguageUtils.getMessage("gestiontalleres.label.id"),
		TDSLanguageUtils.getMessage("gestiontalleres.label.cif"),
		TDSLanguageUtils.getMessage("gestiontalleres.label.adreca"),
		TDSLanguageUtils.getMessage("gestiontalleres.label.capacitat"),
		TDSLanguageUtils.getMessage("gestiontalleres.label.captaller")
	};
		

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					GestorConexionInterface gestorConexion = (GestorConexionInterface) Naming.lookup("rmi://localhost/GestorConexion");
					TDSLanguageUtils.setDefaultLanguage("i18n/messages");
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
		tabla.setModel(getAllTallers());
		initComboCapTaller();
		enableButtons(false);
	}
	
	private void initialize (){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnSalir = new JButton(TDSLanguageUtils.getMessage("gestiontalleres.boton.salir"));
		btnSalir.setActionCommand("BTN_SALIR");
		btnSalir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				actions (e);
				
			}
		});
		panel.add(btnSalir);
		
		JPanel panel_1 = new JPanel();

		contentPane.add(panel_1, BorderLayout.EAST);
		
		JButton btnFiltrar = new JButton(TDSLanguageUtils.getMessage("gestiontalleres.boton.filtrar"));
		btnFiltrar.setActionCommand("BTN_FILTRAR");
		btnFiltrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				actions (e);
				
			}
		});
		
		panel_1.add(btnFiltrar);
		
		Panel panel_2 = new Panel();
		contentPane.add(panel_2, BorderLayout.NORTH);
		
		JButton btnNuevo = new JButton(TDSLanguageUtils.getMessage("gestiontalleres.boton.alta"));
		btnNuevo.setActionCommand("BTN_NUEVO");
		btnNuevo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				actions (e);				
			}
		});
		
		btnModificar = new JButton(TDSLanguageUtils.getMessage("gestiontalleres.boton.modif"));
		btnModificar.setActionCommand("BTN_MODIFICAR");
		btnModificar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				actions(e);
				
			}
		});
		
		btnBaja = new JButton(TDSLanguageUtils.getMessage("gestiontalleres.boton.baja"));
		btnBaja.setActionCommand("BTN_BAJA");
		btnBaja.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				actions (e);
				
			}
		});
		
		JSeparator separator = new JSeparator();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNuevo)
								.addComponent(btnModificar))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBaja)
							.addContainerGap(522, Short.MAX_VALUE))
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 926, Short.MAX_VALUE)))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(btnNuevo)
					.addGap(12)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(btnBaja)
						.addComponent(btnModificar))
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.CENTER);
		
		JPanel panel_4 = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_3.createSequentialGroup()
					.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(1)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
					.addGap(6)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
					.addGap(10))
		);
		
		JLabel lblId = new JLabel(TDSLanguageUtils.getMessage("gestiontalleres.label.id"));
		JLabel lblNewLabel = new JLabel(TDSLanguageUtils.getMessage("gestiontalleres.label.cif"));		
		JLabel lblNewLabel_1 = new JLabel(TDSLanguageUtils.getMessage("gestiontalleres.label.adreca"));
		JLabel lblNewLabel_2 = new JLabel(TDSLanguageUtils.getMessage("gestiontalleres.label.capacitat"));	
		JLabel lblNewLabel_3 = new JLabel(TDSLanguageUtils.getMessage("gestiontalleres.label.captaller"));
		
		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.addKeyListener(new KeyAdapterNumbersOnly());
		
		txtCif = new JTextField();
		txtCif.setColumns(10);
		
		txtAdreca = new JTextField();
		txtAdreca.setColumns(10);
		
		txtCapacitat = new JTextField();
		txtCapacitat.setColumns(10);
		txtCapacitat.addKeyListener(new KeyAdapterNumbersOnly());
		
		cmbCapTaller = new JComboBox();
		
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(lblId)
						.addComponent(txtId))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(txtCif)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addComponent(txtAdreca))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2)
						.addComponent(txtCapacitat))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_3)
						.addComponent(cmbCapTaller, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(68))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(lblId)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_3))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCif, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtAdreca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCapacitat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbCapTaller, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
			/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				boolean[] columnEditables = new boolean[] {
						false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {		
			}
			
			public void mousePressed(MouseEvent e) {
			}
			
			public void mouseExited(MouseEvent e) {
			}
			
			public void mouseEntered(MouseEvent e) {
			}
			
			public void mouseClicked(MouseEvent e) {
				if (e.getModifiers() == InputEvent.BUTTON1_MASK){
					enableButtons(true);
				}
			}
		});
		
		
		return tabla;  
	}

	
	private void actions (ActionEvent actionEvent){
		if (actionEvent.getActionCommand().toString().equals("BTN_FILTRAR")) {
			if ((txtId.getText().length()> 0) || (txtCif.getText().length() > 0) ||					
					(txtAdreca.getText().length()> 0) || (txtCapacitat.getText().length() > 0) ||
					(cmbCapTaller.getSelectedIndex())> -1)					
				tabla.setModel(getTallersByFilter());
			else
				tabla.setModel(getAllTallers());
			enableButtons(false);
		} else if (actionEvent.getActionCommand().toString().equals("BTN_NUEVO")) {
			winMantenimiento("ALTA","");
		} else if (actionEvent.getActionCommand().toString().equals("BTN_MODIFICAR")){
			winMantenimiento("MODIFICAR",tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
		} else if (actionEvent.getActionCommand().toString().equals("BTN_BAJA")){
			winMantenimiento("BAJA",tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
			
		} else if (actionEvent.getActionCommand().toString().equals("BTN_SALIR")){
			dispose();
		}
	}
	
	private TableModel getTallersByFilter () {
		List<Taller> talleres = null;
		TableModel model = null;
		try {
			talleres = gestorConexion.getTallersByFilter( txtId.getText(), txtCif.getText(), 
						txtAdreca.getText(), txtCapacitat.getText(), 
						cbCapTaller.get(cmbCapTaller.getSelectedIndex()).getAux());
			model = new DefaultTableModel((Object[][]) makeTabla(talleres), columnNames);
		}  catch (RemoteException e) {
			showError(e.getMessage(),"GESCON.showmessage.error");		
		} catch (GestorConexionException e) {
			showError(e.getMessage(),"GESCON.showmessage.error");			
		
		}
		return model;
	}
		
	private TableModel getAllTallers () {
		
		List<Taller> tallers = null;
		try {
			tallers = gestorConexion.getAllTallers();
		} catch (RemoteException e) {
			showError(e.getMessage(),"GESCON.showmessage.error");		
		} catch (GestorConexionException e) {
			showError(e.getMessage(),"GESCON.showmessage.error");		
		}
		
		TableModel model = new DefaultTableModel((Object[][]) makeTabla(tallers), columnNames);
		return model;
		
	}
	
	private Object makeTabla(List<Taller> talleres){
		Object rowData [][] = new Object [talleres.size()][5];
		Usuari usuari = null;
		int z=0;
		for (Taller taller : talleres) {
			if (taller.getCapTaller()>0){
				try {			
					usuari = gestorConexion.getUsuariById(taller.getCapTaller());
					rowData[z][4] = String.valueOf(usuari.getNomCognoms());
				} catch (RemoteException e) {
					showError(e.getMessage(), "GESCON.showmessage.error");
					rowData[z][4] = String.valueOf(GestorConexionException.ERR_USER_NOTFOUND);
				} catch (GestorConexionException e) {
					showError(e.getMessage(), "GESCON.showmessage.error");
				}
			}
			rowData[z][0] = String.valueOf(taller.getId());
			rowData[z][1] = String.valueOf(taller.getCif());
			rowData[z][2] = String.valueOf(taller.getAdreca());
			rowData[z][3] = String.valueOf(taller.getCapacitat());
			z++;						
		}
		return rowData;
	}
	
	private void winMantenimiento (String accion, String id) {
		MntoTaller mnto;
		if (accion.equals("ALTA"))
			mnto = new MntoTaller(gestorConexion);
		else
			mnto = new MntoTaller(gestorConexion, accion, Integer.valueOf(id));
			
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		mnto.setLocation(dim.width/2-mnto.getSize().width/2, dim.height/2-mnto.getSize().height/2);
		mnto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mnto.setVisible(true);		
	}
	
	private void enableButtons (boolean status){
		btnModificar.setEnabled(status);
		btnBaja.setEnabled(status);
		
	}
	
	private void initComboCapTaller () {
		DefaultComboBoxModel defaultCombo = new DefaultComboBoxModel();
		List<Usuari> capTaller = null;
		cbCapTaller = new ArrayList<ItemCombo>();
		try {
			capTaller = gestorConexion.getUsuarisCapTaller();
			cbCapTaller.add (new ItemCombo(0, "", ""));
			for (int i = 0; i < capTaller.size(); i++) {
				Usuari usuari = capTaller.get(i);
				cbCapTaller.add(new ItemCombo(i+1, usuari.getNomCognoms(), 
						String.valueOf(usuari.getId())));							
			}
			for (int i = 0; i < cbCapTaller.size(); i++)
				defaultCombo.insertElementAt(cbCapTaller.get(i).getValue(), i);
			cmbCapTaller.setModel(defaultCombo);
			cmbCapTaller.setSelectedIndex(0);
		} catch (RemoteException e) {
			showError(e.getMessage(),"GESCON.showmessage.error");
		} catch (GestorConexionException e) {
			showError(e.getMessage(),"GESCON.showmessage.error");			
		}
		
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
