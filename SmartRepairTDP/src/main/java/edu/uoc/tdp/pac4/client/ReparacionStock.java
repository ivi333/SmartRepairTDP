package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;

import edu.uoc.tdp.pac4.beans.DetallPeca;
import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.exception.GestorReparacionException;
import edu.uoc.tdp.pac4.service.GestorReparacionInterface;
import javax.swing.DefaultComboBoxModel;

public class ReparacionStock extends JFrame {

	private static ReparacionStock reparacionStock;
	private GestorReparacionInterface gestorReparacion;
	private JPanel contentPane;
	private JTextField lblStockPiezas;
	private JTextField txtNumero;
	private JTable table1;
	private JTable table2;
	
	private Usuari usuario;
	
	private static final Object columnNames1[] = {
		"C\u00F3digo", "Marca", "Modelo", "Unidades", "Precio total", "Descripci\u00F3n"
	};
	private static final Object columnNames2[] = {
		"C\u00F3digo", "Marca", "Modelo", "Stock Min.", "Stock", "Precio2", "Descripci\u00F3n"
	};
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;

	private List<DetallPeca> piezasSeleccionadas = new ArrayList<DetallPeca>() ;
	private JTextField txtFiltrar;
	private JComboBox cmbFiltrar;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReparacionStock frame = new ReparacionStock(null,null);
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
	public ReparacionStock(GestorReparacionInterface conexion, final Usuari usuario) {
		this.gestorReparacion = conexion;
		this.usuario = usuario;
		
		setTitle("Stock Piezas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 502, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblStockPiezas = new JTextField();
		lblStockPiezas.setHorizontalAlignment(SwingConstants.CENTER);
		lblStockPiezas.setText("Stock Piezas");
		lblStockPiezas.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblStockPiezas.setColumns(10);
		
		JLabel lblFiltrar = new JLabel("Filtrar");
		
		cmbFiltrar = new JComboBox();
		cmbFiltrar.setModel(new DefaultComboBoxModel(new String[] {"Todas", "Código", "Marca", "Stock Mín => Stock", "Precio"}));
		
		JButton btnSeleccionarTodos = new JButton("Seleccionar todos");
		btnSeleccionarTodos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table2.selectAll();
			}
		});
		
		JButton btnAnadir = new JButton("Añadir");
		btnAnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				anadirPieza(1);
			}
		});
		
		JLabel lblPiezasAnadidas = new JLabel("Piezas añadidas para realizar el pedido");
		lblPiezasAnadidas.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPiezasAnadidas.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnMas = new JButton("+");
		btnMas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sumarPiezas();
			}
		});
		
		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		
		JButton btnRealizarPedido = new JButton("Realizar pedido");
		btnRealizarPedido.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				realizarPedido();
			}
		});
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminarPieza();
			}
		});
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		
		scrollPane1 = new JScrollPane();
		
		scrollPane2 = new JScrollPane();
		
		txtFiltrar = new JTextField();
		txtFiltrar.setColumns(10);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					table2.setModel(getTableModel(true));
				} catch (RemoteException e1) {
					e1.printStackTrace();
				} catch (GestorReparacionException e1) {
					e1.printStackTrace();
				}
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
								.addComponent(lblPiezasAnadidas, GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
								.addComponent(lblStockPiezas, GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblFiltrar)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(cmbFiltrar, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtFiltrar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnFiltrar))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnSeleccionarTodos)
									.addGap(7)
									.addComponent(btnAnadir))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 403, GroupLayout.PREFERRED_SIZE)
									.addGap(8)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(txtNumero, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
										.addComponent(btnMas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnRealizarPedido)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnEliminar)
									.addPreferredGap(ComponentPlacement.RELATED, 219, Short.MAX_VALUE)
									.addComponent(btnSalir)))
							.addGap(95))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 445, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(21, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblStockPiezas, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFiltrar)
						.addComponent(cmbFiltrar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtFiltrar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnFiltrar))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSeleccionarTodos)
						.addComponent(btnAnadir))
					.addGap(26)
					.addComponent(lblPiezasAnadidas, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnMas)
							.addGap(8)
							.addComponent(txtNumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRealizarPedido)
						.addComponent(btnEliminar)
						.addComponent(btnSalir)))
		);
		
		table2 = new JTable();
		scrollPane2.setViewportView(table2);
		
		table1 = new JTable();
		scrollPane1.setViewportView(table1);
		
		initCampos();
		
		contentPane.setLayout(gl_contentPane);
	}
	
	private TableModel getTableModel (boolean filtro) throws RemoteException, GestorReparacionException  {
		TableModel model = null;
		List<DetallPeca> list = null;
		if (filtro) {
			list = gestorReparacion.getDetallePiezasTallerFiltro(usuario.getTaller(), cmbFiltrar.getSelectedIndex(), String.valueOf(txtFiltrar.getText()));
		} else {
			list = gestorReparacion.getDetallePiezasTaller(usuario.getTaller(), "");
		}
		Object rowData [][] = new Object [list.size()][7];
		int z=0;
		for (DetallPeca bean : list) {
			rowData[z][0] = String.valueOf(bean.getCodiPeca());
			rowData[z][1] = String.valueOf(bean.getMarca());
			rowData[z][2] = String.valueOf(bean.getModel());
			rowData[z][3] = String.valueOf(bean.getStockMinim());
			rowData[z][4] = String.valueOf(bean.getStock());
			rowData[z][5] = String.valueOf(bean.getPvp());
			rowData[z][6] = String.valueOf(bean.getDescipcio());
			z++;
		}
		model = new DefaultTableModel(rowData, columnNames2);
		
		return model;
	}
	
	private void initCampos() {
		try {
			scrollPane1.setViewportView(table1);
			inicializarLista();
			
			scrollPane2.setViewportView(table2);
			table2.setModel(getTableModel(false));

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (GestorReparacionException e) {
			e.printStackTrace();
		}
	}
	
	private void inicializarLista() {
		piezasSeleccionadas.add(new DetallPeca());
		TableModel model = new DefaultTableModel(new Object [0][7], columnNames1);
		piezasSeleccionadas.remove(0);
		table1.setModel(model);
	}
	
	private void anadirPieza(int unidades) {
		int idPiezaAnadir;
		boolean piezaEncontrada = false;
		try {
			if (table2.getSelectedRowCount() == 1) {
				idPiezaAnadir = getIdPiezaSeleccionada(table2);
				if (piezasSeleccionadas != null) {
					for (int i=0; i<piezasSeleccionadas.size(); i++) {
						if (piezasSeleccionadas.get(i).getCodiPeca() == idPiezaAnadir) {
							JOptionPane.showMessageDialog(reparacionStock, "Esta pieza ya ha sido añadida anteriormente. Puede aumentar el número de piezas si lo desea.", "Error", JOptionPane.ERROR_MESSAGE);
							piezaEncontrada = true;
						}
					}
				}
				if (!piezaEncontrada) {
					DetallPeca peca = new DetallPeca(idPiezaAnadir, getDescPiezaSeleccionada(), getStockPiezaSeleccionada(), unidades, getPvpPiezaSeleccionada(), getStockMinimoPiezaSeleccionada(), getMarcaPiezaSeleccionada(), getModeloPiezaSeleccionada());
					piezasSeleccionadas.add(peca);
				}
				
				Object rowData [][] = new Object [piezasSeleccionadas.size()][7];
				int z=0;
				for (DetallPeca bean : piezasSeleccionadas) {
					rowData[z][0] = String.valueOf(bean.getCodiPeca());
					rowData[z][1] = String.valueOf(bean.getMarca());
					rowData[z][2] = String.valueOf(bean.getModel());
					rowData[z][3] = String.valueOf(unidades);
					rowData[z][4] = String.valueOf(bean.getPvp());
					rowData[z][5] = String.valueOf(bean.getPvp()*unidades);
					rowData[z][6] = String.valueOf(bean.getDescipcio());
					z++;
				}
				TableModel model = new DefaultTableModel(rowData, columnNames1);
				table1.setModel(model);
				
				
			} else {
				JOptionPane.showMessageDialog(reparacionStock, "Debe seleccionar una fila para poder añadirla.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (GestorReparacionException e1) {
			e1.printStackTrace();
		}
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
			return table2.getValueAt(table2.getSelectedRow(), 6).toString();
		} else {
			return "";
		}
	}
	
	private String getMarcaPiezaSeleccionada () throws RemoteException, GestorReparacionException {
		if (table2.getSelectedRow() >= 0) {
			return table2.getValueAt(table2.getSelectedRow(), 1).toString();
		} else {
			return "";
		}
	}
	
	private String getModeloPiezaSeleccionada () throws RemoteException, GestorReparacionException {
		if (table2.getSelectedRow() >= 0) {
			return table2.getValueAt(table2.getSelectedRow(), 2).toString();
		} else {
			return "";
		}
	}
	
	private int getStockPiezaSeleccionada () throws RemoteException, GestorReparacionException {
		if (table2.getSelectedRow() >= 0) {
			return Integer.parseInt(table2.getValueAt(table2.getSelectedRow(), 4).toString());
		} else {
			return -1;
		}
	}
	
	private int getStockMinimoPiezaSeleccionada () throws RemoteException, GestorReparacionException {
		if (table2.getSelectedRow() >= 0) {
			return Integer.parseInt(table2.getValueAt(table2.getSelectedRow(), 3).toString());
		} else {
			return -1;
		}
	}
	
	private double getPvpPiezaSeleccionada () throws RemoteException, GestorReparacionException {
		if (table2.getSelectedRow() >= 0) {
			return Double.parseDouble(table2.getValueAt(table2.getSelectedRow(), 5).toString());
		} else {
			return -1;
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
				Object rowData [][] = new Object [piezasSeleccionadas.size()][7];
				int z=0;
				for (DetallPeca bean : piezasSeleccionadas) {
					rowData[z][0] = String.valueOf(bean.getCodiPeca());
					rowData[z][1] = String.valueOf(bean.getMarca());
					rowData[z][2] = String.valueOf(bean.getModel());
					rowData[z][3] = String.valueOf(bean.getCantidad());
					rowData[z][4] = String.valueOf(bean.getPvp());
					rowData[z][5] = String.valueOf(bean.getPvp()*bean.getCantidad());
					rowData[z][6] = String.valueOf(bean.getDescipcio());
					z++;
				}
				TableModel model = new DefaultTableModel(rowData, columnNames1);
				table1.setModel(model);
		} else {
			JOptionPane.showMessageDialog(reparacionStock, "Debe seleccionar una fila para poder eliminarla.", "Error", JOptionPane.ERROR_MESSAGE);
		}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (GestorReparacionException e) {
			e.printStackTrace();
		}	
	}
	
	private void sumarPiezas() {
		int idPiezaAnadir;
		try {
			if (table1.getSelectedRowCount() == 1) {
				idPiezaAnadir = getIdPiezaSeleccionada(table1);
				if (piezasSeleccionadas != null) {
					for (int i=0; i<piezasSeleccionadas.size(); i++) {
						if (piezasSeleccionadas.get(i).getCodiPeca() == idPiezaAnadir) {
							piezasSeleccionadas.get(i).setCantidad(piezasSeleccionadas.get(i).getCantidad() + Integer.valueOf(txtNumero.getText()));
						}
					}
				}
				
				Object rowData [][] = new Object [piezasSeleccionadas.size()][7];
				int z=0;
				for (DetallPeca bean : piezasSeleccionadas) {
					rowData[z][0] = String.valueOf(bean.getCodiPeca());
					rowData[z][1] = String.valueOf(bean.getMarca());
					rowData[z][2] = String.valueOf(bean.getModel());
					rowData[z][3] = String.valueOf(bean.getCantidad());
					rowData[z][4] = String.valueOf(bean.getPvp());
					rowData[z][5] = String.valueOf(bean.getPvp()*bean.getCantidad());
					rowData[z][6] = String.valueOf(bean.getDescipcio());
					z++;
				}
				TableModel model = new DefaultTableModel(rowData, columnNames1);
				table1.setModel(model);
				
				
			} else {
				JOptionPane.showMessageDialog(reparacionStock, "Debe seleccionar una fila para poder añadir más unidades.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (GestorReparacionException e1) {
			e1.printStackTrace();
		}
	}
	
	private void realizarPedido() {
		try {
			if (piezasSeleccionadas != null) {
				for (int i=0; i<piezasSeleccionadas.size(); i++) {
					DetallPeca pieza = piezasSeleccionadas.get(i);
					gestorReparacion.setPiezaComanda(false, pieza.getCodiPeca(), usuario.getTaller(), 0, pieza.getCantidad(), false);
				}
			} else {
				JOptionPane.showMessageDialog(reparacionStock, "Debe añadir piezas antes de realizar el pedido.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (GestorReparacionException e1) {
			e1.printStackTrace();
		}
	}
}
