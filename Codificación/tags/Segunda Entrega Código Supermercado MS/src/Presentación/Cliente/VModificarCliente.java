package Presentación.Cliente;

import javax.swing.JFrame;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import Negocio.Cliente.TCliente;
import Negocio.Cliente.TClientePremium;
import Presentación.FormComponent;
import Presentación.Command.Context;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;
import Presentación.FactoriaVistas.IGUI;


@SuppressWarnings("serial")
public class VModificarCliente extends JFrame implements IGUI {
	private ArrayList<JTextField> textFields;
	private TCliente cliente;
	private ArrayList<String> global;
	
	public VModificarCliente(TCliente cliente){
		super("Editar Cliente");
		setTitle("Modificar Datos de Cliente");
		textFields = new ArrayList<JTextField>();
		this.cliente = cliente;
		init_GUI();
	}
	
	public void init_GUI(){
		this.getContentPane().removeAll();
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		
		JPanel backButtonContainer = backButtonContainer();
		
		// FORM COMPONENT
		ArrayList<String> noPremium = new ArrayList<String>();
		noPremium.add("Nombre");
		noPremium.add("Mail");
		noPremium.add("DNI");
		
		ArrayList<String> premium = new ArrayList<String>();
		premium.add("Nombre");
		premium.add("Mail");
		premium.add("DNI");
		premium.add("Antiguedad");
		premium.add("Telefono");
		premium.add("Direccion");
		
		ArrayList<String> values = new ArrayList<String>();
        values.add(cliente.getNombre());
        values.add(cliente.getMail());
        values.add(cliente.getDNI());
		
		if (cliente instanceof TClientePremium){
			global = premium;
			values.add(((TClientePremium) cliente).getAntiguedad().toString().replace('-', '/'));
			values.add(((TClientePremium) cliente).getTelefono());
			values.add(((TClientePremium) cliente).getDireccion());
		}
		else global = noPremium;
		
		
		FormComponent formComponent = new FormComponent(global, "Editar Cliente", textFields, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (textFields.get(0).getText().length() > 2 && textFields.get(1).getText().length() > 2
						&& textFields.get(2).getText().length() > 2) {
					if (cliente instanceof TClientePremium) {
						cliente.setNombre(textFields.get(0).getText());
						cliente.setMail(textFields.get(1).getText());
						cliente.setDNI(textFields.get(2).getText());
						String[] dateNums = textFields.get(3).getText().split("/");
						LocalDate date_cadud = null;

						if (dateNums.length == 3) {
							int year = Integer.parseInt(dateNums[0]);
							int month = Integer.parseInt(dateNums[1]);
							int day = Integer.parseInt(dateNums[2]);
							date_cadud = LocalDate.of(year, month, day);
						}
						((TClientePremium) cliente).setAntiguedad(date_cadud);
						((TClientePremium) cliente).setTelefono(textFields.get(4).getText());
						((TClientePremium) cliente).setDireccion(textFields.get(5).getText());
					}
					else {
						cliente.setNombre(textFields.get(0).getText());
						cliente.setMail(textFields.get(1).getText());
						cliente.setDNI(textFields.get(2).getText());
					}
					
					
					Controller.getInstance().action(new Context(Eventos.MODIFICAR_CLIENTE, cliente));
					dispose();
				}else{
					JOptionPane.showMessageDialog(null, "Rellene todos los campos, por favor");
				}
			}
		});
		
		formComponent.setValues(values);
		
		// CONSTRUIR VISTA
		this.add(mainPanel);
		mainPanel.add(backButtonContainer);
		mainPanel.add(formComponent);
		
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("icono2.png")).getImage();
		this.setIconImage(iconFrame);
		
		this.pack();
		this.setVisible(true);
	}
	
	private JPanel backButtonContainer(){
		
		JPanel backButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonContainer.setMaximumSize(new Dimension(1000, 75));
		
		JButton backButton = new JButton();
		backButton.setBackground(new Color(237, 237, 237));
		backButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("back_icon.png"))));	
		backButton.setToolTipText("Volver a Clientes");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);
	
		backButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_CLIENTE));
				dispose();
			}
			
		});
		
		
		backButtonContainer.add(backButton);
		
		return backButtonContainer;
	}
	
	@Override
	public void actualizar(Context res) {
		if(res.getEvento() == Eventos.RES_MODIFICAR_CLIENTE_OK){
			JOptionPane.showMessageDialog(null, "Cliente modificado correctamente");
		}else if(res.getEvento() == Eventos.RES_MODIFICAR_CLIENTE_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido modificar cliente");
		}
		
		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_CLIENTE));
		dispose();
	}

}