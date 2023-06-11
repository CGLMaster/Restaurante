package Presentación.ClienteRest;

import javax.swing.JFrame;
import Presentación.FactoriaVistas.IGUI;

import java.util.ArrayList;
import javax.swing.JTextField;

import Negocio.ClienteRest.TClienteRest;

import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;
import Presentación.FormComponent;
import Presentación.Command.Context;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VAltaClienteRest extends JFrame implements IGUI {
	

private static final long serialVersionUID = 1L;
private ArrayList<JTextField> textFields;
	

public VAltaClienteRest() {
	super("Alta Cliente Restaurante");
	textFields = new ArrayList<JTextField>();
	init_GUI();
	this.setLocationRelativeTo(null);
}

public void init_GUI() {
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
	ArrayList<String> names = new ArrayList<String>();
	names.add("Nombre");
	names.add("Apellidos");
	names.add("DNI");

	FormComponent formComponent = new FormComponent(names, "Alta Cliente Restaurante", textFields, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (textFields.get(0).getText().length() > 2 && textFields.get(1).getText().length() > 2
								&& textFields.get(2).getText().length() > 2) {
				TClienteRest cliente = new TClienteRest();
				cliente.setNombre(textFields.get(0).getText());
				cliente.setApellidos(textFields.get(1).getText());
				cliente.setDni(textFields.get(2).getText());
				cliente.setActivo(true);
				Controller.getInstance().action(new Context(Eventos.ALTA_CLIENTE_REST, cliente));
			} else {
				JOptionPane.showMessageDialog(null, "Rellene todos los campos, por favor");
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
	backButton.setToolTipText("Volver a Clientes Restaurante");
	backButton.setPreferredSize(new Dimension(60, 60));
	backButton.setBorderPainted(false);
	backButton.setAlignmentX(LEFT_ALIGNMENT);

	backButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_CLIENTE_REST, 0));
			dispose();
		}
	});

	backButtonContainer.add(backButton);

	return backButtonContainer;
}

@Override
public void actualizar(Context res) {
	
	
	if (res.getEvento() == Eventos.RES_ALTA_CLIENTE_REST_OK) {
		JOptionPane.showMessageDialog(null, "Cliente creado con exito con id: " + (int)res.getDatos());
	} else if (res.getEvento() == Eventos.RES_ALTA_CLIENTE_REST_KO) {
		JOptionPane.showMessageDialog(null, "No se ha podido anadir el cliente");
	}
	
	Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_CLIENTE_REST, 0));
	dispose();
}



	
}