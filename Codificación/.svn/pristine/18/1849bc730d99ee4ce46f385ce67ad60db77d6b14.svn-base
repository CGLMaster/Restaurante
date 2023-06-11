package Presentación.Ingrediente;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
import javax.swing.JTextField;

import Negocio.Ingrediente.TIngrediente;

@SuppressWarnings("serial")
public class VAniadirIngrediente extends JFrame implements IGUI {
	ArrayList<JTextField> textFields;

	public VAniadirIngrediente() {
		super("Nuevo Ingrediente");
		setTitle("Aniadir Ingrediente");
		textFields = new ArrayList<JTextField>();
		init_GUI();
		this.setLocationRelativeTo(null);
	}

	public void init_GUI() {
		this.setPreferredSize(new Dimension(1150, 750));

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
		ArrayList<String> names = new ArrayList<String>();
		names.add("Nombre");
		names.add("Calorias");

		FormComponent formComponent = new FormComponent(names, "Aniadir Ingrediente", textFields, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				try{
					if(textFields.get(0).getText().length() > 1){
						TIngrediente ingrediente = new TIngrediente(textFields.get(0).getText(), Double.parseDouble(textFields.get(1).getText()));
						
						ingrediente.setCalorias(Double.parseDouble(textFields.get(1).getText()));
						if(ingrediente.getCalorias()<0)
							throw new NumberFormatException();
						ingrediente.setActivo(true);
						Controller.getInstance().action(new Context(Eventos.GUARDAR_INGREDIENTE, ingrediente));
						dispose();
					}else{
						JOptionPane.showMessageDialog(null, "Introduzca un nombre de ingrediente valido.");
					}
				}catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "Introduzca calorias validas(valor numerico positivo).");
				}

			}
		});
		formComponent.setSubmitButtonColor(new Color(128, 0, 0));

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
		backButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("backRest_icon.png")));
		backButton.setToolTipText("Volver a Ingredientes");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_INGREDIENTE, 0));
				dispose();
			}
		});

		backButtonContainer.add(backButton);

		return backButtonContainer;
	}

	@Override
	public void actualizar(Context res) {

		if (res.getEvento() == Eventos.RES_ALTA_INGREDIENTE_OK) {
			JOptionPane.showMessageDialog(null, "Se ha dado de alta correctamente el ingrediente con id: " + res.getDatos());
		} else if (res.getEvento() == Eventos.RES_ALTA_INGREDIENTE_KO) {
			JOptionPane.showMessageDialog(null, "No se ha podido dar de alta el ingrediente");
		}

		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_INGREDIENTE));
		dispose();
	}

}