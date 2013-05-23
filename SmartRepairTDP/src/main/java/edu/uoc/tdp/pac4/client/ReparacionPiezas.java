package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;

import edu.uoc.tdp.pac4.beans.DetallPeca;
import edu.uoc.tdp.pac4.beans.DetallReparacio;
import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.exception.GestorReparacionException;
import edu.uoc.tdp.pac4.service.GestorReparacionInterface;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;

public class ReparacionPiezas extends JFrame {

	private JPanel contentPane;
	private JTextField txtOrdenReparacion;
	private JTextField txtMatricula;
	private JTextField txtMarca;
	private JTextField txtModelo;
	private JTextField txtBuscarPieza;
	
	private static ReparacionPiezas reparacionPiezas;
	private GestorReparacionInterface gestorReparacion;
	private JTextArea txtObservaciones;
	private JTable table1;
	private JTable table2;
	private JTextField txtAnadir = new JTextField();
	
	private static final Object columnNames1[] = {
		"C\u00F3digo", "Descripci\u00F3n", "Unidades", "Stock", "Precio"
	};
	private static final Object columnNames2[] = {
		"C\u00F3digo", "Descripci\u00F3n", "Stock", "Precio"
	};
	
	private JScrollPane scrollPanel1;
	private JScrollPane scrollPanel2;
	private JButton btnBuscarPieza;
	private JButton btnAnadir;
	
	private Usuari usuario;
	private int ordenReparacion;
	
