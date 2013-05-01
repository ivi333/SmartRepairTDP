package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import edu.uoc.tdp.pac4.common.TDSLanguageUtils;

public class MenuGestionAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
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

	/**
	 * Create the frame.
	 */
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
			//ITEM 1
			this.jMenuClientes = new JMenu();
			this.jMenuBar1.add(this.jMenuClientes);
			this.jMenuClientes.setText(TDSLanguageUtils.getMessage("menu.gestionAdmin.clientes"));
			this.jMenuItemAltaCliente = new JMenuItem();
			this.jMenuClientes.add(this.jMenuItemAltaCliente);
			this.jMenuItemAltaCliente.setText(TDSLanguageUtils.getMessage("menu.gestionAdmin.alta"));
			this.jMenuItemConsulCliente = new JMenuItem();
			this.jMenuClientes.add(this.jMenuItemConsulCliente);
			this.jMenuItemConsulCliente.setText(TDSLanguageUtils.getMessage("menu.gestionAdmin.consulta"));
			
		
			//ITEM 2
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
			
			this.jMenuItemConsulSol = new JMenuItem();
			this.jMenuSoli.add(this.jMenuItemConsulSol);
			this.jMenuItemConsulSol.setText(TDSLanguageUtils
					.getMessage("menu.gestionAdmin.consulta"));
			
			this.jMenuItemBajaSol = new JMenuItem();
			this.jMenuSoli.add(this.jMenuItemBajaSol);
			this.jMenuItemBajaSol.setText(TDSLanguageUtils
					.getMessage("menu.gestionAdmin.baja"));
		

			//ITEM 3
			
			this.jMenuAvisos = new JMenu();
			this.jMenuBar1.add(this.jMenuAvisos);
			this.jMenuAvisos.setText(TDSLanguageUtils
					.getMessage("menu.gestionAdmin.avisos"));
			
			this.jMenuItemGestionAvisos = new JMenuItem();
			this.jMenuAvisos.add(this.jMenuItemGestionAvisos);
			this.jMenuItemGestionAvisos.setText(TDSLanguageUtils
					.getMessage("menu.gestionAdmin.gestion"));

			//ITEM 4
			
			this.jMenuAlmacen = new JMenu();
			this.jMenuBar1.add(this.jMenuAlmacen);
			this.jMenuAlmacen.setText(TDSLanguageUtils
					.getMessage("menu.gestionAdmin.almacen"));
			
			this.jMenuItemPedidoAlmacen = new JMenuItem();
			this.jMenuAlmacen.add(this.jMenuItemPedidoAlmacen);
			this.jMenuItemPedidoAlmacen.setText(TDSLanguageUtils
					.getMessage("menu.gestionAdmin.recepcion"));
			
			
			
			setSize(663, 399);
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}
}
