package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.UIManager;

import edu.uoc.tdp.pac4.beans.DetallReparacio;
import edu.uoc.tdp.pac4.beans.Reparacio;
import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.exception.GestorReparacionException;
import edu.uoc.tdp.pac4.service.GestorReparacionInterface;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReparacionGestion extends JFrame {

	private JPanel contentPane;
	private JTextField txtDe;
	private JTextField txtHasta;
	private JTextField txtNombreCliente;
	private JTextField txtApellidoCliente;
	
	private static ReparacionGestion reparacionGestion;
	private GestorReparacionInterface gestorReparacion;
	private static final Object columnNames[] = {
		"Orden Reparaci\u00F3n", "Fecha Entrada", "Contador Min", "Matr\u00EDcula", "Marca", "Modelo", "Observaciones", "Aceptada", "Asignada"
	};
	
	private JTable table;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReparacionGestion frame = new ReparacionGestion(null,null);
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
	
	public ReparacionGestion(GestorReparacionInterface conexion, Usuari usuario) {
		this.gestorReparacion = conexion;
		
		JOptionPane.showMessageDialog(reparacionGestion, usuario.getCognoms(), "ALERTA", JOptionPane.INFORMATION_MESSAGE);
		
		setSize(new Dimension(580, 380));
		setTitle("Gestión de reparaciones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 380);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblReparaciones = new JLabel("Reparaciones");
		lblReparaciones.setBackground(UIManager.getColor("Button.background"));
		lblReparaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblReparaciones.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		JComboBox cmbFiltro = new JComboBox();
		
		JLabel lblDe = new JLabel("De");
		
		txtDe = new JTextField();
		txtDe.setColumns(10);
		
		JLabel lblHasta = new JLabel("Hasta");
		
		txtHasta = new JTextField();
		txtHasta.setColumns(10);
		
		JLabel lblNombreCliente = new JLabel("Nombre cliente");
		
		txtNombreCliente = new JTextField();
		txtNombreCliente.setColumns(10);
		
		JLabel lblApellidoCliente = new JLabel("Apellido cliente");
		
		txtApellidoCliente = new JTextField();
		txtApellidoCliente.setColumns(10);
		
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnDetalle = new JButton("Detalle");
		
		JButton btnAceptar = new JButton("Aceptar");
		
		JButton btnAsignar = new JButton("Asignar");
		
		JButton btnFinalizar = new JButton("Finalizar");
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				System.exit(0);
			}
		});
		
		JScrollPane scrollPanel = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPanel, GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(cmbFiltro, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
									.addGap(48)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblDe)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(txtDe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblHasta)
											.addGap(18)
											.addComponent(txtHasta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblNombreCliente)
											.addGap(18)
											.addComponent(txtNombreCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblApellidoCliente)
											.addGap(18)
											.addComponent(txtApellidoCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnActualizar)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnDetalle)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnAceptar)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAsignar)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnFinalizar)
									.addPreferredGap(ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
									.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGap(95))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblReparaciones, GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
							.addGap(106))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(4)
					.addComponent(lblReparaciones, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(cmbFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblDe)
							.addComponent(txtDe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtNombreCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNombreCliente)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblHasta)
							.addComponent(txtHasta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtApellidoCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblApellidoCliente)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPanel, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnActualizar)
						.addComponent(btnSalir)
						.addComponent(btnDetalle)
						.addComponent(btnAceptar)
						.addComponent(btnAsignar)
						.addComponent(btnFinalizar))
					.addGap(29))
		);
		
		table = new JTable();
		
		scrollPanel.setViewportView(table);
		
		try {
			table.setModel(getTableModel());
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (GestorReparacionException e) {
			e.printStackTrace();
		}
		
	
		
		contentPane.setLayout(gl_contentPane);
	}
	
	private TableModel getTableModel () throws RemoteException, GestorReparacionException  {
		List<DetallReparacio> list = gestorReparacion.getDetalleReparaciones();
		Object rowData [][] = new Object [list.size()][9];
		int z=0;
		for (DetallReparacio bean : list) {
			rowData[z][0] = String.valueOf(bean.getOrdreReparacio());
			rowData[z][1] = String.valueOf(bean.getDataEntrada());
			rowData[z][2] = String.valueOf(bean.getComptador());
			rowData[z][3] = String.valueOf(bean.getMatricula());
			rowData[z][4] = String.valueOf(bean.getMarca());
			rowData[z][5] = String.valueOf(bean.getModel());
			rowData[z][6] = String.valueOf(bean.getObservacions());
			rowData[z][7] = String.valueOf(bean.getAcceptada());
			rowData[z][8] = String.valueOf(bean.getAssignada());
			z++;
		}
		TableModel model = new DefaultTableModel(rowData, columnNames);
		return model;
	}
	

	
}
