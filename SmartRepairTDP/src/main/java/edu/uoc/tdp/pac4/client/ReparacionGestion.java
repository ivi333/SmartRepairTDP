package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

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
import javax.swing.JDialog;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.UIManager;

import edu.uoc.tdp.pac4.beans.DetallReparacio;
import edu.uoc.tdp.pac4.beans.Reparacio;
import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import edu.uoc.tdp.pac4.exception.GestorReparacionException;
import edu.uoc.tdp.pac4.service.GestorReparacionInterface;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;

public class ReparacionGestion extends JFrame {

	private JPanel contentPane;
	private JTextField txtDe;
	private JTextField txtHasta;
	private JTextField txtNombreCliente;
	private JTextField txtApellidoCliente;
	
	private static ReparacionGestion reparacionGestion;
	private GestorReparacionInterface gestorReparacion;
	private static final Object columnNames[] = {
		TDSLanguageUtils.getMessage("repGestion.tablas.reparacion"), TDSLanguageUtils.getMessage("repGestion.tablas.fentrada"), TDSLanguageUtils.getMessage("repGestion.tablas.contador"), TDSLanguageUtils.getMessage("repGestion.tablas.matricula"), TDSLanguageUtils.getMessage("repGestion.tablas.marca"), TDSLanguageUtils.getMessage("repGestion.tablas.modelo"), TDSLanguageUtils.getMessage("repGestion.tablas.observaciones"), TDSLanguageUtils.getMessage("repGestion.tablas.aceptada"), TDSLanguageUtils.getMessage("repGestion.tablas.asignada")
	};
	
	private JTable table;
	private JButton btnDetalle;
	private JButton btnAceptar;
	private JButton btnAsignar;
	private JTextField txtFiltro;
	private JComboBox cmbFiltro;
	

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
	
