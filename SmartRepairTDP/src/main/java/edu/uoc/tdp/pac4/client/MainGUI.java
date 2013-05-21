package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.Component;
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

import edu.uoc.tdp.pac4.beans.PerfilUsuari;
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


public class MainGUI extends JFrame {

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
	
	private JMenu mnMantenimiento;
	private JMenu mnAdministracion;
	private JMenu mnReparacion;
	private JMenu mnEstadistica;
	private JMenuItem mntmSalir;
	private JMenuBar menuBar;
	
	private String perfiles[];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TDSLanguageUtils.setDefaultLanguage("i18n/messages");
					MainGUI window = new MainGUI();					
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
	public MainGUI() {
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
		
		menuBar = new JMenuBar();
		frmSmartRepairTdp.setJMenuBar(menuBar);
		
		mnMantenimiento = new JMenu("Mantenimiento");
		mnMantenimiento.setEnabled(true);
		menuBar.add(mnMantenimiento);
		
		JMenuItem mntmMantTaller = new JMenuItem("Gestión de Talleres");		
		mnMantenimiento.add(mntmMantTaller);
		mntmMantTaller.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				GestionTalleres gestionTalleres = new GestionTalleres (gestorConexion);								
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				gestionTalleres.setLocation(dim.width/2-gestionTalleres.getSize().width/2, dim.height/2-gestionTalleres.getSize().height/2);
				gestionTalleres.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gestionTalleres.setVisible(true);

			}
		});
		
		JMenuItem mntmMantUsuario = new JMenuItem("Gestión de Usuarios");
		mntmMantUsuario.setEnabled(true);
		mnMantenimiento.add(mntmMantUsuario);
		
		mntmMantUsuario.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("ENTRA");
				GestionUsuarios gestionUsuarios = new GestionUsuarios (gestorConexion);				
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				gestionUsuarios.setLocation(dim.width/2-gestionUsuarios.getSize().width/2, dim.height/2-gestionUsuarios.getSize().height/2);
				gestionUsuarios.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gestionUsuarios.setVisible(true);

			}
		});
		
		mnAdministracion = new JMenu("Administración");
		mnAdministracion.setEnabled(true);
		menuBar.add(mnAdministracion);
		
		mnReparacion = new JMenu("Reparaciones");
		mnReparacion.setEnabled(true);
		menuBar.add(mnReparacion);
		
		mnEstadistica = new JMenu("Estadísticas");
		mnEstadistica.setEnabled(true);
		menuBar.add(mnEstadistica);
		
		mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSmartRepairTdp.dispose();
				System.exit(0);
			}
		});
		menuBar.add(mntmSalir);
		
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
		if (login.isLogin()) {
			usuari = login.getUsuari();
			perfiles = usuari.getPerfil().split(";");
		}
	}

	private void enableMenu (){
		// TODO Pendiente montar menus segun perfil
		for (String a : perfiles){
			if (a.equals(PerfilUsuari.Administrador.toString())) {
				for (Component i : mnMantenimiento.getComponents()) {
					i.setEnabled(true);
					i.setVisible(true);
				}
				mnMantenimiento.setEnabled(true);
				mnMantenimiento.setVisible(true);
			} else if (a.equals(PerfilUsuari.Administracion.toString())) {
				mnAdministracion.setEnabled(true);
				mnAdministracion.setVisible(true);
				
			} else if (a.equals(PerfilUsuari.JefeTaller.toString())) {
				mnReparacion.setEnabled(true);
				mnReparacion.setVisible(true);
				
			} else if (a.equals(PerfilUsuari.Mecanico.toString())) {
				mnReparacion.setEnabled(true);
				mnReparacion.setVisible(true);
				
			}
		}
	}
	
	private void disableMenu () {
		
		for (Component i : mnMantenimiento.getComponents()) {
			i.setEnabled(false);
			i.setVisible(false);
		}
		for (Component i : mnAdministracion.getComponents()) {
			i.setEnabled(false);
			i.setVisible(false);
		}	
		for (Component i : mnReparacion.getComponents()) {
			i.setEnabled(false);
			i.setVisible(false);
		}
		for (Component i : mnEstadistica.getComponents()) {
			i.setEnabled(false);
			i.setVisible(false);
		}
		
		mnMantenimiento.setEnabled(false);
		mnMantenimiento.setVisible(false);
		mnAdministracion.setEnabled(false);
		mnAdministracion.setVisible(false);
		mnReparacion.setEnabled(false);
		mnReparacion.setVisible(false);
		mnEstadistica.setEnabled(false);
		mnEstadistica.setVisible(false);
	
	}
}
