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

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Negocio.Turno.TTurno;

public class VModificarTurno extends JFrame implements IGUI {

	private ArrayList<JTextField> textFields;
	private TTurno turno;

	public VModificarTurno(TTurno turno) {
		super("Editar Turno");
		setTitle("Modificar Datos de Turno");
		textFields = new ArrayList<JTextField>();
		this.turno = turno;
		init_GUI();
		this.setLocationRelativeTo(null);
	}

	public void init_GUI() {
		this.getContentPane().removeAll();
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);
		
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("iconoRest2.png")).getImage();
		this.setIconImage(iconFrame);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		JPanel backButtonContainer = backButtonContainer();

		// FORM COMPONENT
		ArrayList<String> names = new ArrayList<String>();
		names.add("Nombre");
		names.add("Hora de Inicio");
		names.add("Hora Fin");

		ArrayList<String> values = new ArrayList<String>();
		values.add(turno.getNombre());
		values.add(turno.getHoraInicio());
		values.add(turno.getHoraFin());

		FormComponent formComponent = new FormComponent(names, "Editar Turno", textFields, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (textFields.get(0).getText().length() > 1) {
					turno.setNombre(textFields.get(0).getText());

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
							else if((HoraFin-HoraIni >8) || (HoraFin-HoraIni == 8 && MinFin-MinIni > 0) || (HoraFin-HoraIni > -16 && HoraFin-HoraIni <0) || ( HoraFin-HoraIni == 0 && MinFin-MinIni<=0)){
								JOptionPane.showMessageDialog(null,
										"Horario mal introducido, los horarios de trabajo no deben exceder las 8 horas diarias.", "Información", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getClassLoader().getResource("sans.gif")));
							}
							else{
								turno.setHoraInicio(HoraIni + ":" + MinIni);
								turno.setHoraFin(HoraFin + ":" + MinFin);
								Controller.getInstance().action(new Context(Eventos.UPDATE_TURNO, turno));
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
		backButton.setToolTipText("Volver a Turnos");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);

		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_TURNO));
				dispose();
			}

		});

		backButtonContainer.add(backButton);

		return backButtonContainer;
	}

	public void actualizar(Context res) {
		if (res.getEvento() == Eventos.RES_MODIFICAR_TURNO_OK) {
			JOptionPane.showMessageDialog(null, "Turno modificado correctamente con id " + res.getDatos());
		} else if (res.getEvento() == Eventos.RES_MODIFICAR_TURNO_KO) {
			JOptionPane.showMessageDialog(null,
					"No se ha podido modificar el Turno, asegúrese de que los datos introducidos son sintácticamemte correctos y no existen duplicados");
		}
		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_TURNO));
		dispose();
	}

}