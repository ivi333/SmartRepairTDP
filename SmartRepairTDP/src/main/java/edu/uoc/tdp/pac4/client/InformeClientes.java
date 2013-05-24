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

import edu.uoc.tdp.pac4.common.TDSLanguageUtils;

import java.awt.Font;

public class InformeClientes extends JFrame {

	private JPanel contentPane;
	private JTextField tbxNomClient;
	private JTextField tbxMarca;
	private JTextField tbxCompAsseguradora;
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
	public InformeClientes() {
		setTitle(TDSLanguageUtils.getMessage("infCliente.titulo"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 659, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel(TDSLanguageUtils.getMessage("infCliente.nomClient"));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(100, 11, 94, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNombreMecnico = new JLabel(TDSLanguageUtils.getMessage("infCliente.marca"));
		lblNombreMecnico.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombreMecnico.setBounds(238, 11, 103, 14);
		contentPane.add(lblNombreMecnico);
		
		JLabel lblTipoDeReparacin = new JLabel(TDSLanguageUtils.getMessage("infCliente.cAsseguradora"));
		lblTipoDeReparacin.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTipoDeReparacin.setBounds(376, 11, 148, 14);
		contentPane.add(lblTipoDeReparacin);
		
		tbxNomClient = new JTextField();
		tbxNomClient.setBounds(96, 28, 121, 20);
		contentPane.add(tbxNomClient);
		tbxNomClient.setColumns(10);
		
		tbxMarca = new JTextField();
		tbxMarca.setBounds(238, 28, 121, 20);
		contentPane.add(tbxMarca);
		tbxMarca.setColumns(10);
		
		JButton btnConsultar = new JButton(TDSLanguageUtils.getMessage("infCliente.btn.consultar"));
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConsultar.setBounds(266, 61, 89, 23);
		contentPane.add(btnConsultar);
		
		tbxCompAsseguradora = new JTextField();
		tbxCompAsseguradora.setBounds(376, 28, 148, 20);
		contentPane.add(tbxCompAsseguradora);
		tbxCompAsseguradora.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 119, 581, 224);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnSalir = new JButton(TDSLanguageUtils.getMessage("infCliente.btn.sortir"));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalir.setBounds(522, 364, 89, 23);
		contentPane.add(btnSalir);
	}
}
