package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import edu.uoc.tdp.pac4.service.GestorAdministracionImpl;
import edu.uoc.tdp.pac4.service.GestorAdministracionInterface;
import edu.uoc.tdp.pac4.service.GestorConexionImpl;
import edu.uoc.tdp.pac4.service.GestorConexionInterface;
import edu.uoc.tdp.pac4.service.GestorEstadisticaImpl;
import edu.uoc.tdp.pac4.service.GestorEstadisticaInterface;
import edu.uoc.tdp.pac4.service.GestorReparacionImpl;
import edu.uoc.tdp.pac4.service.GestorReparacionInterface;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ReparacionMainMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JFrame frmSmartRepairTdp;

	private GestorConexionInterface gestorConexion;
	private GestorAdministracionInterface gestorAdministracion;
	private GestorReparacionInterface gestorReparacion;
	private GestorEstadisticaInterface gestorEstadistica;
	
	private final String urlRMIConex = new String("rmi://localhost/GestorConexion");
	private final String urlRMIAdmin = new String("rmi://localhost/GestorAdministracion");
	private final String urlRMIRepar = new String("rmi://localhost/GestorReparacion");
	private final String urlRMIEstad = new String("rmi://localhost/GestorEstadistica");
	private Usuari usuari;
	
	private Login login;
	
	private JMenu mnNewMenu;
	private JMenu mnNewMenu_1;
	private JMenu mnNewMenu_2;
	private JMenu mnNewMenu_3;
	private JMenuItem mntmReparacinAsignada;
	private JMenuItem mntmGestinReparaciones;
	private JMenuItem mntmStockPiezas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TDSLanguageUtils.setDefaultLanguage("i18n/messages");
					ReparacionMainMenu window = new ReparacionMainMenu();					
					window.frmSmartRepairTdp.setVisible(true);
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					window.frmSmartRepairTdp.setSize(500, 300);
					window.frmSmartRepairTdp.setLocation(dim.width/2-window.frmSmartRepairTdp.getSize().width/2, 
														dim.height/2-window.frmSmartRepairTdp.getHeight()/2);
					window.frmSmartRepairTdp.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ReparacionMainMenu() {
		initialize();

		try{
			doRegistry ();		
			winLogin();
			doLogin();		
			disableMenu();
			if (usuari!= null){
				enableMenu ();
			}
		}catch (Exception e){
			disableMenu();
			JOptionPane.showMessageDialog(this, TDSLanguageUtils.getMessage("mensaje.cliente.noconec"), "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSmartRepairTdp = new JFrame();
		frmSmartRepairTdp.setResizable(false);
		frmSmartRepairTdp.setTitle("Smart Repair TDP 2013 - FiveCoreDumped");
		frmSmartRepairTdp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmSmartRepairTdp.setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Mantenimiento");
		mnNewMenu.setEnabled(true);
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Gestión de Talleres");		
		mnNewMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				GestionTalleres gestionTalleres = new GestionTalleres (gestorConexion);								
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				gestionTalleres.setLocation(dim.width/2-gestionTalleres.getSize().width/2, dim.height/2-gestionTalleres.getSize().height/2);
				gestionTalleres.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gestionTalleres.setVisible(true);
				gestionTalleres.setAlwaysOnTop(true);

			}
		});
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Gestión de Usuarios");
		mntmNewMenuItem.setEnabled(true);
		mnNewMenu.add(mntmNewMenuItem_1);
		
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				GestionUsuarios gestionUsuarios = new GestionUsuarios (gestorConexion);				
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				gestionUsuarios.setLocation(dim.width/2-gestionUsuarios.getSize().width/2, dim.height/2-gestionUsuarios.getSize().height/2);
				gestionUsuarios.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gestionUsuarios.setVisible(true);
				gestionUsuarios.setAlwaysOnTop(true);

			}
		});
		
		mnNewMenu_1 = new JMenu("Administración");
		mnNewMenu_1.setEnabled(true);
		menuBar.add(mnNewMenu_1);
		
		mnNewMenu_2 = new JMenu("Reparaciones");
		mnNewMenu_2.setEnabled(true);
		menuBar.add(mnNewMenu_2);
		
		mntmReparacinAsignada = new JMenuItem("Reparación asignada");
		mntmReparacinAsignada.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				ReparacionAsignadas dialog = new ReparacionAsignadas(gestorReparacion, usuari);
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				dialog.setSize(1000, 500);
				dialog.setLocation(dim.width/2-dialog.getSize().width/2, dim.height/2-dialog.getSize().height/2);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmReparacinAsignada);
		
		mntmGestinReparaciones = new JMenuItem("Gestión reparaciones");
		mntmGestinReparaciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ReparacionGestion dialog = new ReparacionGestion(gestorReparacion, usuari);
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				dialog.setSize(1000, 500);
				dialog.setLocation(dim.width/2-dialog.getSize().width/2, dim.height/2-dialog.getSize().height/2);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmGestinReparaciones);
		
		mntmStockPiezas = new JMenuItem("Stock Piezas");
		mntmStockPiezas.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				ReparacionStock dialog = new ReparacionStock(gestorReparacion, usuari);
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				dialog.setSize(1000, 500);
				dialog.setLocation(dim.width/2-dialog.getSize().width/2, dim.height/2-dialog.getSize().height/2);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmStockPiezas);
		
		mnNewMenu_3 = new JMenu("Estadísticas");
		mnNewMenu_3.setEnabled(true);
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Salir");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSmartRepairTdp.dispose();
				System.exit(0);
			}
		});
		menuBar.add(mntmNewMenuItem_2);
		
		JLabel jLabel = new JLabel("Menu Principal Smart Repair", SwingConstants.CENTER);
		jLabel.setFont(new Font("Serif", Font.BOLD, 24));
		
		frmSmartRepairTdp.getContentPane().add(jLabel, BorderLayout.CENTER);

	}

	private void doRegistry() throws MalformedURLException, RemoteException, NotBoundException  {
		gestorConexion = (GestorConexionInterface) Naming.lookup(urlRMIConex);
		gestorAdministracion = (GestorAdministracionInterface) Naming.lookup(urlRMIAdmin);
		gestorReparacion = (GestorReparacionInterface) Naming.lookup(urlRMIRepar);
		gestorEstadistica = (GestorEstadisticaInterface) Naming.lookup(urlRMIEstad);

	}
	
	private void winLogin(){
		login = new Login (this.frmSmartRepairTdp,gestorConexion);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		login.setSize(450, 300);
		login.setLocation(dim.width/2-login.getSize().width/2, dim.height/2-login.getSize().height/2);
		login.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		login.setModal(true);
		login.setVisible(true);
		login.setAlwaysOnTop(true);
		
	}
	
	private void doLogin (){
		if (login.isLogin()) 
			usuari = login.getUsuari();			
	}

	private void enableMenu (){

		/*Administracion, JefeTaller, Administrador, Mecanic;*/
		if (usuari.getPerfil().equalsIgnoreCase("Administrador")){
			mnNewMenu.setEnabled(true);
			mnNewMenu.setVisible(true);		
		}else if (usuari.getPerfil().equalsIgnoreCase("Administracion")){
			mnNewMenu_1.setEnabled(true);
			mnNewMenu_1.setVisible(true);
		} else if (usuari.getPerfil().equalsIgnoreCase("JefeTaller")) {
			mnNewMenu_2.setEnabled(true);
			mnNewMenu_2.setVisible(true);
		} else if (usuari.getPerfil().equalsIgnoreCase("Mecanic")){
			mnNewMenu_2.setEnabled(true);
			mnNewMenu_2.setVisible(true);
		}
		mnNewMenu_2.setEnabled(true);
		mnNewMenu_2.setVisible(true);
		mnNewMenu_3.setEnabled(true);
		mnNewMenu_3.setVisible(true);
	}
	
	private void disableMenu () {
		mnNewMenu.setEnabled(false);
		mnNewMenu.setVisible(false);
		mnNewMenu_1.setEnabled(false);
		mnNewMenu_1.setVisible(false);
		mnNewMenu_2.setEnabled(false);
		mnNewMenu_2.setVisible(false);
		mnNewMenu_3.setEnabled(false);
		mnNewMenu_3.setVisible(false);
	}
}
