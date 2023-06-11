package Presentación.Personal;

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
import javax.swing.JTextField;

import Negocio.Personal.TPersonal;
import Negocio.Personal.TTiempoCompleto;
import Negocio.Personal.TTiempoParcial;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;


public class VAltaPersonal extends JFrame implements IGUI {
	
	
	private static final long serialVersionUID = 1L;
	private ActionListener prueba;
	private ArrayList<JTextField> textFields;
	JComboBox<String> selType;
	private ArrayList<String> global;
	private String string;
	FormComponent formComponent;

	public VAltaPersonal() {
		super("Nuevo Personal");
		setTitle("Alta Personal");
		textFields = new ArrayList<JTextField>();
		global = new ArrayList<>();
		string = "";
		init_GUI();
		this.setLocationRelativeTo(null);
	}

	@Override
	public void actualizar(Context res) {
		if (res.getEvento() == Eventos.RES_ALTA_PERSONAL_OK) {
			JOptionPane.showMessageDialog(null, "Personal creado correctamente con id " + (int) res.getDatos());
		} else if (res.getEvento() == Eventos.RES_ALTA_PERSONAL_KO) {
			JOptionPane.showMessageDialog(null, "No se ha podido añadir personal");
		}
		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PERSONAL));
		dispose();
	}

	public void init_GUI() {
		prueba = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TPersonal personal = null;
				try {
					if (selType.getSelectedItem().equals("Tiempo Completo")){
					 if (textFields.get(0).getText().length() > 2 && textFields.get(1).getText().length() > 2
							 && Integer.parseInt(textFields.get(2).getText()) > 0 && Double.parseDouble(textFields.get(3).getText()) >= 0 && Integer.parseInt(textFields.get(4).getText()) >=0) {
						
							string = "formato invalido: debe introducir valores numericos en:\n nombre, DNI,ID_turno, nomina,horasExtras";
							TTiempoCompleto personalTC = new TTiempoCompleto();
							personalTC.setNombre(textFields.get(0).getText());
							personalTC.setDni(textFields.get(1).getText());
							personalTC.setId_turno(Integer.parseInt(textFields.get(2).getText()));
							personalTC.setNomina(Double.parseDouble(textFields.get(3).getText()));
							personalTC.setHorasExtras(Integer.parseInt(textFields.get(4).getText()));
							personalTC.setActivo(true);
							personalTC.setNumPedidos(0);
							personal = personalTC;
							Controller.getInstance().action(new Context(Eventos.ALTA_PERSONAL, personal));
							dispose();
					 }else
							JOptionPane.showMessageDialog(null, "Rellene todos los campos, por favor");

					} else {
							string = "Formato invalido: debe introducir valores numericos en:\n nombre, DNI,ID_turno, horasExtras";
							TTiempoParcial personalTP = new TTiempoParcial();
							if (textFields.get(0).getText().length() > 2 && textFields.get(1).getText().length() > 2 &&
									Integer.parseInt(textFields.get(2).getText()) > 0 && Double.parseDouble(textFields.get(3).getText()) >= 0 && Integer.parseInt(textFields.get(4).getText()) >= 0) {

							personalTP.setNombre(textFields.get(0).getText());
							personalTP.setDni(textFields.get(1).getText());
							personalTP.setId_turno(Integer.parseInt(textFields.get(2).getText()));
							personalTP.setPrecioHora(Double.parseDouble(textFields.get(3).getText()));
							personalTP.setNumeroHoras(Integer.parseInt(textFields.get(4).getText()));
							personalTP.setActivo(true);
							personalTP.setNumPedidos(0);
							
							personal = personalTP;
							
							Controller.getInstance().action(new Context(Eventos.ALTA_PERSONAL, personal));
							dispose();
							
							}else
								JOptionPane.showMessageDialog(null, "Rellene todos los campos, por favor");

						
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, string);
				}
			}
		};
		this.getContentPane().removeAll();
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.add(mainPanel);

		JPanel backButtonContainer = backButtonContainer();
		mainPanel.add(backButtonContainer);

		selType = new JComboBox<String>();
		selType.setToolTipText("Tipo de personal");
		selType.addItem("Tiempo Completo");
		selType.addItem("Tiempo Parcial");

		selType.setMaximumSize(new Dimension(440, 30));

		mainPanel.add(selType);

		ArrayList<String> tiempoCompleto = new ArrayList<String>();
		tiempoCompleto.add("Nombre");
		tiempoCompleto.add("DNI");
		tiempoCompleto.add("ID_Turno");
		tiempoCompleto.add("Nomina");
		tiempoCompleto.add("Horas Extras");

		ArrayList<String> tiempoParcial = new ArrayList<String>();
		tiempoParcial.add("Nombre");
		tiempoParcial.add("DNI");
		tiempoParcial.add("ID_Turno");
		tiempoParcial.add("Precio por horas");
		tiempoParcial.add("Horas a trabajar");

		global = tiempoCompleto;

		//FORM COMPONENT

		formComponent = new FormComponent(global, "Alta de Personal Tiempo Completo", textFields, prueba);

		selType.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (selType.getSelectedItem().equals("Tiempo Completo")) {
					global = tiempoCompleto;
					mainPanel.remove(formComponent);
					textFields = new ArrayList<JTextField>();
					formComponent = new FormComponent(global, "Alta de Personal Tiempo Completo", textFields, prueba);
					formComponent.setSubmitButtonColor(new Color(128, 0, 0));
					mainPanel.add(formComponent);
					mainPanel.revalidate();
					mainPanel.repaint();

				} else if (selType.getSelectedItem().equals("Tiempo Parcial")) {
					global = tiempoParcial;
					mainPanel.remove(formComponent);
					textFields = new ArrayList<JTextField>();
					formComponent = new FormComponent(global, "Alta de Personal Tiempo Parcial", textFields, prueba);
					formComponent.setSubmitButtonColor(new Color(128, 0, 0));
					mainPanel.add(formComponent);
					mainPanel.revalidate();
					mainPanel.repaint();
				}
			}
		});
		formComponent.setSubmitButtonColor(new Color(128, 0, 0));
		mainPanel.add(formComponent);

		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("iconoRest2.png")).getImage();
		this.setIconImage(iconFrame);

		this.pack();
		this.setVisible(true);
	}

	private JPanel backButtonContainer() {
		//BACK BUTTON
		JPanel backButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonContainer.setMaximumSize(new Dimension(1000, 50));

		JButton backButton = new JButton();

		backButton.setBackground(new Color(237, 237, 237));
		backButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("backRest_icon.png"))));
		backButton.setToolTipText("Volver a Empleados");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);
		backButtonContainer.add(backButton);

		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PERSONAL));
				dispose();
			}

		});

		return backButtonContainer;
	}
}