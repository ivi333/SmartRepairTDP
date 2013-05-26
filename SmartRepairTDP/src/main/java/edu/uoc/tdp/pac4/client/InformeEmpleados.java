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
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
	private ListSelectionModel lsm;
	private final static String urlRMIEstad = new String("rmi://localhost/GestorEstadistica");
	
	private GestorEstadisticaInterface gestorEstadistica;
	
	private static final Object columnNames[] = {
		TDSLanguageUtils.getMessage("infEmpleado.idEmpleat"),
		TDSLanguageUtils.getMessage("infEmpleado.nomEmpleat"),
		TDSLanguageUtils.getMessage("infEmpleado.cognomEmpleat"),
		TDSLanguageUtils.getMessage("infEmpleado.nif"),
		TDSLanguageUtils.getMessage("infEmpleado.actiu")
		,};
	private JTextField tbxCognom;



	/**
	 * Create the frame.
	 */
	public InformeEmpleados(GestorEstadisticaInterface gi) {
		gestorEstadistica = gi;
		setTitle(TDSLanguageUtils.getMessage("infEmpleado.titulo"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbIdUsuari = new JLabel(TDSLanguageUtils.getMessage("infEmpleado.idEmpleat"));
		lbIdUsuari.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbIdUsuari.setBounds(157, 11, 105, 14);
		contentPane.add(lbIdUsuari);
		
		JLabel lblNomUsuari = new JLabel(TDSLanguageUtils.getMessage("infEmpleado.nomEmpleat"));
		lblNomUsuari.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNomUsuari.setBounds(312, 11, 155, 14);
		contentPane.add(lblNomUsuari);
		
		tbxIdUsuari = new JTextField();
		tbxIdUsuari.setBounds(157, 28, 105, 20);
		contentPane.add(tbxIdUsuari);
		tbxIdUsuari.setColumns(10);
		
		tbxNomUsuari = new JTextField();
		tbxNomUsuari.setBounds(312, 28, 155, 20);
		contentPane.add(tbxNomUsuari);
		tbxNomUsuari.setColumns(10);
		
		
		
		JButton btnSortir = new JButton(TDSLanguageUtils.getMessage("infEmpleado.btn.sortir"));
		btnSortir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSortir.setBounds(547, 310, 89, 23);
		contentPane.add(btnSortir);
		btnSortir.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				dispose();
			}
		});
		
		JLabel lblNHorasTrabajadas = new JLabel(TDSLanguageUtils.getMessage("infEmpleado.lbl.horesTreballades"));
		lblNHorasTrabajadas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNHorasTrabajadas.setBounds(39, 266, 259, 14);
		contentPane.add(lblNHorasTrabajadas);
		
		JLabel lblNReparacionesResueltas = new JLabel(TDSLanguageUtils.getMessage("infEmpleado.lbl.repRessoltes"));
		lblNReparacionesResueltas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNReparacionesResueltas.setBounds(39, 314, 250, 14);
		contentPane.add(lblNReparacionesResueltas);
		
		tbxHoresTrab = new JTextField();
		tbxHoresTrab.setBounds(308, 263, 86, 20);
		contentPane.add(tbxHoresTrab);
		tbxHoresTrab.setColumns(10);
		
		tbxRepRessolt = new JTextField();
		tbxRepRessolt.setBounds(308, 311, 86, 20);
		contentPane.add(tbxRepRessolt);
		tbxRepRessolt.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 92, 776, 153);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			null,
			columnNames
			
		));
		scrollPane.setViewportView(table);
		lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new ListSelectionListener() {			
			public void valueChanged(ListSelectionEvent arg0) {
				try{
					GestorEstadisticaInterface gestorEstadistica = new GestorEstadisticaImpl();
					
					if ( table.getSelectedRow() != -1)
					{
						int iIDMecanic = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
						tbxRepRessolt.setText(String.valueOf(gestorEstadistica.calcularNumRepRessoltes(iIDMecanic)));
						tbxHoresTrab.setText(String.valueOf(gestorEstadistica.calcularNumHoresRep(iIDMecanic)));
					}					 
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
		


		
		
		JLabel lblCognom = new JLabel(TDSLanguageUtils.getMessage("infEmpleado.cognomEmpleat"));
		lblCognom.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCognom.setBounds(519, 11, 147, 14);
		contentPane.add(lblCognom);
		
		tbxCognom = new JTextField();
		tbxCognom.setBounds(506, 28, 155, 20);
		contentPane.add(tbxCognom);
		tbxCognom.setColumns(10);
		
		
		JButton btnConsultar = new JButton(TDSLanguageUtils.getMessage("infEmpleado.btn.consultar"));
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConsultar.setBounds(322, 58, 131, 23);
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
				//tbxHoresTrab.setText(df.format(gestorEstadistica.calcularNumRepRessoltes(usuaris)));
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

		Object rowData [][] = new Object [usuaris.size()][5];
		
		int z=0;
		for (Usuari usuari : usuaris) {
					
			rowData[z][0] = String.valueOf(usuari.getId());
			rowData[z][1] = String.valueOf(usuari.getNom());
			rowData[z][2] = String.valueOf(usuari.getCognoms());
			rowData[z][3] = String.valueOf(usuari.getNif());
			rowData[z][4] = String.valueOf(usuari.isActiu());
		
			
			z++;						
		}
		
		TableModel model = new DefaultTableModel(rowData, columnNames);
		
		tabla.setModel(model);
	}
		
	
	
	
	
	
	
	
	
}