package edu.uoc.tdp.pac4.client;
import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.util.Locale;

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
	private JCheckBox chkPassword;
	private JMenuBar menuBar;
	
	public Login() {
		initialize ();

	}
	
	public Login (Frame frame, GestorConexionInterface gestorConexion){
		super(frame,TDSLanguageUtils.getMessage("login.titulo.ventana"),true);
		this.gestorConexion = gestorConexion;
		initialize();
		
	}
	
	private void initialize (){					

		getContentPane().setLayout(new BorderLayout(0, 0));
		this.getContentPane().add(panelNorth(),BorderLayout.NORTH);
		this.getContentPane().add(panelSouth(),BorderLayout.SOUTH);
		this.getContentPane().add(panelWest(),BorderLayout.WEST);
		this.getContentPane().add(panelEast(),BorderLayout.EAST);
		this.getContentPane().add(panelCenter(),BorderLayout.CENTER);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnIdioma = new JMenu("Idioma");
		menuBar.add(mnIdioma);
		JRadioButtonMenuItem mn1 = new JRadioButtonMenuItem("Catalan");
		mnIdioma.add(mn1);
		JRadioButtonMenuItem mn2 = new JRadioButtonMenuItem("Ingles");
		mnIdioma.add(mn2);
		JRadioButtonMenuItem mn3 = new JRadioButtonMenuItem("Castellano", true);
		mnIdioma.add(mn3);

		ButtonGroup buttonGroup = new ButtonGroup();
	    buttonGroup.add(mn1);
	    buttonGroup.add(mn2);
	    buttonGroup.add(mn3);
		
	    mn1.setName("mn1");
	    mn2.setName("mn2");
	    mn3.setName("mn3");
	    
	    mn1.addItemListener(new ItemHandler());
	    mn2.addItemListener(new ItemHandler());
	    mn3.addItemListener(new ItemHandler());
	    
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
		chkPassword = new JCheckBox(TDSLanguageUtils.getMessage("login.label.changepass"));
		GroupLayout groupLayout = new GroupLayout(panel);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.CENTER)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
						.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 127, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(chkPassword)
						.addComponent(txtPassword, 142, 142, 142))
					.addGap(134))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(187)
					.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(134, Short.MAX_VALUE))
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
					.addGap(34)
					.addComponent(chkPassword)
					.addContainerGap(51, Short.MAX_VALUE))
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
//				JOptionPane.showMessageDialog(this, TDSLanguageUtils.getMessage("login.accion.OK"), 
//						this.getTitle(), JOptionPane.INFORMATION_MESSAGE);
				dispose ();
				if (chkPassword.isSelected()){
					ChangePassword changePassword = new ChangePassword(gestorConexion, usuari);
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					changePassword.setSize(450, 300);
					changePassword.setLocation(dim.width/2-changePassword.getSize().width/2, dim.height/2-changePassword.getSize().height/2);
					changePassword.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					changePassword.setModal(true);
					changePassword.setVisible(true);
					changePassword.setAlwaysOnTop(true);
				}
			} catch (GestorConexionException e) {
				String msg = TDSLanguageUtils.getMessage(e.getMessage());
				if (msg.equalsIgnoreCase(""))
					msg = e.getMessage();
				JOptionPane.showMessageDialog(this, msg, this.getTitle(), JOptionPane.ERROR_MESSAGE);				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), this.getTitle(), JOptionPane.ERROR_MESSAGE);				
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
  public class ItemHandler implements ItemListener {
	    public void itemStateChanged(ItemEvent e) {	    	
	      AbstractButton button = (AbstractButton) e.getItem();
	      if (button.isSelected()) {
	    	  if ("mn1".equals(button.getName())) {
	    		  TDSLanguageUtils.setLanguage("i18n/messages", new Locale("ca"));
	    	  } else if ("mn1".equals(button.getName())) {
	    		  TDSLanguageUtils.setLanguage("i18n/messages", new Locale("en"));
	    	  } else if ("mn1".equals(button.getName())) {
	    		  TDSLanguageUtils.setLanguage("i18n/messages", new Locale("es"));
	    	  }
	      }
	  }
  }
}