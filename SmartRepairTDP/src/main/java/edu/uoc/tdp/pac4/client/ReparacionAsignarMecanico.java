package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.uoc.tdp.pac4.beans.DetallReparacio;
import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.exception.GestorReparacionException;
import edu.uoc.tdp.pac4.service.GestorReparacionInterface;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

public class ReparacionAsignarMecanico extends JFrame {

	private JPanel contentPane;
	private JTextField txtOrdenReparacion;
	private JTextField txtMatricula;
	private JTextField txtMarca;
	private JTextField txtModelo;
	private JTable tblMecanicoAsignado;
	private JTable tblMecanicos;
	private JButton btnEliminar;
	private JLabel lblMecanico;
	private JTextField txtMecanico;
	private JButton btnBuscar;
	private JButton btnAsignar;
	private JButton btnSalir;
	
	private static ReparacionGestion reparacionGestion;
	private GestorReparacionInterface gestorReparacion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReparacionAsignarMecanico frame = new ReparacionAsignarMecanico(null,null,-1);
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
	public ReparacionAsignarMecanico(GestorReparacionInterface conexion, final Usuari usuario, int ordenReparacion) {
		this.gestorReparacion = conexion;
		
		setTitle("Asignación mecánico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 665, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblAsignacionMecanico = new JLabel("Asignación Mecánico");
		lblAsignacionMecanico.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblAsignacionMecanico.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblOrdenReparacion = new JLabel("Orden reparación");
		
		txtOrdenReparacion = new JTextField();
		txtOrdenReparacion.setColumns(10);
		
		JLabel lblMatricula = new JLabel("Matrícula");
		
		txtMatricula = new JTextField();
		txtMatricula.setColumns(10);
		
		JLabel lblMarca = new JLabel("Marca");
		
		txtMarca = new JTextField();
		txtMarca.setColumns(10);
		
		JLabel lblModelo = new JLabel("Modelo");
		
		txtModelo = new JTextField();
		txtModelo.setColumns(10);
		
		JLabel lblMecanicoAsignado = new JLabel("Mecánico asignado a la reparación");
		lblMecanicoAsignado.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblMecanicos = new JLabel("Mecánicos");
		lblMecanicos.setHorizontalAlignment(SwingConstants.CENTER);
		
		tblMecanicoAsignado = new JTable();
		tblMecanicoAsignado.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"New column", "New column"
			}
		));
		
		tblMecanicos = new JTable();
		tblMecanicos.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"New column", "New column"
			}
		));
		
		btnEliminar = new JButton("Eliminar");
		
		lblMecanico = new JLabel("Mecánico");
		
		txtMecanico = new JTextField();
		txtMecanico.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		
		btnAsignar = new JButton("Asignar");
		
		btnSalir = new JButton("Salir");
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblAsignacionMecanico, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblOrdenReparacion)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(txtOrdenReparacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(lblMatricula)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(txtMatricula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(lblMarca)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(txtMarca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(lblModelo)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(txtModelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblMecanicoAsignado, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
									.addGap(36)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(tblMecanicos, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
										.addComponent(lblMecanicos, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblMecanico)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(txtMecanico, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnBuscar)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnAsignar))
										.addComponent(btnSalir, Alignment.TRAILING))))
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnEliminar)
								.addComponent(tblMecanicoAsignado, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAsignacionMecanico, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOrdenReparacion)
						.addComponent(txtOrdenReparacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMatricula)
						.addComponent(txtMatricula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMarca)
						.addComponent(txtMarca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblModelo)
						.addComponent(txtModelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMecanicoAsignado)
						.addComponent(lblMecanicos))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(tblMecanicoAsignado, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
						.addComponent(tblMecanicos, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEliminar)
						.addComponent(txtMecanico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMecanico)
						.addComponent(btnBuscar)
						.addComponent(btnAsignar))
					.addGap(18)
					.addComponent(btnSalir)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		initCampos(ordenReparacion);
		
		contentPane.setLayout(gl_contentPane);
	}

	private void initCampos(int ordenReparacion) {
		try {
			DetallReparacio datosReparacion = gestorReparacion.getDetalleReparacion(ordenReparacion);
			this.txtOrdenReparacion.setText(String.valueOf(datosReparacion.getOrdreReparacio()));
			this.txtMatricula.setText(datosReparacion.getMatricula());
			this.txtMarca.setText(datosReparacion.getMarca());
			this.txtModelo.setText(datosReparacion.getModel());

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (GestorReparacionException e) {
			e.printStackTrace();
		}
	}
	
}
