package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import edu.uoc.tdp.pac4.beans.Taller;
import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.client.MenuGestionAdmin.OptionFrame;
import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import edu.uoc.tdp.pac4.service.GestorAdministracionInterface;
import edu.uoc.tdp.pac4.service.GestorConexionInterface;
import edu.uoc.tdp.pac4.service.GestorEstadisticaInterface;
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
	private Taller taller;
	private Login login;
	private JLabel jLabelMain;
	
	private JMenuBar menuBar;
	
	private String perfiles[];
	private boolean isAdministrador = false;
	boolean isAdministrativo = false; 
	boolean isJefeTaller = false; 
	boolean isMecanico = false;

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
			if (usuari!= null){	
				if (isAdministrador) {
					enableMenu(isAdministrador, isAdministrativo, isJefeTaller, isMecanico);
				} else {
					taller = gestorConexion.getTallerById(usuari.getTaller());
					if (taller.isActiu()) {
						enableMenu(isAdministrador, isAdministrativo, isJefeTaller, isMecanico);
					} else {
						JOptionPane.showMessageDialog(this, 
								TDSLanguageUtils.getMessage("maingui.msg.tallernoactivo"), 
								TDSLanguageUtils.getMessage("GESCON.showmessage.aviso"), 
								JOptionPane.WARNING_MESSAGE);
					}
				}
				
			}
		}catch (Exception e){
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
		
		jLabelMain = new JLabel("Menu Principal Smart Repair", SwingConstants.CENTER);
		jLabelMain.setFont(new Font("Serif", Font.BOLD, 24));
		
		frmSmartRepairTdp.getContentPane().add(jLabelMain, BorderLayout.CENTER);

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
			for (String perfil : perfiles) {
				if (perfil.equals(PerfilUsuari.Administrador.toString())){
					isAdministrador = true;
				} else if (perfil.equals(PerfilUsuari.JefeTaller.toString())) {
					isJefeTaller = true;
				} else if (perfil.equals(PerfilUsuari.Mecanico.toString())) {
					isMecanico = true;
				} else if (perfil.equals(PerfilUsuari.Administracion.toString())) {
					isAdministrativo = true;
				}
			}
		} else {
			crearMenuLanguageSalir ();
		}
	}

	private void enableMenu (boolean isAdministrador, boolean isAdministrativo, boolean isJefeTaller, boolean isMecanico){
		boolean loadPerfilOK=false;
		
		if (isAdministrador) {
			crearMenuMantenimiento ();				
			jLabelMain.setText("<html>Bienvenido al subsistema de Mantenimiento. <br> (Administradores)</html>");
			loadPerfilOK=true;
		} 
		
		if (isAdministrativo  || isJefeTaller) {
			crearMenuAdministrativos(isAdministrador, isAdministrativo, isJefeTaller, isMecanico);			
			crearMenuEstadisticas (isAdministrador, isAdministrativo, isJefeTaller, isMecanico);
			jLabelMain.setText("<html>Subsistema de Administracion y <br> Estadisticas. <br> (Administrativos)</html>");			
			loadPerfilOK=true;
		}
		
		if (!isJefeTaller && isMecanico){
			crearMenuReparaciones(isAdministrador, isAdministrativo, isJefeTaller, isMecanico);			
			jLabelMain.setText("<html> Bienvenido al subsistema de Reparaciones. (Mecanicos) </html>");
			loadPerfilOK=true;
		} else if (isJefeTaller && !isMecanico) {
			crearMenuReparaciones(isAdministrador, isAdministrativo, isJefeTaller, isMecanico);
			crearMenuEstadisticas(isAdministrador, isAdministrativo, isJefeTaller, isMecanico);
			jLabelMain.setText("<html>Bienvenido al subsistema Administrativo y Estadisticas<br> (Jefe de Taller)</html>");
			loadPerfilOK=true;
		} else if (isJefeTaller && isMecanico) {
			crearMenuReparaciones(isAdministrador, isAdministrativo, isJefeTaller, isMecanico);
			crearMenuEstadisticas(isAdministrador, isAdministrativo, isJefeTaller, isMecanico);
			jLabelMain.setText("<html>Bienvenido al subsistema Administrativo y Estadisticas<br> (Jefe de Taller)</html>");
			loadPerfilOK=true;
		} 
		if (!loadPerfilOK) {
			//No se mostrara ningun subsistema, solo la opcion de Salir
			//El usuario no está dentro de los perfiles permitidos
			JOptionPane.showMessageDialog(this, 
					TDSLanguageUtils.getMessage("mensaje.cliente.perfilerror"), 
					TDSLanguageUtils.getMessage("GESCON.showmessage.aviso"), 
					JOptionPane.WARNING_MESSAGE);
		}
		
		crearMenuLanguageSalir ();
	}
	
	private void crearMenuEstadisticas (boolean isAdministrador, boolean isAdministrativo, boolean isJefeTaller, boolean isMecanico) {
		JMenu mnInformes = new JMenu("Informes");
		menuBar.add(mnInformes);
		if (isAdministrativo) {
			JMenuItem mntmInfClientes = new JMenuItem("Informe Clientes");
			mnInformes.add(mntmInfClientes);
		}
		
		if (isAdministrativo || isJefeTaller) {
			JMenuItem mntmInfEmpleados = new JMenuItem("Informe Empleados");
			mnInformes.add(mntmInfEmpleados);
		}
		
		if (isAdministrativo || isJefeTaller) {		
			JMenuItem mntmInfReparaciones = new JMenuItem("Informe Reparaciones");
			mnInformes.add(mntmInfReparaciones);
		}
		
	}
	
	private void crearMenuReparaciones (boolean isAdministrador, boolean isAdministrativo, boolean isJefeTaller, boolean isMecanico) {

		JMenu mnNewMenu_2 = new JMenu("Reparaciones");
		mnNewMenu_2.setEnabled(true);
		menuBar.add(mnNewMenu_2);
		
		if (isMecanico || isJefeTaller) {
			JMenuItem mntmReparacinAsignada = new JMenuItem("Reparación asignada");
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
		}
		
		if (isJefeTaller || isMecanico) {
			JMenuItem mntmGestinReparaciones = new JMenuItem("Gestión reparaciones");
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
		}
	
		if (isJefeTaller) {
			JMenuItem mntmStockPiezas = new JMenuItem("Stock Piezas");
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
		}

	}
	
	private void crearMenuMantenimiento () {
		JMenu mnMantenimiento = new JMenu("Mantenimiento");
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
		
	}
	
	private void crearMenuAdministrativos (boolean isAdministrador, boolean isAdministrativo, boolean isJefeTaller, boolean isMecanico) {
		JMenu jMenuClientes;
		JMenu jMenuSoli;
		JMenu jMenuAvisos;
		JMenu jMenuAlmacen;

		JMenuItem jMenuItemAltaCliente;
		JMenuItem jMenuItemConsulCliente;

		JMenuItem jMenuItemAltaSol;
		JMenuItem jMenuItemConsulSol;
		JMenuItem jMenuItemBajaSol;

		JMenuItem jMenuItemGestionAvisos;
		JMenuItem jMenuItemPedidoAlmacen;

		if (isAdministrativo) {
	
			// ITEM 1
			jMenuClientes = new JMenu();
			menuBar.add(jMenuClientes);
			jMenuClientes.setText(TDSLanguageUtils
					.getMessage("menu.gestionAdmin.clientes"));
			jMenuItemAltaCliente = new JMenuItem();
			jMenuClientes.add(jMenuItemAltaCliente);
			jMenuItemAltaCliente.setText(TDSLanguageUtils
					.getMessage("menu.gestionAdmin.alta"));
			jMenuItemAltaCliente.addActionListener(new ActionListener() {
				public void actionPerformed(
						ActionEvent paramAnonymousActionEvent) {
							OpenFrm_Option(OptionFrame.Frm_NewCliente);
				}
			});
	
			jMenuItemConsulCliente = new JMenuItem();
			jMenuClientes.add(jMenuItemConsulCliente);
			jMenuItemConsulCliente.setText(TDSLanguageUtils
					.getMessage("menu.gestionAdmin.consulta"));
			jMenuItemConsulCliente.addActionListener(new ActionListener() {
				public void actionPerformed(
						ActionEvent paramAnonymousActionEvent) {
							OpenFrm_Option(OptionFrame.Frm_UpdCliente);
				}
			});
	
			// ITEM 2
			jMenuSoli = new JMenu();
			menuBar.add(jMenuSoli);
			jMenuSoli.setText(TDSLanguageUtils
					.getMessage("menu.gestionAdmin.solicitudes"));
			jMenuSoli.setVisible(true);
			jMenuSoli.setEnabled(true);
			jMenuItemAltaSol = new JMenuItem();
			jMenuSoli.add(jMenuItemAltaSol);
			jMenuItemAltaSol.setText(TDSLanguageUtils
					.getMessage("menu.gestionAdmin.alta"));
			jMenuItemAltaSol.addActionListener(new ActionListener() {
				public void actionPerformed(
						ActionEvent paramAnonymousActionEvent) {
							OpenFrm_Option(OptionFrame.Frm_NewSol);
				}
			});
	
			jMenuItemConsulSol = new JMenuItem();
			jMenuSoli.add(jMenuItemConsulSol);
			jMenuItemConsulSol.setText(TDSLanguageUtils
					.getMessage("menu.gestionAdmin.consulta"));
			jMenuItemConsulSol.addActionListener(new ActionListener() {
				public void actionPerformed(
						ActionEvent paramAnonymousActionEvent) {
							OpenFrm_Option(OptionFrame.Frm_UpdSol);
				}
			});
			jMenuItemBajaSol = new JMenuItem();
			jMenuSoli.add(jMenuItemBajaSol);
			jMenuItemBajaSol.setText(TDSLanguageUtils
					.getMessage("menu.gestionAdmin.baja"));
			jMenuItemBajaSol.addActionListener(new ActionListener() {
				public void actionPerformed(
						ActionEvent paramAnonymousActionEvent) {
							OpenFrm_Option(OptionFrame.Frm_DeleteSol);
				}
			});
	
			// ITEM 3
	
			jMenuAvisos = new JMenu();
			menuBar.add(jMenuAvisos);
			jMenuAvisos.setText(TDSLanguageUtils
					.getMessage("menu.gestionAdmin.avisos"));
	
			jMenuItemGestionAvisos = new JMenuItem();
			jMenuAvisos.add(jMenuItemGestionAvisos);
			jMenuItemGestionAvisos.setText(TDSLanguageUtils
					.getMessage("menu.gestionAdmin.gestion"));
			jMenuItemGestionAvisos.addActionListener(new ActionListener() {
				public void actionPerformed(
						ActionEvent paramAnonymousActionEvent) {
							OpenFrm_Option(OptionFrame.Frm_Gestion);
				}
			});
		}
		// ITEM 4
		if (isJefeTaller) {
			jMenuAlmacen = new JMenu();
			menuBar.add(jMenuAlmacen);
			jMenuAlmacen.setText(TDSLanguageUtils
					.getMessage("menu.gestionAdmin.almacen"));

		
			jMenuItemPedidoAlmacen = new JMenuItem();
			jMenuAlmacen.add(jMenuItemPedidoAlmacen);
			jMenuItemPedidoAlmacen.setText(TDSLanguageUtils
					.getMessage("menu.gestionAdmin.recepcion"));
	
			jMenuItemPedidoAlmacen.addActionListener(new ActionListener() {
				public void actionPerformed(
						ActionEvent paramAnonymousActionEvent) {
							OpenFrm_Option(OptionFrame.Frm_Recepcion);
				}
			});
		}
	
	}

	private void crearMenuLanguageSalir () {
		/*JMenu jMenuLenguages = new JMenu();
		menuBar.add(jMenuLenguages);
		jMenuLenguages.setText(TDSLanguageUtils
				.getMessage("menu.general.idioma"));
		
		JMenuItem jm1 = new JMenuItem();
		jm1.setText(TDSLanguageUtils
				.getMessage("menu.general.ca"));

		JMenuItem jm2 = new JMenuItem();
		jm2.setText(TDSLanguageUtils
				.getMessage("menu.general.es"));
		
		JMenuItem jm3 = new JMenuItem();
		jm3.setText(TDSLanguageUtils
				.getMessage("menu.general.en"));
		

		
		jMenuLenguages.add(jm1);
		jMenuLenguages.add(jm2);
		jMenuLenguages.add(jm3);
		*/
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSmartRepairTdp.dispose();
				System.exit(0);
			}
		});
		menuBar.add(mntmSalir);
	}
	
	private void OpenFrm_Option(OptionFrame Option) {
		try {
			final int x ;
			final int y; 
			switch (Option) {
			case Frm_NewCliente:
				AltaCliente altaCliente = new AltaCliente("NEW",this.gestorAdministracion);
				altaCliente.setSize(422, 781);
				final Toolkit toolkit = Toolkit.getDefaultToolkit();
				final Dimension screenSize = toolkit.getScreenSize();
				x = (screenSize.width - altaCliente.getWidth()) / 2;
				 y = (screenSize.height - altaCliente.getHeight()) / 2;
				altaCliente.setLocation(x, y);
				altaCliente.setVisible(true);
				
				break;
			case Frm_UpdCliente:
				AltaCliente updCliente = new AltaCliente("UPD",this.gestorAdministracion);
				updCliente.setSize(422, 781);
				final Toolkit toolkit1 = Toolkit.getDefaultToolkit();
				final Dimension screenSize1 = toolkit1.getScreenSize();
				x = (screenSize1.width - updCliente.getWidth()) / 2;
				y= (screenSize1.height - updCliente.getHeight()) / 2;
				updCliente.setLocation(x, y);
				updCliente.setVisible(true);
				break;
			case Frm_NewSol:

				AltaSolicitud NewSol = new AltaSolicitud(this.gestorAdministracion);
				NewSol.setSize(469, 426);
				final Toolkit toolkit2 = Toolkit.getDefaultToolkit();
				final Dimension screenSize2 = toolkit2.getScreenSize();
				x = (screenSize2.width - NewSol.getWidth()) / 2;
				y= (screenSize2.height - NewSol.getHeight()) / 2;
				NewSol.setLocation(x, y);
				NewSol.setVisible(true);
				break;
			case Frm_UpdSol:

				ConsultaSolicitud UpdSol = new ConsultaSolicitud(this.gestorAdministracion);
				UpdSol.setSize(398, 441);
				final Toolkit toolkit3 = Toolkit.getDefaultToolkit();
				final Dimension screenSize3 = toolkit3.getScreenSize();
				x = (screenSize3.width - UpdSol.getWidth()) / 2;
				y= (screenSize3.height - UpdSol.getHeight()) / 2;
				UpdSol.setLocation(x, y);
				UpdSol.setVisible(true);
				break;
			case Frm_DeleteSol:
				BajaSolicitud bajaSol = new BajaSolicitud(this.gestorAdministracion);
				bajaSol.setSize(398, 526);
				final Toolkit toolkit4 = Toolkit.getDefaultToolkit();
				final Dimension screenSize4= toolkit4.getScreenSize();
				x = (screenSize4.width - bajaSol.getWidth()) / 2;
				y= (screenSize4.height - bajaSol.getHeight()) / 2;
				bajaSol.setLocation(x, y);
				bajaSol.setVisible(true);
				break;
			case Frm_Gestion:
				GestionAvisos gestion = new GestionAvisos(this.gestorAdministracion);
				gestion.setSize(912,250);
				final Toolkit toolkitgestion = Toolkit.getDefaultToolkit();
				final Dimension screenSizetoolkitgestion = toolkitgestion
						.getScreenSize();
				x = (screenSizetoolkitgestion.width - gestion.getWidth()) / 2;
				y = (screenSizetoolkitgestion.height - gestion.getHeight()) / 2;
				gestion.setLocation(x, y);
				gestion.setVisible(true);
				break;
			case Frm_Recepcion:
				RecepcionPedidos pedidos = new RecepcionPedidos(this.gestorAdministracion);
				pedidos.setSize(762, 250);
				final Toolkit toolkitpedidos = Toolkit.getDefaultToolkit();
				final Dimension screenSizetoolkitpedidos = toolkitpedidos
						.getScreenSize();
				x = (screenSizetoolkitpedidos.width - pedidos.getWidth()) / 2;
				y = (screenSizetoolkitpedidos.height - pedidos.getHeight()) / 2;
				pedidos.setLocation(x, y);
				pedidos.setVisible(true);

				break;
			}

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, 
					TDSLanguageUtils.getMessage("mensaje.cliente.createmenu"), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
