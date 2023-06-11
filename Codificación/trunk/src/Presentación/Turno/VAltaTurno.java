package Presentación.Turno;

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

import Negocio.Turno.TTurno;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class VAltaTurno extends JFrame implements IGUI {
	ArrayList<JTextField> textFields;
	
	public VAltaTurno(){
		super("Nuevo Turno");
		setTitle("Alta Turno");
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
		names.add("Hora inicio");
		names.add("Hora fin");

		FormComponent formComponent = new FormComponent(names, "Alta Turno", textFields, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TTurno turno = new TTurno(textFields.get(0).getText());
				
				if (textFields.get(0).getText().length() > 1) {

					String[] horarioIni = textFields.get(1).getText().split(":");
					String[] horarioFin = textFields.get(2).getText().split(":");

					try {
						if (horarioIni.length != 2 || horarioFin.length != 2) {
							JOptionPane.showMessageDialog(null,
									"Horario mal introducido, asegúrese de separar las horas de los minutos por el símbolo ':'.");
						} else {
							int HoraIni = Integer.parseInt(horarioIni[0]);
							int MinIni = Integer.parseInt(horarioIni[1]);
							int HoraFin = Integer.parseInt(horarioFin[0]);
							int MinFin = Integer.parseInt(horarioFin[1]);

							if (HoraIni >= 24 || HoraFin >= 24 || MinIni >= 60 || MinFin >= 60 || HoraIni < 0
									|| HoraFin < 0 || MinIni < 0 || MinFin < 0)
								JOptionPane.showMessageDialog(null,
										"Horario mal introducido, las horas deben indicarse entre 0 y 23 y los minutos entre 0 y 59.");
							else if((HoraFin-HoraIni >8) || (HoraFin-HoraIni == 8 && MinFin-MinIni > 0) || (HoraFin-HoraIni > -16 && HoraFin-HoraIni <0) || (HoraFin-HoraIni == 0 && MinFin-MinIni==0)){
								JOptionPane.showMessageDialog(null,
										"Horario mal introducido, los horarios de trabajo no deben exceder las 8 horas diarias.", "Información", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getClassLoader().getResource("sans.gif")));
							}
							else{
								turno.setHoraInicio(textFields.get(1).getText());
								turno.setHoraFin(textFields.get(2).getText());
								Controller.getInstance().action(new Context(Eventos.GUARDAR_TURNO, turno));
								dispose();
								
							}
						}

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Error en el horario, introduzca un valor numérico.");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Introduzca un nombre (único) válido.");
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
		backButton.setToolTipText("Volver a Turnos");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_TURNO, 0));
				dispose();
			}
		});

		backButtonContainer.add(backButton);

		return backButtonContainer;
	}


	@Override
	public void actualizar(Context res) {
		
		if(res.getEvento() == Eventos.RES_ALTA_TURNO_OK){
			JOptionPane.showMessageDialog(null, "Se ha dado de alta correctamente al turno con id: " + res.getDatos());
		}else if(res.getEvento() == Eventos.RES_ALTA_TURNO_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido dar de alta el turno.");
		}
		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_TURNO));
		dispose();
	}
	
}