	private List<DetallPeca> piezasSeleccionadas = new ArrayList<DetallPeca>() ;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReparacionPiezas frame = new ReparacionPiezas(null,null,-1);
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
	public ReparacionPiezas(GestorReparacionInterface conexion, final Usuari usuario, final int ordenReparacion) {
		this.gestorReparacion = conexion;
		this.usuario = usuario;
		this.ordenReparacion = ordenReparacion;
		
		setTitle("Piezas reparación");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 778, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblPiezasReparacion = new JLabel("Piezas Reparación");
		lblPiezasReparacion.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblPiezasReparacion.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblOrdenReparacion = new JLabel("Orden reparación");
		
		txtOrdenReparacion = new JTextField();
		txtOrdenReparacion.setColumns(10);
		
		JLabel lblMatricula = new JLabel("Matricula");
		
		txtMatricula = new JTextField();
		txtMatricula.setColumns(10);
		
		JLabel lblMarca = new JLabel("Marca");
		
		txtMarca = new JTextField();
		txtMarca.setColumns(10);
		
		JLabel lblModelo = new JLabel("Modelo");
		
		txtModelo = new JTextField();
		txtModelo.setColumns(10);
		
		JLabel lblObservaciones = new JLabel("Observaciones para la reparación");
		
		txtObservaciones = new JTextArea();
		
		btnAnadir = new JButton("Añadir");
		btnAnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				anadirPieza();
			}
		});
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				eliminarPieza();
			}
		});
		
		btnBuscarPieza = new JButton("Buscar pieza");
		btnBuscarPieza.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					table2.setModel(getTableModel(-1, txtBuscarPieza.getText()));
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (GestorReparacionException e) {
					e.printStackTrace();
				}
			}
		});
		
		txtBuscarPieza = new JTextField();
		txtBuscarPieza.setColumns(10);
		
		JButton btnRealizarPedido = new JButton("Realizar pedido");
		btnRealizarPedido.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
						realizarPedido();
			}
		});
		
		JButton btnAsignarMecanico = new JButton("Asignar mecánico");
		btnAsignarMecanico.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReparacionAsignarMecanico dialog = new ReparacionAsignarMecanico(gestorReparacion, usuario, ordenReparacion);
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				dialog.setSize(1000, 500);
				dialog.setLocation(dim.width/2-dialog.getSize().width/2, dim.height/2-dialog.getSize().height/2);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		
		scrollPanel1 = new JScrollPane();
		
		scrollPanel2 = new JScrollPane();
		
		txtAnadir.setColumns(10);
		
		JLabel lblUnidades = new JLabel("Unidades");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnRealizarPedido)
							.addPreferredGap(ComponentPlacement.RELATED, 444, Short.MAX_VALUE)
							.addComponent(btnAsignarMecanico)
							.addGap(22)
							.addComponent(btnSalir))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(136)
									.addComponent(btnEliminar)
									.addGap(194)
									.addComponent(btnBuscarPieza)
									.addGap(18)
									.addComponent(txtBuscarPieza, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblObservaciones)
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
									.addGap(26)
									.addComponent(lblModelo)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtModelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblPiezasReparacion, GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
								.addComponent(txtObservaciones, GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE))
							.addGap(94))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPanel1, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnAnadir, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
									.addGap(16))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(txtAnadir, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblUnidades)
									.addGap(18)))
							.addComponent(scrollPanel2, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPiezasReparacion, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOrdenReparacion)
						.addComponent(txtOrdenReparacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMatricula)
						.addComponent(txtMatricula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMarca)
						.addComponent(txtMarca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtModelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblModelo))
					.addGap(18)
					.addComponent(lblObservaciones)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtObservaciones, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPanel2, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPanel1, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(17)
									.addComponent(btnEliminar))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtBuscarPieza, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnBuscarPieza)))
							.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnRealizarPedido)
								.addComponent(btnAsignarMecanico)
								.addComponent(btnSalir)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(17)
							.addComponent(btnAnadir)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtAnadir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUnidades))
							.addGap(117))))
		);
		
		table2 = new JTable();
		scrollPanel2.setViewportView(table2);
		
		table1 = new JTable();
		scrollPanel1.setViewportView(table1);
		
		initCampos(ordenReparacion);
		
		contentPane.setLayout(gl_contentPane);
	}
	
	private TableModel getTableModel (int ordenReparacion, String nombrePieza) throws RemoteException, GestorReparacionException  {
		List<DetallPeca> list = null;
		TableModel model = null;
		if (ordenReparacion == -1){
			list = gestorReparacion.getDetallePiezasTaller(usuario.getTaller(), nombrePieza);
			Object rowData [][] = new Object [list.size()][4];
			int z=0;
			for (DetallPeca bean : list) {
				rowData[z][0] = String.valueOf(bean.getCodiPeca());
				rowData[z][1] = String.valueOf(bean.getDescipcio());
				rowData[z][2] = String.valueOf(bean.getStock());
				rowData[z][3] = String.valueOf(bean.getPvp());
				z++;
			}
			model = new DefaultTableModel(rowData, columnNames2);
		} else {
			list = gestorReparacion.getPiezasReparacion(ordenReparacion);
			Object rowData [][] = new Object [list.size()][5];
			int z=0;
			for (DetallPeca bean : list) {
				rowData[z][0] = String.valueOf(bean.getCodiPeca());
				rowData[z][1] = String.valueOf(bean.getDescipcio());
				rowData[z][2] = String.valueOf(bean.getCantidad());
				rowData[z][3] = String.valueOf(bean.getStock());
				rowData[z][4] = String.valueOf(bean.getPvp());
				z++;
			}
			model = new DefaultTableModel(rowData, columnNames1);
		}
		return model;
	}
	
	private int getIdPiezaSeleccionada (JTable tabla) throws RemoteException, GestorReparacionException {
		if (tabla.getSelectedRow() >= 0) {
			return Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
		} else {
			return -1;
		}
	}
	
	private String getDescPiezaSeleccionada () throws RemoteException, GestorReparacionException {
		if (table2.getSelectedRow() >= 0) {
			return table2.getValueAt(table2.getSelectedRow(), 1).toString();
		} else {
			return "";
		}
	}
	
	private int getStockPiezaSeleccionada () throws RemoteException, GestorReparacionException {
		if (table2.getSelectedRow() >= 0) {
			return Integer.parseInt(table2.getValueAt(table2.getSelectedRow(), 2).toString());
		} else {
			return -1;
		}
	}
	
	private double getPvpPiezaSeleccionada () throws RemoteException, GestorReparacionException {
		if (table2.getSelectedRow() >= 0) {
			return Double.parseDouble(table2.getValueAt(table2.getSelectedRow(), 3).toString());
		} else {
			return -1;
		}
	}
	
	private void anadirPieza() {
		int idPiezaAnadir;
		boolean piezaEncontrada = false;
		try {
			if (table2.getSelectedRowCount() == 1) {
				if (!txtAnadir.getText().isEmpty()) {
					int numPiezas = Integer.parseInt(txtAnadir.getText());
					idPiezaAnadir = getIdPiezaSeleccionada(table2);
					if (piezasSeleccionadas != null) {
						for (int i=0; i<piezasSeleccionadas.size(); i++) {
							if (piezasSeleccionadas.get(i).getCodiPeca() == idPiezaAnadir) {
								piezasSeleccionadas.get(i).setCantidad(piezasSeleccionadas.get(i).getCantidad() + numPiezas);
								piezaEncontrada = true;
							}
						}
					}
					if (!piezaEncontrada) {
						DetallPeca peca = new DetallPeca(idPiezaAnadir, getDescPiezaSeleccionada(), getStockPiezaSeleccionada(), numPiezas, getPvpPiezaSeleccionada(),gestorReparacion.getStockMinimoPieza(idPiezaAnadir));
						piezasSeleccionadas.add(peca);
					}
					
					Object rowData [][] = new Object [piezasSeleccionadas.size()][5];
					int z=0;
					for (DetallPeca bean : piezasSeleccionadas) {
						rowData[z][0] = String.valueOf(bean.getCodiPeca());
						rowData[z][1] = String.valueOf(bean.getDescipcio());
						rowData[z][2] = String.valueOf(bean.getCantidad());
						rowData[z][3] = String.valueOf(bean.getStock());
						rowData[z][4] = String.valueOf(bean.getPvp());
						z++;
					}
					TableModel model = new DefaultTableModel(rowData, columnNames1);
					table1.setModel(model);
				} else {
					JOptionPane.showMessageDialog(reparacionPiezas, "Debe indicar el número de piezas a añadir.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			} else {
				JOptionPane.showMessageDialog(reparacionPiezas, "Debe seleccionar una fila para poder añadirla.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (GestorReparacionException e1) {
			e1.printStackTrace();
		}
	}
	
	private void eliminarPieza() {
		
			try {
				if (table1.getSelectedRowCount() == 1) {
					int idPieza;
					idPieza = getIdPiezaSeleccionada(table1);
					if (piezasSeleccionadas != null) {
						for (int i=0; i<piezasSeleccionadas.size(); i++) {
							if (piezasSeleccionadas.get(i).getCodiPeca() == idPieza) {
								piezasSeleccionadas.remove(i);
							}
						}
					}
					Object rowData [][] = new Object [piezasSeleccionadas.size()][5];
					int z=0;
					for (DetallPeca bean : piezasSeleccionadas) {
						rowData[z][0] = String.valueOf(bean.getCodiPeca());
						rowData[z][1] = String.valueOf(bean.getDescipcio());
						rowData[z][2] = String.valueOf(bean.getCantidad());
						rowData[z][3] = String.valueOf(bean.getStock());
						rowData[z][4] = String.valueOf(bean.getPvp());
						z++;
					}
					TableModel model = new DefaultTableModel(rowData, columnNames1);
					table1.setModel(model);
			} else {
				JOptionPane.showMessageDialog(reparacionPiezas, "Debe seleccionar una fila para poder eliminarla.", "Error", JOptionPane.ERROR_MESSAGE);
			}
				
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (GestorReparacionException e) {
				e.printStackTrace();
			}	
	}
	
	private void realizarPedido() {
		try {
			if (piezasSeleccionadas != null) {
				for (int i=0; i<piezasSeleccionadas.size(); i++) {
					DetallPeca pieza = piezasSeleccionadas.get(i);
					boolean estado = false;
					if (pieza.getCantidad() <= pieza.getStockMinim()) {
						estado = true;
					}
					gestorReparacion.setPiezaComanda(estado,pieza.getCodiPeca(), usuario.getTaller(), ordenReparacion, pieza.getCantidad());
					if (estado == true) {
						gestorReparacion.setDescontarStock(pieza.getCodiPeca(), pieza.getCantidad());
					}
				}
				gestorReparacion.setReparacionAceptada(ordenReparacion);
			} else {
				JOptionPane.showMessageDialog(reparacionPiezas, "Debe añadir piezas antes de realizar el pedido.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (GestorReparacionException e1) {
			e1.printStackTrace();
		}
	}
	
	private void initCampos(int ordenReparacion) {
		try {
			DetallReparacio datosReparacion = gestorReparacion.getDetalleReparacion(ordenReparacion);
			this.txtOrdenReparacion.setText(String.valueOf(datosReparacion.getOrdreReparacio()));
			this.txtMatricula.setText(datosReparacion.getMatricula());
			this.txtMarca.setText(datosReparacion.getMarca());
			this.txtModelo.setText(datosReparacion.getModel());
			this.txtObservaciones.setText(datosReparacion.getObservacions());
			
			scrollPanel1.setViewportView(table1);
			inicializarLista();
			
			scrollPanel2.setViewportView(table2);
			table2.setModel(getTableModel(-1, ""));

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (GestorReparacionException e) {
			e.printStackTrace();
		}
	}
	
	private void inicializarLista() {
		try {
			piezasSeleccionadas = gestorReparacion.getPiezasReparacion(ordenReparacion);
			Object rowData [][] = new Object [piezasSeleccionadas.size()][5];
			int z=0;
			for (DetallPeca bean : piezasSeleccionadas) {
				rowData[z][0] = String.valueOf(bean.getCodiPeca());
				rowData[z][1] = String.valueOf(bean.getDescipcio());
				rowData[z][2] = String.valueOf(bean.getCantidad());
				rowData[z][3] = String.valueOf(bean.getStock());
				rowData[z][4] = String.valueOf(bean.getPvp());
				z++;
			}
			
			TableModel model = new DefaultTableModel(rowData, columnNames1);
			table1.setModel(model);
			
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (GestorReparacionException e1) {
			e1.printStackTrace();
		}
	}
}
