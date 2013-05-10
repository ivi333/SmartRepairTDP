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
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class ReparacionStock extends JFrame {

	private JPanel contentPane;
	private JTextField lblStockPiezas;
	private JTable tblStock;
	private JTable tblPiezasAnadidas;
	private JTextField btnNumero;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReparacionStock frame = new ReparacionStock();
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
	public ReparacionStock() {
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
		
		JComboBox cmbFiltrar = new JComboBox();
		
		tblStock = new JTable();
		tblStock.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
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
		
		JButton btnSeleccionarTodos = new JButton("Seleccionar todos");
		
		JButton btnAnadir = new JButton("Añadir");
		
		JLabel lblPiezasAnadidas = new JLabel("Piezas añadidas para realizar el pedido");
		lblPiezasAnadidas.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPiezasAnadidas.setHorizontalAlignment(SwingConstants.CENTER);
		
		tblPiezasAnadidas = new JTable();
		tblPiezasAnadidas.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
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
		
		JButton btnMas = new JButton("+");
		
		btnNumero = new JTextField();
		btnNumero.setColumns(10);
		
		JButton btnRealizarPedido = new JButton("Realizar pedido");
		
		JButton btnEliminar = new JButton("Eliminar");
		
		JButton btnSalir = new JButton("Salir");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPiezasAnadidas, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
						.addComponent(lblStockPiezas, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(lblFiltrar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cmbFiltrar, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
						.addComponent(tblStock, GroupLayout.PREFERRED_SIZE, 455, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnSeleccionarTodos)
							.addGap(7)
							.addComponent(btnAnadir))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(tblPiezasAnadidas, GroupLayout.PREFERRED_SIZE, 393, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNumero, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
								.addComponent(btnMas, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnRealizarPedido)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEliminar)
							.addPreferredGap(ComponentPlacement.RELATED, 222, Short.MAX_VALUE)
							.addComponent(btnSalir)))
					.addGap(95))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblStockPiezas, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFiltrar)
						.addComponent(cmbFiltrar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tblStock, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSeleccionarTodos)
						.addComponent(btnAnadir))
					.addGap(26)
					.addComponent(lblPiezasAnadidas, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(tblPiezasAnadidas, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnMas)
							.addGap(8)
							.addComponent(btnNumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRealizarPedido)
						.addComponent(btnEliminar)
						.addComponent(btnSalir)))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
