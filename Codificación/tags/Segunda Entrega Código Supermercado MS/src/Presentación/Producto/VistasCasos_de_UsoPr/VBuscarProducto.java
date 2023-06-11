package Presentación.Producto.VistasCasos_de_UsoPr;


import javax.swing.JFrame;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import Negocio.Producto.TProdNoPerecedero;
import Negocio.Producto.TProdPerecedero;
import Negocio.Producto.TProducto;
import Presentación.Command.Context;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;
import Presentación.FactoriaVistas.IGUI;

import javax.swing.JLabel;
import javax.swing.JOptionPane;


@SuppressWarnings("serial")
public class VBuscarProducto extends JFrame implements IGUI {
	private TProducto producto;
	
	public VBuscarProducto(TProducto producto){
		this.producto = producto;
		init_GUI();
	}
	
	private void init_GUI() {
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);
		
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("icono2.png")).getImage();
		this.setIconImage(iconFrame);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500,500));
		mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		// BACK BUTTON 
		JPanel backButtonContainer = backButtonContainer();
		
		// TITULO
		JLabel titleLabel = new JLabel("Producto");
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, 20));
		
		// CONTENT CONTAINER
		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);
		contentContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentContainer.setBackground(Color.white);
		
		// INFO
		JLabel idLabel = new JLabel("ID: " + producto.getIDProducto());
		JLabel nombreLabel = new JLabel("Nombre: " + producto.getNombre());
		JLabel tipoProd = new JLabel("Tipo: ");
		JLabel especial = new JLabel();
		if(producto instanceof TProdPerecedero){
			TProdPerecedero pe = (TProdPerecedero) producto;
			tipoProd.setText(tipoProd.getText() + "Perecedero");
			especial.setText("Fecha de caducidad: " + pe.getFechaDeCaducidad());
		}else{
			TProdNoPerecedero pn = (TProdNoPerecedero) producto;
			tipoProd.setText(tipoProd.getText() + "No perecedero");
			especial.setText("Tipo de no perecedero: " + pn.getTipo());
		}
		JLabel precioLabel = new JLabel("Precio: " + producto.getPrecio());
		JLabel stockLabel = new JLabel("Stock: " + producto.getStock());
		JLabel marcaLabel = new JLabel("Marca: " + producto.getIDMarca());
		JLabel seccionLabel = new JLabel("Seccion: " + producto.getIDSeccion());
		
		JLabel activoLabel = new JLabel();
		if(producto.getActivo()){
			activoLabel.setText("Activo");
			activoLabel.setForeground(Color.green);
		}else{
			activoLabel.setText("Inactivo");
			activoLabel.setForeground(Color.red);
		}
		
		
		// CONSTRUIR VISTA
		this.add(mainPanel);
		mainPanel.add(backButtonContainer);
		mainPanel.add(titleLabel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(contentContainer);
			contentContainer.add(idLabel);
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
			contentContainer.add(nombreLabel);
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
			contentContainer.add(tipoProd);
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
			contentContainer.add(especial);
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
			contentContainer.add(precioLabel);
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
			contentContainer.add(stockLabel);
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
			contentContainer.add(marcaLabel);
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
			contentContainer.add(seccionLabel);
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
			contentContainer.add(activoLabel);
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		
		this.pack();
		this.setVisible(true);
	}
	
	private JPanel backButtonContainer(){
		
		JPanel backButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonContainer.setMaximumSize(new Dimension(1000, 75));
		
		JButton backButton = new JButton();
		backButton.setBackground(new Color(237, 237, 237));
		backButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("back_icon.png")));
		backButton.setToolTipText("Volver a Productos");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);
	
		backButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PRODUCTO));
				dispose();
			}
			
		});
		
		
		backButtonContainer.add(backButton);
		
		return backButtonContainer;
	}

	@Override
	public void actualizar(Context res) {
		if(res.getEvento() == Eventos.RES_BUSCAR_PRODUCTO_KO){}
		else if(res.getEvento() == Eventos.RES_BUSCAR_PRODUCTO_KO){
			JOptionPane.showMessageDialog(null, "No se ha encontrado el producto");
		}
		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PRODUCTO));
		dispose();
	}
}