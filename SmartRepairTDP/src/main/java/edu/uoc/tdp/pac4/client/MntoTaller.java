package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.uoc.tdp.pac4.service.GestorConexionInterface;

import javax.swing.JButton;
import java.awt.FlowLayout;
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

public class MntoTaller extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtCif;
	private JTextField txtAdreca;
	private JTextField txtCapacitat;
	private JTextField txtDAlta;
	private JTextField txtDModificacio;
	private JTextField txtDBaixa;
	private JTextField txtTelefon;
	private JTextField txtWeb;

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
		initialize ();
	}
	
	public MntoTaller (GestorConexionInterface gestorConexion, String accion, int IdTaller){
		initialize ();
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
		panel_5.add(btnSalir);
		
		JButton btnAceptar = new JButton("New button");
		panel_4.add(btnAceptar);
		
		JButton btnCancelar = new JButton("New button");
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
				RowSpec.decode("default:grow"),}));
		
		JLabel lblId = new JLabel("New label");
		panel_1.add(lblId, "2, 4, right, default");
		
		txtId = new JTextField();
		txtId.setText("txtId");
		panel_1.add(txtId, "4, 4");
		txtId.setColumns(10);
		
		JLabel lblNif = new JLabel("lblNif");
		panel_1.add(lblNif, "6, 4, right, default");
		
		txtCif = new JTextField();
		txtCif.setText("txtNif");
		panel_1.add(txtCif, "8, 4");
		txtCif.setColumns(10);
		
		JLabel lblAdreca = new JLabel("New label");
		panel_1.add(lblAdreca, "6, 6, right, default");
		
		txtAdreca = new JTextField();
		txtAdreca.setText("txtNom");
		panel_1.add(txtAdreca, "8, 6");
		txtAdreca.setColumns(10);
		
		JLabel lblCapacitat = new JLabel("lblCognoms");
		panel_1.add(lblCapacitat, "6, 8, right, default");
		
		txtCapacitat = new JTextField();
		txtCapacitat.setText("txtCogoms");
		panel_1.add(txtCapacitat, "8, 8");
		txtCapacitat.setColumns(10);
		
		JLabel lblCapTaller = new JLabel("CapTaller");
		panel_1.add(lblCapTaller, "6, 10, right, default");
		
		JComboBox cmbCapTaller = new JComboBox();
		panel_1.add(cmbCapTaller, "8, 10, fill, default");
		
		JLabel lblTelefon = new JLabel("New label");
		panel_1.add(lblTelefon, "6, 12, right, default");
		
		txtTelefon = new JTextField();
		panel_1.add(txtTelefon, "8, 12, fill, default");
		txtTelefon.setColumns(10);
		
		JLabel lblWeb = new JLabel("lblRepeatPassword");
		panel_1.add(lblWeb, "6, 14, right, default");
		
		txtWeb = new JTextField();
		panel_1.add(txtWeb, "8, 14, fill, default");
		txtWeb.setColumns(10);
		
		JCheckBox chkActiu = new JCheckBox("chkActivo");
		panel_1.add(chkActiu, "8, 16");
		
		JLabel lblDAlta = new JLabel("lblAlta");
		panel_1.add(lblDAlta, "2, 20, right, default");
		
		txtDAlta = new JTextField();
		txtDAlta.setText("txtAlta");
		panel_1.add(txtDAlta, "4, 20, fill, default");
		txtDAlta.setColumns(10);
		
		JLabel lblDModificacio = new JLabel("lblModificacion");
		panel_1.add(lblDModificacio, "2, 22, right, default");
		
		txtDModificacio = new JTextField();
		txtDModificacio.setText("txtModificacion");
		panel_1.add(txtDModificacio, "4, 22, fill, default");
		txtDModificacio.setColumns(10);
		
		JLabel lblDBaixa = new JLabel("lblBaja");
		panel_1.add(lblDBaixa, "2, 24, right, default");
		
		txtDBaixa = new JTextField();
		txtDBaixa.setText("txtBaja");
		panel_1.add(txtDBaixa, "4, 24, fill, default");
		txtDBaixa.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("New label");
		panel_2.add(lblTitle);
	}
}
