package edu.uoc.tdp.pac4.client;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class InformeReparaciones extends JFrame {

	private JPanel contentPane;
	private JTextField tbxIDReparacio;
	private JTextField tbxNomClient;
	private JTextField tbxNomMecanic;
	private JTextField tbxDataAlta;
	private JTextField tbxDataFi;
	private JTable table;
	private JTextField tbxTMigEspera;
	private JTextField tbxTMigReparacio;
	private JTextField tbxTMigFi;
	private JTextField tbxCognomClient;
	private JTextField tbxCognomMecanic;
	private DefaultTableModel modelo;
	private final static String urlRMIEstad = new String("rmi://localhost/GestorEstadistica");
	
	private GestorEstadisticaInterface gestorEstadistica;
	
	private static final Object columnNames[] = {
		TDSLanguageUtils.getMessage("infReparacion.ordreReparacio"),
		TDSLanguageUtils.getMessage("infReparacion.nomClient"),
		TDSLanguageUtils.getMessage("infReparacion.cognomClient"),
		TDSLanguageUtils.getMessage("infReparacion.nomMecanic"),
		TDSLanguageUtils.getMessage("infReparacion.cognomMecanic"),
		TDSLanguageUtils.getMessage("infReparacion.tipusReparacio"),
		TDSLanguageUtils.getMessage("infReparacion.horaInicio"),
		TDSLanguageUtils.getMessage("infReparacion.horaFin")};
	

	public InformeReparaciones(GestorEstadisticaInterface gi) {
		gestorEstadistica=gi;
		setTitle(TDSLanguageUtils.getMessage("infReparacion.titulo"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 659, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdReparacio = new JLabel(TDSLanguageUtils.getMessage("infReparacion.ordreReparacio"));
		lblIdReparacio.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIdReparacio.setBounds(10, 11, 94, 14);
		contentPane.add(lblIdReparacio);
		
		JLabel lblNomClient = new JLabel(TDSLanguageUtils.getMessage("infReparacion.nomClient"));
		lblNomClient.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNomClient.setBounds(114, 11, 94, 14);
		contentPane.add(lblNomClient);
		
		JLabel lblNombMecnic = new JLabel(TDSLanguageUtils.getMessage("infReparacion.nomMecanic"));
		lblNombMecnic.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombMecnic.setBounds(356, 11, 103, 14);
		contentPane.add(lblNombMecnic);
		
		JLabel lblTipoDeReparacion = new JLabel(TDSLanguageUtils.getMessage("infReparacion.tipusReparacio"));
		lblTipoDeReparacion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTipoDeReparacion.setBounds(135, 59, 118, 14);
		contentPane.add(lblTipoDeReparacion);
		
		JLabel lblDataInici = new JLabel(TDSLanguageUtils.getMessage("infReparacion.dataInici"));
		lblDataInici.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDataInici.setBounds(328, 59, 69, 14);
		contentPane.add(lblDataInici);
		
		JLabel lblDataFi = new JLabel(TDSLanguageUtils.getMessage("infReparacion.dataFi"));
		lblDataFi.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDataFi.setBounds(453, 59, 67, 14);
		contentPane.add(lblDataFi);
		
		tbxIDReparacio = new JTextField();
		tbxIDReparacio.setBounds(10, 28, 89, 20);
		contentPane.add(tbxIDReparacio);
		tbxIDReparacio.setColumns(10);
		
		tbxNomClient = new JTextField();
		tbxNomClient.setBounds(114, 28, 89, 20);
		contentPane.add(tbxNomClient);
		tbxNomClient.setColumns(10);
		
		tbxNomMecanic = new JTextField();
		tbxNomMecanic.setBounds(356, 28, 121, 20);
		contentPane.add(tbxNomMecanic);
		tbxNomMecanic.setColumns(10);
		
		
		final JComboBox comboTipusRep = new JComboBox();
		comboTipusRep.setBounds(135, 79, 103, 20);
		
		comboTipusRep.addItem("Totes");
		comboTipusRep.addItem("Rebuda");
		comboTipusRep.addItem("En Curs");
		comboTipusRep.addItem("En Espera");
		comboTipusRep.addItem("Rebutjada");
		comboTipusRep.addItem("Finalitzada");
		
		contentPane.add(comboTipusRep);
		

		JButton btnSalir = new JButton(TDSLanguageUtils.getMessage("infReparacion.sortir"));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalir.setBounds(522, 416, 89, 23);
		contentPane.add(btnSalir);
		btnSalir.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				dispose();
			}
		});

		
		JLabel lblCognomclient = new JLabel(TDSLanguageUtils.getMessage("infReparacion.cognomClient"));
		lblCognomclient.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCognomclient.setBounds(226, 11, 82, 14);
		contentPane.add(lblCognomclient);
		
		JLabel lblCognomMecanic = new JLabel(TDSLanguageUtils.getMessage("infReparacion.cognomMecanic"));
		lblCognomMecanic.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCognomMecanic.setBounds(510, 11, 101, 14);
		contentPane.add(lblCognomMecanic);
		
		tbxCognomClient = new JTextField();
		tbxCognomClient.setBounds(222, 28, 112, 20);
		contentPane.add(tbxCognomClient);
		tbxCognomClient.setColumns(10);
		
		tbxCognomMecanic = new JTextField();
		tbxCognomMecanic.setBounds(505, 28, 118, 20);
		contentPane.add(tbxCognomMecanic);
		tbxCognomMecanic.setColumns(10);

		tbxDataAlta = new JTextField();
		tbxDataAlta.setBounds(328, 79, 89, 20);
		contentPane.add(tbxDataAlta);
		tbxDataAlta.setColumns(10);
		
		tbxDataFi = new JTextField();
		tbxDataFi.setBounds(453, 79, 94, 20);
		contentPane.add(tbxDataFi);
		tbxDataFi.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 188, 581, 173);
		contentPane.add(scrollPane);
		
		table = new JTable();
		
		table.setModel(new DefaultTableModel(
			null,
			columnNames
		));
		scrollPane.setViewportView(table);
		
		JLabel lblTMigEspera = new JLabel(TDSLanguageUtils.getMessage("infReparacion.lbl.tempsMigEspera"));
		lblTMigEspera.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTMigEspera.setBounds(30, 375, 187, 14);
		contentPane.add(lblTMigEspera);
		
		JLabel lblTMigReparacio = new JLabel(TDSLanguageUtils.getMessage("infReparacion.lbl.tempsMigReparacio"));
		lblTMigReparacio.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTMigReparacio.setBounds(30, 400, 206, 14);
		contentPane.add(lblTMigReparacio);
		
		JLabel lblTMigFi = new JLabel(TDSLanguageUtils.getMessage("infReparacion.lbl.tempsMigFinalitzacio"));
		lblTMigFi.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTMigFi.setBounds(30, 425, 206, 14);
		contentPane.add(lblTMigFi);
		
		tbxTMigEspera = new JTextField();
		tbxTMigEspera.setBounds(246, 372, 86, 20);
		contentPane.add(tbxTMigEspera);
		tbxTMigEspera.setColumns(10);
		
		tbxTMigReparacio = new JTextField();
		tbxTMigReparacio.setBounds(246, 397, 86, 20);
		contentPane.add(tbxTMigReparacio);
		tbxTMigReparacio.setColumns(10);
		
		tbxTMigFi = new JTextField();
		tbxTMigFi.setBounds(246, 422, 86, 20);
		contentPane.add(tbxTMigFi);
		tbxTMigFi.setColumns(10);
		
		JButton btnConsultar = new JButton(TDSLanguageUtils.getMessage("infReparacion.btn.consultar"));
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConsultar.setBounds(246, 131, 118, 23);
		contentPane.add(btnConsultar);
		btnConsultar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//TODO:
				try{
				DecimalFormat df = new DecimalFormat("#.##");
//				GestorEstadisticaInterface gestorEstadistica = new GestorEstadisticaImpl();
				
				ArrayList<Reparacio> reparacions = gestorEstadistica.obtenirReparacions(tbxIDReparacio.getText(),tbxNomClient.getText(),tbxCognomClient.getText(),tbxNomMecanic.getText(), tbxCognomMecanic.getText(),comboTipusRep.getSelectedItem().toString(), tbxDataAlta.getText(), tbxDataFi.getText());
				rellenarTabla(table,reparacions);
				tbxTMigEspera.setText(df.format(gestorEstadistica.calcularTempsMigEspera(reparacions)));
				tbxTMigReparacio.setText(df.format(gestorEstadistica.calcularTempsMigReparacio(reparacions)));
				tbxTMigFi.setText(df.format(gestorEstadistica.calcularTempsMigFinalitzacio(reparacions)));
				 
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

	
	
	
	private void rellenarTabla(JTable tabla, ArrayList<Reparacio> reparacions){	//Obtener todos los datos de la tabla de reparación

		Object rowData [][] = new Object [reparacions.size()][8];
		
		int z=0;
		for (Reparacio reparacio : reparacions) {
					
			rowData[z][0] = String.valueOf(reparacio.getOrdreReparacio());
			rowData[z][1] = String.valueOf(reparacio.getSolicitud().getObjClient().getNom());
			rowData[z][2] = String.valueOf(reparacio.getSolicitud().getObjClient().getCognoms());
			rowData[z][3] = String.valueOf(reparacio.getMecanic().getNom());
			rowData[z][4] = String.valueOf(reparacio.getMecanic().getCognoms());
			rowData[z][5] = String.valueOf(reparacio.getEstatReparacio());
			rowData[z][6] = reparacio.prettyHoraInici();
			rowData[z][7] = reparacio.prettyHoraFi();
			
			z++;						
		}
		
		TableModel model = new DefaultTableModel(rowData, columnNames);
		
		tabla.setModel(model);
	}
		
	
	}

