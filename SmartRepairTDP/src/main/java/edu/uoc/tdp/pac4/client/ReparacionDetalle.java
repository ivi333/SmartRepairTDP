package edu.uoc.tdp.pac4.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.rmi.RemoteException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;

import edu.uoc.tdp.pac4.beans.DetallPeca;
import edu.uoc.tdp.pac4.beans.DetallReparacio;
import edu.uoc.tdp.pac4.beans.Mecanic;
import edu.uoc.tdp.pac4.beans.Reparacio;
import edu.uoc.tdp.pac4.beans.Usuari;
import edu.uoc.tdp.pac4.common.TDSLanguageUtils;
import edu.uoc.tdp.pac4.exception.GestorReparacionException;
import edu.uoc.tdp.pac4.service.GestorReparacionInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JScrollPane;

public class ReparacionDetalle extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombreMecanico;
	private JTextField txtApellido;
	private JTextField txtId;
	private JTextField txtOrdenReparacion;
	private JTextField txtMatricula;
	private JTextField txtMarca;
	private JTextField txtModelo;
	private JTextField txtFechaAsignacion;
	private JTextField txtFechaInicio;
	private JTextField txtFechaFin;
	
	private static ReparacionGestion reparacionGestion;
	private GestorReparacionInterface gestorReparacion;
	private JTextArea txtObservaciones;
	private JTable table;
	
	private static final Object columnNames[] = {
		TDSLanguageUtils.getMessage("repDetalle.tablas.codigo"), TDSLanguageUtils.getMessage("repDetalle.tablas.descripcion"), TDSLanguageUtils.getMessage("repDetalle.tablas.unidades"), TDSLanguageUtils.getMessage("repDetalle.tablas.disponible")
	};
	private JScrollPane scrollPane;
	private JButton btnPlay;
	
	private int ordenReparacion;
	private Usuari usuario;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReparacionDetalle frame = new ReparacionDetalle(null,null,-1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ReparacionDetalle(GestorReparacionInterface conexion, final Usuari usuario, final int ordenReparacion) {
		this.gestorReparacion = conexion;
		this.ordenReparacion = ordenReparacion;
		this.usuario = usuario;
		
		setTitle(TDSLanguageUtils.getMessage("repDetalle.titulo"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblDetalleReparacionAsignada = new JLabel(TDSLanguageUtils.getMessage("repDetalle.titulo"));
		lblDetalleReparacionAsignada.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblDetalleReparacionAsignada.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNombreMecanico = new JLabel(TDSLanguageUtils.getMessage("repDetalle.nombremec"));
		
		txtNombreMecanico = new JTextField();
		txtNombreMecanico.setEditable(false);
		txtNombreMecanico.setColumns(10);
		
		JLabel lblApellido = new JLabel(TDSLanguageUtils.getMessage("repDetalle.apellido"));
		
		txtApellido = new JTextField();
		txtApellido.setEditable(false);
		txtApellido.setColumns(10);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setColumns(10);
		
		JLabel lblOrdenReparacion = new JLabel(TDSLanguageUtils.getMessage("repDetalle.ordenrep"));
		
		txtOrdenReparacion = new JTextField();
		txtOrdenReparacion.setEditable(false);
		txtOrdenReparacion.setColumns(10);
		
		JLabel lblMatricula = new JLabel(TDSLanguageUtils.getMessage("repDetalle.matricula"));
		
		txtMatricula = new JTextField();
		txtMatricula.setEditable(false);
		txtMatricula.setColumns(10);
		
		JLabel lblMarca = new JLabel(TDSLanguageUtils.getMessage("repDetalle.marca"));
		
		txtMarca = new JTextField();
		txtMarca.setEditable(false);
		txtMarca.setColumns(10);
		
		JLabel lblModelo = new JLabel(TDSLanguageUtils.getMessage("repDetalle.modelo"));
		
		txtModelo = new JTextField();
		txtModelo.setEditable(false);
		txtModelo.setColumns(10);
		
		JLabel lblObservaciones = new JLabel(TDSLanguageUtils.getMessage("repDetalle.obsrep"));
		
		txtObservaciones = new JTextArea();
		txtObservaciones.setEditable(false);
		
		JLabel lblPiezasAsignadas = new JLabel(TDSLanguageUtils.getMessage("repDetalle.piezasrep"));
		
		JLabel lblFechas = new JLabel(TDSLanguageUtils.getMessage("repDetalle.fechas"));
		lblFechas.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblContador = new JLabel(TDSLanguageUtils.getMessage("repDetalle.contador"));
		lblContador.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblFechaAsignacion = new JLabel(TDSLanguageUtils.getMessage("repDetalle.fasignacion"));
		lblFechaAsignacion.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtFechaAsignacion = new JTextField();
		txtFechaAsignacion.setEditable(false);
		txtFechaAsignacion.setColumns(10);
		
		JButton btnFechaInicio = new JButton(TDSLanguageUtils.getMessage("repDetalle.finicio"));
		btnFechaInicio.setEnabled(false);
		
		JButton btnFechaFin = new JButton(TDSLanguageUtils.getMessage("repDetalle.ffin"));
		btnFechaFin.setEnabled(false);
		
		txtFechaInicio = new JTextField();
		txtFechaInicio.setEditable(false);
		txtFechaInicio.setColumns(10);
		
		txtFechaFin = new JTextField();
		txtFechaFin.setEditable(false);
		txtFechaFin.setColumns(10);
		
		lblMinutos = new JLabel(TDSLanguageUtils.getMessage("repDetalle.ceromin"));
		lblMinutos.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnPlay = new JButton(TDSLanguageUtils.getMessage("repDetalle.play"));
		btnPlay.setVisible(false);
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					DetallReparacio reparacionAct = gestorReparacion.getDetalleReparacion(ordenReparacion);
					if (!String.valueOf(reparacionAct.getDataInici()).contains("-")) {
						btnPlay.setEnabled(false);
						gestorReparacion.setHoraInicioReparacion(ordenReparacion);
						txtFechaInicio.setText(gestorReparacion.getHoraInicioReparacion(ordenReparacion).toString());
						JOptionPane.showMessageDialog(reparacionGestion, TDSLanguageUtils.getMessage("repDetalle.info.contini"), TDSLanguageUtils.getMessage("repDetalle.info"), JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(reparacionGestion, TDSLanguageUtils.getMessage("repDetalle.info.reparacionfinalizada"), TDSLanguageUtils.getMessage("repDetalle.info"), JOptionPane.INFORMATION_MESSAGE);
					}
					
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (GestorReparacionException e) {
					e.printStackTrace();
				}
			}
		});
		
		btnStop = new JButton(TDSLanguageUtils.getMessage("repDetalle.stop"));
		btnStop.setVisible(false);
		btnStop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					Reparacio reparacionAct = gestorReparacion.getReparacion(ordenReparacion);
					if (!String.valueOf(reparacionAct.getDataFi()).contains("-")) {
						btnStop.setEnabled(false);
						gestorReparacion.setHoraFinReparacion(ordenReparacion);
						txtFechaFin.setText(gestorReparacion.getHoraFinReparacion(ordenReparacion).toString());
						gestorReparacion.setReparacionFinalizada(ordenReparacion);
						JOptionPane.showMessageDialog(reparacionGestion, TDSLanguageUtils.getMessage("repDetalle.info.contfin"), TDSLanguageUtils.getMessage("repDetalle.info"), JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(reparacionGestion, TDSLanguageUtils.getMessage("repDetalle.info.reparacionyafinalizada"), TDSLanguageUtils.getMessage("repDetalle.info"), JOptionPane.INFORMATION_MESSAGE);
					}
					
				} catch (RemoteException e1) {
					e1.printStackTrace();
				} catch (GestorReparacionException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		JButton btnSalir = new JButton(TDSLanguageUtils.getMessage("repDetalle.salir"));
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		
		scrollPane = new JScrollPane();
		
		JLabel lblId = new JLabel(TDSLanguageUtils.getMessage("repDetalle.id"));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(0)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDetalleReparacionAsignada, GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
								.addComponent(txtObservaciones, GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(lblNombreMecanico)
													.addGap(10)
													.addComponent(txtNombreMecanico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
													.addGap(18)
													.addComponent(lblApellido)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(lblOrdenReparacion)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(txtOrdenReparacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
													.addGap(18)
													.addComponent(lblMatricula)
													.addGap(10)
													.addComponent(txtMatricula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
													.addGap(18)
													.addComponent(lblMarca)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(txtMarca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
											.addGap(27)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblId)
												.addComponent(lblModelo)))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(txtFechaAsignacion, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(txtFechaInicio, Alignment.LEADING)
														.addComponent(txtFechaFin, Alignment.LEADING))
													.addPreferredGap(ComponentPlacement.RELATED)
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(btnFechaFin, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
														.addComponent(btnFechaInicio, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))))))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(20)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
												.addComponent(lblMinutos, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblContador, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addPreferredGap(ComponentPlacement.RELATED)
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
														.addComponent(btnStop, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(btnPlay, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)))))
										.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtModelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addGap(1))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblPiezasAsignadas)
							.addGap(102)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblFechaAsignacion, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFechas, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))
							.addGap(162))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnSalir)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblObservaciones)
							.addContainerGap(577, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDetalleReparacionAsignada, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblApellido)
						.addComponent(lblNombreMecanico)
						.addComponent(txtNombreMecanico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblId))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOrdenReparacion)
						.addComponent(txtOrdenReparacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMatricula)
						.addComponent(lblMarca)
						.addComponent(txtMarca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtMatricula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblModelo)
						.addComponent(txtModelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblObservaciones)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtObservaciones, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPiezasAsignadas)
						.addComponent(lblFechas)
						.addComponent(lblContador))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFechaAsignacion)
								.addComponent(lblMinutos))
							.addGap(5)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnPlay)
								.addComponent(txtFechaAsignacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(5)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnStop)
								.addComponent(txtFechaInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnFechaInicio))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtFechaFin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnFechaFin))
							.addGap(18)
							.addComponent(btnSalir))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		initCampos(ordenReparacion);
		
		contentPane.setLayout(gl_contentPane);
	
		
	}

	private TableModel getTableModel (int ordenReparacion) throws RemoteException, GestorReparacionException  {
		List<DetallPeca> list = gestorReparacion.getPiezasReparacion(ordenReparacion);
		Object rowData [][] = new Object [list.size()][4];
		int z=0;
		for (DetallPeca bean : list) {
			rowData[z][0] = String.valueOf(bean.getCodiPeca());
			rowData[z][1] = String.valueOf(bean.getDescipcio());
			rowData[z][2] = String.valueOf(bean.getCantidad());
			if (bean.getCantidad() <= bean.getStock()) {
				rowData[z][3] = TDSLanguageUtils.getMessage("repDetalle.si");
			} else {
				rowData[z][3] = TDSLanguageUtils.getMessage("repDetalle.no");
			}
			z++;
		}
		TableModel model = new DefaultTableModel(rowData, columnNames);
		return model;
	}
	
	private void initCampos(int ordenReparacion) {
		try {
			go();
			DetallReparacio datosDetalleReparacion = gestorReparacion.getDetalleReparacion(ordenReparacion);
			this.txtOrdenReparacion.setText(String.valueOf(datosDetalleReparacion.getOrdreReparacio()));
			this.txtMatricula.setText(datosDetalleReparacion.getMatricula());
			this.txtMarca.setText(datosDetalleReparacion.getMarca());
			this.txtModelo.setText(datosDetalleReparacion.getModel());
			this.txtObservaciones.setText(datosDetalleReparacion.getObservacions());
			
			if (datosDetalleReparacion.getIdMecanic() != 0) {
				Usuari datosUsuario = gestorReparacion.getUsuario(datosDetalleReparacion.getIdMecanic());
				this.txtId.setText(String.valueOf(datosUsuario.getId()));
				this.txtNombreMecanico.setText(datosUsuario.getNom());
				this.txtApellido.setText(datosUsuario.getCognoms());
			}
			
			Reparacio datosReparacion = gestorReparacion.getReparacion(datosDetalleReparacion.getOrdreReparacio());
			this.txtFechaAsignacion.setText(datosReparacion.getDataAssignacio()!=null ? String.valueOf(datosReparacion.getDataAssignacio()) : "");
			this.txtFechaInicio.setText(datosReparacion.getDataInici()!=null ? String.valueOf(datosReparacion.getDataInici()) : "");
			this.txtFechaFin.setText(datosReparacion.getDataFi() != null ? String.valueOf(datosReparacion.getDataFi()) : "");
			
			this.lblMinutos.setText(String.valueOf(datosReparacion.getComptador()));
			
			scrollPane.setViewportView(table);
			table.setModel(getTableModel(datosReparacion.getOrdreReparacio()));
			
			if (usuario.getPerfil().contains("Mecanic") && datosDetalleReparacion.getIdMecanic() != 0) {
				btnPlay.setVisible(true);
				btnStop.setVisible(true);
			}
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (GestorReparacionException e) {
			e.printStackTrace();
		}
	}
	
	/* Cronometro */
	
	JFrame frame;  

	private long init;
	JButton startStopButton;
	private JLabel lblMinutos;
	private JButton btnStop;


	
	public void go() {  
		btnPlay.setToolTipText("Start");
		btnPlay.addActionListener(new StartStopListener());  
		btnStop.addActionListener(new StartStopListener());
	}  

   class StartStopListener implements ActionListener {  
	   
		public void actionPerformed(ActionEvent e) {
			JButton j = (JButton)e.getSource();			
			if ("Start".equals(j.getToolTipText())) {
				init = System.currentTimeMillis();
				btnPlay.setToolTipText("Stop");
			} else {
				long seconds = (System.currentTimeMillis() - init) / 1000;
				int day = (int)TimeUnit.SECONDS.toDays(seconds);        
				long hours = TimeUnit.SECONDS.toHours(seconds) - (day *24);
				long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds)* 60);
				long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) *60);
				btnPlay.setToolTipText("Start");
				
				double contador = minute + (hours * 60);
				String ceroHours = "";
				String ceroMinute = "";
				String ceroSecond = "";
				if (hours < 10) {
					ceroHours = "0";
				}
				if (minute < 10) {
					ceroMinute = "0";
				}
				if (second < 10) {
					ceroSecond = "0";
				}
				
				lblMinutos.setText( ceroHours + hours + ":" + ceroMinute + minute + ":" + ceroSecond + second);
				try {
					gestorReparacion.setContadorReparacion(ordenReparacion, contador);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				} catch (GestorReparacionException e1) {
					e1.printStackTrace();
				}
			}
		}  
	}  
}


