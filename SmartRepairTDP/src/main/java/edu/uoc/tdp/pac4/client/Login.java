package edu.uoc.tdp.pac4.client;
import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import edu.uoc.tdp.pac4.exception.GestorConexionException;
import edu.uoc.tdp.pac4.service.GestorConexionInterface;


public class Login extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected GestorConexionInterface gestorConexion = null;
	protected Usuari usuari = null;
	private JTextField txtUsername = new JTextField();
	private JPasswordField txtPassword = new JPasswordField();

	public Login() {
		initialize ();

	}
	
	public Login (Frame frame, GestorConexionInterface gestorConexion){
		super(frame,TDSLanguageUtils.getMessage("login.titulo.ventana"),true);
		this.gestorConexion = gestorConexion;
		initialize();
		
	}
	
	private void initialize (){					

		this.setLayout(new BorderLayout(0, 0));
		this.getContentPane().add(panelNorth(),BorderLayout.NORTH);
		this.getContentPane().add(panelSouth(),BorderLayout.SOUTH);
		this.getContentPane().add(panelWest(),BorderLayout.WEST);
		this.getContentPane().add(panelEast(),BorderLayout.EAST);
		this.getContentPane().add(panelCenter(),BorderLayout.CENTER);
		
		this.setResizable(false);
		this.pack();
		
	}
	
	private Panel panelSouth (){

		Panel panel = new Panel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		{
			JButton btnAceptar = new JButton(TDSLanguageUtils.getMessage("login.boton.aceptar"));
			btnAceptar.setActionCommand("BTN_ACEPTAR");
			btnAceptar.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					actions(e);
					
				}
			});
			panel.add(btnAceptar);
			getRootPane().setDefaultButton(btnAceptar);
		}
		{
			JButton btnCancelar = new JButton(TDSLanguageUtils.getMessage("login.boton.cancelar"));
			btnCancelar.setActionCommand("BTN_CANCELAR");
			btnCancelar.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					actions(e);
					
				}
			});
			panel.add(btnCancelar);
		}

		return panel;		
		
	}
	
	private Panel panelCenter () {
		Panel panel = new Panel();		

		JLabel lblUsername = new JLabel(TDSLanguageUtils.getMessage("login.label.usuario"));
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblPassword = new JLabel(TDSLanguageUtils.getMessage("login.label.password"));
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);

		txtPassword = new JPasswordField();
		
		getContentPane().add(panel, BorderLayout.CENTER);
		GroupLayout groupLayout = new GroupLayout(panel);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.CENTER)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
						.addComponent(lblUsername, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 106, Short.MAX_VALUE)) 
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.CENTER, false)
						.addComponent(txtPassword)
						.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
					.addGap(134))
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(59)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsername))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(108, Short.MAX_VALUE))
		);
		panel.setLayout(groupLayout);

		return panel;

	}
	
	private Panel panelEast (){
		Panel panel = new Panel ();		
		return panel;
	}

	private Panel panelWest (){
		Panel panel = new Panel ();
		return panel;
	}
	
	private JPanel panelNorth (){
		JPanel panel = new JPanel();					
		JLabel lblTitulo = new JLabel(TDSLanguageUtils.getMessage("login.titulo"));
		lblTitulo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTitulo.setFont(new Font("Serif", Font.BOLD, 16));
		panel.add(lblTitulo);
		return panel;
	}
	
	private void actions (ActionEvent actionEvent){
		if (actionEvent.getActionCommand().equals("BTN_ACEPTAR")){
			try {
				this.usuari = gestorConexion.doLogin(txtUsername.getText().toUpperCase(), String.valueOf(txtPassword.getPassword()));
				dispose ();
			} catch (RemoteException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);				
			} catch (GestorConexionException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);				
			}
		}
		if (actionEvent.getActionCommand().equals("BTN_CANCELAR")){
				dispose();
				
		}
	}
	
	public Usuari getUsuari () {
		return this.usuari;
	}
	
	public boolean isLogin () {
		return (this.usuari!=null);
	}
}