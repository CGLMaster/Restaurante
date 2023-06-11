package Presentación.Plato;

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

import Negocio.Plato.TBebida;
import Negocio.Plato.TComida;
import Negocio.Plato.TPlato;

public class VModificarPlato extends JFrame implements IGUI {
	private ArrayList<JTextField> textFields;
	private TPlato plato;
	
	public VModificarPlato(TPlato plato){
		super("Editar Plato");
		this.plato = plato;
		textFields = new ArrayList<JTextField>();
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
		String atributoVariable = "variable";
		String valorAtributo = "nada";
		
		if(plato instanceof TComida){
			atributoVariable = "Categoria";
			TComida aux= (TComida) plato;
			valorAtributo = aux.getCategoria();
			
		}else if(plato instanceof TBebida){
			TBebida aux = (TBebida) plato;
			atributoVariable = "Volumen";
			valorAtributo = Double.toString(aux.getVolumen());
		}
		
		
		// FORM COMPONENT
		ArrayList<String> names = new ArrayList<String>();
		names.add("Nombre");
		names.add("Descripcion");
		names.add("Precio");
		names.add("Stock");
		names.add(atributoVariable);

		ArrayList<String> values = new ArrayList<String>();
		values.add(plato.getNombre());
		values.add(plato.getDescripcion());
		values.add(Double.toString(plato.getPrecio()));
		values.add(Integer.toString(plato.getStock()));
		values.add(valorAtributo);

		FormComponent formComponent = new FormComponent(names, "Editar Plato", textFields, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if (textFields.get(0).getText().length() > 1) {
						plato.setNombre(textFields.get(0).getText());
						plato.setDescripcion(textFields.get(1).getText());
						float precio = Float.parseFloat(textFields.get(2).getText());
						int stock = Integer.parseInt(textFields.get(3).getText());
						
						plato.setPrecio(precio);
						plato.setStock(stock);
						
						if(plato instanceof TComida){
							((TComida) plato).setCategoria(textFields.get(4).getText());
							
						}else if(plato instanceof TBebida){
							TBebida aux = (TBebida) plato;
							((TBebida) plato).setVolumen(Double.parseDouble(textFields.get(4).getText()));
						}
						
						if(precio > 0 && stock > 0){
							Controller.getInstance().action(new Context(Eventos.UPDATE_PLATO, plato));
							dispose();
						}
						
					}
				}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "El stock, el precio y el volumen deben ser valores numericos");
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

				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PLATO));
				dispose();
			}

		});

		backButtonContainer.add(backButton);

		return backButtonContainer;
	}
	
	@Override
	public void actualizar(Context res) {

		if (res.getEvento() == Eventos.RES_MODIFICAR_PLATO_OK) {
			JOptionPane.showMessageDialog(null, "Se ha modificado correctamente el plato");
		} else if (res.getEvento() == Eventos.RES_MODIFICAR_PLATO_KO) {
			JOptionPane.showMessageDialog(null, "No se ha podido dar de modificar el plato.");
		}

		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PLATO));
		dispose();
	}
}