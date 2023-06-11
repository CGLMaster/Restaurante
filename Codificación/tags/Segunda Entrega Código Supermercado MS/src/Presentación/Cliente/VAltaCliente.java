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
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Cliente.TCliente;
import Negocio.Cliente.TClienteNoPremium;
import Negocio.Cliente.TClientePremium;
import Presentación.FormComponent;
import Presentación.Command.Context;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;
import Presentación.FactoriaVistas.IGUI;

@SuppressWarnings("serial")
public class VAltaCliente extends JFrame implements IGUI {
	
	private ActionListener prueba;
	private ArrayList<JTextField> textFields;
	JComboBox<String> selType;
	private ArrayList<String> global;
	private String string;
	FormComponent formComponent;
	
	public VAltaCliente(){
		super("Nuevo Cliente");
		setTitle("Alta Cliente");
		textFields = new ArrayList<JTextField>();
		global = new ArrayList<>();
		string = "";
		init_GUI();
	}

	@Override
	public void actualizar(Context res) {	
		if(res.getEvento() == Eventos.RES_ALTA_CLIENTE_OK ){
			JOptionPane.showMessageDialog(null, "Cliente creado correctamente con id " +(int) res.getDatos());
		}
		else if(res.getEvento() == Eventos.RES_ALTA_CLIENTE_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido añadir cliente");
		}
		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_CLIENTE));
		dispose();
	}

	public void init_GUI(){
		prueba = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TCliente cliente = null;
				try {
					if (textFields.get(0).getText().length() > 2 && textFields.get(1).getText().length() > 2
							&& textFields.get(2).getText().length() > 2) {
						if (selType.getSelectedItem().equals("No Premium")) {
							string = "formato invalido: debe introducir valores numericos en:\n nombre, mail, DNI";
							TClienteNoPremium clienteNP = new TClienteNoPremium();
							clienteNP.setNombre(textFields.get(0).getText());
							clienteNP.setMail(textFields.get(1).getText());
							clienteNP.setDNI(textFields.get(2).getText());
							clienteNP.setEsActivo(true);
							clienteNP.setNumCompras(0);
							cliente = clienteNP;
							
						} else {
							string = "Formato invalido: debe introducir valores numericos en:\n nombre, mail, DNI, telefono, direccion y fecha(yyyy/mm/dd).";
							TClientePremium clienteP = new TClientePremium();
							String[] dateNums = textFields.get(3).getText().split("/");
							LocalDate date_cadud = null;

							if (dateNums.length == 3) {
								int year = Integer.parseInt(dateNums[0]);
								int month = Integer.parseInt(dateNums[1]);
								int day = Integer.parseInt(dateNums[2]);
								date_cadud = LocalDate.of(year, month, day);
							}else throw new NumberFormatException();
							
							clienteP.setNombre(textFields.get(0).getText());
							clienteP.setMail(textFields.get(1).getText());
							clienteP.setDNI(textFields.get(2).getText());
							clienteP.setTelefono(textFields.get(4).getText());
							clienteP.setDireccion(textFields.get(5).getText());
							clienteP.setEsActivo(true);
							clienteP.setAntiguedad(date_cadud);
							cliente = clienteP;
						}

						Controller.getInstance().action(new Context(Eventos.ALTA_CLIENTE, cliente));
						dispose();
					}
					else JOptionPane.showMessageDialog(null, "Rellene todos los campos, por favor");
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
		
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		this.add(mainPanel);
		
		JPanel backButtonContainer = backButtonContainer();
		mainPanel.add(backButtonContainer);
		
		selType = new JComboBox<String>();
		selType.setToolTipText("Tipo de cliente");
		selType.addItem("No Premium");
		selType.addItem("Premium");
		
		selType.setMaximumSize(new Dimension(440, 30));
		
		mainPanel.add(selType);
		
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
		
		global = noPremium;
		
		
		//FORM COMPONENT
		
		
		formComponent = new FormComponent(global, "Alta Cliente No Premium", textFields, prueba);
		
		selType.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (selType.getSelectedItem().equals("Premium")) {
					global = premium;
					mainPanel.remove(formComponent);
					textFields = new ArrayList<JTextField>();
					formComponent = new FormComponent(global, "Alta Cliente Premium", textFields, prueba);
					mainPanel.add(formComponent);
					mainPanel.revalidate();
					mainPanel.repaint();

				} else if (selType.getSelectedItem().equals("No Premium")) {
					global = noPremium;
					mainPanel.remove(formComponent);
					textFields = new ArrayList<JTextField>();
					formComponent = new FormComponent(global, "Alta Cliente No Premium", textFields, prueba);
					mainPanel.add(formComponent);
					mainPanel.revalidate();
					mainPanel.repaint();
				}
			}
		});
		
		mainPanel.add(formComponent);
		
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("icono2.png")).getImage();
		this.setIconImage(iconFrame);
		
		this.pack();
		this.setVisible(true);
	}
	
	private JPanel backButtonContainer(){
		//BACK BUTTON
		JPanel backButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonContainer.setMaximumSize(new Dimension(1000, 50));
		
		JButton backButton = new JButton();
		
		backButton.setBackground(new Color(237, 237, 237));
		backButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("back_icon.png"))));
		backButton.setToolTipText("Volver a Clientes");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);
		backButtonContainer.add(backButton);
	
		backButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_CLIENTE));
				dispose();
			}
			
		});
		
		return backButtonContainer;
	}

}