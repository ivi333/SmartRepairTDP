package edu.uoc.tdp.pac4.server;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.swing.JOptionPane;

import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import edu.uoc.tdp.pac4.service.GestorAdministracionImpl;
import edu.uoc.tdp.pac4.service.GestorAdministracionInterface;
import edu.uoc.tdp.pac4.service.GestorConexionImpl;
import edu.uoc.tdp.pac4.service.GestorConexionInterface;
import edu.uoc.tdp.pac4.service.GestorEstadisticaImpl;
import edu.uoc.tdp.pac4.service.GestorEstadisticaInterface;
import edu.uoc.tdp.pac4.service.GestorReparacionImpl;
import edu.uoc.tdp.pac4.service.GestorReparacionInterface;

/**
 * Smart Repair 
 * ETIG - TDP PAC 4 Primavera 2013
 * Grup: FiveCoreDumped
 */
public class Servidor extends javax.swing.JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7129339830009307597L;
	private final String urlRMIConex = new String("rmi://localhost/GestorConexion");
	private final String urlRMIAdmin = new String("rmi://localhost/GestorAdministracion");
	private final String urlRMIRepar = new String("rmi://localhost/GestorReparacion");
	private final String urlRMIEstad = new String("rmi://localhost/GestorEstadistica");
	
	private GestorAdministracionInterface 	gestorAdmin;
	private GestorConexionInterface		  	gestorConex;
	private GestorEstadisticaInterface		gestorEstad;
	private GestorReparacionInterface		gestorRepar;
	
	/**
	 * 
	 */
	public Servidor() {
		initComponents();
		String tituloventana = TDSLanguageUtils.getMessage("titulo.ventana")
				+ " " +TDSLanguageUtils.getMessage("titulo.servidor")
				+ " " +TDSLanguageUtils.getMessage("titulo.uoc");
		setTitle(tituloventana);
		jLabel1.setText(TDSLanguageUtils.getMessage("mensaje.noiniciado"));
		jButton1.setText(TDSLanguageUtils.getMessage("boton.iniciar"));
		jButton2.setText(TDSLanguageUtils.getMessage("boton.parar"));
		jButton1.setEnabled(true);
		jButton2.setEnabled(false);
		initRemoteObjects();
	}
	
	private void initRemoteObjects () {
		try {
			gestorAdmin = new GestorAdministracionImpl ();
			gestorConex = new GestorConexionImpl();
			gestorEstad = new GestorEstadisticaImpl();
			gestorRepar = new GestorReparacionImpl();
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog (null, 
					TDSLanguageUtils.getMessage("ERR_REG_RMI") + ":" + e.getMessage(), 
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}


	/**
	 * 
	 */
	private void initComponents() {

		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				formWindowClosing(evt);
			}
		});

		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGap(26, 26, 26)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createSequentialGroup()
										.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
										.addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap())
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
						.addGap(11, 11, 11)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
								.addContainerGap())
				);

		pack();
	}


	/**
	 * 
	 * @param evt
	 */
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			LocateRegistry.createRegistry(1099);
			Naming.rebind(urlRMIConex, gestorConex);
			Naming.rebind(urlRMIAdmin, gestorAdmin);
			Naming.rebind(urlRMIRepar, gestorRepar);
			Naming.rebind(urlRMIEstad, gestorEstad);
			jLabel1.setText(TDSLanguageUtils.getMessage("mensaje.iniciado"));
			jButton1.setEnabled(false);
			jButton2.setEnabled(true);
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog (null, TDSLanguageUtils.getMessage("ERR_REG_RMI") + ":" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog //      
			(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}


	/**
	 * 
	 * @param evt
	 */
	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		dispose();
		System.exit(0);
	}

	/**
	 * 
	 * @param evt
	 */
	private void formWindowClosing(java.awt.event.WindowEvent evt) {
		dispose();
		System.exit(0);
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		TDSLanguageUtils.setDefaultLanguage("i18n/messages");
		Servidor window = new Servidor();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);		
		window.setVisible(true);
	}

	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JLabel jLabel1;
}
