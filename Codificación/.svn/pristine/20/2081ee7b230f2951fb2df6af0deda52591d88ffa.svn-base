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
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Negocio.Personal.TPersonal;
import Negocio.Personal.TTiempoCompleto;
import Negocio.Personal.TTiempoParcial;

public class VModificarPersonal extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;
	private ArrayList<JTextField> textFields;
	private TPersonal personal;
	private ArrayList<String> global;

	public VModificarPersonal(TPersonal personal){
		super("Editar Empleado");
		setTitle("Modificar Datos de Empleado");
		textFields = new ArrayList<JTextField>();
		this.personal=personal;
		this.global = new ArrayList<String>();
		init_GUI();
		this.setLocationRelativeTo(null);
	}
	
	public void init_GUI(){
		this.getContentPane().removeAll();
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500, 500));
		this.add(mainPanel);
		
		//BACK BUTTON
		JPanel backButtonContainer = backButtonContainer();
		
		//FORM COMPONENT
		global.add("Nombre");
		global.add("DNI");
		global.add("Id turno");
		if(personal instanceof TTiempoCompleto){
			global.add("Nomina");
			global.add("Horas extra trabajadas");
		}
		else if(personal  instanceof TTiempoParcial){
			global.add("Precio hora");
			global.add("Numero horas que trabaja");
		}
		
		ArrayList<String> values = new ArrayList<String>();
		values.add(personal.getNombre());
		values.add(personal.getDni());
		values.add(personal.getIDTurno()+"");
		if(personal instanceof TTiempoCompleto){
			TTiempoCompleto tiempoC = (TTiempoCompleto) personal;
			values.add(tiempoC.getNomina()+"");
			values.add(tiempoC.getHorasExtras()+"");
		}
		else if(personal  instanceof TTiempoParcial){
			TTiempoParcial tiempoP = (TTiempoParcial) personal;
			values.add(tiempoP.getPrecioHora()+"");
			values.add(tiempoP.getNumeroHoras()+"");
		}
		
		FormComponent formComponent = new FormComponent(global, "Editar Personal", textFields, new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if(comprobarDatos(textFields)){
					personal.setNombre(textFields.get(0).getText());
					personal.setDni(textFields.get(1).getText());
					personal.setId_turno(Integer.parseInt(textFields.get(2).getText()));
					if(personal instanceof TTiempoCompleto){
						((TTiempoCompleto) personal).setNomina(Double.parseDouble(textFields.get(3).getText()));
						((TTiempoCompleto) personal).setHorasExtras(Integer.parseInt(textFields.get(4).getText()));
					}
					else if(personal  instanceof TTiempoParcial){
						((TTiempoParcial) personal).setPrecioHora(Double.parseDouble(textFields.get(3).getText()));
						((TTiempoParcial) personal).setNumeroHoras(Integer.parseInt(textFields.get(4).getText()));
					}
					Controller.getInstance().action(new Context(Eventos.MODIFICAR_PERSONAL, personal));
					dispose();
				}else{
					JOptionPane.showMessageDialog(null, "Introduzca los campos correctamente");
				}
			}
		});
		
		formComponent.setSubmitButtonColor(new Color(128, 0, 0));
		formComponent.setValues(values);
		
		//CONSTRUIR VISTA
		this.add(mainPanel);
		mainPanel.add(backButtonContainer);
		mainPanel.add(formComponent);
		
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("iconoRest2.png")).getImage();
		this.setIconImage(iconFrame);
		
		this.pack();
		this.setVisible(true);
	}
	
	private JPanel backButtonContainer(){
		JPanel backButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonContainer.setMaximumSize(new Dimension(1000, 75));
		
		JButton backButton = new JButton();
		backButton.setContentAreaFilled(false);
		backButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("backRest_icon.png"))));
		backButton.setToolTipText("Volver a Personal Restaurante");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);
		
		backButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PERSONAL));
				dispose();
			}
		});
		
		backButtonContainer.add(backButton);
		
		return backButtonContainer;
	}
	
	private boolean comprobarDatos(ArrayList<JTextField> textFields){
		try {
			if(textFields.get(0).getText().length()>2 && textFields.get(1).getText().length()>2 && Integer.parseInt(textFields.get(2).getText())>0){
				if(personal instanceof TTiempoCompleto){
					double nomina = Double.parseDouble(textFields.get(3).getText());
					int horasExtra = Integer.parseInt(textFields.get(4).getText());
					if(nomina < 0 || horasExtra < 0){
						return false;
					}				
				}
				else if(personal  instanceof TTiempoParcial){
					double precioHora = Double.parseDouble(textFields.get(3).getText());
					int horasTrabajadas = Integer.parseInt(textFields.get(4).getText());
					if(precioHora < 0 || horasTrabajadas < 0){
						return false;
					}	
				}
			}
			else
				return false;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Formato invalido");
			return false;
		}
		return true;
	}
	
	@Override
	public void actualizar(Context res) {
		if(res.getEvento()==Eventos.RES_MODIFICAR_PERSONAL_OK){
			JOptionPane.showMessageDialog(null, "Empleado modificado correctamente");
		}else if(res.getEvento()==Eventos.RES_MODIFICAR_PERSONAL_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido modificar el empleado");
		}
		
		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PERSONAL));
		dispose();
	}
}