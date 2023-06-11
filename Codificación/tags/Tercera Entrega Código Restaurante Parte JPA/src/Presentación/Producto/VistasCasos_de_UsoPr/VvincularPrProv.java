package Presentación.Producto.VistasCasos_de_UsoPr;

import javax.swing.JFrame;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Producto.TDistribuye;
import Presentación.FormComponent;
import Presentación.Command.Context;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;
import Presentación.FactoriaVistas.IGUI;


@SuppressWarnings("serial")
public class VvincularPrProv extends JFrame implements IGUI {
	private ArrayList<JTextField> textFields;
	int producto;
	
	public VvincularPrProv(int idProd){	
		producto = idProd;
		this.setTitle("Vincular producto a proveedor");
		textFields = new ArrayList<JTextField>();
		init_GUI();
		this.setLocationRelativeTo(null);
	}
	
	private void init_GUI() {
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);
		
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("icono2.png")).getImage();
		this.setIconImage(iconFrame);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		
		// BACK BUTTON
		JPanel backButtonContainer = backButtonContainer();
		
		//FORM COMPONENT
		ArrayList<String> names = new ArrayList<String>();
		names.add("ID Proveedor");
				
		FormComponent formComponent = new FormComponent(names, "Vincular Producto a Proveedor", textFields, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					TDistribuye tDist = new TDistribuye(producto, Integer.parseInt(textFields.get(0).getText()));
					Controller.getInstance().action(new Context(Eventos.GUARDAR_VINCULAR, tDist));
				}catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "ID no valido.");
				}
			}
		});


		// CONSTRUIR VISTA
		this.add(mainPanel);
		mainPanel.add(backButtonContainer);
		mainPanel.add(formComponent);
				
				
		this.pack();
		this.setVisible(true);
		
	}


	@Override
	public void actualizar(Context res) {
		
		if(res.getEvento() == Eventos.RES_VINCULAR_PRODUCTO_PROVEEDOR_OK){
			JOptionPane.showMessageDialog(null, "Vinculacion realizada correctamente");
		}else if(res.getEvento() == Eventos.RES_VINCULAR_PRODUCTO_PROVEEDOR_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido realizar la vinculacion");
		}
		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PRODUCTO));
		dispose();
		
	}
	
	private JPanel backButtonContainer() {

		JPanel backButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonContainer.setMaximumSize(new Dimension(1000, 75));

		JButton backButton = new JButton();
		backButton.setBackground(new Color(237, 237, 237));
		backButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("back_icon.png")));
		backButton.setToolTipText("Volver a Productos");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(JPanel.LEFT_ALIGNMENT);

		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PRODUCTO));
				dispose();
			}

		});

		backButtonContainer.add(backButton);

		return backButtonContainer;
	}
	
}