package Presentación.Seccion;

import javax.swing.JFrame;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Negocio.Seccion.TSeccion;
import Presentación.Command.Context;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;
import Presentación.FactoriaVistas.IGUI;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class VBajaSeccion extends JFrame implements IGUI {

	TSeccion seccion;

	public VBajaSeccion(TSeccion seccion) {
		super("Baja Seccion");
		this.seccion = seccion;
		init_GUI();
		this.setLocationRelativeTo(null);
	}

	public void init_GUI() {
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("icono2.png")).getImage();
		this.setIconImage(iconFrame);
		this.setTitle("Eliminar Sección");
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500, 300));
		mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

		// TITULO
		JLabel titleLabel = new JLabel("¿Desea eliminar la sección con la siguiente información?");
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, 15));

		// CONTENT CONTAINER
		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);
		contentContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentContainer.setBackground(Color.white);

		// INFO
		JLabel idLabel = new JLabel("ID: " + seccion.getID_Seccion());
		JLabel seccionLabel = new JLabel("Zona: " + seccion.getZona());
		JLabel pasilloLabel = new JLabel("Pasillo: " + seccion.getPasillo());

		// CONSTRUIR VISTA
		mainPanel.add(titleLabel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(contentContainer);
		contentContainer.add(idLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(seccionLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(pasilloLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));

		// BOTONES
		JButton yesButton = new JButton("YES");
		JButton noButton = new JButton("NO");
		yesButton.setBackground(new Color(39, 174, 95));
		yesButton.setForeground(Color.white);
		yesButton.setBorderPainted(false);

		noButton.setBackground(new Color(236, 115, 115));
		noButton.setForeground(Color.white);
		noButton.setBorderPainted(false);

		noButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_SECCION));
				dispose();
			}

		});

		yesButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Controller.getInstance().action(new Context(Eventos.BAJA_SECCION, seccion.getID_Seccion()));
				dispose();
			}

		});
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
		buttonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		buttonsPanel.add(yesButton);
		buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonsPanel.add(noButton);

		this.add(mainPanel);
		mainPanel.add(buttonsPanel);

		this.pack();
		this.setVisible(true);
	}

	@Override
	public void actualizar(Context res) {
		if(res.getEvento() == Eventos.RES_BAJA_SECCION_OK ){
			JOptionPane.showMessageDialog(null, "Seccion borrado correctamente");
		}
		else if(res.getEvento() == Eventos.RES_BAJA_SECCION_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido borrar la seccion");
		}
		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_SECCION));
		dispose();
	}
}