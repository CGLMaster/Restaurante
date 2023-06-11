package Presentación.Marca.VistasCasos_de_Uso;


import javax.swing.JFrame;

import Presentación.FormComponent;
import Presentación.Command.Context;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;
import Presentación.FactoriaVistas.IGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Marca.TMarca;

@SuppressWarnings("serial")
public class VModificarMarca extends JFrame implements IGUI {

	private ArrayList<JTextField> textFields;
	private TMarca marca;

	public VModificarMarca(TMarca marca) {
		setTitle("Modificar Marca");
		this.marca = marca;
		textFields = new ArrayList<JTextField>();
		init_GUI();
		this.setLocationRelativeTo(null);
	}

	public void init_GUI() {

		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);
		
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("icono2.png")).getImage();
		this.setIconImage(iconFrame);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		JPanel backButtonContainer = backButtonContainer();

		// FORM COMPONENT
		ArrayList<String> names = new ArrayList<String>();
		names.add("Nombre");
		names.add("Web");

		ArrayList<String> values = new ArrayList<String>();
		values.add(marca.getNombre());
		values.add(marca.getWeb());

		FormComponent formComponent = new FormComponent(names, "Modificar Marca", textFields, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				marca.setNombre(textFields.get(0).getText());
				marca.setWeb(textFields.get(1).getText());
				Context aux = new Context(Eventos.UPDATE_MARCA, marca);

				Controller.getInstance().action(aux);
			}
		});

		formComponent.setValues(values);

		// CONSTRUIR VISTA
		this.add(mainPanel);
		mainPanel.add(backButtonContainer);
		mainPanel.add(formComponent);

		this.pack();
		this.setVisible(true);
	}

	private JPanel backButtonContainer() {

		JPanel backButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonContainer.setMaximumSize(new Dimension(1000, 50));

		JButton backButton = new JButton();
		backButton.setBackground(new Color(237, 237, 237));
		backButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("back_icon.png"))));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);

		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_MARCA));
				dispose();
			}
		});

		backButtonContainer.add(backButton);

		return backButtonContainer;
	}

	public void actualizar(Context res) {
		if (res.getEvento() == Eventos.RES_MODIFICAR_MARCA_OK) {
			JOptionPane.showMessageDialog(null, "Marca modificada correctamente");
		} else if (res.getEvento() == Eventos.RES_MODIFICAR_MARCA_KO) {
			JOptionPane.showMessageDialog(null, "No se ha podido modificar marca");
		}
		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_MARCA));
		dispose();
	}

}