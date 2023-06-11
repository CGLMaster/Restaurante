package Presentación.Marca.VistasCasos_de_Uso;

import javax.swing.JFrame;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Marca.TMarca;
import Presentación.FormComponent;
import Presentación.Command.Context;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;
import Presentación.FactoriaVistas.IGUI;

@SuppressWarnings("serial")
public class VCrearMarca extends JFrame implements IGUI {
	
	private ArrayList<JTextField> textFields;
	
	public VCrearMarca(){
		super("Crear Marca");
		textFields = new ArrayList<JTextField>();
		init_GUI();
	}
	
	
	public void init_GUI(){
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400,100);
		
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("icono2.png")).getImage();
		this.setIconImage(iconFrame);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		//BACK BUTTON
		JPanel backbuttonContainer = backButtonContainer();
		
		//FORM COMPONENT
		ArrayList<String> names = new ArrayList<String>();
		names.add("Nombre");
		names.add("Web");
		
		FormComponent formComponent = new FormComponent(names, "Crear Marca", textFields, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TMarca marca = new TMarca(textFields.get(0).getText());
				if(textFields.get(1).getText().length() > 2){
					marca.setWeb(textFields.get(1).getText());
				}
				
				if(textFields.get(0).getText().length()>1) Controller.getInstance().action(new Context(Eventos.GUARDAR_MARCA, marca));
				else JOptionPane.showMessageDialog(null, "Nombre Intoducido no válido");
			}
		});
		
		
		//BUILD
		this.add(mainPanel);
		mainPanel.add(backbuttonContainer);
		mainPanel.add(formComponent);
		
		this.pack();
		this.setVisible(true);
	}

	private JPanel backButtonContainer() {
		JPanel backButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonContainer.setMaximumSize(new Dimension(1000, 50));
		
		JButton backButton = new JButton();
		backButton.setBackground(new Color(237, 237, 237));
		backButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("back_icon.png"))));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);
	
		backButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_MARCA));
				dispose();
			}
			
		});
		
		
		backButtonContainer.add(backButton);
		
		return backButtonContainer;
	}


	@Override
	public void actualizar(Context res) {
		if(res.getEvento() == Eventos.RES_ALTA_MARCA_OK){
			JOptionPane.showMessageDialog(null, "Marca creada correctamente cond id: " + res.getDatos());
		}
		else if(res.getEvento() == Eventos.RES_ALTA_MARCA_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido añadir marca");
		}
		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_MARCA));
		dispose();
		
	}
}