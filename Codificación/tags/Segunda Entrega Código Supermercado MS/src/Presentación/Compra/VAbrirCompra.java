package Presentación.Compra;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Negocio.Compra.TCarrito;
import Negocio.Compra.TLineaDeCompra;
import Presentación.Command.Context;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;
import Presentación.FactoriaVistas.IGUI;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

public class VAbrirCompra extends JFrame implements IGUI {
	
	private TCarrito carrito;

	public VAbrirCompra(TCarrito carrito) throws HeadlessException {
		this.carrito=carrito;
		init_GUI();
	}
	
	public void init_GUI(){
		this.getContentPane().removeAll();

		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("icono2.png")).getImage();
		this.setIconImage(iconFrame);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500,500));


		// CONTENT CONTAINER
		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);
		contentContainer.setAutoscrolls(true);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));			
			
		// AÑADIR PRODUCTOS CONTAINER
		JPanel addProductsContainer = new JPanel(new GridLayout(0, 3, 10, 10));
		addProductsContainer.setMaximumSize(new Dimension(1000, 30));
		addProductsContainer.setOpaque(false);
			
			// ID PRODUCTO INPUT 
			JTextField idInput = new JTextField(6);
			idInput.setText("ID de producto");
			idInput.setForeground(Color.GRAY);
			idInput.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {

					idInput.setText(null);
					idInput.setForeground(Color.BLACK);
				}

				public void mousePressed(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
				
			});;
			
			// ID PRODUCTO INPUT 
			JTextField cantidadInput = new JTextField(6);
			cantidadInput.setText("Cantidad");
			cantidadInput.setForeground(Color.GRAY);
			cantidadInput.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {

					cantidadInput.setText(null);
					cantidadInput.setForeground(Color.BLACK);
				}

				public void mousePressed(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
				
			});	

			
			// AÑADIR BUTTON
			JButton addButton = new JButton("Añadir");
			addButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("añadir_icon.png"))));
			addButton.setBackground(new Color(39, 174, 95));
			addButton.setForeground(Color.white);
			addButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {

					try{
						HashMap<String, Object> args = new HashMap<String, Object>();
						
						args.put("LineaDeCompra", new TLineaDeCompra(Integer.parseInt(idInput.getText()), Integer.parseInt(cantidadInput.getText())));
						args.put("Carrito", carrito);
						
						Controller.getInstance().action(new Context(Eventos.ANIADIR_PRODUCTO_COMPRA, args));
					}catch(NumberFormatException a){
						JOptionPane.showMessageDialog(null, "Introduzca valor numerico");
					}
				}
			});
			
		
		// PRODUCTOS EN CARRITO CONTAINER
		JPanel gridOutsideProductsContainer = new JPanel(new GridLayout(0,1));
		JPanel productosContainer = new JPanel();
		productosContainer.setLayout(new BoxLayout(productosContainer, BoxLayout.Y_AXIS));
		productosContainer.setBorder(new EmptyBorder(10, 10, 10, 10));
		productosContainer.setBackground(new Color(197, 233, 205));
		
		//PANEL DEL FINAL
		
		JPanel bottomContainer = bottomContainer();
		
		// CONSTRUIR VISTA
		this.add(mainPanel);
		JScrollPane scrollFrame = new JScrollPane(contentContainer);
		mainPanel.add(scrollFrame);
		
			// BACK BUTTON
			contentContainer.add(backButtonContainer());
			
			contentContainer.add(Box.createRigidArea(new Dimension(0, 15)));
			
			// AÑADIR PRODUCTOS
			contentContainer.add(addProductsContainer);
				addProductsContainer.add(idInput);
				addProductsContainer.add(cantidadInput);
				addProductsContainer.add(addButton);
			
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
			
			// PRODUCTOS EN CARRITO
			contentContainer.add(gridOutsideProductsContainer);
			gridOutsideProductsContainer.add(productosContainer);
			
				if(carrito.getLineaDeCompra().isEmpty()){
					productosContainer.setLayout(new GridLayout(0,1));
					productosContainer.add(new JLabel("Carrito vacío", SwingConstants.CENTER));
				}
				
				for(TLineaDeCompra producto : carrito.getLineaDeCompra()){
					productosContainer.add(productoPanel(producto));
					productosContainer.add(Box.createRigidArea(new Dimension(0, 10)));
				}
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
			contentContainer.add(bottomContainer);
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		
		
		this.pack();
		this.setVisible(true);
	}
	
	
	private JPanel productoPanel(TLineaDeCompra prod){
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.white);
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setMaximumSize(new Dimension(1000, 100));
		
		// BUTTON CONTAINER
		JPanel buttonContainer = new JPanel(new FlowLayout());
		buttonContainer.setOpaque(false);
		buttonContainer.setSize(buttonContainer.getPreferredSize());
		
		// DELETE BUTTON
		JButton deleteButton = new JButton("Delete");
		deleteButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("trash_icon.jpg"))));
		deleteButton.setBackground(new Color(236, 115, 115));
		deleteButton.setForeground(Color.white);
		deleteButton.setBorderPainted(false);
		
		deleteButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				HashMap<String, Object> args = new HashMap<String, Object>();
				args.put("LineaDeCompra", prod);
				args.put("Carrito", carrito);
				
				Controller.getInstance().action(new Context(Eventos.ELIMINAR_PRODUCTO_COMPRA, args));
			}
			
		});
		
		//PRODUCT INFO CONTAINER
		JPanel productInfoContainer = new JPanel();
		productInfoContainer.setLayout(new BoxLayout(productInfoContainer,BoxLayout.Y_AXIS));
		productInfoContainer.setOpaque(false);
		productInfoContainer.setAlignmentY(CENTER_ALIGNMENT);
		
		//LABELS
		JLabel idYCantidad = new JLabel(prod.getIDProducto() + " X " + prod.getCantidad());
		idYCantidad.setFont(new Font(idYCantidad.getFont().getName(), Font.PLAIN, 15));

		
		// CONSTRUIR PANEL
		panel.add(productInfoContainer, BorderLayout.WEST);
			productInfoContainer.add(idYCantidad);
		panel.add(buttonContainer, BorderLayout.EAST);
			buttonContainer.add(deleteButton);
		
		return panel;
	}
	
