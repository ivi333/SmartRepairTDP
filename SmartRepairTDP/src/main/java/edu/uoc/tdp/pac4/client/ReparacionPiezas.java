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
import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
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
		TDSLanguageUtils.getMessage("repGestion.tablas.codigo"), TDSLanguageUtils.getMessage("repGestion.tablas.descripcion"), TDSLanguageUtils.getMessage("repGestion.tablas.unidades"), TDSLanguageUtils.getMessage("repGestion.tablas.stock"), TDSLanguageUtils.getMessage("repGestion.tablas.precio")
	};
	private static final Object columnNames2[] = {
		TDSLanguageUtils.getMessage("repGestion.tablas.codigo"), TDSLanguageUtils.getMessage("repGestion.tablas.descripción"), TDSLanguageUtils.getMessage("repGestion.tablas.stock"), TDSLanguageUtils.getMessage("repGestion.tablas.precio")
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
		
		setTitle(TDSLanguageUtils.getMessage("repPiezas.titulo"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 778, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblPiezasReparacion = new JLabel(TDSLanguageUtils.getMessage("repPiezas.titulo"));
		lblPiezasReparacion.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblPiezasReparacion.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblOrdenReparacion = new JLabel(TDSLanguageUtils.getMessage("repPiezas.ordenrep"));
		
		txtOrdenReparacion = new JTextField();
		txtOrdenReparacion.setEditable(false);
		txtOrdenReparacion.setColumns(10);
		
		JLabel lblMatricula = new JLabel(TDSLanguageUtils.getMessage("repPiezas.matricula"));
		
		txtMatricula = new JTextField();
		txtMatricula.setEditable(false);
		txtMatricula.setColumns(10);
		
		JLabel lblMarca = new JLabel(TDSLanguageUtils.getMessage("repPiezas.marca"));
		
		txtMarca = new JTextField();
		txtMarca.setEditable(false);
		txtMarca.setColumns(10);
		
		JLabel lblModelo = new JLabel(TDSLanguageUtils.getMessage("repPiezas.modelo"));
		
		txtModelo = new JTextField();
		txtModelo.setEditable(false);
		txtModelo.setColumns(10);
		
		JLabel lblObservaciones = new JLabel(TDSLanguageUtils.getMessage("repPiezas.obsrep"));
		
		txtObservaciones = new JTextArea();
		txtObservaciones.setEditable(false);
		
		btnAnadir = new JButton(TDSLanguageUtils.getMessage("repPiezas.anadir"));
		btnAnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtAnadir.getText().matches("[0-9]*")) 
					if (Integer.parseInt(txtAnadir.getText())>0 && Integer.parseInt(txtAnadir.getText())<10000) {
						anadirPieza();
					} else {
						JOptionPane.showMessageDialog(reparacionPiezas, TDSLanguageUtils.getMessage("repStock.alert.numeropiezas"), TDSLanguageUtils.getMessage("repStock.alert"), JOptionPane.ERROR_MESSAGE);
					} 
				else {
					JOptionPane.showMessageDialog(reparacionPiezas, TDSLanguageUtils.getMessage("repStock.alert.numeropiezas"), TDSLanguageUtils.getMessage("repStock.alert"), JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton btnEliminar = new JButton(TDSLanguageUtils.getMessage("repPiezas.eliminar"));
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				eliminarPieza();
			}
		});
		
		btnBuscarPieza = new JButton(TDSLanguageUtils.getMessage("repPiezas.buscarpieza"));
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
		
		JButton btnRealizarPedido = new JButton(TDSLanguageUtils.getMessage("repPiezas.realizarpedido"));
		btnRealizarPedido.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
						realizarPedido();
			}
		});
		
		JButton btnAsignarMecanico = new JButton(TDSLanguageUtils.getMessage("repPiezas.asignarmec"));
		btnAsignarMecanico.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReparacionAsignarMecanico dialog;
				try {
					DetallReparacio reparacionSel = gestorReparacion.getDetalleReparacion(ordenReparacion);
					if (reparacionSel.getAcceptada()) {
						int comandasPendientes = gestorReparacion.getNumComandasPendientes(reparacionSel.getOrdreReparacio());
						if (comandasPendientes == 0) {
							dialog = new ReparacionAsignarMecanico(gestorReparacion, usuario, ordenReparacion);
							Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
							dialog.setSize(1000, 500);
							dialog.setLocation(dim.width/2-dialog.getSize().width/2, dim.height/2-dialog.getSize().height/2);
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(reparacionPiezas, TDSLanguageUtils.getMessage("repPiezas.alert.comandaspendientes"), TDSLanguageUtils.getMessage("repGestion.alert"), JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(reparacionPiezas, TDSLanguageUtils.getMessage("repPiezas.alert.reparacionnoaceptada"), TDSLanguageUtils.getMessage("repGestion.alert"), JOptionPane.ERROR_MESSAGE);
					} 
				} catch (RemoteException e1) {
					e1.printStackTrace();
				} catch (GestorReparacionException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		scrollPanel1 = new JScrollPane();
		
		scrollPanel2 = new JScrollPane();
		
		txtAnadir.setColumns(10);
		
		JLabel lblUnidades = new JLabel(TDSLanguageUtils.getMessage("repPiezas.unidades"));
		
		JButton btnSalir = new JButton(TDSLanguageUtils.getMessage("repPiezas.salir"));
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnRealizarPedido, Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
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
								.addComponent(lblPiezasReparacion, GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
								.addComponent(txtObservaciones, GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnEliminar)
									.addPreferredGap(ComponentPlacement.RELATED, 379, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnAsignarMecanico)
										.addComponent(btnBuscarPieza))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(txtBuscarPieza, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnSalir))))
							.addGap(193))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPanel1, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnAnadir, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(txtAnadir, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblUnidades)))
							.addGap(28)
							.addComponent(scrollPanel2, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
							.addGap(97)))
					.addGap(0))
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
								.addComponent(scrollPanel1, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPanel2, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnEliminar)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtBuscarPieza, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnBuscarPieza)))
							.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
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
						DetallPeca peca = new DetallPeca(idPiezaAnadir, getDescPiezaSeleccionada(), getStockPiezaSeleccionada(), numPiezas, getPvpPiezaSeleccionada(),gestorReparacion.getStockMinimoPieza(idPiezaAnadir), gestorReparacion.getMarcaPieza(idPiezaAnadir), gestorReparacion.getModeloPieza(idPiezaAnadir));
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
					JOptionPane.showMessageDialog(reparacionPiezas, TDSLanguageUtils.getMessage("repPiezas.alert.numeropiezas"), TDSLanguageUtils.getMessage("repPiezas.alert"), JOptionPane.ERROR_MESSAGE);
				}
				
			} else {
				JOptionPane.showMessageDialog(reparacionPiezas, TDSLanguageUtils.getMessage("repPiezas.alert.seleccionarfila"), TDSLanguageUtils.getMessage("repPiezas.alert"), JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(reparacionPiezas, TDSLanguageUtils.getMessage("repPiezas.alert.seleccionarfilaelim"), TDSLanguageUtils.getMessage("repPiezas.alert"), JOptionPane.ERROR_MESSAGE);
		}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (GestorReparacionException e) {
			e.printStackTrace();
		}	
	}
	
	private void realizarPedido() {
		try {
			DetallReparacio reparacionAct = gestorReparacion.getDetalleReparacion(ordenReparacion);
			if (!reparacionAct.getAcceptada()) {
				if (piezasSeleccionadas != null) {
					for (int i=0; i<piezasSeleccionadas.size(); i++) {
						DetallPeca pieza = piezasSeleccionadas.get(i);
						boolean estado = false;
						if (pieza.getCantidad() <= pieza.getStockMinim()) {
							estado = true;
						}
						gestorReparacion.setPiezaComanda(estado,pieza.getCodiPeca(), usuario.getTaller(), ordenReparacion, pieza.getCantidad(), true);
						if (estado == true) {
							gestorReparacion.setDescontarStock(pieza.getCodiPeca(), pieza.getCantidad());
						}
					}
					gestorReparacion.setReparacionAceptada(ordenReparacion);
					JOptionPane.showMessageDialog(reparacionPiezas, TDSLanguageUtils.getMessage("repPiezas.info.pedidorealizado"), TDSLanguageUtils.getMessage("repPiezas.info"), JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(reparacionPiezas, TDSLanguageUtils.getMessage("repPiezas.alert.anadirpiezas"), TDSLanguageUtils.getMessage("repPiezas.alert"), JOptionPane.ERROR_MESSAGE);
				} 	
			} else {
				JOptionPane.showMessageDialog(reparacionPiezas, TDSLanguageUtils.getMessage("repPiezas.alert.repyaaceptada"), TDSLanguageUtils.getMessage("repPiezas.alert"), JOptionPane.ERROR_MESSAGE);
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
