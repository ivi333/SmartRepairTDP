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

import edu.uoc.tdp.pac4.beans.Reparacio;
import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import edu.uoc.tdp.pac4.exception.GestorEstadisticaException;
import edu.uoc.tdp.pac4.service.GestorEstadisticaImpl;
import edu.uoc.tdp.pac4.service.GestorEstadisticaInterface;




public class InformeClientes extends JFrame {

	private JPanel contentPane;
	private JTextField tbxNomClient;
	private JTextField tbxMarca;
	private JTextField tbxCompAsseguradora;
	private JTable table;

	private GestorEstadisticaInterface gestorEstadistica;
	
	private final static String urlRMIEstad = new String("rmi://localhost/GestorEstadistica");
	
	private static final Object columnNames[] = {
		TDSLanguageUtils.getMessage("infReparacion.ordreReparacio"),
		TDSLanguageUtils.getMessage("infReparacion.nomClient"),
		TDSLanguageUtils.getMessage("infReparacion.cognomClient"),
		TDSLanguageUtils.getMessage("infReparacion.nomMecanic"),
		TDSLanguageUtils.getMessage("infReparacion.cognomMecanic"),
	};
	private JTextField tbxOrdre;
	private JTextField tbxCognom;
	private JTextField tbxAsseg;
	
	
	
	public InformeClientes(GestorEstadisticaInterface gi) {
		gestorEstadistica = gi;
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
		
		tbxNomClient = new JTextField();
		tbxNomClient.setBounds(96, 28, 121, 20);
		contentPane.add(tbxNomClient);
		tbxNomClient.setColumns(10);
		
		tbxOrdre = new JTextField();
		tbxOrdre.setBounds(0, 28, 86, 20);
		contentPane.add(tbxOrdre);
		tbxOrdre.setColumns(10);
		
		JLabel lblOrdreR = new JLabel("New label");
		lblOrdreR.setBounds(10, 3, 46, 14);
		contentPane.add(lblOrdreR);
		
		tbxCognom = new JTextField();
		tbxCognom.setBounds(10, 93, 86, 20);
		contentPane.add(tbxCognom);
		tbxCognom.setColumns(10);
		
		JLabel lblCognom = new JLabel("New label");
		lblCognom.setBounds(10, 69, 46, 14);
		contentPane.add(lblCognom);
		
		tbxMarca = new JTextField();
		tbxMarca.setBounds(238, 28, 121, 20);
		contentPane.add(tbxMarca);
		tbxMarca.setColumns(10);
		
		tbxAsseg = new JTextField();
		tbxAsseg.setBounds(411, 28, 86, 20);
		contentPane.add(tbxAsseg);
		tbxAsseg.setColumns(10);
		
		JLabel lblAsseg = new JLabel("New label");
		lblAsseg.setBounds(411, 11, 46, 14);
		contentPane.add(lblAsseg);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 188, 581, 173);
		contentPane.add(scrollPane);
		
		table = new JTable();
		
		table.setModel(new DefaultTableModel(
			null,
			columnNames
		));
		scrollPane.setViewportView(table);
		
		
		JButton btnConsultar = new JButton(TDSLanguageUtils.getMessage("infCliente.btn.consultar"));
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConsultar.setBounds(266, 61, 89, 23);
		contentPane.add(btnConsultar);
		
	btnConsultar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try{
					DecimalFormat df = new DecimalFormat("#.##");
//					GestorEstadisticaInterface gestorEstadistica = new GestorEstadisticaImpl();
					
					ArrayList<Reparacio> r = gestorEstadistica.obtenirClients(tbxOrdre.getText(),tbxNomClient.getText(),tbxCognom.getText(),tbxMarca.getText(), tbxAsseg.getText());
					rellenarTabla(table,r);
				
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
				
				
				//TODO:
				//cargar GestorEstadisticaRIM g
				//ArrayList<Client> ar = g.capturarClientes(blablabla);
				//rellenarTabla(table, ar );
				
		

		btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConsultar.setBounds(246, 131, 118, 23);
		contentPane.add(btnConsultar);
		
		
		
		
		
	}
	
	private void rellenarTabla (JTable table , ArrayList<Reparacio> ar){
		float fImporte ;
		Reparacio auxr = new Reparacio();

		/*for ( Reparacio rep : ar )
		{
			fImporte = 0 ;
			
			//for ( Comanda com : com.getReparacio().getComanda.getComandas())
			for ( Comanda com : auxr.getComanda())
			{
				fImporte += com.getNumPeces() * com.getPeca().getPVP();
			}*/
			
			Object rowData [][] = new Object [ar.size()][8];
			
			int z=0;
			for (Reparacio reparacio : ar) {
						
				rowData[z][0] = String.valueOf(reparacio.getOrdreReparacio());
				rowData[z][1] = String.valueOf(reparacio.getSolicitud().getObjClient().getNom());
				rowData[z][2] = String.valueOf(reparacio.getSolicitud().getObjClient().getCognoms());
				rowData[z][3] = String.valueOf(reparacio.getSolicitud().getObjClient().getMarca());
				rowData[z][4] = String.valueOf(reparacio.getSolicitud().getObjClient().getIdasseguradora());
				
				z++;						
			}
			
			TableModel model = new DefaultTableModel(rowData, columnNames);
			
			table.setModel(model);
			
			
			//Rellena campo rep.GetNumReparacio()
			//Rellena campo rep.getSolicitud().getClient().getNom()
			//Rellena campo rep.getSolicitud().getClient().gerMrca()
			//Rellena campo Compañía
			//Rellena fImporte			
		}
	}//empleados crear consulta por rep cliente en dao y hacer calculos en service*/