private JPanel backButtonContainer() {
		
		JPanel backButtonContainer = new JPanel(new BorderLayout());
		backButtonContainer.setMaximumSize(new Dimension(1000, 50));
		backButtonContainer.setOpaque(false);
		
		JButton backButton = new JButton();
		backButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("back_icon.png"))));
		backButton.setBackground(new Color(237, 237, 237));
		backButton.setBorderPainted(false);
	
		backButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Controller.getInstance().action(new Context(Eventos.CREAR_VPRINCIPAL, carrito.getCompra().getIdTrabajador()));
				dispose();
			}
		});
		
		// TITLE
		JLabel title = new JLabel("Carrito");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 25));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
				
		backButtonContainer.add(backButton, BorderLayout.WEST);
		
		return backButtonContainer;
	}
	private JPanel bottomContainer(){
		JPanel bottomContainer = new JPanel();
		bottomContainer.setLayout(new BoxLayout(bottomContainer, BoxLayout.X_AXIS));
		bottomContainer.setMaximumSize(new Dimension(1000, 50));
		bottomContainer.setOpaque(false);
		
		
		
		//PAGAR BUTTON
		JButton pagarButton = new JButton("Pagar");
		pagarButton.setBackground(new Color(39, 174, 95));
		pagarButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("pago_icon.jpg"))));
		pagarButton.setForeground(Color.white);
		pagarButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				
				if(carrito.getLineaDeCompra().size() > 0){
					
					Controller.getInstance().action(new Context(Eventos.VALIDAR_COMPRA_SIN_CLIENTE, carrito));
					
				}else{
					JOptionPane.showMessageDialog(null, "Operacion no permitida el carrito esta vacio");
				}
				
				
			}
		});
		
		
		//AYUDA BUTTON
		JButton botonAyuda = new JButton();
		JDialog vIden = new VMostrarAyuda();
		botonAyuda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vIden.setVisible(true);
			}
		});
		
		botonAyuda.setBackground(new Color(237, 237, 237));
		botonAyuda.setBorderPainted(false);
		botonAyuda.setPreferredSize(new Dimension(60, 50));
		botonAyuda.setMinimumSize(new Dimension(40, 50));
		botonAyuda.setMaximumSize(new Dimension(40, 50));
		botonAyuda.setIcon(new ImageIcon((getClass().getClassLoader().getResource("ayuda_icon.png"))));
		botonAyuda.setToolTipText("Necesito ayuda");
		
		bottomContainer.add(Box.createRigidArea(new Dimension(450, 0)));
		bottomContainer.add(pagarButton);
		bottomContainer.add(Box.createRigidArea(new Dimension(400, 0)));
		bottomContainer.add(botonAyuda);
		
		return bottomContainer;
	}

	@Override
	public void actualizar(Context res) {
		if(res.getEvento() == Eventos.RES_ABRIR_COMPRA_KO){
			JOptionPane.showMessageDialog(null, "Error en la validación de la compra. Revise que los IDs existen, el stock es suficiente y los productos están activos.");
		}
		if(res.getEvento() == Eventos.RES_ABRIR_COMPRA_OK){
			Controller.getInstance().action(new Context(Eventos.VALIDAR_ID_CLIENTE, (TCarrito) res.getDatos()));
			dispose();
		}
		else if(res.getEvento() == Eventos.STOCK_INSUFICIENTE){
			JOptionPane.showMessageDialog(null, "Algún producto no tiene suficiente stock");
		}
		else if(res.getEvento() == Eventos.RES_ANIADIR_PRODUCTO_COMPRA_OK){
			carrito=(TCarrito) res.getDatos();
			init_GUI();
		}
		else if(res.getEvento() == Eventos.RES_ELIMINAR_PRODUCTO_COMPRA_OK){
			carrito=(TCarrito) res.getDatos();
			init_GUI();
		}
		
	}
	
	
}