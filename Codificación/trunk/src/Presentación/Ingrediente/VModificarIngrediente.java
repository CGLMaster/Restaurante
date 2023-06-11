package Presentación.Ingrediente;

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
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Negocio.Ingrediente.TIngrediente;

@SuppressWarnings("serial")
public class VModificarIngrediente extends JFrame implements IGUI {

	private ArrayList<JTextField> jTextFields;
	private TIngrediente ingrediente;

	public VModificarIngrediente(TIngrediente datos) {
		super("Modificar Ingrediente");
		setTitle("Modificar Datos de Ingrediente");
		jTextFields = new ArrayList<JTextField>();
		ingrediente = datos;
		init_GUI();
		this.setLocationRelativeTo(null);
	}

	public void init_GUI() {
		this.getContentPane().removeAll();
		this.setPreferredSize(new Dimension(1150, 750));

		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("iconoRest2.png")).getImage();
		this.setIconImage(iconFrame);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		JPanel backButtonContainer = backButtonContainer();

		// FORM COMPONENT
		ArrayList<String> names = new ArrayList<String>();
		names.add("Nombre");
		names.add("Calorías");

		ArrayList<String> values = new ArrayList<String>();
		values.add(ingrediente.getNombre());
		values.add(Double.toString(ingrediente.getCalorias()));

		FormComponent formComponent = new FormComponent(names, "Modificar Ingrediente", jTextFields, new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (jTextFields.get(0).getText().replaceAll("\\s", "").length() >= 1) {

							ingrediente.setNombre(jTextFields.get(0).getText());

							try {
								if (jTextFields.get(1).getText().replaceAll("\\s", "").length() >= 1) {

									float calorias = Float.parseFloat(jTextFields.get(1).getText());

									if (calorias >= 0) {
										ingrediente.setCalorias(calorias);
										Controller.getInstance().action(new Context(Eventos.UPDATE_INGREDIENTE, ingrediente));
										dispose();
									} else {
										JOptionPane.showMessageDialog(null,
												"Calorías incorrectas, por favor introduzca un valor positivo");
									}

								} else {
									JOptionPane.showMessageDialog(null,
											"Calorías incorrectas, asegúrese de introducir un valor");
								}
							} catch (NumberFormatException e2) {
								JOptionPane.showMessageDialog(null,
										"Calorías incorrectas, asegúrese de introducir un valor numérico");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Asegúrese de introducir un nombre");
						}
					}
				});
		formComponent.setValues(values);
		formComponent.setSubmitButtonColor(new Color(128, 0, 0));

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
		backButton.setContentAreaFilled(false);
		backButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("backRest_icon.png")));
		backButton.setToolTipText("Volver a Ingredientes");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);

		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_INGREDIENTE));
				dispose();
			}

		});

		backButtonContainer.add(backButton);

		return backButtonContainer;
	}

	@Override
	public void actualizar(Context res) {
		if (res.getEvento() == Eventos.RES_MODIFICAR_INGREDIENTE_OK) {
			JOptionPane.showMessageDialog(null, "Ingrediente modificado correctamente con id " + res.getDatos());
		} else if (res.getEvento() == Eventos.RES_MODIFICAR_INGREDIENTE_KO) {
			JOptionPane.showMessageDialog(null,	"No se ha podido modificar el Ingrediente, asegúrese de que los datos introducidos son sintácticamemte correctos y no existen duplicados");
		}
		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_INGREDIENTE));
		dispose();
	}
}