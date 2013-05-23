package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import edu.uoc.tdp.pac4.beans.DetallReparacio;
import edu.uoc.tdp.pac4.beans.Mecanic;
import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.exception.GestorReparacionException;
import edu.uoc.tdp.pac4.service.GestorReparacionInterface;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;

public class ReparacionAsignarMecanico extends JFrame {

	private JPanel contentPane;
	private JTextField txtOrdenReparacion;
	private JTextField txtMatricula;
	private JTextField txtMarca;
	private JTextField txtModelo;
	private JButton btnEliminar;
	private JLabel lblMecanico;
	private JTextField txtMecanico;
	private JButton btnBuscar;
	private JButton btnAsignar;
	private JButton btnSalir;
	
	private static ReparacionAsignarMecanico reparacionAsignarMecanico;
	private GestorReparacionInterface gestorReparacion;
	private JTable table1;
	private JTable table2;
	private JScrollPane scrollPanel1;
	private JScrollPane scrollPanel2;
	
	private static final Object columnNames1[] = {
		"Id", "Nombre", "Apellidos"
	};
	private static final Object columnNames2[] = {
		"Id", "Nombre", "Apellidos", "Reparaciones asignadas"
	};
	
	private List<Usuari> mecanicosSeleccionados = new ArrayList<Usuari>() ;
	

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
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminarMecanico();
			}
		});
		
		lblMecanico = new JLabel("Mecánico");
		
		txtMecanico = new JTextField();
		txtMecanico.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					table2.setModel(getTableModel(-1, txtMecanico.getText()));
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (GestorReparacionException e) {
					e.printStackTrace();
				}
			}

		});
		
		btnAsignar = new JButton("Asignar");
		btnAsignar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				anadirMecanico();
			}
		});
		
		btnSalir = new JButton("Salir");
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		
		scrollPanel1 = new JScrollPane();
		
		scrollPanel2 = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
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
								.addComponent(lblMecanicos, GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblMecanico)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtMecanico, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnBuscar)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAsignar))
								.addComponent(btnSalir, Alignment.TRAILING)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(220)
							.addComponent(btnEliminar))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPanel1, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPanel2, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPanel1, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPanel2, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
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
		
		table2 = new JTable();
		scrollPanel2.setViewportView(table2);
		
		table1 = new JTable();
		scrollPanel1.setViewportView(table1);
		
		initCampos(ordenReparacion);
		
		contentPane.setLayout(gl_contentPane);
	}

	private TableModel getTableModel (int idMecanico, String nombre) throws RemoteException, GestorReparacionException  {
		TableModel model = null;
		if (idMecanico == -1){
			List<Usuari> list = gestorReparacion.getUsuarios("Mecanico", nombre);
			Object rowData [][] = new Object [list.size()][4];
			int z=0;
			for (Usuari bean : list) {
				rowData[z][0] = String.valueOf(bean.getId());
				rowData[z][1] = String.valueOf(bean.getNom());
				rowData[z][2] = String.valueOf(bean.getCognoms());
				rowData[z][3] = String.valueOf(bean.getReparacionsAssignades());
				z++;
			}
			model = new DefaultTableModel(rowData, columnNames2);
		} else {
			Usuari usuario = gestorReparacion.getUsuario(idMecanico);
			/*Mecanic mecanico = gestorReparacion.getMecanico(idMecanico);*/
			Object rowData [][] = new Object [1][3];
			rowData[0][0] = String.valueOf(usuario.getId());
			rowData[0][1] = String.valueOf(usuario.getNom());
			rowData[0][2] = String.valueOf(usuario.getCognoms());
			model = new DefaultTableModel(rowData, columnNames1);
		}
		return model;
	}
	
	private int getIdMecanicoSeleccionado (JTable tabla) throws RemoteException, GestorReparacionException {
		if (tabla.getSelectedRow() >= 0) {
			return Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
		} else {
			return -1;
		}
	}
	
	private void anadirMecanico() {
		int idMecanicoAnadir;
		boolean existeMecanicoAsignado = false;
		try {
			if (table2.getSelectedRowCount() == 1) {
				idMecanicoAnadir = getIdMecanicoSeleccionado(table2);
				if (mecanicosSeleccionados != null) {
					if (mecanicosSeleccionados.size() == 1) {
						JOptionPane.showMessageDialog(reparacionAsignarMecanico, "Debe eliminar el mecánico asignado a la reparación antes de añadir otro.", "Error", JOptionPane.ERROR_MESSAGE);
						existeMecanicoAsignado = true;
					}
				}
				if (!existeMecanicoAsignado) {
					Usuari mecanico = gestorReparacion.getUsuario(idMecanicoAnadir);
					mecanicosSeleccionados.add(mecanico);
					
					if (mecanico.getReparacionsAssignades() < 2) {
						int ordenReparacion1 = 0;
						int ordenReparacion2 = 0;
						if (mecanico.getReparacionsAssignades() == 0) {
							ordenReparacion1 = Integer.valueOf(txtOrdenReparacion.getText());
						} else {
							Mecanic objMecanico = gestorReparacion.getMecanico(idMecanicoAnadir);
							ordenReparacion1 = objMecanico.getIdrep1();
							ordenReparacion2 = Integer.valueOf(txtOrdenReparacion.getText());
						}
						actualizarAsignacion(false, idMecanicoAnadir, Integer.valueOf(txtOrdenReparacion.getText()), ordenReparacion1, ordenReparacion2);
											
						Object rowData [][] = new Object [mecanicosSeleccionados.size()][3];
						int z=0;
						for (Usuari bean : mecanicosSeleccionados) {
							rowData[z][0] = String.valueOf(bean.getId());
							rowData[z][1] = String.valueOf(bean.getNom());
							rowData[z][2] = String.valueOf(bean.getCognoms());
							z++;
						}
						TableModel model = new DefaultTableModel(rowData, columnNames1);
						table1.setModel(model);
					} else {
						JOptionPane.showMessageDialog(reparacionAsignarMecanico, "Este mecánico ya tiene dos reparaciones asignadas. Elija otro mecánico.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			} else {
				JOptionPane.showMessageDialog(reparacionAsignarMecanico, "Debe seleccionar una fila para poder añadirla.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (GestorReparacionException e1) {
			e1.printStackTrace();
		}
	}
	
	private void eliminarMecanico() {
		try {
			if (table1.getSelectedRowCount() == 1) {
				int idMecanico;
				idMecanico = getIdMecanicoSeleccionado(table1);
				if (mecanicosSeleccionados != null) {
					for (int i=0; i<mecanicosSeleccionados.size(); i++) {
						if (mecanicosSeleccionados.get(i).getId() == idMecanico) {
							mecanicosSeleccionados.remove(i);
						}
					}
				}
				
				int ordenReparacion = Integer.valueOf(txtOrdenReparacion.getText());
				int ordenReparacion1 = 0;
				int ordenReparacion2 = 0;
				Usuari mecanico = gestorReparacion.getUsuario(idMecanico);
				if (mecanico.getReparacionsAssignades() == 2) {
					Mecanic objMecanico = gestorReparacion.getMecanico(idMecanico);
					if (objMecanico.getIdrep1() == ordenReparacion) {
						ordenReparacion1 = objMecanico.getIdrep2();
					} else {
						ordenReparacion1 = objMecanico.getIdrep1();
					}
				}
				actualizarAsignacion(true, idMecanico, ordenReparacion, ordenReparacion1, ordenReparacion2);
			
				Object rowData [][] = new Object [mecanicosSeleccionados.size()][3];
				int z=0;
				for (Usuari bean : mecanicosSeleccionados) {
					rowData[z][0] = String.valueOf(bean.getId());
					rowData[z][1] = String.valueOf(bean.getNom());
					rowData[z][2] = String.valueOf(bean.getCognoms());
					z++;
				}
				TableModel model = new DefaultTableModel(rowData, columnNames1);
				table1.setModel(model);
			} else {
				JOptionPane.showMessageDialog(reparacionAsignarMecanico, "Debe seleccionar una fila para poder eliminarla.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (GestorReparacionException e) {
			e.printStackTrace();
		}	
	}
	
	private void actualizarAsignacion(boolean eliminar, int idMecanico, int ordenReparacion, int ordenReparacion1, int ordenReparacion2) {
		try {
			int ordenesReparacion = 0;
			if (ordenReparacion1 > 0) {
				ordenesReparacion = 1;
			}
			if (ordenReparacion2 > 0) {
				ordenesReparacion = 2;
			}
			gestorReparacion.asignarReparacionesMecanico(idMecanico, ordenReparacion1, ordenReparacion2);
			gestorReparacion.asignarUsuarioNumeroReparacion(idMecanico, ordenesReparacion);
			if (eliminar) {
				gestorReparacion.asignarMecanicoReparacion(0, ordenReparacion);
			} else {
				gestorReparacion.asignarMecanicoReparacion(idMecanico, ordenReparacion);	
			}
			
			scrollPanel2.setViewportView(table2);
			table2.setModel(getTableModel(-1, ""));

			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (GestorReparacionException e) {
			e.printStackTrace();
		}
	}
	
	private void initCampos(int ordenReparacion) {
		try {
			DetallReparacio datosReparacion = gestorReparacion.getDetalleReparacion(ordenReparacion);
			this.txtOrdenReparacion.setText(String.valueOf(datosReparacion.getOrdreReparacio()));
			this.txtMatricula.setText(datosReparacion.getMatricula());
			this.txtMarca.setText(datosReparacion.getMarca());
			this.txtModelo.setText(datosReparacion.getModel());
			
			scrollPanel1.setViewportView(table1);
			inicializarLista(datosReparacion.getIdMecanic());
			
			scrollPanel2.setViewportView(table2);
			table2.setModel(getTableModel(-1, ""));

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (GestorReparacionException e) {
			e.printStackTrace();
		}
	}
	
	private void inicializarLista(int idMecanico) {
		try {
			mecanicosSeleccionados.add(gestorReparacion.getUsuario(idMecanico));
			boolean tieneMec = false;
			Object rowData [][] = new Object [mecanicosSeleccionados.size()][3];
			int z=0;
			for (Usuari bean : mecanicosSeleccionados) {
				if (bean != null) {
					tieneMec = true;
					rowData[z][0] = String.valueOf(bean.getId());
					rowData[z][1] = String.valueOf(bean.getNom());
					rowData[z][2] = String.valueOf(bean.getCognoms());
				}
				z++;	
			}
			TableModel model;
			if (tieneMec) {
				model = new DefaultTableModel(rowData, columnNames1);
			}
			else {
				model = new DefaultTableModel(new Object [0][3], columnNames1);
				mecanicosSeleccionados.remove(0);
			}
			
			table1.setModel(model);
			
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (GestorReparacionException e1) {
			e1.printStackTrace();
		}
	}
}
