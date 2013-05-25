package edu.uoc.tdp.pac4.client;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import edu.uoc.tdp.pac4.beans.Client;
import edu.uoc.tdp.pac4.beans.Reparacio;
import edu.uoc.tdp.pac4.beans.Solicitud;
import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import edu.uoc.tdp.pac4.service.GestorAdministracionInterface;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;

public class GestionAvisos extends JDialog {
	private static int port = 1099;
	 private final static String urlRMIAdmin = new String("rmi://localhost/GestorAdministracion");
	private JPanel contentPane;
	private JButton btnSalir;
	private JButton btnGestionar;
	private JTable jTableResultado = new JTable();
	private JScrollPane scrollPaneResultado;
	private static GestorAdministracionInterface conexionRemota;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionAvisos frame = new GestionAvisos();
					frame.setLocationRelativeTo(null);
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
	public GestionAvisos() {
		getContentPane().setLayout(null);

		try {
			seleccionIdioma();
			initialize();
			CargarControles();

			GruposTableModel gtm = (GruposTableModel) jTableResultado
					.getModel();
			gtm.addRow(new Object[] { "", "", "", "" });

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public GestionAvisos(GestorAdministracionInterface remoto) {
		getContentPane().setLayout(null);

		try {
			seleccionIdioma();
			initialize();
			conexionRemota=remoto; 
			CargarControles();

			GruposTableModel gtm = (GruposTableModel) jTableResultado
					.getModel();
			gtm.addRow(new Object[] { "", "", "", "" });

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void initialize() {
		setSize(new Dimension(912, 250));
	}

	private void seleccionIdioma() {
		Locale localLocale = new Locale("", "");
		//TDSLanguageUtils.setDefaultLanguage("i18n/messages");
	}
	

	private void LeerError(String paramString1, String paramString2) {
		JOptionPane.showMessageDialog(this, paramString1, paramString2, 0);
	}
	
   private void MuestraOk(String paramString1, String paramString2) {
	        JOptionPane.showMessageDialog(this, paramString1, paramString2, 1);
	    }

	private void CargarControles() {
		setTitle(TDSLanguageUtils.getMessage("aviso.titulo"));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		contentPane.add(getbtnGestionar());

		contentPane.add(getbtnSalirJ());

		scrollPaneResultado = new JScrollPane();
		scrollPaneResultado.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneResultado.setViewportBorder(UIManager
				.getBorder("ComboBox.border"));
		scrollPaneResultado.setBounds(new Rectangle(25, 11, 869, 150));
		this.jTableResultado.setModel(new GruposTableModel());
		
		jTableResultado.getColumnModel().getColumn(0).setPreferredWidth(40);
		jTableResultado.getColumnModel().getColumn(1).setPreferredWidth(60);
		jTableResultado.getColumnModel().getColumn(2).setPreferredWidth(60);
		jTableResultado.getColumnModel().getColumn(3).setPreferredWidth(60);
		jTableResultado.getColumnModel().getColumn(4).setPreferredWidth(80);
		jTableResultado.getColumnModel().getColumn(5).setPreferredWidth(45);
		jTableResultado.getColumnModel().getColumn(6).setPreferredWidth(65);
		jTableResultado.getColumnModel().getColumn(7).setPreferredWidth(80);
		jTableResultado.getColumnModel().getColumn(8).setPreferredWidth(80);
		jTableResultado.getColumnModel().getColumn(9).setPreferredWidth(80);
		scrollPaneResultado.setViewportView(jTableResultado);
		getContentPane().add(this.scrollPaneResultado, null);

	}

	private JButton getbtnSalirJ() {
		if (btnSalir == null) {
			btnSalir = new JButton();
			btnSalir.setForeground(Color.RED);
			btnSalir.setBounds(new Rectangle(764, 172, 89, 23));
			btnSalir.setText(TDSLanguageUtils.getMessage("aviso.btn.salir"));
			btnSalir.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();

				}
			});
		}
		return btnSalir;
	}

	private JButton getbtnGestionar() {
		if (btnGestionar == null) {
			btnGestionar = new JButton();
			btnGestionar.setForeground(new Color(0, 128, 0));
			btnGestionar.setBounds(new Rectangle(35, 172, 89, 23));
			btnGestionar.setText(TDSLanguageUtils
					.getMessage("aviso.btn.gestionar"));
			btnGestionar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getDadesGestion();

				}
			});
		}
		return btnGestionar;
	}
	
	private void getDadesGestion()
	{
		try{
			
			GruposTableModel gtm1 = (GruposTableModel) jTableResultado.getModel();
			if (gtm1.getRowCount() > 0) {
			    for (int i = gtm1.getRowCount() - 1; i > -1; i--) {
			    	gtm1.removeRow(i);
			    }
			}

			
			GruposTableModel gtm = (GruposTableModel) jTableResultado.getModel();
			ArrayList<Reparacio>Reparaciones = conexionRemota.getReparaciones();
			
			if(Reparaciones!=null)
			{
			
			for(Reparacio r :Reparaciones)
			{
				Solicitud sol =new Solicitud();
				Client cli=new Client();
				
				sol=conexionRemota.getSolicitudByCodeReparacion(r.getOrdreReparacio());
				int numsol=0;
				boolean bPendiente=false;
				boolean bFinalizada=false;
				String nom="";
				String Cognom="";
				String nif="";
				if(sol!=null)
				{
					numsol=	sol.getNumsol();
					bPendiente=	sol.isPendent();
					bFinalizada=sol.isFinalitzada();
					
					cli=conexionRemota.getDadeClient(String.valueOf(sol.getClient()));
					if(cli!=null)
					{
						nom=cli.getNom();
						Cognom=cli.getCognoms();
						nif=	cli.getNif();
					}
				}
				
				String strDateAsig=strDate(r.getDataAssignacio().toString());
				String strDateIni=strDate(r.getDataInici().toString());
				String strDateFin=strDate(r.getDataFi().toString());
				
				
				gtm.addRow(new Object[] {numsol,bPendiente,bFinalizada,nom,Cognom,nif,r.getObservacions(),strDateAsig,strDateIni,strDateFin});
			}
		
			}
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	private String strDate(String strDate)
	{
		String str="";
		try{
			String dateString1 = strDate;
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString1);
			str = new SimpleDateFormat("dd-MM-yyyy").format(date);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			str=strDate;
		}
		return str;
	}

	private static class GruposTableModel extends DefaultTableModel {

		private static final long serialVersionUID = 1L;
		
		public GruposTableModel() {
			super(new String[] {
					"Núm.", //
					TDSLanguageUtils.getMessage("aviso.lbl.estadoPendiente"), //
					TDSLanguageUtils.getMessage("aviso.lbl.estadoFinalizado"), //
					//TDSLanguageUtils.getMessage("aviso.lbl.poliza"),
					TDSLanguageUtils.getMessage("aviso.lbl.nombre"),
					TDSLanguageUtils.getMessage("aviso.lbl.apellido"),
					TDSLanguageUtils.getMessage("aviso.lbl.nif"),
					TDSLanguageUtils.getMessage("aviso.lbl.detalle.reparacion"),
					TDSLanguageUtils.getMessage("aviso.lbl.reparacion.fechaAsig"),
					TDSLanguageUtils.getMessage("aviso.lbl.reparacion.fechainicio"),
					TDSLanguageUtils.getMessage("aviso.lbl.reparacion.fechafin"),
					//
			}, 0);// numFilas); //
		}

		@Override
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	}

}
