package edu.uoc.tdp.pac4.client;
import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;

import javax.swing.*;

import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.exception.GestorConexionException;
import edu.uoc.tdp.pac4.service.GestorConexionInterface;
/******************************************************************************
*	Classe que mostra la finestra per validar els usuaris del sistema
*	@version  1.0j de 2-12-2002
*	@author GRUPO1
*/
public class Login extends JDialog {

	protected GestorConexionInterface gestorConexion = null;
	protected Usuari usuari = null;
	private JTextField txtUsername = new JTextField();
	private JPasswordField txtPassword = new JPasswordField();

	public Login() {
		initialize ();

	}
	
	public Login (Frame frame, GestorConexionInterface gestorConexion){
		super(frame,"IDENTI",true);
		this.gestorConexion = gestorConexion;
		initialize();
	}
	
	private void initialize (){						
		this.setLayout(new BorderLayout(50, 50));
		this.add(panelTop(),BorderLayout.NORTH);
		this.add(panelBottom(),BorderLayout.SOUTH);
		this.add(panelCentral(),BorderLayout.CENTER);
		this.add(panelLeft(),BorderLayout.WEST);
		this.add(panelRigth(),BorderLayout.EAST);
		this.setResizable(false);
		this.pack();
		
	}
	
	private JPanel panelBottom (){
		JPanel panel = new JPanel();
		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setActionCommand("BTN_ACEPTAR");
		btnAceptar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				actions(e);
				
			}
		});
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setActionCommand("BTN_CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				actions(e);
				
			}
		});
		panel.setLayout(new FlowLayout());
		panel.add(btnAceptar);
		panel.add(btnCancelar);
		return panel;		
		
	}
	
	private JPanel panelCentral () {
		JPanel panel = new JPanel();
		JLabel lblUsername = new JLabel("USUARIO");
		JLabel lblPassword = new JLabel("PASSWORD");
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtPassword = new JPasswordField();
		txtPassword.setColumns(10);		
		panel.setLayout( new GridLayout(2, 2, 5, 5));
		panel.add(lblUsername);
		panel.add(txtUsername);
		panel.add(lblPassword);
		panel.add(txtPassword);		
		return panel;
	}
	
	private JPanel panelRigth (){
		JPanel panel = new JPanel ();		
		return panel;
	}

	private JPanel panelLeft (){
		JPanel panel = new JPanel ();
		return panel;
	}
	
	private JPanel panelTop (){
		JPanel panel = new JPanel();
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