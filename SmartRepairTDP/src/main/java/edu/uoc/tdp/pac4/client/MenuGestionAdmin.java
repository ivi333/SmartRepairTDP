package edu.uoc.tdp.pac4.client;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Locale;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import edu.uoc.tdp.pac4.service.GestorAdministracionInterface;

public class MenuGestionAdmin extends JFrame {
	private static int port = 1099;
	 private final static String urlRMIAdmin = new String("rmi://localhost/GestorAdministracion");
	
	private static final long serialVersionUID = 1L;
	private static GestorAdministracionInterface conexionRemota;
	private JScrollPane jScrollPanel;
	private JMenu jMenuClientes;
	private JMenu jMenuSoli;
	private JMenu jMenuAvisos;
	private JMenu jMenuAlmacen;
	private JMenuBar jMenuBar1;

	private JMenuItem jMenuItemAltaCliente;
	private JMenuItem jMenuItemConsulCliente;

	private JMenuItem jMenuItemAltaSol;
	private JMenuItem jMenuItemConsulSol;
	private JMenuItem jMenuItemBajaSol;

	private JMenuItem jMenuItemGestionAvisos;
	private JMenuItem jMenuItemPedidoAlmacen;
	private JPanel jPanelSeleccionPantalla;

	private JFrame OpenPantalla;
	private static String NEW = "NEW";
	private static String UPD = "UPD";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuGestionAdmin menuGestionAdmin = new MenuGestionAdmin();
					menuGestionAdmin.setLocationRelativeTo(null);
					menuGestionAdmin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static GestorAdministracionInterface getRemoto()
			throws RemoteException, NotBoundException {
		try {
		
			if (conexionRemota == null) {
				Registry registry = LocateRegistry.getRegistry("localhost",
						port);
				conexionRemota = (GestorAdministracionInterface) Naming.lookup(urlRMIAdmin);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return conexionRemota;
	}
	public MenuGestionAdmin() {
		try {
			seleccionIdioma();
			initialize();
			inicializaControles();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void seleccionIdioma() {
		Locale localLocale = new Locale("", "");
		TDSLanguageUtils.setDefaultLanguage("i18n/messages");
	}

	private void initialize() {
		setSize(new Dimension(521, 277));
	}

	private void inicializaControles() {
		try {
			setDefaultCloseOperation(3);

			setTitle(TDSLanguageUtils.getMessage("menu.gestionAdmin.titulo"));
			this.jScrollPanel = new JScrollPane();
			getContentPane().add(this.jScrollPanel, "Center");

			this.jMenuBar1 = new JMenuBar();
			setJMenuBar(this.jMenuBar1);
			// ITEM 1
			this.jMenuClientes = new JMenu();
			this.jMenuBar1.add(this.jMenuClientes);
			this.jMenuClientes.setText(TDSLanguageUtils
					.getMessage("menu.gestionAdmin.clientes"));
			this.jMenuItemAltaCliente = new JMenuItem();
			this.jMenuClientes.add(this.jMenuItemAltaCliente);
			this.jMenuItemAltaCliente.setText(TDSLanguageUtils
					.getMessage("menu.gestionAdmin.alta"));
			this.jMenuItemAltaCliente.addActionListener(new ActionListener() {
				public void actionPerformed(
						ActionEvent paramAnonymousActionEvent) {
					MenuGestionAdmin.this
							.OpenFrm_Option(OptionFrame.Frm_NewCliente);
				}
			});

			this.jMenuItemConsulCliente = new JMenuItem();
			this.jMenuClientes.add(this.jMenuItemConsulCliente);
			this.jMenuItemConsulCliente.setText(TDSLanguageUtils
					.getMessage("menu.gestionAdmin.consulta"));
			this.jMenuItemConsulCliente.addActionListener(new ActionListener() {
				public void actionPerformed(
						ActionEvent paramAnonymousActionEvent) {
					MenuGestionAdmin.this
							.OpenFrm_Option(OptionFrame.Frm_UpdCliente);
				}
			});

			// ITEM 2
			this.jMenuSoli = new JMenu();
			this.jMenuBar1.add(this.jMenuSoli);
			this.jMenuSoli.setText(TDSLanguageUtils
					.getMessage("menu.gestionAdmin.solicitudes"));
			this.jMenuSoli.setVisible(true);
			this.jMenuSoli.setEnabled(true);
			this.jMenuItemAltaSol = new JMenuItem();
			this.jMenuSoli.add(this.jMenuItemAltaSol);
			this.jMenuItemAltaSol.setText(TDSLanguageUtils
					.getMessage("menu.gestionAdmin.alta"));
			this.jMenuItemAltaSol.addActionListener(new ActionListener() {
				public void actionPerformed(
						ActionEvent paramAnonymousActionEvent) {
					MenuGestionAdmin.this
							.OpenFrm_Option(OptionFrame.Frm_NewSol);
				}
			});

			this.jMenuItemConsulSol = new JMenuItem();
			this.jMenuSoli.add(this.jMenuItemConsulSol);
			this.jMenuItemConsulSol.setText(TDSLanguageUtils
					.getMessage("menu.gestionAdmin.consulta"));
			this.jMenuItemConsulSol.addActionListener(new ActionListener() {
				public void actionPerformed(
						ActionEvent paramAnonymousActionEvent) {
					MenuGestionAdmin.this
							.OpenFrm_Option(OptionFrame.Frm_UpdSol);
				}
			});
			this.jMenuItemBajaSol = new JMenuItem();
			this.jMenuSoli.add(this.jMenuItemBajaSol);
			this.jMenuItemBajaSol.setText(TDSLanguageUtils
					.getMessage("menu.gestionAdmin.baja"));
			this.jMenuItemBajaSol.addActionListener(new ActionListener() {
				public void actionPerformed(
						ActionEvent paramAnonymousActionEvent) {
					MenuGestionAdmin.this
							.OpenFrm_Option(OptionFrame.Frm_DeleteSol);
				}
			});

			// ITEM 3

			this.jMenuAvisos = new JMenu();
			this.jMenuBar1.add(this.jMenuAvisos);
			this.jMenuAvisos.setText(TDSLanguageUtils
					.getMessage("menu.gestionAdmin.avisos"));

			this.jMenuItemGestionAvisos = new JMenuItem();
			this.jMenuAvisos.add(this.jMenuItemGestionAvisos);
			this.jMenuItemGestionAvisos.setText(TDSLanguageUtils
					.getMessage("menu.gestionAdmin.gestion"));
			this.jMenuItemGestionAvisos.addActionListener(new ActionListener() {
				public void actionPerformed(
						ActionEvent paramAnonymousActionEvent) {
					MenuGestionAdmin.this
							.OpenFrm_Option(OptionFrame.Frm_Gestion);
				}
			});
			// ITEM 4

			this.jMenuAlmacen = new JMenu();
			this.jMenuBar1.add(this.jMenuAlmacen);
			this.jMenuAlmacen.setText(TDSLanguageUtils
					.getMessage("menu.gestionAdmin.almacen"));

			this.jMenuItemPedidoAlmacen = new JMenuItem();
			this.jMenuAlmacen.add(this.jMenuItemPedidoAlmacen);
			this.jMenuItemPedidoAlmacen.setText(TDSLanguageUtils
					.getMessage("menu.gestionAdmin.recepcion"));

			this.jMenuItemPedidoAlmacen.addActionListener(new ActionListener() {
				public void actionPerformed(
						ActionEvent paramAnonymousActionEvent) {
					MenuGestionAdmin.this
							.OpenFrm_Option(OptionFrame.Frm_Recepcion);
				}
			});

			setSize(663, 399);
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	private void OpenFrm_Option(OptionFrame Option) {
		try {
			final int x ;
			final int y; 
			switch (Option) {
			case Frm_NewCliente:
				AltaCliente altaCliente = new AltaCliente("NEW",getRemoto());
				altaCliente.setSize(422, 781);
				final Toolkit toolkit = Toolkit.getDefaultToolkit();
				final Dimension screenSize = toolkit.getScreenSize();
				x = (screenSize.width - altaCliente.getWidth()) / 2;
				 y = (screenSize.height - altaCliente.getHeight()) / 2;
				altaCliente.setLocation(x, y);
				altaCliente.setVisible(true);
				
				break;
			case Frm_UpdCliente:
				AltaCliente updCliente = new AltaCliente("UPD",getRemoto());
				updCliente.setSize(422, 781);
				final Toolkit toolkit1 = Toolkit.getDefaultToolkit();
				final Dimension screenSize1 = toolkit1.getScreenSize();
				x = (screenSize1.width - updCliente.getWidth()) / 2;
				y= (screenSize1.height - updCliente.getHeight()) / 2;
				updCliente.setLocation(x, y);
				updCliente.setVisible(true);
				break;
			case Frm_NewSol:

				AltaSolicitud NewSol = new AltaSolicitud(getRemoto());
				NewSol.setSize(469, 426);
				final Toolkit toolkit2 = Toolkit.getDefaultToolkit();
				final Dimension screenSize2 = toolkit2.getScreenSize();
				x = (screenSize2.width - NewSol.getWidth()) / 2;
				y= (screenSize2.height - NewSol.getHeight()) / 2;
				NewSol.setLocation(x, y);
				NewSol.setVisible(true);
				break;
			case Frm_UpdSol:

				ConsultaSolicitud UpdSol = new ConsultaSolicitud(getRemoto());
				UpdSol.setSize(398, 441);
				final Toolkit toolkit3 = Toolkit.getDefaultToolkit();
				final Dimension screenSize3 = toolkit3.getScreenSize();
				x = (screenSize3.width - UpdSol.getWidth()) / 2;
				y= (screenSize3.height - UpdSol.getHeight()) / 2;
				UpdSol.setLocation(x, y);
				UpdSol.setVisible(true);
				break;
			case Frm_DeleteSol:
				BajaSolicitud bajaSol = new BajaSolicitud(getRemoto());
				bajaSol.setSize(398, 526);
				final Toolkit toolkit4 = Toolkit.getDefaultToolkit();
				final Dimension screenSize4= toolkit4.getScreenSize();
				x = (screenSize4.width - bajaSol.getWidth()) / 2;
				y= (screenSize4.height - bajaSol.getHeight()) / 2;
				bajaSol.setLocation(x, y);
				bajaSol.setVisible(true);
				break;
			case Frm_Gestion:
				GestionAvisos gestion = new GestionAvisos(getRemoto());
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
				RecepcionPedidos pedidos = new RecepcionPedidos(getRemoto());
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
			ex.printStackTrace();
		}

	}

	public enum OptionFrame {
		Frm_NewCliente, Frm_UpdCliente, Frm_NewSol, Frm_UpdSol, Frm_DeleteSol, Frm_Gestion, Frm_Recepcion;
	}
}
