package Presentación.Seccion;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Seccion.TSeccion;
import Presentación.FormComponent;
import Presentación.Command.Context;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;
import Presentación.FactoriaVistas.IGUI;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class VModificarSeccion extends JFrame implements IGUI {

	private ArrayList<JTextField> textFields;
	private TSeccion seccion;

	public VModificarSeccion(TSeccion seccion) throws HeadlessException {
		this.setTitle("Modificar Datos de Seccion");
		textFields = new ArrayList<JTextField>();
		this.seccion = seccion;
		init_GUI();
		this.setLocationRelativeTo(null);
	}

	private void init_GUI() {
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("icono2.png")).getImage();
		this.setIconImage(iconFrame);
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		JPanel backButtonContainer = backButtonContainer();

		// FORM COMPONENT
		ArrayList<String> names = new ArrayList<String>();
		names.add("Zona");
		names.add("Pasillo");

		ArrayList<String> values = new ArrayList<String>();
		values.add(seccion.getZona());
		values.add(Integer.toString(seccion.getPasillo()));

		FormComponent formComponent = new FormComponent(names, "Editar Seccion", textFields, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					if (textFields.get(0).getText().length() > 1) {
						seccion.setZona(textFields.get(0).getText());
						seccion.setPasillo(Integer.parseInt(textFields.get(1).getText()));
						Controller.getInstance().action(new Context(Eventos.MODIFICAR_SECCION, seccion));
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Introduzca una zona valida.");
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Introduzca un pasillo valido(valor numerico).");
				}
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

	public void actualizar(Context res) {
		if (res.getEvento() == Eventos.RES_MODIFICAR_SECCION_OK) {
			JOptionPane.showMessageDialog(null, "Seccion modificada correctamente");
		} else if (res.getEvento() == Eventos.RES_MODIFICAR_SECCION_KO && (int) res.getDatos() == -2) {
			JOptionPane.showMessageDialog(null, "No se ha podido modificar la Seccion, pasillo no valido");
		} else if (res.getEvento() == Eventos.RES_MODIFICAR_SECCION_KO) {
			JOptionPane.showMessageDialog(null, "No se ha podido modificar la Seccion");
		}
		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_SECCION));
		dispose();
	}

}
