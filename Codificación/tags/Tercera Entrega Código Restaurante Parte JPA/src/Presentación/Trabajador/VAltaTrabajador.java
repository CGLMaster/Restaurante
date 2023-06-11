package Presentación.Trabajador;

import javax.swing.JFrame;

import Presentación.FormComponent;
import Presentación.Command.Context;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;
import Presentación.FactoriaVistas.IGUI;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Negocio.Trabajador.TTrabajador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

public class VAltaTrabajador extends JFrame implements IGUI {

	private ArrayList<JTextField> textFields;
	
	public VAltaTrabajador() {
		super("Nuevo Trabajador");
		setTitle("Alta Trabajador");
		textFields = new ArrayList<JTextField>();
		init_GUI();
		this.setLocationRelativeTo(null);
	}

	public void actualizar(int evento, Object datos) {
		if (evento == Eventos.RES_ALTA_TRABAJADOR_OK) {
			JOptionPane.showMessageDialog(null, "Trabajador creado con exito con id: " + (int)datos);
		} else if (evento == Eventos.RES_ALTA_TRABAJADOR_KO) {
			JOptionPane.showMessageDialog(null, "No se ha podido anadir el trabajador");
		}
	}

	public void init_GUI() {
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);
		
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("icono2.png")).getImage();
		this.setIconImage(iconFrame);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500, 500));
		this.add(mainPanel);
		

		// BACK BUTTON
		JPanel buttonPanel = backButtonContainer();

		// FORM COMPONENT
		ArrayList<String> names = new ArrayList<String>();
		names.add("Nombre");
		names.add("Mail");
		names.add("DNI");

		FormComponent formComponent = new FormComponent(names, "Alta Trabajador", textFields, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (textFields.get(0).getText().length() > 2 && textFields.get(1).getText().length() > 2
									&& textFields.get(2).getText().length() > 2) {
					TTrabajador trabajador = new TTrabajador();
					trabajador.setNombre(textFields.get(0).getText());
					trabajador.setMail(textFields.get(1).getText());
					trabajador.setDNI(textFields.get(2).getText());
					Controller.getInstance().action(new Context(Eventos.GUARDAR_TRABAJADOR, trabajador));
				} else {
					JOptionPane.showMessageDialog(null, "Rellene todos los campos, por favor");
				}
				
			}
		});

		mainPanel.add(buttonPanel);
		mainPanel.add(formComponent);
		this.pack();
		this.setVisible(true);
	}

	private JPanel backButtonContainer() {
		JPanel backButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonContainer.setMaximumSize(new Dimension(1000, 50));

		JButton backButton = new JButton();
		backButton.setBackground(new Color(237, 237, 237));
		backButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("back_icon.png")));
		backButton.setToolTipText("Volver a Trabajadores");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_TRABAJADOR, 0));
				dispose();
			}
		});

		backButtonContainer.add(backButton);

		return backButtonContainer;
	}

	@Override
	public void actualizar(Context res) {
		
		System.out.println("SI" + res.getEvento());
		
		if (res.getEvento() == Eventos.RES_ALTA_TRABAJADOR_OK) {
			JOptionPane.showMessageDialog(null, "Trabajador creado con exito con id: " + (int)res.getDatos());
		} else if (res.getEvento() == Eventos.RES_ALTA_TRABAJADOR_KO) {
			JOptionPane.showMessageDialog(null, "No se ha podido anadir el trabajador");
		}
		
		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_TRABAJADOR, 0));
		dispose();
	}
}