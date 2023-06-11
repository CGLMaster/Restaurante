package Presentación.Proveedor;

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

import Negocio.Proveedor.TProveedor;


public class VModificarProveedor extends JFrame implements IGUI {
	
	private ArrayList<JTextField> textFields;
	private TProveedor proveedor;

	public VModificarProveedor(TProveedor pr){
		textFields = new ArrayList<JTextField>();
		this.proveedor = pr;
		init_GUI();
	}

	private void init_GUI() {
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);
		
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("icono2.png")).getImage();
		this.setIconImage(iconFrame);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500, 500));
		
		
		// BACK BUTTON CONTAINER
		JPanel backButtonContainer = backButtonContainer();

		// FORM COMPONENT
		ArrayList<String> names = new ArrayList<String>();
		names.add("Nombre");
		
		ArrayList<String> values = new ArrayList<String>();
		values.add(proveedor.getNombre());

		FormComponent formComponent = new FormComponent(names, "Editar Proveedor", textFields, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textFields.get(0).getText().length() > 0) {
					proveedor.setNombre(textFields.get(0).getText());

					Controller.getInstance().action(new Context(Eventos.UPDATE_PROVEEDOR, proveedor));
					
				} else {
					JOptionPane.showMessageDialog(null, "Rellene todos los campos, por favor");
				}
			}
		});

		formComponent.setValues(values);

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
		backButton.setBackground(new Color(237, 237, 237));
		backButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("back_icon.png")));	
		backButton.setToolTipText("Volver a Proveedores");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PROVEEDOR, 1));
				dispose();
			}
		});

		backButtonContainer.add(backButton);

		return backButtonContainer;
	}

	@Override
	public void actualizar(Context res) {
		
		int evento = res.getEvento();
		
		if(evento == Eventos.RES_MODIFICAR_PROVEEDOR_OK){
			JOptionPane.showMessageDialog(null, "Proveedor editado con exito.");
			Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PROVEEDOR, 1));
			dispose();
		}else if(evento == Eventos.RES_MODIFICAR_PROVEEDOR_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido modificar la información del proveedor.");
		}
		
		
	}
}