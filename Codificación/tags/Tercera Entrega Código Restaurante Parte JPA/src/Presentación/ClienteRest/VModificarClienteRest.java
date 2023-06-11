package Presentación.ClienteRest;

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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Negocio.Cliente.TClientePremium;
import Negocio.ClienteRest.TClienteRest;

@SuppressWarnings("serial")
public class VModificarClienteRest extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;
	private ArrayList<JTextField> textFields;
	private TClienteRest cliente;
	private ArrayList<String> global;

	public VModificarClienteRest(TClienteRest cliente){
		super("Editar Cliente Restaurante");
		setTitle("Modificar Datos de Cliente Restaurante");
		textFields = new ArrayList<JTextField>();
		global = new ArrayList<>();
		this.cliente=cliente;
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
		global.add("Apellidos");
		global.add("DNI");
		
		ArrayList<String> values = new ArrayList<String>();
		values.add(cliente.getNombre());
		values.add(cliente.getApellidos());
		values.add(cliente.getDni());
		
		FormComponent formComponent = new FormComponent(global, "Editar Cliente", textFields, new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if(textFields.get(0).getText().length()>2 && textFields.get(1).getText().length()>2 && textFields.get(2).getText().length()>2){
					cliente.setNombre(textFields.get(0).getText());
					cliente.setApellidos(textFields.get(1).getText());
					cliente.setDni(textFields.get(2).getText());
					
					Controller.getInstance().action(new Context(Eventos.MODIFICAR_CLIENTE_REST, cliente));
					dispose();
				}else{
					JOptionPane.showMessageDialog(null, "Rellene todos los campos, por favor");
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
		backButton.setToolTipText("Volver a Clientes Restaurante");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);
		
		backButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_CLIENTE_REST));
				dispose();
			}
		});
			
		backButtonContainer.add(backButton);
		
		return backButtonContainer;
	}
	
	@Override
	public void actualizar(Context res) {
		if(res.getEvento()==Eventos.RES_MODIFICAR_CLIENTE_REST_OK){
			JOptionPane.showMessageDialog(null, "Cliente modificado correctamente");
		}else if(res.getEvento()==Eventos.RES_MODIFICAR_CLIENTE_REST_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido modificar el cliente");
		}
		
		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_CLIENTE_REST));
		dispose();
	}
}