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
		"C\u00F3digo", "Descripci\u00F3n", "Unidades", "Disponible"
	};
	private JScrollPane scrollPane;
	private JButton btnPlay;


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
		
		setTitle("Detalle reparación asignada");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblDetalleReparacionAsignada = new JLabel("Detalle Reparación Asignada");
		lblDetalleReparacionAsignada.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblDetalleReparacionAsignada.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNombreMecanico = new JLabel("Nombre Mecánico");
		
		txtNombreMecanico = new JTextField();
		txtNombreMecanico.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		
		JLabel lblId = new JLabel("Id");
		
		txtId = new JTextField();
		txtId.setColumns(10);
		
		JLabel lblOrdenReparacion = new JLabel("Orden Reparación");
		
		txtOrdenReparacion = new JTextField();
		txtOrdenReparacion.setColumns(10);
		
		JLabel lblMatricula = new JLabel("Matrícula");
		
		txtMatricula = new JTextField();
		txtMatricula.setColumns(10);
		
		JLabel lblMarca = new JLabel("Marca");
		
		txtMarca = new JTextField();
		txtMarca.setColumns(10);
		
		JLabel lblModelo = new JLabel("Modelo");
		
		txtModelo = new JTextField();
		txtModelo.setColumns(10);
		
		JLabel lblObservaciones = new JLabel("Observaciones para la reparación");
		
		txtObservaciones = new JTextArea();
		
		JLabel lblPiezasAsignadas = new JLabel("Piezas asignadas a la reparación");
		
		JLabel lblFechas = new JLabel("Fechas");
		lblFechas.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblContador = new JLabel("Contador");
		lblContador.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblFechaAsignacion = new JLabel("Fecha Asignación");
		lblFechaAsignacion.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtFechaAsignacion = new JTextField();
		txtFechaAsignacion.setColumns(10);
		
		JButton btnFechaInicio = new JButton("Fecha inicio");
		btnFechaInicio.setEnabled(false);
		
		JButton btnFechaFin = new JButton("Fecha Fin");
		btnFechaFin.setEnabled(false);
		
		txtFechaInicio = new JTextField();
		txtFechaInicio.setColumns(10);
		
		txtFechaFin = new JTextField();
		txtFechaFin.setColumns(10);
		
		lblMinutos = new JLabel("0 min");
		lblMinutos.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnPlay = new JButton("Play");
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					btnPlay.setEnabled(false);
					gestorReparacion.setHoraInicioReparacion(ordenReparacion);
					txtFechaInicio.setText(gestorReparacion.getHoraInicioReparacion(ordenReparacion).toString());
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (GestorReparacionException e) {
					e.printStackTrace();
				}
			}
		});
		
		btnStop = new JButton("Stop");
		btnStop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					btnStop.setEnabled(false);
					gestorReparacion.setHoraFinReparacion(ordenReparacion);
					txtFechaFin.setText(gestorReparacion.getHoraFinReparacion(ordenReparacion).toString());
				} catch (RemoteException e1) {
					e1.printStackTrace();
				} catch (GestorReparacionException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		
		scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(0)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblDetalleReparacionAsignada, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
								.addComponent(txtObservaciones, GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
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
											.addComponent(lblModelo))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(txtFechaAsignacion, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
													.addComponent(txtFechaInicio, Alignment.LEADING)
													.addComponent(txtFechaFin, Alignment.LEADING))))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(525)
											.addComponent(lblId)))
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
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addComponent(txtModelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addGap(1))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblPiezasAsignadas)
							.addGap(102)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblFechaAsignacion, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(92)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
											.addComponent(btnFechaFin, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
											.addComponent(btnFechaInicio, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))))
								.addComponent(lblFechas, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))
							.addGap(162))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnSalir)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblObservaciones)
							.addContainerGap(471, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDetalleReparacionAsignada, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblApellido)
						.addComponent(lblNombreMecanico)
						.addComponent(txtNombreMecanico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
								.addComponent(btnFechaInicio)
								.addComponent(btnStop)
								.addComponent(txtFechaInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnFechaFin)
								.addComponent(txtFechaFin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
				rowData[z][3] = "Si";
			} else {
				rowData[z][3] = "No";
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
			
			Usuari datosUsuario = gestorReparacion.getUsuario(datosDetalleReparacion.getIdMecanic());
			this.txtId.setText(String.valueOf(datosUsuario.getId()));
			this.txtNombreMecanico.setText(datosUsuario.getNom());
			this.txtApellido.setText(datosUsuario.getCognoms());
			
			Reparacio datosReparacion = gestorReparacion.getReparacion(datosDetalleReparacion.getOrdreReparacio());
			this.txtFechaAsignacion.setText(String.valueOf(datosReparacion.getDataAssignacio()));
			this.txtFechaInicio.setText(String.valueOf(datosReparacion.getDataInici()));
			this.txtFechaFin.setText(String.valueOf(datosReparacion.getDataFi()));
			
			scrollPane.setViewportView(table);
			table.setModel(getTableModel(datosReparacion.getOrdreReparacio()));
			
			
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
				lblMinutos.setText( hours + ":" + minute + ":" + second);
			}
		}  
	}  
	
}


