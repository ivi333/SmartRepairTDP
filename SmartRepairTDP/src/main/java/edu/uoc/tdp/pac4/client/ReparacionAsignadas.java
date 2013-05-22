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
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JScrollPane;

import edu.uoc.tdp.pac4.beans.DetallReparacio;
import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.exception.GestorReparacionException;
import edu.uoc.tdp.pac4.service.GestorReparacionInterface;

public class ReparacionAsignadas extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	
	private static ReparacionAsignadas reparacionAsignadas;
	private GestorReparacionInterface gestorReparacion;
	private static final Object columnNames[] = {
		"Orden reparaci\u00F3n", "Fecha asignaci\u00F3n", "Fecha Inicio", "Num. Orden", "Matr\u00EDcula", "Marca", "Modelo", "Contador"
	};
	
	private Usuari usuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReparacionAsignadas frame = new ReparacionAsignadas(null,null);
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
	public ReparacionAsignadas(GestorReparacionInterface conexion, final Usuari usuario) {
		this.gestorReparacion = conexion;
		this.usuario = usuario;
		
		setTitle("Reparaciones asignadas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 241);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Reparaciones Asignadas");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		JLabel lblFIltro = new JLabel("Filtro");
		
		JComboBox cmbFiltro = new JComboBox();
		
		JButton btnDetalle = new JButton("Detalle");
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
						JOptionPane.showMessageDialog(reparacionAsignadas, "Debe seleccionar una fila para poder ver el detalle de la reparación", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (RemoteException e1) {
					e1.printStackTrace();
				} catch (GestorReparacionException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		
		scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 406, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblFIltro)
							.addGap(18)
							.addComponent(cmbFiltro, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnDetalle)
							.addPreferredGap(ComponentPlacement.RELATED, 288, Short.MAX_VALUE)
							.addComponent(btnSalir))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 406, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFIltro)
						.addComponent(cmbFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDetalle)
						.addComponent(btnSalir))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		initCampos();
		
		contentPane.setLayout(gl_contentPane);
	}
	
	private TableModel getTableModel () throws RemoteException, GestorReparacionException  {
		List<DetallReparacio> list = gestorReparacion.getDetalleReparacionesMecanico(usuario.getId());
		Object rowData [][] = new Object [list.size()][8];
		int z=0;
		for (DetallReparacio bean : list) {
			rowData[z][0] = String.valueOf(bean.getOrdreReparacio());
			/*rowData[z][1] = String.valueOf(bean.getDataAsignacio());
			rowData[z][2] = String.valueOf(bean.getDataInici());*/
			rowData[z][1] = String.valueOf(bean.getDataEntrada());
			rowData[z][2] = String.valueOf(bean.getDataEntrada());
			rowData[z][3] = String.valueOf(bean.getOrdreReparacio());
			rowData[z][4] = String.valueOf(bean.getMatricula());
			rowData[z][5] = String.valueOf(bean.getMarca());
			rowData[z][6] = String.valueOf(bean.getModel());
			rowData[z][7] = String.valueOf(bean.getComptador());
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
	
	private void initCampos() {
		try {
			scrollPane.setViewportView(table);
			table.setModel(getTableModel());
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (GestorReparacionException e) {
			e.printStackTrace();
		}
	}
	
}
