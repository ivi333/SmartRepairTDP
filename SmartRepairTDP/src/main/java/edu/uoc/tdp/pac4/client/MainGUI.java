package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

public class MainGUI {

	private JFrame frmSmartRepairTdp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		
		JMenu mnNewMenu = new JMenu("Mantenimiento");
		mnNewMenu.setEnabled(true);
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Gestión de Talleres");		
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Gestión de Usuarios");
		mntmNewMenuItem.setEnabled(true);
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("Administración");
		mnNewMenu_1.setEnabled(true);
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("Reparaciones");
		mnNewMenu_2.setEnabled(true);
		menuBar.add(mnNewMenu_2);
		
		JMenu mnNewMenu_3 = new JMenu("Estadísticas");
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

}
