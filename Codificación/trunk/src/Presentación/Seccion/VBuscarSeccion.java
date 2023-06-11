package Presentación.Seccion;

import Negocio.Seccion.TSeccion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Presentación.Command.Context;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;
import Presentación.FactoriaVistas.IGUI;


@SuppressWarnings("serial")
public class VBuscarSeccion extends JFrame implements IGUI {
	private TSeccion seccion;

	public VBuscarSeccion(TSeccion seccion) {
		this.seccion = seccion;
		init_GUI();
		this.setLocationRelativeTo(null);
	}

	public void init_GUI() {
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("icono2.png")).getImage();
		this.setIconImage(iconFrame);
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500, 500));
		mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

		// BACK BUTTON
		JPanel backButtonContainer = backButtonContainer();

		// TITULO
		JLabel titleLabel = new JLabel("Seccion");
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, 20));

		// CONTENT CONTAINER
		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);
		contentContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentContainer.setBackground(Color.white);

		// INFO
		JLabel idLabel = new JLabel("ID: " + seccion.getID_Seccion());
		JLabel nombreLabel = new JLabel("Zona: " + seccion.getZona());
		JLabel webLabel = new JLabel("Pasillo: " + seccion.getPasillo());
		JLabel activoLabel = new JLabel();
		if(seccion.getActivo()){
			activoLabel.setText("Activo");
			activoLabel.setForeground(Color.green);
		}else{
			activoLabel.setText("Inactivo");
			activoLabel.setForeground(Color.red);
		}
		
		// CONSTRUIR VISTA
		this.add(mainPanel);
		mainPanel.add(backButtonContainer);
		mainPanel.add(titleLabel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(contentContainer);
		contentContainer.add(idLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(nombreLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(webLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(activoLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));

		this.pack();
		this.setVisible(true);
	}

	private JPanel backButtonContainer() {

		JPanel backButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonContainer.setMaximumSize(new Dimension(1000, 75));

		JButton backButton = new JButton();
		backButton.setBackground(new Color(237, 237, 237));
		backButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("back_icon.png")));
		backButton.setToolTipText("Volver a Secciones");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);

		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_SECCION));
				dispose();
			}

		});

		backButtonContainer.add(backButton);

		return backButtonContainer;
	}

	@Override
	public void actualizar(Context res) {
		if (res.getEvento() == Eventos.RES_BUSCAR_SECCION_OK) {
		}
		else if(res.getEvento() == Eventos.RES_BUSCAR_SECCION_KO) {
			JOptionPane.showMessageDialog(null, "No se ha encontrado la seccion");
		}
		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_SECCION));
		dispose();
	}
}