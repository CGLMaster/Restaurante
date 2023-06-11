package Presentación.Plato;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JTextField;

import Negocio.Plato.TBebida;
import Negocio.Plato.TComida;
import Negocio.Plato.TPlato;
import Presentación.FormComponent;
import Presentación.Command.Context;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;
import Presentación.FactoriaVistas.IGUI;

@SuppressWarnings("serial")
public class VAltaPlato extends JFrame implements IGUI {

	private ArrayList<JTextField> jTextFields;
	private ArrayList<String> listaVisible;
	private JComboBox<String> selTipoPlato;
	private FormComponent formComponent;
	private ActionListener listenerPrinc;

	public VAltaPlato() {
		super("Alta plato");
		setTitle("Nuevo Plato");
		jTextFields = new ArrayList<JTextField>();
		init_GUI();
		this.setLocationRelativeTo(null);
	}

	public void init_GUI() {

		listenerPrinc = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (jTextFields.get(0).getText().replaceAll("\\s", "").length() > 0
							&& jTextFields.get(1).getText().replaceAll("\\s", "").length() > 0) {

						TPlato plato = null;
						float precio = Float.parseFloat(jTextFields.get(2).getText());
						int stock = Integer.parseInt(jTextFields.get(3).getText());

						if (precio <= 0 || stock < 0) {
							throw new NumberFormatException();
						}

						String nombre = jTextFields.get(0).getText();
						String desc = jTextFields.get(1).getText();

						if (selTipoPlato.getSelectedItem().equals("Comida")) {
							if (jTextFields.get(4).getText().replaceAll("\\s", "").length() > 0) {
								String categ = jTextFields.get(4).getText();
								plato = new TComida(nombre, desc, precio, stock, true, categ);
								
								Controller.getInstance().action(new Context(Eventos.GUARDAR_PLATO, plato));
								
							} 
							else {
								JOptionPane.showMessageDialog(null, "Asegurese de introducir una categoria");
							}
						} 
						else {

							float volumen;

							try {
								volumen = Float.parseFloat(jTextFields.get(4).getText());

								if (volumen < 0) {
									throw new NumberFormatException();
								}

								plato = new TBebida(nombre, desc, precio, stock, true, volumen);
								
								Controller.getInstance().action(new Context(Eventos.GUARDAR_PLATO, plato));
								
							} catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(null,
										"Asegurese de introducir un Volumen valido (valor numerico positivo)");
							}
						}
					} 
					else {
						JOptionPane.showMessageDialog(null, "Asegurese de introducir un nombre y una descripccion");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null,
							"Asegurese de introducir Stock y Precio validos (valores numericos positivos y enteros en el caso de Stock)");
				}

			}
		};

		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);

		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("iconoRest2.png")).getImage();
		this.setIconImage(iconFrame);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500, 500));
		this.add(mainPanel);

		// BACK BUTTON
		JPanel buttonPanel = backButtonContainer();

		// FORM COMPONENT
		ArrayList<String> listaComida = new ArrayList<String>();
		listaComida.add("Nombre");
		listaComida.add("Descripcion");
		listaComida.add("Precio");
		listaComida.add("Stock");
		listaComida.add("Categoria");

		ArrayList<String> listaBebida = new ArrayList<String>();
		listaBebida.add("Nombre");
		listaBebida.add("Descripcion");
		listaBebida.add("Precio");
		listaBebida.add("Stock");
		listaBebida.add("Volumen");

		listaVisible = listaComida;

		// PANEL SELECCION COMIDA/BEBIDA.
		selTipoPlato = new JComboBox<String>();
		selTipoPlato.setToolTipText("Tipo de plato");
		selTipoPlato.addItem("Comida");
		selTipoPlato.addItem("Bebida");
		selTipoPlato.setMaximumSize(new Dimension(440, 30));

		// FORM COMPONENT PARTE 2
		formComponent = new FormComponent(listaVisible, "Alta Plato Comida", jTextFields, listenerPrinc);

		formComponent.setSubmitButtonColor(new Color(128, 0, 0));
		mainPanel.add(buttonPanel);
		mainPanel.add(selTipoPlato);

		// PANEL SELECCION COMIDA/BEBIDA PARTE 2
		selTipoPlato.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (selTipoPlato.getSelectedItem().equals("Comida")) {
					listaVisible = listaComida;
					mainPanel.remove(formComponent);
					jTextFields = new ArrayList<JTextField>();
					formComponent = new FormComponent(listaVisible, "Alta Plato Comida", jTextFields, listenerPrinc);
					formComponent.setSubmitButtonColor(new Color(128, 0, 0));
					mainPanel.add(formComponent);
					mainPanel.revalidate();
					mainPanel.repaint();

				} else if (selTipoPlato.getSelectedItem().equals("Bebida")) {
					listaVisible = listaBebida;
					mainPanel.remove(formComponent);
					jTextFields = new ArrayList<JTextField>();
					formComponent = new FormComponent(listaVisible, "Alta Plato Bebida", jTextFields, listenerPrinc);
					formComponent.setSubmitButtonColor(new Color(128, 0, 0));
					mainPanel.add(formComponent);
					mainPanel.revalidate();
					mainPanel.repaint();
				}

			}
		});

		mainPanel.add(formComponent);
		this.pack();
		this.setVisible(true);
	}

	private JPanel backButtonContainer() {
		JPanel backButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonContainer.setMaximumSize(new Dimension(1000, 50));

		JButton backButton = new JButton();
		backButton.setBackground(new Color(237, 237, 237));
		backButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("backRest_icon.png")));
		backButton.setToolTipText("Volver a Platos");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PLATO, 0));
				dispose();
			}
		});

		backButtonContainer.add(backButton);

		return backButtonContainer;
	}

	@Override
	public void actualizar(Context res) {

		if (res.getEvento() == Eventos.RES_ALTA_PLATO_OK) {
			JOptionPane.showMessageDialog(null, "Se ha dado de alta correctamente el plato con id: " + res.getDatos());
		} else if (res.getEvento() == Eventos.RES_ALTA_PLATO_KO) {
			JOptionPane.showMessageDialog(null, "No se ha podido dar de alta el plato.");
		}

		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PLATO));
		dispose();
	}
}