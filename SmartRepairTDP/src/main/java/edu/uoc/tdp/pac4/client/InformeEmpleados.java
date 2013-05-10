package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

public class InformeEmpleados extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InformeReparaciones frame = new InformeReparaciones();
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
	public InformeEmpleados() {
		setTitle("Informe de Empleados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 297);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Id Empleado");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(157, 11, 80, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre Empleado");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(398, 11, 117, 14);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(157, 28, 80, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(398, 28, 155, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConsultar.setBounds(276, 58, 89, 23);
		contentPane.add(btnConsultar);
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnImprimir.setBounds(544, 174, 89, 23);
		contentPane.add(btnImprimir);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalir.setBounds(544, 222, 89, 23);
		contentPane.add(btnSalir);
		
		JLabel lblNHorasTrabajadas = new JLabel("Nº Horas trabajadas:");
		lblNHorasTrabajadas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNHorasTrabajadas.setBounds(59, 178, 131, 14);
		contentPane.add(lblNHorasTrabajadas);
		
		JLabel lblNReparacionesResueltas = new JLabel("Nº Reparaciones resueltas:");
		lblNReparacionesResueltas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNReparacionesResueltas.setBounds(59, 203, 168, 14);
		contentPane.add(lblNReparacionesResueltas);
		
		JLabel lblNFaltasDe = new JLabel("Nº Faltas de asistencia:");
		lblNFaltasDe.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNFaltasDe.setBounds(59, 231, 132, 14);
		contentPane.add(lblNFaltasDe);
		
		textField_2 = new JTextField();
		textField_2.setBounds(221, 175, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(221, 200, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(221, 228, 86, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(59, 104, 575, 43);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(table);
	}
}