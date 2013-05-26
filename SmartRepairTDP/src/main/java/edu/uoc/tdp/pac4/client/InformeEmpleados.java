package edu.uoc.tdp.pac4.client;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import edu.uoc.tdp.pac4.exception.GestorEstadisticaException;
import edu.uoc.tdp.pac4.service.GestorEstadisticaImpl;
import edu.uoc.tdp.pac4.service.GestorEstadisticaInterface;

public class InformeEmpleados extends JFrame {

	private JPanel contentPane;
	private JTextField tbxIdUsuari;
	private JTextField tbxNomUsuari;
	private JTextField tbxHoresTrab;
	private JTextField tbxRepRessolt;
	private JTable table;
	private final static String urlRMIEstad = new String("rmi://localhost/GestorEstadistica");
	
	private GestorEstadisticaInterface gestorEstadistica;
	
	private static final Object columnNames[] = {
		TDSLanguageUtils.getMessage("infReparacion.id"),
		TDSLanguageUtils.getMessage("infReparacion.nomClient"),
		TDSLanguageUtils.getMessage("infReparacion.cognomClient"),
		TDSLanguageUtils.getMessage("infReparacion.nif"),
		TDSLanguageUtils.getMessage("infReparacion.dataAta"),
		TDSLanguageUtils.getMessage("infReparacion.actiu"),};
	private JTextField tbxCognom;



	/**
	 * Create the frame.
	 */
	public InformeEmpleados(GestorEstadisticaInterface gi) {
		gestorEstadistica = gi;
		setTitle(TDSLanguageUtils.getMessage("infEmpleado.titulo"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 263);
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
		lblNomUsuari.setBounds(312, 11, 117, 14);
		contentPane.add(lblNomUsuari);
		
		tbxIdUsuari = new JTextField();
		tbxIdUsuari.setBounds(157, 28, 80, 20);
		contentPane.add(tbxIdUsuari);
		tbxIdUsuari.setColumns(10);
		
		tbxNomUsuari = new JTextField();
		tbxNomUsuari.setBounds(312, 28, 155, 20);
		contentPane.add(tbxNomUsuari);
		tbxNomUsuari.setColumns(10);
		
		
		
		JButton btnSortir = new JButton(TDSLanguageUtils.getMessage("infEmpleado.btn.sortir"));
		btnSortir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSortir.setBounds(545, 199, 89, 23);
		contentPane.add(btnSortir);
		
		JLabel lblNHorasTrabajadas = new JLabel(TDSLanguageUtils.getMessage("infEmpleado.lbl.horesTreballades"));
		lblNHorasTrabajadas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNHorasTrabajadas.setBounds(52, 163, 131, 14);
		contentPane.add(lblNHorasTrabajadas);
		
		JLabel lblNReparacionesResueltas = new JLabel(TDSLanguageUtils.getMessage("infEmpleado.lbl.repRessoltes"));
		lblNReparacionesResueltas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNReparacionesResueltas.setBounds(52, 203, 168, 14);
		contentPane.add(lblNReparacionesResueltas);
		
		tbxHoresTrab = new JTextField();
		tbxHoresTrab.setBounds(221, 160, 86, 20);
		contentPane.add(tbxHoresTrab);
		tbxHoresTrab.setColumns(10);
		
		tbxRepRessolt = new JTextField();
		tbxRepRessolt.setBounds(221, 200, 86, 20);
		contentPane.add(tbxRepRessolt);
		tbxRepRessolt.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 92, 581, 50);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			null,
			columnNames
			
		));
		scrollPane.setViewportView(table);
		

		JButton btnSalir = new JButton(TDSLanguageUtils.getMessage("infReparacion.sortir"));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalir.setBounds(522, 416, 89, 23);
		contentPane.add(btnSalir);
		btnSalir.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				dispose();
			}
		});
		
		JLabel lblCognom = new JLabel("New label");
		lblCognom.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCognom.setBounds(519, 11, 46, 14);
		contentPane.add(lblCognom);
		
		tbxCognom = new JTextField();
		tbxCognom.setBounds(506, 28, 86, 20);
		contentPane.add(tbxCognom);
		tbxCognom.setColumns(10);
		
		
		JButton btnConsultar = new JButton(TDSLanguageUtils.getMessage("infEmpleado.btn.consultar"));
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConsultar.setBounds(276, 58, 89, 23);
		contentPane.add(btnConsultar);
		btnConsultar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//TODO:
				try{
				DecimalFormat df = new DecimalFormat("#.##");
//				GestorEstadisticaInterface gestorEstadistica = new GestorEstadisticaImpl();
				
				ArrayList<Usuari> usuaris = gestorEstadistica.obtenirEmpleats(tbxIdUsuari.getText(),tbxNomUsuari.getText(),tbxCognom.getText());
				rellenarTabla(table,usuaris);
				//tbxTMigEspera.setText(df.format(gestorEstadistica.calcularTempsMigEspera(reparacions)));
				//tbxTMigReparacio.setText(df.format(gestorEstadistica.calcularTempsMigReparacio(reparacions)));
				//tbxTMigFi.setText(df.format(gestorEstadistica.calcularTempsMigFinalitzacio(reparacions)));
				 
				}
				catch ( RemoteException e)//tbxDataAlta
				{
					//JOptionPane.showMessageDialog(, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (GestorEstadisticaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		});

	
		
	}

	
	
	
	private void rellenarTabla(JTable tabla, ArrayList<Usuari> usuaris){	//Obtener todos los datos de la tabla de reparación

		Object rowData [][] = new Object [usuaris.size()][6];
		
		int z=0;
		for (Usuari usuari : usuaris) {
					
			rowData[z][0] = String.valueOf(usuari.getId());
			rowData[z][1] = String.valueOf(usuari.getNom());
			rowData[z][2] = String.valueOf(usuari.getCognoms());
			rowData[z][3] = String.valueOf(usuari.getNif());
			rowData[z][4] = String.valueOf(usuari.getDataAlta());
			rowData[z][5] = String.valueOf(usuari.isActiu());
		
			
			z++;						
		}
		
		TableModel model = new DefaultTableModel(rowData, columnNames);
		
		tabla.setModel(model);
	}
		
	
	
	
	
	
	
	
	
}