	public ReparacionGestion(GestorReparacionInterface conexion, final Usuari usuario) {
		this.gestorReparacion = conexion;
	
		setSize(new Dimension(580, 380));
		setTitle(TDSLanguageUtils.getMessage("repGestion.titulo"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 380);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblReparaciones = new JLabel(TDSLanguageUtils.getMessage("repGestion.reparaciones"));
		lblReparaciones.setBackground(UIManager.getColor("Button.background"));
		lblReparaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblReparaciones.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		cmbFiltro = new JComboBox();
		cmbFiltro.setModel(new DefaultComboBoxModel(new String[] {TDSLanguageUtils.getMessage("repGestion.filtro.todas"), TDSLanguageUtils.getMessage("repGestion.filtro.ordenrep"), TDSLanguageUtils.getMessage("repGestion.filtro.matricula"), TDSLanguageUtils.getMessage("repGestion.filtro.marca"), TDSLanguageUtils.getMessage("repGestion.filtro.modelo"), TDSLanguageUtils.getMessage("repGestion.filtro.aceptadas"), TDSLanguageUtils.getMessage("repGestion.filtro.noaceptadas"), TDSLanguageUtils.getMessage("repGestion.filtro.asignadas"), TDSLanguageUtils.getMessage("repGestion.filtro.noasignadas")}));
		
		JLabel lblDe = new JLabel(TDSLanguageUtils.getMessage("repGestion.filtro.de"));
		
		txtDe = new JTextField();
		txtDe.setColumns(10);
		
		JLabel lblHasta = new JLabel(TDSLanguageUtils.getMessage("repGestion.filtro.hasta"));
		
		txtHasta = new JTextField();
		txtHasta.setColumns(10);
		
		JLabel lblNombreCliente = new JLabel(TDSLanguageUtils.getMessage("repGestion.filtro.nombrecli"));
		
		txtNombreCliente = new JTextField();
		txtNombreCliente.setColumns(10);
		
		JLabel lblApellidoCliente = new JLabel(TDSLanguageUtils.getMessage("repGestion.filtro.apellidocli"));
		
		txtApellidoCliente = new JTextField();
		txtApellidoCliente.setColumns(10);
		
		
		JButton btnActualizar = new JButton(TDSLanguageUtils.getMessage("repGestion.actualizar"));
		btnActualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					boolean fechaValida = true;
					if (txtDe.getText()!=null && !txtDe.getText().trim().equals("")){
						if (!validarFecha(txtDe.getText())) {
							fechaValida = false;
							JOptionPane.showMessageDialog(reparacionGestion, TDSLanguageUtils.getMessage("repGestion.alert.fdevalida"), TDSLanguageUtils.getMessage("repGestion.alert"), JOptionPane.ERROR_MESSAGE);
						}
					}
					
					if (txtHasta.getText()!=null && !txtHasta.getText().trim().equals("")){
						if (!validarFecha(txtHasta.getText())) {
							fechaValida = false;
							JOptionPane.showMessageDialog(reparacionGestion, TDSLanguageUtils.getMessage("repGestion.alert.fhastavalida"), TDSLanguageUtils.getMessage("repGestion.alert"), JOptionPane.ERROR_MESSAGE);
						}
					}
					
					if (fechaValida) {
						table.setModel(getTableModel(true));
					}
					
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (GestorReparacionException e) {
					e.printStackTrace();
				}
			}
		});
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		btnDetalle = new JButton(TDSLanguageUtils.getMessage("repGestion.detalle"));
		btnDetalle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReparacionDetalle dialog;
				try {
					int idFilaSeleccionada = getFilaSeleccionada();
					if (idFilaSeleccionada >= 0) {
						dialog = new ReparacionDetalle(gestorReparacion, usuario, idFilaSeleccionada);
						Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
						dialog.setSize(1000, 500);
						dialog.setLocation(dim.width/2-dialog.getSize().width/2, dim.height/2-dialog.getSize().height/2);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(reparacionGestion, TDSLanguageUtils.getMessage("repGestion.alert.seleccionarfila"), TDSLanguageUtils.getMessage("repGestion.alert"), JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (RemoteException e1) {
					e1.printStackTrace();
				} catch (GestorReparacionException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnAceptar = new JButton(TDSLanguageUtils.getMessage("repGestion.aceptar"));
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ReparacionPiezas dialog;
				try {
					int idFilaSeleccionada = getFilaSeleccionada();
					if (idFilaSeleccionada >= 0) {
						DetallReparacio reparacionAct = gestorReparacion.getDetalleReparacion(idFilaSeleccionada);
						if (!reparacionAct.getAcceptada()) {
							dialog = new ReparacionPiezas(gestorReparacion, usuario, getFilaSeleccionada());
							Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
							dialog.setSize(1000, 500);
							dialog.setLocation(dim.width/2-dialog.getSize().width/2, dim.height/2-dialog.getSize().height/2);
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(reparacionGestion, TDSLanguageUtils.getMessage("repGestion.alert.repyaaceptada"), TDSLanguageUtils.getMessage("repPiezas.alert"), JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(reparacionGestion, TDSLanguageUtils.getMessage("repGestion.alert.seleccionarfilapiezas"), TDSLanguageUtils.getMessage("repGestion.alert"), JOptionPane.ERROR_MESSAGE);
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (GestorReparacionException e) {
					e.printStackTrace();
				}
			}
		});
		
		btnAsignar = new JButton(TDSLanguageUtils.getMessage("repGestion.asignar"));
		btnAsignar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReparacionAsignarMecanico dialog;
				try {
					int idFilaSeleccionada = getFilaSeleccionada();
					if (idFilaSeleccionada >= 0) {
						DetallReparacio reparacionSel = gestorReparacion.getDetalleReparacion(getFilaSeleccionada());
						if (reparacionSel.getAcceptada()) {
							int comandasPendientes = gestorReparacion.getNumComandasPendientes(reparacionSel.getOrdreReparacio());
							if (comandasPendientes == 0) {
								if (!String.valueOf(reparacionSel.getDataInici()).contains("-")) {
									dialog = new ReparacionAsignarMecanico(gestorReparacion, usuario, getFilaSeleccionada());
									Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
									dialog.setSize(1000, 500);
									dialog.setLocation(dim.width/2-dialog.getSize().width/2, dim.height/2-dialog.getSize().height/2);
									dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
									dialog.setVisible(true);
								} else {
									JOptionPane.showMessageDialog(reparacionGestion, TDSLanguageUtils.getMessage("repGestion.alert.reparacioniniciada"), TDSLanguageUtils.getMessage("repGestion.alert"), JOptionPane.ERROR_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(reparacionGestion, TDSLanguageUtils.getMessage("repGestion.alert.comandaspendientes"), TDSLanguageUtils.getMessage("repGestion.alert"), JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(reparacionGestion, TDSLanguageUtils.getMessage("repGestion.alert.reparacionnoaceptada"), TDSLanguageUtils.getMessage("repGestion.alert"), JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(reparacionGestion, TDSLanguageUtils.getMessage("repGestion.alert.seleccionarfilamec"), TDSLanguageUtils.getMessage("repGestion.alert"), JOptionPane.ERROR_MESSAGE);
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				} catch (GestorReparacionException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnSalir = new JButton(TDSLanguageUtils.getMessage("repGestion.salir"));
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		
		JScrollPane scrollPanel = new JScrollPane();
		
		txtFiltro = new JTextField();
		txtFiltro.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPanel, GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(cmbFiltro, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(46)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblDe)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(txtDe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblHasta)
											.addGap(18)
											.addComponent(txtHasta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblNombreCliente)
											.addGap(18)
											.addComponent(txtNombreCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblApellidoCliente)
											.addGap(18)
											.addComponent(txtApellidoCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnActualizar)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnDetalle)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnAceptar)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAsignar)
									.addPreferredGap(ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
									.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)))
							.addGap(95))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblReparaciones, GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
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
							.addComponent(lblApellidoCliente))
						.addComponent(txtFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPanel, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnActualizar)
						.addComponent(btnSalir)
						.addComponent(btnDetalle)
						.addComponent(btnAceptar)
						.addComponent(btnAsignar))
					.addGap(29))
		);
		
		table = new JTable();
		
		scrollPanel.setViewportView(table);
		
		try {
			table.setModel(getTableModel(false));
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (GestorReparacionException e) {
			e.printStackTrace();
		}
		
	
		
		contentPane.setLayout(gl_contentPane);
	}
	
	private TableModel getTableModel (boolean filtro) throws RemoteException, GestorReparacionException  {
		List<DetallReparacio> list = null; 
		if (filtro) {
			list = gestorReparacion.getDetalleReparacionesFiltro(cmbFiltro.getSelectedIndex(), String.valueOf(txtFiltro.getText()), String.valueOf(txtNombreCliente.getText()), String.valueOf(txtApellidoCliente.getText()), String.valueOf(txtDe.getText()), String.valueOf(txtHasta.getText()));
		} else {
			list = gestorReparacion.getDetalleReparaciones();
		}
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
	
	private int getFilaSeleccionada () throws RemoteException, GestorReparacionException {
		if (table.getSelectedRow() >= 0) {
			return Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
		} else {
			return -1;
		}
	}
	
	private boolean validarFecha(String fecha) {  
		  
		if (fecha == null)  
		return false;  
		  
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //año-mes-dia  
		  
		if (fecha.trim().length() != dateFormat.toPattern().length())  
		return false;  
		  
		dateFormat.setLenient(false);  
		  
		try {  
			dateFormat.parse(fecha.trim());  
		}  
		catch (ParseException pe) {  
			return false;  
		}  
		return true;  
	}  

	
}
