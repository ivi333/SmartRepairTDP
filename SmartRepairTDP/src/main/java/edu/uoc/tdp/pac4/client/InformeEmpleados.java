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

public class InformeEmpleados extends JFrame {

	private JPanel contentPane;
	private JTextField tbxIdUsuari;
	private JTextField tbxNomUsuari;
	private JTextField tbxHoresTrab;
	private JTextField tbxRepRessolt;
	private JTextField tbxFaltasAssist;
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
		setTitle(TDSLanguageUtils.getMessage("infEmpleado.titulo"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 297);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbIdUsuari = new JLabel(TDSLanguageUtils.getMessage("infEmpleado.idEmpleat"));
		lbIdUsuari.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbIdUsuari.setBounds(157, 11, 80, 14);
		contentPane.add(lbIdUsuari);
		
		JLabel lblNomUsuari = new JLabel(TDSLanguageUtils.getMessage("infEmpleado.nomEmpleat"));
		lblNomUsuari.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNomUsuari.setBounds(398, 11, 117, 14);
		contentPane.add(lblNomUsuari);
		
		tbxIdUsuari = new JTextField();
		tbxIdUsuari.setBounds(157, 28, 80, 20);
		contentPane.add(tbxIdUsuari);
		tbxIdUsuari.setColumns(10);
		
		tbxNomUsuari = new JTextField();
		tbxNomUsuari.setBounds(398, 28, 155, 20);
		contentPane.add(tbxNomUsuari);
		tbxNomUsuari.setColumns(10);
		
		JButton btnConsultar = new JButton(TDSLanguageUtils.getMessage("infEmpleado.btn.consultar"));
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConsultar.setBounds(276, 58, 89, 23);
		contentPane.add(btnConsultar);
		
		JButton btnSortir = new JButton(TDSLanguageUtils.getMessage("infEmpleado.btn.sortir"));
		btnSortir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSortir.setBounds(544, 222, 89, 23);
		contentPane.add(btnSortir);
		
		JLabel lblNHorasTrabajadas = new JLabel(TDSLanguageUtils.getMessage("infEmpleado.lbl.horesTreballades"));
		lblNHorasTrabajadas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNHorasTrabajadas.setBounds(59, 178, 131, 14);
		contentPane.add(lblNHorasTrabajadas);
		
		JLabel lblNReparacionesResueltas = new JLabel(TDSLanguageUtils.getMessage("infEmpleado.lbl.repRessoltes"));
		lblNReparacionesResueltas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNReparacionesResueltas.setBounds(59, 203, 168, 14);
		contentPane.add(lblNReparacionesResueltas);
		
		JLabel lblNFaltasDe = new JLabel(TDSLanguageUtils.getMessage("infEmpleado.lbl.faltesAssitencia"));
		lblNFaltasDe.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNFaltasDe.setBounds(59, 231, 132, 14);
		contentPane.add(lblNFaltasDe);
		
		tbxHoresTrab = new JTextField();
		tbxHoresTrab.setBounds(221, 175, 86, 20);
		contentPane.add(tbxHoresTrab);
		tbxHoresTrab.setColumns(10);
		
		tbxRepRessolt = new JTextField();
		tbxRepRessolt.setBounds(221, 200, 86, 20);
		contentPane.add(tbxRepRessolt);
		tbxRepRessolt.setColumns(10);
		
		tbxFaltasAssist = new JTextField();
		tbxFaltasAssist.setBounds(221, 228, 86, 20);
		contentPane.add(tbxFaltasAssist);
		tbxFaltasAssist.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(59, 104, 575, 43);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(table);
	}
}