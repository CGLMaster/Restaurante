package Presentación.Personal;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import Presentación.Command.Context;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;
import Presentación.FactoriaVistas.IGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.Image;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import Negocio.Personal.TPersonal;
import Negocio.Personal.TTiempoCompleto;
import Negocio.Personal.TTiempoParcial;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VBuscarUnoIDPersonal extends JFrame implements IGUI {
	
private TPersonal personal;
	
	public VBuscarUnoIDPersonal(TPersonal personal) {
		this.personal = personal;
		init_GUI();
		this.setLocationRelativeTo(null);
	}
	private void init_GUI() {
		this.getContentPane().removeAll();
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500, 500));
		mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("iconoRest2.png")).getImage();
		this.setIconImage(iconFrame);
		
		// BACK BUTTON
		JPanel backButtonContainer = backButtonContainer();
				
		// TITULO
		JLabel titleLabel = new JLabel("Personal Restaurante");
		titleLabel.setFont(new Font("sans-serif", 1, 20));
		titleLabel.setForeground(new Color(128, 0, 0));
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
				
		// CONTENT CONTAINER
		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);
		contentContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentContainer.setBackground(Color.white);
		
		// INFO

		JLabel idLabel = new JLabel("ID: " + personal.getIDPersonal());
		JLabel nombreLabel = new JLabel("Nombre: " + personal.getNombre());
		JLabel dniLabel = new JLabel("DNI: " + personal.getDni());
		JLabel sueldoLabel = new JLabel("Sueldo: " + personal.getSueldo() + " \u20AC");
		JLabel numPedidosLabel = new JLabel("Numero de pedidos atendidos: " + personal.getNumPedidos());
		JLabel idTurnoLabel = new JLabel("Id Turno al que pertenece: " + personal.getIDTurno());
		JLabel activoLabel = new JLabel();
		
		this.add(mainPanel);
		mainPanel.add(backButtonContainer);
		mainPanel.add(titleLabel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(contentContainer);
		contentContainer.add(idLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(nombreLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(dniLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(sueldoLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(numPedidosLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(idTurnoLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));		
		
		
		if(personal instanceof TTiempoCompleto){
			TTiempoCompleto tiempoC = (TTiempoCompleto) personal;
			JLabel nominaLabel = new JLabel("Nomina: " + tiempoC.getNomina() + " \u20AC");
			JLabel horasExtraLabel = new JLabel("Horas extra trabajadas: " + tiempoC.getHorasExtras());
			contentContainer.add(nominaLabel);
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
			contentContainer.add(horasExtraLabel);
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		}
		else if(personal  instanceof TTiempoParcial){
			TTiempoParcial tiempoP = (TTiempoParcial) personal;
			JLabel precioHoraLabel = new JLabel("Precio hora: " + tiempoP.getPrecioHora()+ " \u20AC" );
			JLabel numeroHorasLabel = new JLabel("Numero horas que trabaja: " + tiempoP.getNumeroHoras());
			contentContainer.add(precioHoraLabel);
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
			contentContainer.add(numeroHorasLabel);
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		}
		contentContainer.add(activoLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		
		if (personal.getActivo()) {
			activoLabel.setText("Activo");
			activoLabel.setForeground(Color.green);
		} else {
			activoLabel.setText("Inactivo");
			activoLabel.setForeground(Color.red);
		}
			
		// CONSTRUIR VISTA
		

		this.pack();
		this.setVisible(true);
		
	}
	
	private JPanel backButtonContainer() {
		JPanel backButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonContainer.setMaximumSize(new Dimension(1000, 75));

		JButton backButton = new JButton();
		backButton.setBackground(new Color(237, 237, 237));
		backButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("backRest_icon.png"))));
		backButton.setToolTipText("Volver a Personal Restaurante");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);
		backButton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PERSONAL));
			dispose();
		}
		});

		backButtonContainer.add(backButton);

		return backButtonContainer;
	}

	@Override
	public void actualizar(Context res) {
		if (res.getEvento() == Eventos.RES_BUSCAR_PERSONAL_OK) {
		} else if (res.getEvento() == Eventos.RES_BUSCAR_PERSONAL_KO) {
			JOptionPane.showMessageDialog(null, "No se ha encontrado el empleado");
		}
		Controller.getInstance().action(new Context(Eventos.BUSCAR_PERSONAL));
		dispose();
	}
}